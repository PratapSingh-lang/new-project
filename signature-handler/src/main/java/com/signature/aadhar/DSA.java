package com.signature.aadhar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.lang.invoke.ClassSpecializer.Factory;
//import java.lang.ref.Reference;
import javax.xml.crypto.dsig.Reference;
import java.rmi.MarshalException;
import java.security.InvalidAlgorithmParameterException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package signature;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.crypto.dsig.SignedInfo;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.management.modelmbean.XMLParseException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignContext;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import org.jcp.xml.dsig.internal.dom.DOMReference;
import org.apache.xml.security.signature.XMLSignature;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;

//The Digital Signature Algorithm (DSA)
//The DSA algorithm involves four operations: key generation (which creates the key pair), key distribution, signing and signature verification.
public class DSA {
 
    
     public static void main(String args[]) {
    	 String originalXmlFilePath =  "C:\\Users\\Dell\\Downloads\\file.xml";
    	 String destnSignedXmlFilePath = "C:\\Users\\Dell\\Downloads\\filexmlOut.xml";
    	 String privateKey = 
    			 "C:\\Users\\Dell\\Documents\\selfCert\\gcrt\\private8.der"
//    			 "C:\\Users\\Dell\\Documents\\selfCert\\newcert\\private_key.pem"
//    			 "C:\\Users\\Dell\\Documents\\selfCert\\newcert\\privekey.pem"
    			 ;
    	 String publicKey = 
//    			 "C:\\Users\\Dell\\Documents\\selfCert\\newcert\\pubkey.pem"
    			 "C:\\Users\\Dell\\Documents\\selfCert\\gcrt\\public.der"
//    			 "C:\\Users\\Dell\\Documents\\selfCert\\newcert\\public_key.pem"
    			 ;
    	 new DSA().generateXMLDigitalSignature(originalXmlFilePath,destnSignedXmlFilePath,privateKey,publicKey);
//    	 Document doc = getXmlDocument(originalXmlFilePath);
			/*
			 * try { sign(doc); } catch (MarshalException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch (InstantiationException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch
			 * (IllegalAccessException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (ClassNotFoundException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch
			 * (NoSuchAlgorithmException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (InvalidAlgorithmParameterException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch (KeyException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); } catch
			 * (FileNotFoundException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (XMLParseException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); } catch (TransformerException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
    }
    
     

	/*
	 * public static void validateMessageSignature(PublicKey publicKey, byte[]
	 * message, byte[] signature) throws NoSuchAlgorithmException,
	 * InvalidKeyException, SignatureException { Signature clientSig =
	 * Signature.getInstance("DSA"); clientSig.initVerify(publicKey);
	 * clientSig.update(message); if (clientSig.verify(signature)) {
	 * System.out.println("The message is properly signed."); } else {
	 * System.err.println("It is not possible to validate the signature."); } }
	 * 
	 * public static byte[] signMessage(byte[] message,PrivateKey privateKey) throws
	 * NoSuchAlgorithmException, InvalidKeyException, SignatureException { Signature
	 * sig = Signature.getInstance("DSA"); sig.initSign(privateKey);
	 * sig.update(message); byte[] sign= sig.sign(); return sign; }
	 * 
	 * 
	 * public static class SignerUser { private PublicKey publicKey; private
	 * PrivateKey privateKey; public PublicKey getPubKey() { return publicKey; }
	 * 
	 * public SignerUser() throws NoSuchAlgorithmException{ KeyPairGenerator kpg =
	 * KeyPairGenerator.getInstance("DSA"); SecureRandom secRan = new
	 * SecureRandom(); kpg.initialize(512, secRan); KeyPair keyP =
	 * kpg.generateKeyPair(); this.publicKey= keyP.getPublic(); this.privateKey =
	 * keyP.getPrivate(); }
	 * 
	 * public PublicKey getPublicKey() { return publicKey; }
	 * 
	 * public void setPublicKey(PublicKey publicKey) { this.publicKey = publicKey; }
	 * 
	 * public PrivateKey getPrivateKey() { return privateKey; }
	 * 
	 * public void setPrivateKey(PrivateKey privateKey) { this.privateKey =
	 * privateKey; }
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 */
    
	private static Document sign(Document doc) throws InstantiationException, IllegalAccessException,
				ClassNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyException,
				MarshalException, XMLParseException, FileNotFoundException, TransformerException {
			
//			String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
			DOMSignContext dsc = new DOMSignContext
					  (kp.getPrivate(), doc.getDocumentElement());
			XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
			
			javax.xml.crypto.dsig.Reference ref = fac.newReference
					  ("", fac.newDigestMethod(DigestMethod.SHA256, null),
					    Collections.singletonList
					      (fac.newTransform(Transform.ENVELOPED,
					        (TransformParameterSpec) null)), null, null);
			
			javax.xml.crypto.dsig.SignedInfo si = fac.newSignedInfo
					  (fac.newCanonicalizationMethod
					    (
					    		CanonicalizationMethod.EXCLUSIVE
//					    		CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS
//					    		CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS
					    		,
					      (C14NMethodParameterSpec) null),
					    fac.newSignatureMethod(
//					    		"http://www.w3.org/2009/xmldsig11#dsa-sha256"
					    		"http://www.w3.org/2000/09/xmldsig#rsa-sha1"
					    		, null),
					    Collections.singletonList(ref)); 
			
			KeyInfoFactory kif = fac.getKeyInfoFactory();
			KeyValue kv = kif.newKeyValue(kp.getPublic());
			KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));
			javax.xml.crypto.dsig.XMLSignature signature = fac.newXMLSignature(si, ki); 
//			try {
//				signature.sign((XMLSignContext) dsc);
//			} catch (javax.xml.crypto.MarshalException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (XMLSignatureException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
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
			
			//output the resulting document
			OutputStream os;
			
			os = new FileOutputStream("C:\\Users\\Dell\\Downloads\\xmlOut.xml");
			System.out.println("filecreated");
			trans.transform(new DOMSource(doc), new StreamResult(os));
			return doc;
			
			}
    
	/**
     * Method used to retrieve the keys in the form byte array
     *
     * @param filePath of the key
     * @return byte array
     */
    private byte[] getKeyData(String filePath) {
        File file = new File(filePath);
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }
	
	public PrivateKey getStoredPrivateKey(String filePath) {
	    PrivateKey privateKey = null;
	    byte[] keydata = getKeyData(filePath);
	    PKCS8EncodedKeySpec encodedPrivateKey = new PKCS8EncodedKeySpec(keydata);
	    KeyFactory keyFactory = null;
	    try {
	        keyFactory = KeyFactory.getInstance("RSA");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    try {
	        System.out.println("hello");
	        privateKey = keyFactory.generatePrivate(encodedPrivateKey);
	    } catch (InvalidKeySpecException e) {
	        e.printStackTrace();
	    }
	    return privateKey;
	}
		
	  public PublicKey getStoredPublicKey(String filePath) {
	        PublicKey publicKey = null;
	        byte[] keydata = getKeyData(filePath);
	        KeyFactory keyFactory = null;
	        try {
	            keyFactory = KeyFactory.getInstance("RSA");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        X509EncodedKeySpec encodedPublicKey = new X509EncodedKeySpec(keydata);
	        try {
	            publicKey = keyFactory.generatePublic(encodedPublicKey);
	        } catch (NullPointerException npe) {
	            npe.printStackTrace();
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return publicKey;
	    }
	
	private KeyInfo getKeyInfo(XMLSignatureFactory xmlSigFactory, String publicKeyPath) {
        KeyInfo keyInfo = null;
        KeyValue keyValue = null;
        PublicKey publicKey = getStoredPublicKey(publicKeyPath);
        KeyInfoFactory keyInfoFact = xmlSigFactory.getKeyInfoFactory();

        try {
            keyValue = keyInfoFact.newKeyValue(publicKey);
        } catch (KeyException ex) {
            ex.printStackTrace();
        }
        keyInfo = keyInfoFact.newKeyInfo(Collections.singletonList(keyValue));
        return keyInfo;
    }
	/*
     * Method used to store the signed XMl document
     */
    private void storeSignedDoc(Document doc, String destnSignedXmlFilePath) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer trans = null;
        try {
            trans = transFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        }
        try {
            StreamResult streamRes = new StreamResult(new File(destnSignedXmlFilePath));
            trans.transform(new DOMSource(doc), streamRes);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
        System.out.println("XML file with attached digital signature generated successfully ...");
    }

    /**
     * Method used to attach a generated digital signature to the existing
     * document
     *
     * @param originalXmlFilePath
     * @param destnSignedXmlFilePath
     * @param privateKeyFilePath
     * @param publicKeyFilePath
     */
	public void generateXMLDigitalSignature(String originalXmlFilePath,  
		    String destnSignedXmlFilePath, String privateKeyFilePath, String publicKeyFilePath) {  
		    //Get the XML Document object  
		    Document doc = getXmlDocument(originalXmlFilePath);  
		    //Create XML Signature Factory  
		    XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");  
		    PrivateKey privateKey = getStoredPrivateKey(privateKeyFilePath);  
		    DOMSignContext domSignCtx = new DOMSignContext(privateKey, doc.getDocumentElement());  
		    Reference ref = null;  
		    SignedInfo signedInfo = null;  
		    try {  
		    	
		        ref = (Reference) xmlSigFactory.newReference("", xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null),
		         Collections.singletonList(xmlSigFactory.newTransform(Transform.ENVELOPED,  
		                (TransformParameterSpec) null)), null, null);  
		        
		        signedInfo = (SignedInfo) xmlSigFactory.newSignedInfo
		        		(xmlSigFactory.newCanonicalizationMethod
		        				(CanonicalizationMethod.EXCLUSIVE,  
		                (C14NMethodParameterSpec) null),
		            xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),  
		            (List<? extends javax.xml.crypto.dsig.Reference>) 
		            Collections.singletonList(ref));  
		    } catch (NoSuchAlgorithmException ex) {  
		        ex.printStackTrace();  
		    } catch (InvalidAlgorithmParameterException ex) {  
		        ex.printStackTrace();  
		    }  
		    //Pass the Public Key File Path  
		    KeyInfo keyInfo = getKeyInfo(xmlSigFactory, publicKeyFilePath);  
		    //Create a new XML Signature  
		    javax.xml.crypto.dsig.XMLSignature xmlSignature = xmlSigFactory.newXMLSignature((javax.xml.crypto.dsig.SignedInfo) signedInfo, keyInfo);  
		    try {  
		        //Sign the document  
		        try {
					xmlSignature.sign(domSignCtx);
				} catch (javax.xml.crypto.MarshalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		    } catch (XMLSignatureException ex) {  
		        ex.printStackTrace();  
		    }  
		    //Store the digitally signed document inta a location  
		    storeSignedDoc(doc, destnSignedXmlFilePath);  
		}  
			  
			 

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

}
