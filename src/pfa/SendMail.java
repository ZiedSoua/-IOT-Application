/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfa;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Zied
 */
public class SendMail {
    
    public void sendmed(String nomp,double agep,String medmail,int freq)
    {
        final String username = "Ziedfatipi@gmail.com";
        final String password = "ziedfati94";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}
        });

	try {
            Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("Ziedfatipi@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(medmail));
	    message.setSubject("Notification of Heart Rate ");
	    message.setText("Dear Doctor,"+"\nPatient Information:\nname:"+nomp+"\nage:"+agep+"\n The Heart Rate of the previous minute is:"+freq);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);}
    }
    
    public void sendneig(String nomp,double agep,String negmail,int freq)
    {
        final String username = "Ziedfatipi@gmail.com";
        final String password = "ziedfati94";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}
        });

	try {
            Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("Ziedfatipi@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(negmail));
	    message.setSubject("Alert");
	    message.setText("Dear friend,"+"\nPatient Information:\nname:"+nomp+"\nage:"+agep+"\n The Heart Rate of the previous minute is:"+freq);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);}
    }
}
