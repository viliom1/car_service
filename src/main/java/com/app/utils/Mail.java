package com.app.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Mail {

    private static String USER_NAME = "yourcarserviceapp";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "asdfghjkl123456789"; // GMail password


    public static void sendFromGMail(String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        setProps(props, host);

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            sendMultipleRecipent(to, subject, body, host, session, message);
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    public static void sendFromGMail(String to, String subject, String body){
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        setProps(props, host);

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            sendSingleRecipent(to, subject, body, host, session, message);
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    private static void sendSingleRecipent(String to, String subject, String body, String host, Session session, MimeMessage message) throws MessagingException {
        message.setFrom(new InternetAddress(USER_NAME));
        InternetAddress[] toAddress = new InternetAddress[1];


            toAddress[0] = new InternetAddress(to);



            message.addRecipient(Message.RecipientType.TO, toAddress[0]);


        message.setSubject(subject);
        message.setText(body);
        Transport transport = session.getTransport("smtp");
        transport.connect(host, USER_NAME, PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private static void sendMultipleRecipent(String[] to, String subject, String body, String host, Session session, MimeMessage message) throws MessagingException {
        message.setFrom(new InternetAddress(USER_NAME));
        InternetAddress[] toAddress = new InternetAddress[to.length];

        // To get the array of addresses
        for( int i = 0; i < to.length; i++ ) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for( int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }

        message.setSubject(subject);
        message.setText(body);
        Transport transport = session.getTransport("smtp");
        transport.connect(host, USER_NAME, PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private static void setProps(Properties props, String host) {
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
    }
}
