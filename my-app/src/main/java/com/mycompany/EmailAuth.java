package com.mycompany;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.util.Properties;


class EmailAuth {

    String emailAuth = "";

    /**
     * This method sends an email to the user.
     * The Email contains a code for the user to get authorised.
     *
     * @param name User login (Email)
     * @version 1.0
     * @author Casper Bøgeskov Hansen
     * @author Danial Virk
     * @author Morten Allan Jensen
     */
    void SendMail(String name) {

        final String username = ""; // Your e-mail to send from
        final String password = ""; // Password for that e-mail
        RandomNumberGen rng;
        String tempRandomCode;

        /*--------------------------------------
                Set your own properties
        --------------------------------------*/
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            rng = new RandomNumberGen();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(name));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(name));
            message.setSubject("Login authentication");
            tempRandomCode = rng.randSixNum();
            emailAuth = tempRandomCode;

            String bodyText = "";

            bodyText += "<br>" +
                    addColor("<font size=4>Hello " + name + " </font>", Color.BLACK);
            bodyText += "<br>" +
                    addColor("<font size=4>This is the code you need to login with: </font>", Color.BLACK);
            bodyText += "<br>" +
                    addColor("<font size=6> " + tempRandomCode + "</font>", Color.BLUE);
            bodyText += "<br>" +
                    addColor("<font size=1>If you have any question, email us: test@mail.com</font>", Color.BLACK);

            message.setContent(bodyText, // Insert all text
                    "text/html");

            Transport.send(message); // Send mail

            System.out.println("E-mail sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String addColor(String msg, Color color) {
        String Color = String.format("#%06X", (0xFFFFFF & color.getRGB()));
        String colorMsg = "<FONT COLOR=\"#" + Color + "\">" + msg + "</FONT>";
        return colorMsg;
    }
}