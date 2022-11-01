package com.sendmail.send_mail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {

	//This method is to send message and Attachment files
	private static void sendAttach(String message, String subject, String to, String from) {
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		// smtp -- simple mail transfer protocol
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");// ssl -- secure sockets layer
		properties.put("mail.smtp.auth", "true");

		// step 1: get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("from mailID", "password of from mailID");
			}
		});
		session.setDebug(true);

		// step 2: compose the message (storing message)
		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			// setting from mail
			mimeMessage.setFrom(from);

			// setting recipient mail
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// setting subject
			mimeMessage.setSubject(subject);
			
			//attachment
			String  path = "file path of the attachment";
			
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			
			MimeBodyPart textMime = new MimeBodyPart();
			
			MimeBodyPart fileMime = new MimeBodyPart();
			//For message
			textMime.setText(message);
			//To attach the files with mail
			File file = new File(path);
			fileMime.attachFile(file);
			
			//add both text and file inside mimemultipart object
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
			
		
			mimeMessage.setContent(mimeMultipart);
			
			//step 3: Sending mail using Transport class
			Transport.send(mimeMessage);

			System.out.println("Mail sent Successfully.............");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String message = "This is the test mail sent............";
		String subject = "hello";
		String to = "to mail";
		String from = "from mail";

		sendAttach(message, subject, to, from);

	}

}
