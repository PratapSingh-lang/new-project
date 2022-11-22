package com.signature.aadhar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.MarshalException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Collections;
import java.util.ServiceLoader.Provider;

import javax.management.modelmbean.XMLParseException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.SignedInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class revised {

private  static void check(Document inputDocument) {
	/*
	 * //            if (rootElement != null) {
//				NodeList nl = rootElement.getChildNodes();
//				System.out.println(nl);
//				Node n = nl.item(0);
//				System.out.println(n.getNodeName());
//				Element UidData = (Element) n;
//				NodeList childNodes = UidData.getChildNodes();
//				Element Poi = (Element) childNodes;
//				System.out.println(" :  "+ childNodes);
////				Poi.getAttribute("dob")
////				System.out.println(" dob :  "+((Object) childNodes).getAttributeValue("dob"));
//            }
            NodeList UidData = document.getElementsByTagName("UidData");
            String UidDataValue = document.getElementsByTagName("UidData").item(0).getTextContent();
//            System.out.println(" UidData is  : " +
//            		UidData);
//            System.out.println(" element at 0 th index is :   "+UidData.item(0)); // element at 0th index is :   [UidData: null]
            Node UidDataItem = UidData.item(0);
            String UidDataItemlocalName = UidDataItem.getLocalName();
//            System.out.println(UidDataItemlocalName);
            
            
            NodeList Poi  = document.getElementsByTagName("Poi");
           System.out.println("POI IS : "+Poi);
           
//            String X509certificate = document.getElementsByTagName("X509Certificate").item(0).getTextContent();
//            System.out.println(" X509Certificate is  : " +
//            		X509certificate);
            
//            System.out.println("OfflinePaperlessKyc : " + document.getDocumentElement().getNodeName()); //OfflinePaperlessKyc : OfflinePaperlessKyc  
////             Get all the element by the tag name
//            NodeList Signature = document.getElementsByTagName("SignatureValue");
//            String signatureValue = document.getElementsByTagName("SignatureValue").item(0).getTextContent();
//            System.out.println(" signature is  : " +
//            						signatureValue);
//            System.out.println(" element at 0 th index is :   "+Signature.item(0)); // element at 0th index is :   [UidData: null]
//
//            String X509certificate = document.getElementsByTagName("X509Certificate").item(0).getTextContent();
//            System.out.println(" X509Certificate is  : " +
//            		X509certificate);	
	 */
	
	
		if (inputDocument != null) {
			Element rootElement = inputDocument.getDocumentElement();
			
			if (rootElement != null) {
				NodeList nl = rootElement.getChildNodes();
				if (nl != null) {
					for (int i = 0; i < nl.getLength(); i++) {
						Node n = nl.item(i);
						/***added code***/
//						if(n.getNodeName() != null && n.getLocalName().equals("Signature")) {
//							Element sign = (Element) n;
//							NodeList childNodes = sign.getChildNodes();
//							if(childNodes != null) {
//								for (int i1 = 0; i < childNodes.getLength(); i1++) {
//									Node n2 = childNodes.item(i1);
//									if(n2.getNodeName() != null && n2.getLocalName().equals("SignedInfo")) {
//										Element signedInfo = (Element) n2;
//										NodeList signedInfoChild = signedInfo.getChildNodes();
//										if(signedInfoChild != null) {
//											for(int j = 0;j<signedInfoChild.getLength();j++) {
//												Node signedInfoChilditem = signedInfoChild.item(j);
//												System.out.println("######  "+ signedInfoChilditem.getLocalName());
//												if (signedInfoChilditem.getNodeName() != null && signedInfoChilditem != null) {
//													if (signedInfoChilditem.getNodeName() != null && "SignatureMethod".equalsIgnoreCase(signedInfoChilditem.getLocalName())) {
//														NodeList elementsByTagName = signedInfo.getElementsByTagName("SignatureMethod");
//														signedInfo.getAttribute("Algorithm");
//														System.out.println(
//																"####################" +
////																		signedInfo.getAttribute("Algorithm")
//																((DocumentBuilderFactory) signedInfoChilditem).getAttribute("Algorithm")
//																);
////														((DocumentBuilderFactory) signedInfoChilditem).getAttribute("Algorithm");
//														signedInfo.removeChild(signedInfoChilditem);
////														return inputDocument ;
//													}
//											}
//										}
////										System.out.println("$##### : " + 
////												signedInfo.getFirstChild() + " &" +
////										signedInfo.getLastChild());
//									}
//									System.out.println(" node name is   : "+n2);
//								}
//							}
//						}
//						}
						/*****/
						if (n != null) {
							if (n.getNodeName() != null && "Signature".equalsIgnoreCase(n.getLocalName())) {
//								return n;
							}
						}
					}
				}
			}
		}
		
//		return null;
		
		
	}
	
	
	
//	byte[] encoded = pk.getEncoded();
//	String pkey = new String(decoder.decode(encoded));  
//    System.out.println("Decoded  public key string: "+pkey); 
    
	/* Get the bytes to be signed from the string */
//	var msgBytes = Encoding.UTF8.GetBytes(signatureValue);
//    byte[] decodedBytes = Base64.getDecoder().decode(signatureValue.getBytes());
//	String decodedString = new String(decodedBytes);
//	System.out.println(decodedString);
	
	// Getting decoder  
     
 // Decoding string  
//    String dStr = new String(decoder.decode(signatureValue));  
//    System.out.println("Decoded string: "+dStr);  

//      //Creating a Signature object
//      Signature sign = ((Signature) Signature).getInstance("SHA256withDSA");
//
//      //Initializing the signature
//      sign.initSign(pk);
//      byte[] bytes = "Hello how are you".getBytes();
//      
//      //Adding data to the signature
//      sign.update(bytes);
//      
//      //Calculating the signature
//      byte[] signature = sign.sign();      
//      
//      //Initializing the signature
//      sign.initVerify(pair.getPublic());
//      sign.update(bytes);
//      
//      //Verifying the signature
//      boolean bool = sign.verify(signature);
//      
//      if(bool) {
//         System.out.println("Signature verified");   
//      } else {
//         System.out.println("Signature failed");
//      }
	
private static Document getXmlDocument(String originalXmlFilePath) {
	// TODO Auto-generated method stub
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	Document document = null;
  try {
      DocumentBuilder builder = factory.newDocumentBuilder(); 
      // Get Document
       document = builder.parse(new File(originalXmlFilePath));
      // Normalize the xml structure
      document.getDocumentElement().normalize();
  }catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
	
	return document;
}  	

	private static Document sign(Document doc) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyException,
			MarshalException, XMLParseException, FileNotFoundException, TransformerException {

		String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

		DigestMethod digestMethod = fac.newDigestMethod(DigestMethod.SHA256, null);
		Transform transform = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
		Reference reference = fac.newReference("", digestMethod,
//				singletonList(transform)
				null
				, null, null);
		SignatureMethod signatureMethod = fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256",
				null);
		CanonicalizationMethod canonicalizationMethod = fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
				(C14NMethodParameterSpec) null);

// Create the SignedInfo
		javax.xml.crypto.dsig.SignedInfo si = fac.newSignedInfo(canonicalizationMethod, signatureMethod
//				,
//				singletonList(reference)
, null
				
				);

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);

		KeyPair kp = kpg.generateKeyPair();

		KeyInfoFactory kif = fac.getKeyInfoFactory();
		KeyValue kv = kif.newKeyValue(kp.getPublic());

// Create a KeyInfo and add the KeyValue to it
		javax.xml.crypto.dsig.keyinfo.KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));
		DOMSignContext dsc = new DOMSignContext(kp.getPrivate(), doc.getDocumentElement());

		XMLSignature signature = fac.newXMLSignature((javax.xml.crypto.dsig.SignedInfo) si, ki);
		try {
			signature.sign(dsc);
		} catch (javax.xml.crypto.MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLSignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();

// output the resulting document
		OutputStream os;

		os = new FileOutputStream("xmlOut.xml");

		trans.transform(new DOMSource(doc), new StreamResult(os));
		return doc;

	}
//	public static Document removeSignature(Document inputDocument) {
//		String X509certificate = inputDocument.getElementsByTagName("SignatureMethod").item(0).getTextContent();
//		System.out.println(X509certificate);
//		if (inputDocument != null) {
//			Element rootElement = inputDocument.getDocumentElement();
//			check(inputDocument);
//			Node n = getSignatureNode(inputDocument);
//			if (n != null) {
//				rootElement.removeChild(n);
//			}
//			
////			Document n = getSignatureNode(inputDocument);
////			return n;
//		}
//		return null;
//		
////		return inputDocument;
//		
//	}
//	
//	 /** The Constant CHARSET. */
//    private static final String CHARSET = "utf-8";
//
//    /** The Constant ENCRYPTION_ALGORITHM. */
//    private static final String ENCRYPTION_ALGORITHM = "RSA";
//
//    /** The Constant HASH_ENCRIPTION_ALGORITHM. */
//    private static final String HASH_ENCRYPTION_ALGORITHM = "SHA1withRSA";
//	private static final String SIGNATURE_TAG = "Signature";
////	private static final String SIGNATURE_TAG = "SignatureValue";
//}
//
//
//PublicKey pubKey = getPublicKeyFromCertificte();
//Base64.Decoder decoder = Base64.getDecoder();
//
//String encodedSig = signatureValue.replaceAll("[\r\n]+", ""); 
//String dStrS = new String(decoder.decode(encodedSig));
//System.out.println("Decoded Signature string: "+dStrS);
}
