package com.zhuk.examination.common.file;

import java.io.InputStream;

import com.aspose.email.MailMessage;
import com.aspose.email.License;

public class EmlTest {
public static void main(String[] args) throws Exception {

		InputStream is = PptTest.class.getClassLoader().getResourceAsStream("\\license.xml");
	    License aposeLic = new License();
	    aposeLic.setLicense(is);

	    //Create MailMessage instance by loading an Eml file
  		MailMessage message = MailMessage.load("eml.eml");

  		System.out.print("From: ");
  		//Gets the sender info
  		System.out.println(message.getFrom());

  		System.out.print("To: ");
  		//Gets the recipients info
  		System.out.println(message.getTo());

  		System.out.print("Subject: ");
  		//Gets the subject
  		System.out.println(message.getSubject());

  		System.out.print("HtmlBody: ");
  		//Gets the htmlbody
  		System.out.println(message.getHtmlBody());

  		System.out.print("TextBody: ");
  		//Gets the textbody
  		System.out.println(message.getBody());

  		System.out.print("HtmlBodyText: ");
  		//Gets the textbody from HTML
  		System.out.println(message.getHtmlBodyText());
	}
}
