package com.signature.aadhar;

import java.security.CodeSigner;

import javax.swing.text.Utilities;

public class xmlValidate {

	String XMLFilePath = "C:\\Users\\Dell\\Documents\\offlineaadhaar20221107060006746.xml\\"; //Get the XML file
	String KeyFilePath = "C:\\Users\\Dell\\Documents\\certificate.cer"; //Get the public key certificate file
	XmlDocument ObjXmlDocument = new XmlDocument();
	ObjXmlDocument.Load(XMLFilePath); //Load the XML
	XmlAttributeCollection SignatureElement = ObjXmlDocument.DocumentElement.Attributes; //Get the all XML attribute
	string SignatureValue = SignatureElement.GetNamedItem("s").InnerXml; // Get Signature value
	SignatureElement.RemoveNamedItem("s");//Remove the signature "s" attribute from XML and get the new XML to validate


	/*----------------Read and parse the public key as string-----------------------*/
	X509Certificate2 ObjX509Certificate2 = new X509Certificate2(KeyFilePath, "public"); //Initialize the public ket certificate file


	Org.BouncyCastle.X509.X509Certificate objX509Certificate;
	X509CertificateParser objX509CertificateParser = new X509CertificateParser();
	objX509Certificate = objX509CertificateParser.ReadCertificate(ObjX509Certificate2.GetRawCertData());
	/*----------------End-----------------------*/


	/* Init alg */
	CodeSigner signer = Utilities.GetSigner("SHA256withRSA");


	/* Populate key */
	signer.Init(false, objX509Certificate.GetPublicKey());

	/* Get the signature into bytes */
	var expectedSig = Convert.FromBase64String(SignatureValue);


	/* Get the bytes to be signed from the string */
	var msgBytes = Encoding.UTF8.GetBytes(ObjXmlDocument.InnerXml);


	/* Calculate the signature and see if it matches */
	signer.BlockUpdate(msgBytes, 0, msgBytes.Length);
	bool Flag = signer.VerifySignature(expectedSig);
	if (Flag)
	{
	MessageBox.Show("XML Validate Successfully");
	}
	else
	{
	MessageBox.Show("XML Validation Failed");
	}
	
	
	SignerUser  signer = new SignerUser ();
	
}
