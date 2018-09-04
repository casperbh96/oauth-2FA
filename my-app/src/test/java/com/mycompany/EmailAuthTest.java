package com.mycompany;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.junit.Assert.*;

public class EmailAuthTest {

    @Test
    public void sendMail() {
        EmailAuth eAuth = new EmailAuth();
        boolean mailSent;
        if (mailSent = false) {
            eAuth.SendMail("mallanj@ruc.dk");
            mailSent = true;
            Assert.assertTrue(mailSent);
        } else {
            Assert.assertFalse(mailSent);
        }
    }
}