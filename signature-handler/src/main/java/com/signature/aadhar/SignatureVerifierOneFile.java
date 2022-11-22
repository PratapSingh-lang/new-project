//package com.signature.aadhar;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.crypto.dsig.XMLSignatureFactory;
//import javax.xml.crypto.dsig.dom.DOMValidateContext;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.security.PublicKey;
//import java.security.Security;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;
//
//import javax.xml.crypto.dsig.XMLSignature;
//import javax.xml.crypto.dsig.XMLSignature.SignatureValue;
//
////import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import net.lingala.zip4j.ZipFile;
//import net.lingala.zip4j.exception.ZipException;
//
//
//public class SignatureVerifierOneFile {
////	private static final String signedXmlPath = "C:\\Users\\Dell\\Documents\\offlineaadhaar20221107060006746.xml\\";
//	private static final String signedXmlPath = "C:\\Users\\Dell\\Downloads\\file.xml\\";
//	private static final String certFile = "C:\\Users\\Dell\\Documents\\certificate.cer";
//	private static final String Mobile_Number = "9128704048";
//	private static final String password = "1234";
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Security.addProvider(new BouncyCastleProvider());         
//		String signedXmlPath = extractZipFile();
//        SignatureVerifierOneFile signatureVerifier = new SignatureVerifierOneFile();
//        boolean signatureStatus = 
//        		signatureVerifier.verify(signedXmlPath,certFile);
//        if(signatureStatus) {
//        	signatureVerifier.getDataFromXMLFile(signedXmlPath);
//        }
//	}
//
//	private  void getDataFromXMLFile(String signedXmlPath) {
//		// TODO Auto-generated method stub
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder(); 
//            // Get Document
//            Document document = builder.parse(new File("C:\\Users\\Dell\\Downloads\\offlineaadhaar20221107060006746\\offlineaadhaar20221107060006746.xml\\"));
//            // Normalize the xml structure
//            document.getDocumentElement().normalize();
//            Element rootElement = document.getDocumentElement();
//            System.out.println("###### Element under OfflinePaperlessKyc  tag ######## ");
//            NodeList OfflinePaperlessKyc = document.getElementsByTagName("OfflinePaperlessKyc");
//            String ref_Id = OfflinePaperlessKyc.item(0).getAttributes().getNamedItem("referenceId").getNodeValue();
//            System.out.println(ref_Id);
//            NodeList nodeList = document.getElementsByTagName("Poi");
//            System.out.println("###### Element under Poi tag ######## ");
//            System.out.println(nodeList.item(0).getAttributes().getNamedItem("dob").getNodeValue());
//            System.out.println(nodeList.item(0).getAttributes().getNamedItem("gender").getNodeValue());
//            String mob = nodeList.item(0).getAttributes().getNamedItem("m").getNodeValue();
//            System.out.println(mob);
//            System.out.println(nodeList.item(0).getAttributes().getNamedItem("name").getNodeValue());
//            Boolean isMatched = isHashMatched(Mobile_Number,password,mob, ref_Id);
//            if(isMatched) {
//            	System.out.println("Mobile number matched");
//            }else
//            	System.out.println("Mobile number does not matched");
////            System.out.println("Mobile matching status : " + isMatched);
//            
//            NodeList poa = document.getElementsByTagName("Poa");
//            System.out.println("###### Element under Poa tag ######## ");
//            System.out.println(poa.item(0).getAttributes().getNamedItem("careof").getNodeValue());
//            System.out.println(poa.item(0).getAttributes().getNamedItem("country").getNodeValue());
//            System.out.println(poa.item(0).getAttributes().getNamedItem("dist").getNodeValue());
//            System.out.println(poa.item(0).getAttributes().getNamedItem("loc").getNodeValue());
//            System.out.println(poa.item(0).getAttributes().getNamedItem("pc").getNodeValue());
//            System.out.println(poa.item(0).getAttributes().getNamedItem("po").getNodeValue());
//            System.out.println(poa.item(0).getAttributes().getNamedItem("state").getNodeValue());
// 
//            
//        }catch (ParserConfigurationException e) {
//	        e.printStackTrace();
//	    } catch (SAXException e) {
//	        e.printStackTrace();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
//
//	/**
//	 * function to verify user's mobile number 
//	 * **/
//	private boolean isHashMatched(String mobileNo, String zipPassword, String 
//		    hashedMobile ,String ref_Id){
//			int aadharLastDigit=Character.getNumericValue(ref_Id.charAt(3)); 
//		    String concatedString  = mobileNo + zipPassword;
//		    aadharLastDigit = aadharLastDigit == 0 ? 1 : aadharLastDigit; //if last 
//		    //digit is "0", hash only one time.
//		    try {
//		        for(int i = 0; i < aadharLastDigit; i++){
//		            concatedString = DigestUtils.sha256Hex(concatedString);
//		        }
//		        return hashedMobile.equals(concatedString);
//		    }catch (Exception e){
//		        e.printStackTrace();
//		        return false;
//		    }
//		}
//	
//	/*
//	 * function to verify Signature
//	 * */
//    public boolean verify(String signedXml,String publicKeyFile) {
//
//        boolean verificationResult = false;
//
//        try {
//
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            dbf.setNamespaceAware(true);
//            DocumentBuilder builder = dbf.newDocumentBuilder();
//            Document doc = builder.parse(signedXml);
//            NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
////            NodeList signatureMethod = doc.getElementsByTagNameNS(XMLSignature.Algorithm, "SignatureMethod");
//            if (nl.getLength() == 0) {
//                throw new IllegalArgumentException("Cannot find Signature element");
//            }
//
//            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM"
////            		, "BC"
//            		);
//
//            DOMValidateContext valContext = new DOMValidateContext(getPublicKey(publicKeyFile), nl.item(0));
////          System.out.println("hello+++++++++++++++++++");
//            valContext.setProperty("org.jcp.xml.dsig.secureValidation", Boolean.FALSE); 
////            System.out.println(valContext.getProperty("org.jcp.xml.dsig.secureValidation"));
//            XMLSignature signature = fac.unmarshalXMLSignature(valContext);
////         System.out.println("Sig is : " + signature.getKeyInfo());
////         System.out.println("Sig is : " + signature.getSignedInfo());
////         System.out.println("Sig is : " + signature.getSignatureValue());
//            verificationResult = signature.validate(valContext);
////            System.out.println(verificationResult);
//
//        } catch (Exception e) {
//            System.out.println("Error while verifying digital siganature" + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return verificationResult;
//    }
//
//    /*
//     * function to get certificate from a file
//     */
//    private X509Certificate getCertificateFromFile(String certificateFile) throws GeneralSecurityException, IOException {
//        FileInputStream fis = null;
//        try {
//            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
//            fis = new FileInputStream(certificateFile);
//            return (X509Certificate) certFactory.generateCertificate(fis);
//        } finally {
//            if (fis != null) {
//                fis.close();
//            }
//        }
//    }
//    
//    private static String extractZipFile() {
//		// TODO Auto-generated method stub
//		
//		ZipFile zipFile = new ZipFile("C:\\Users\\Dell\\Documents\\offlineaadhaar20221107060006746.zip",
//				password.toCharArray());
//		try {
//			zipFile.extractFile("offlineaadhaar20221107060006746.xml", "C:\\Users\\Dell\\Documents");
////			System.out.println("File extracted");
//		} catch (ZipException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return "C:\\Users\\Dell\\Documents\\offlineaadhaar20221107060006746.xml\\";
//	}
//    
//    private PublicKey getPublicKey(String certFile) throws Exception {
//		CertificateFactory factory = CertificateFactory.getInstance("X.509");
//		FileInputStream fis = new FileInputStream(certFile);
//		Certificate cert = factory.generateCertificate(fis);
//		//fis.close();
//		return cert.getPublicKey();
//	}
//}
