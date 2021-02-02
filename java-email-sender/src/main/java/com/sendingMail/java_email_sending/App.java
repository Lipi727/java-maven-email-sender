package com.sendingMail.java_email_sending;

import java.io.File;
import java.io.IOException;
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


public class App 
{
    public static void main( String[] args )
    {
        String message = "Hello friends,\n\n This mail is by Java Project for testing purpose.\n\n Thank You";
        String subject = "Sent this mail with attachment";
        String to = "receviergmail@gmail.com"; // receiver gmail
        String from = "sendergmail@gmail.com"; //sender gmail 
        
        // for text mail
        // sendEmail(message, subject, to, from);
        // for send mail with attachment
        sendEmailWithAttachment(message, subject, to, from);
    }

    //this is for sending text mail
	private static void sendEmail(String message, String subject, String to, String from) {
		
		//variable for mail host
		String host = "smtp.gmail.com";
		//get the system property
		Properties properties = System.getProperties();
		System.out.println("******PROPERTIES******\n" + properties);
		
		//sending important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//get session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sendergmail@gmail.com", "password");
			}		
		});
	
		session.setDebug(true);
		
		//compose the message
		MimeMessage mimeMessage = new MimeMessage(session);		
		try {
			//from email
			mimeMessage.setFrom(from);
			
			//adding recipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			mimeMessage.setSubject(subject);
			
			//adding text to message
			mimeMessage.setText(message);
			
			//send the message using transport Class
			Transport.send(mimeMessage);
			
			System.out.println("********Mail send successfully********");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	//this is for sending mail with attachment
    private static void sendEmailWithAttachment(String message, String subject, String to, String from)  {
    	//variable for mail host
        String host = "smtp.gmail.com";

    	//get the system property
    	Properties properties = System.getProperties();
    	System.out.println("******PROPERTIES******\n" + properties);

    	//sending important information to properties object
    	//host set
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");

    	//get session object
    	Session session = Session.getInstance(properties, new Authenticator() {
    	     @Override
    	     protected PasswordAuthentication getPasswordAuthentication() {
    		      return new PasswordAuthentication("sendergmail@gmail.com", "password");
             }		
    	});

    	session.setDebug(true);

    	//compose the message
    	MimeMessage mimeMessage = new MimeMessage(session);
        try {
    		//from email
    		mimeMessage.setFrom(from);

    		//adding recipient to message
    		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

    		//adding subject to message
    		mimeMessage.setSubject(subject);

    		//adding text to message
    		mimeMessage.setText(message);

    		//path of file
    		String path = "file path";
    		
    		MimeMultipart mimeMultipart = new MimeMultipart();
    		MimeBodyPart textMime = new MimeBodyPart();
    		MimeBodyPart fileMime = new MimeBodyPart();
    		
    		try {
    			 textMime.setText(message);
        		 File file = new File(path);
				 fileMime.attachFile(file);
				 mimeMultipart.addBodyPart(textMime);
	    		 mimeMultipart.addBodyPart(fileMime);
				} catch (IOException e) {
						e.printStackTrace();
				   }   
    		
            mimeMessage.setContent(mimeMultipart);
    		//send the message using transport Class
    		Transport.send(mimeMessage);
            System.out.println("********Mail send successfully********");
    	} catch (MessagingException e) {
    				e.printStackTrace();
    	}
	}
}
