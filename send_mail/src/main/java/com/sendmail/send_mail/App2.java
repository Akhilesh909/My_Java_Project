package com.sendmail.send_mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App2 {
	
	private static void sendMail(String message, String subject, String to, String from) {

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
				return new PasswordAuthentication("justajavatest99@gmail.com", "dxncfncodwfkstdh");
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

			// setting message in mimeMessage
			mimeMessage.setText(message);

			// step 3: sending message using Transport class
			Transport.send(mimeMessage);

			System.out.println("Mail sent Successfully.............");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		String message = "Hello This is a Test message again.........";
		String subject = "hello";
		String to = "akhilesh13711@gmail.com";
		String from = "justajavatest99@gmail.com";
		
		sendMail(message, subject, to, from );
	}

}
