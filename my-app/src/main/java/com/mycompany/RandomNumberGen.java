package com.mycompany;

import java.security.SecureRandom;

/**
 * This method is used to generate random number between 100.000 - 999.999.
 * The number will be sent to the user via an Email.
 *
 * @author Casper Bøgeskov Hansen
 * @author Danial Virk
 * @author Morten Allan Jensen
 * @version 1.0
 */
public class RandomNumberGen {

    public String randSixNum() {
        int min = 100000;
        int max = 999999;
        SecureRandom r = new SecureRandom();

        // gives us an int between max and min, which is set in the controller
        String randomN = String.valueOf(r.nextInt((max - min) + 1) + min);

        return randomN;
    }
}
