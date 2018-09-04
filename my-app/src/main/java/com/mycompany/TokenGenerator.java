package com.mycompany;

import java.security.SecureRandom;

/**
 * This class will generate 2 different tokens.
 * Ths first token is refresh token.
 * the second one is the access token.
 * The token will return two different randoms and add them together.
 *
 * @author Casper Bøgeskov Hansen
 * @author Danial Virk
 * @author Morten Allan Jensen
 * @version 1.0
 */
public class TokenGenerator {

    private SecureRandom random = new SecureRandom();

    public synchronized String generateRefreshToken() {
        long longToken = Math.abs(random.nextLong());
        String random = Long.toString(longToken, 16); // base 16-numbers, hexa decimal (0-9, A-F)
        String random2 = Long.toString(longToken, 36); // base 36-numbers, numbers and letters

        return (random + random2);
    }

    public synchronized String generateAccessToken() {
        long longToken = Math.abs(random.nextLong());
        String random = Long.toString(longToken, 16);
        String random2 = Long.toString(longToken, 36);

        return (random + random2);
    }
}