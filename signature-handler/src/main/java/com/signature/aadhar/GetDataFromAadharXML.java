package com.signature.aadhar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
//import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class GetDataFromAadharXML {
/*
//<OfflinePaperlessKyc referenceId="616420221107113006740">
//<UidData>
//<Poi dob="27-02-1998" e="" gender="M" m="3dab705a2f4a83281fea58e484a359914869cb92b06a6c711963351818f9396a" name="Bhanu Pratap"/>
//<Poa careof="S/O: Sanjay Singh" country="India" dist="Saharsa" house="" landmark="" loc="Hatiya gachhi ward no 32" pc="852201" po="Saharsa" state="Bihar" street="" subdist="Kahra" vtc="Saharsa"/>
//<Pht>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</Pht>
//</UidData>
//<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">
//<SignedInfo>
//<CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
//<SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
//<Reference URI="">
//<Transforms>
//<Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
//</Transforms>
//<DigestMethod Algorithm="http://www.w3.org/2001/04/xmlenc#sha256"/>
//<DigestValue>PsbjiUOGHAUsBml8VC0RN50tTiaV9DyLOiElc3QuOsM=</DigestValue>
//</Reference>
//</SignedInfo>
//<SignatureValue>ILCc1RmSsVOrgLq+n+WOqMB8iPuf5easOAnPxRIMVOOCR2j7I5ugBg1pEkCyI+HQo2uPIhc7PQQj eGSmFH4qZcDBKEud8Gqst85zyt6TEYBMCxjMXcPmB8PNq6zNA+RV6evZKQdgLimfo16h4X0PBLJD uiHSgd5MqzFBb5KrY8FtxO+Wze3UOdnb5+ClmXDbgPsDcLXVg9OdGLZlsS0dqQs933cosnSfWupl kctJjBSAZvxOcpaYSDgERQchiYZgNTUZRph3CZQeayOHrwdswMfd0QOF7kQwRe4EJzvi8CsqTsEJ 9sv14hyruvwYgyr0SvF6/itzvYBD5vkZf1g9hg==</SignatureValue>
//<KeyInfo>
//<X509Data>
//<X509SubjectName>CN=DS UNIQUE IDENTIFICATION AUTHORITY OF INDIA 05,2.5.4.51=#131b4141444841522048512042414e474c4120534148494220524f4144,STREET=BEHIND KALI MANDIR,ST=Delhi,2.5.4.17=#1306313130303031,O=UNIQUE IDENTIFICATION AUTHORITY OF INDIA,C=IN</X509SubjectName>
//<X509Certificate>MIIHwjCCBqqgAwIBAgIEU5laMzANBgkqhkiG9w0BAQsFADCB/DELMAkGA1UEBhMCSU4xQTA/BgNV BAoTOEd1amFyYXQgTmFybWFkYSBWYWxsZXkgRmVydGlsaXplcnMgYW5kIENoZW1pY2FscyBMaW1p dGVkMR0wGwYDVQQLExRDZXJ0aWZ5aW5nIEF1dGhvcml0eTEPMA0GA1UEERMGMzgwMDU0MRAwDgYD VQQIEwdHdWphcmF0MSYwJAYDVQQJEx1Cb2Rha2RldiwgUyBHIFJvYWQsIEFobWVkYWJhZDEcMBoG A1UEMxMTMzAxLCBHTkZDIEluZm90b3dlcjEiMCAGA1UEAxMZKG4pQ29kZSBTb2x1dGlvbnMgQ0Eg MjAxNDAeFw0yMTAyMjYxMTU0MjRaFw0yNDAyMjcwMDI3MTFaMIHdMQswCQYDVQQGEwJJTjExMC8G A1UEChMoVU5JUVVFIElERU5USUZJQ0FUSU9OIEFVVEhPUklUWSBPRiBJTkRJQTEPMA0GA1UEERMG MTEwMDAxMQ4wDAYDVQQIEwVEZWxoaTEbMBkGA1UECRMSQkVISU5EIEtBTEkgTUFORElSMSQwIgYD VQQzExtBQURIQVIgSFEgQkFOR0xBIFNBSElCIFJPQUQxNzA1BgNVBAMTLkRTIFVOSVFVRSBJREVO VElGSUNBVElPTiBBVVRIT1JJVFkgT0YgSU5ESUEgMDUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAw ggEKAoIBAQCiciwOXy3lunB+2T8DbsKx8LlVkyOQ+swPC8vyDIChXAiLSIaGa3LrJasL9Vov4Gtp 7b1cyDt0x3CdshQebAfGi834WdPa9/P87SQdByBV3BVIhHS0XCyYL6lUqlKqb/+ySBhhxlCF2Etk FY6fQ9nzXKabSM6TAFIhAqTK4JO//UdLCNMtHQQG9of35VvSJqI4S/WKQcOEw5dPHHxRFYGckm3j rfPsu5kExIbx9dUwOXe+pjWENnMptcFor9yVEhcx9/SNQ6988x9pseO755Sdx6ixDAvd66ur3r6g dqHPgWat8GqKQd7fFDv/g129K9W7C2HSRywjSm1EEbybU2CVAgMBAAGjggNnMIIDYzAOBgNVHQ8B Af8EBAMCBsAwKgYDVR0lBCMwIQYIKwYBBQUHAwQGCisGAQQBgjcKAwwGCSqGSIb3LwEBBTCCAQIG A1UdIASB+jCB9zCBhgYGYIJkZAICMHwwegYIKwYBBQUHAgIwbgxsQ2xhc3MgMiBjZXJ0aWZpY2F0 ZXMgYXJlIHVzZWQgZm9yIGZvcm0gc2lnbmluZywgZm9ybSBhdXRoZW50aWNhdGlvbiBhbmQgc2ln bmluZyBvdGhlciBsb3cgcmlzayB0cmFuc2FjdGlvbnMuMGwGBmCCZGQKATBiMGAGCCsGAQUFBwIC MFQMUlRoaXMgY2VydGlmaWNhdGUgcHJvdmlkZXMgaGlnaGVyIGxldmVsIG9mIGFzc3VyYW5jZSBm b3IgZG9jdW1lbnQgc2lnbmluZyBmdW5jdGlvbi4wDAYDVR0TAQH/BAIwADAjBgNVHREEHDAagRhy YWh1bC5rdW1hckB1aWRhaS5uZXQuaW4wggFuBgNVHR8EggFlMIIBYTCCAR6gggEaoIIBFqSCARIw ggEOMQswCQYDVQQGEwJJTjFBMD8GA1UEChM4R3VqYXJhdCBOYXJtYWRhIFZhbGxleSBGZXJ0aWxp emVycyBhbmQgQ2hlbWljYWxzIExpbWl0ZWQxHTAbBgNVBAsTFENlcnRpZnlpbmcgQXV0aG9yaXR5 MQ8wDQYDVQQREwYzODAwNTQxEDAOBgNVBAgTB0d1amFyYXQxJjAkBgNVBAkTHUJvZGFrZGV2LCBT IEcgUm9hZCwgQWhtZWRhYmFkMRwwGgYDVQQzExMzMDEsIEdORkMgSW5mb3Rvd2VyMSIwIAYDVQQD ExkobilDb2RlIFNvbHV0aW9ucyBDQSAyMDE0MRAwDgYDVQQDEwdDUkw1Njk0MD2gO6A5hjdodHRw czovL3d3dy5uY29kZXNvbHV0aW9ucy5jb20vcmVwb3NpdG9yeS9uY29kZWNhMTQuY3JsMCsGA1Ud EAQkMCKADzIwMjEwMjI2MTE1NDI0WoEPMjAyNDAyMjcwMDI3MTFaMBMGA1UdIwQMMAqACE0HvvGe nfu9MB0GA1UdDgQWBBTpS5Cfqf2zdwqjupLAqMwk/bqX9DAZBgkqhkiG9n0HQQAEDDAKGwRWOC4x AwIDKDANBgkqhkiG9w0BAQsFAAOCAQEAbTlOC4sonzb44+u5+VZ3wGz3OFg0uJGsufbBu5efh7kO 2DlYnx7okdEfayQQs6AUzDvsH1yBSBjsaZo3fwBgQUIMaNKdKSrRI0eOTDqilizldHqj113f4eUz U2j4okcNSF7TxQWMjxwyM86QsQ6vxZK7arhBhVjwp443+pxfSIdFUu428K6yH4JBGhZSzWuqD6GN hOhDzS+sS23MkwHFq0GX4erhVfN/W7XLeSjzF4zmjg+O77vTySCNe2VRYDrfFS8EAOcO4q7szc7+ 6xdg8RlgzoZHoRG/GqUp9inpJUn7OIzhHi2e8MllaMdtXo0nbr150tMe8ZSvY2fMiTCY1w==</X509Certificate>
//</X509Data>
//</KeyInfo>
//</Signature>
//</OfflinePaperlessKyc>
//*/
//	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String password = "1234";
//		ZipFile zipFile = new ZipFile("compressed.zip", password.toCharArray());
//		try {
//			zipFile.extractFile("aFile.txt", "/destination_directory");
//		} catch (ZipException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		
//		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	        try {
//	            DocumentBuilder builder = factory.newDocumentBuilder(); 
//	            // Get Document
//	            Document document = builder.parse(new File("C:\\Users\\Dell\\Downloads\\offlineaadhaar20221107060006746\\offlineaadhaar20221107060006746.xml\\"));
//	            // Normalize the xml structure
//	            document.getDocumentElement().normalize();
////	            System.out.println("OfflinePaperlessKyc : " + document.getDocumentElement().getNodeName()); //OfflinePaperlessKyc : OfflinePaperlessKyc  
////	             Get all the element by the tag name
//	            NodeList Signature = document.getElementsByTagName("SignatureValue");
//	            String signatureValue = document.getElementsByTagName("SignatureValue").item(0).getTextContent();
//	            System.out.println(" signature is  : " +
//	            						signatureValue);
////	            System.out.println(" element at 0 th index is :   "+Signature.item(0)); // element at 0th index is :   [UidData: null]
//
//	            String X509certificate = document.getElementsByTagName("X509Certificate").item(0).getTextContent();
//	            System.out.println(" X509Certificate is  : " +
//	            		X509certificate);
//	            String KeyFilePath = "C:\\Users\\Dell\\Desktop\\UIDAI.cer\\";
//	            FileInputStream fin = new FileInputStream(KeyFilePath);
////	            CertificateFactory f = CertificateFactory.getInstance("X.509");
////	            X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
////	            PublicKey publicKey = certificate.getPublicKey();
//	            try {
//					X509Certificate generateCertificate = getCertificateFromFile(X509certificate);
//					PublicKey publicKey = generateCertificate.getPublicKey();
//					System.out.println(generateCertificate);
//					System.out.println( publicKey);
//	            } catch (GeneralSecurityException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
////	            var expectedSig = Convert.FromBase64String(SignatureValue);
////	            String KeyFilePath = "C:\\Users\\Dell\\Desktop\\UIDAI.cer\\";
////	            X509Certificate objX509CertificateParser = new X509Certificate();
////	            X509Certificate ObjX509Certificate2 = new X509Certificate(KeyFilePath, "public");
///*   
//	            for(int i = 0; i <Signature.getLength(); i++) {
//	                Node childNode = Signature.item(i);
//	//	                System.out.println("name of child node : " +childNode.getNodeName()
//	//	                					+ " \n value of node  : " + childNode.getNodeValue());
//	                if(childNode.getNodeType() == Node.ELEMENT_NODE) {
//	//	                    Element laptopElement = (Element) childNode;
//	//	                    System.out.println(laptopElement.getChildNodes().toString()); 
//	//	                    System.out.println("child Nodes of  " + childNode);
//	                    NodeList childNodeDetails =  childNode.getChildNodes();
//	                    for(int j = 0; j < childNodeDetails.getLength(); j++){
//	                        Node detail = childNodeDetails.item(j);
//	                        if(detail.getNodeType() == Node.ELEMENT_NODE) {
//	                            Element detailElement = (Element) detail;
//	                            NamedNodeMap attributes = detailElement.getAttributes();
//	                            for(int k = 0 ; k < attributes.getLength(); k++) {
//	                            	Node item = attributes.item(k);
//	                            	Element atttributeItems = (Element) item;
//	//	                            	System.out.println(atttributeItems.getTagName() + " : " + atttributeItems.getAttribute(atttributeItems.getTagName()));
//	                            }
//	//	                            System.out.println("     " + detailElement.getTagName() + ": " 
//	//	                            + detailElement.getAttribute("dob")
//	//	                            );
//	                        }
//	                    }
//	                }          
//	            }
//*/
//
//
//	        } catch (ParserConfigurationException e) {
//	            e.printStackTrace();
//	        } catch (SAXException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	}
//	
////	private static X509Certificate getCertificateFromFile(String aadhaarCertificateString) throws GeneralSecurityException {
////	    CertificateFactory certFactory = CertificateFactory.getInstance("cer"
//////	    		X509_CERTIFICATE_TYPE
////	    		);
////	    InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(aadhaarCertificateString));
////	    return (X509Certificate) certFactory.generateCertificate(inputStream);
////	}


	/*
	 * private static PublicKey getPublicKeyFromCertificte() {
	 * 
	 * String certFile = "C:\\Users\\Dell\\Documents\\certificate.cer";
	 * 
	 * FileInputStream fin; X509Certificate certificate = null; try { fin = new
	 * FileInputStream("C:\\Users\\Dell\\Documents\\certificate.cer");
	 * CertificateFactory f; f = CertificateFactory.getInstance("X.509");
	 * certificate = (X509Certificate) f.generateCertificate(fin); } catch
	 * (CertificateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (FileNotFoundException e2) { // TODO
	 * Auto-generated catch block e2.printStackTrace(); } PublicKey pk =
	 * certificate.getPublicKey(); System.out.println(pk); return pk; // TODO
	 * Auto-generated method stub
	 * 
	 * 
	 * 
	 * CertificateFactory factory = CertificateFactory.getInstance("X.509");
	 * FileInputStream fis = new FileInputStream(certFile); Certificate cert =
	 * factory.generateCertificate(fis); //fis.close(); return cert.getPublicKey();
	 * 
	 * }
	 */

}
