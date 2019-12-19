/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.mail.Authenticator;
import java.util.Properties;
import java.mail.PasswordsAuthentication;
import java.mail.Session;

/**
 *
 * @author Bawazir
 */
public class JavaMailUtil {
    
    public static void sendMail(String recepin) throws Exception {
    	System.out.println("Prapering to send email"); 
    	Properties properties = new Properties();

    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.host", "smtp.gmail.com");
    	properties.put("mail.smtp.port", "587");

    	String myAccountEmail = "aeka489@gmail.com";
    	String password = "xxxxxxxx";
    	Session session = Session.getInstance(properties, new Authenticator(){
    		@Override
    		protected PasswordsAuthentication getPasswordsAuthentication(){
    		return new PasswordsAuthentication(myAccountEmail, password);
    	}
    	});  
    	Messagemessage = prepareMessage(session, myAccountEmail, recepient);

    	Transport.send(message);
    	System.out.println("Message sent successfully!"); 

    	 }

    	 private static Message prepareMessage(Session session, String myAccountEmail(recepient)){
    	 	try{
    	 		Message message = new MimeMessage(session);
    	 		message.setFrom(new InternetAddress(myAccountEmail));
    	 		message.setRecipient(Message.RecepientType.TO, new InternetAddress(recepient));
    	 		message.setSubject("My First Email from Java App");
    	 		message.setText("Hey There, \n Look my email!");
    	 		return message;
    	 	} catch (Exception ex) {
    	 		Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SERVER, null, ex);
    	 	}
    	 	return null;
    	 }
}
