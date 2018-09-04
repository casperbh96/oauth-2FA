package com.mycompany;

import org.junit.Assert;
import org.junit.Test;

public class TokenGeneratorTest {


    TokenGenerator tokenGenerator = new TokenGenerator();

    /**
     * The test method generates 1 million tokens and compares them so there isn't two identical tokens.
     * These tokens are the refresh tokens. if the tokes are the same the test isn't cleared.
     *
     * @return nothing.
     */
    @Test
    public void generateRefreshToken() {
        for (int i = 0; i < 1000000; i++) {
            final String token = tokenGenerator.generateRefreshToken();
            final String laterToken = tokenGenerator.generateRefreshToken();
            Assert.assertNotEquals(token, laterToken);
            if (token.equals(laterToken)) {
                Assert.assertEquals(token, laterToken);
            }
        }
    }

    /**
     * the test method generates 1 million access tokens and compare them.
     * if to access tokens are the same the test isn't cleared.
     *
     * @return nothing.
     */

    @Test
    public void generateAccessToken() {
        for (int i = 0; i < 1000000; i++) {
            final String token = tokenGenerator.generateAccessToken();
            final String laterToken = tokenGenerator.generateAccessToken();
            Assert.assertNotEquals(token, laterToken);
            if (token.equals(laterToken)) {
                Assert.assertEquals(token, laterToken);
            }
        }
    }

    /**
     * The test method generate 1 million of access tokens and refresh tokens and compare them.
     * if two of the tokens are the same the test isn't cleared.
     *
     * @return nothing.
     */

    @Test
    public void generateTokens() {
        for (int j = 0; j < 1000000; j++) {
            final String tokenAccess = tokenGenerator.generateAccessToken();
            final String tokenRefresh = tokenGenerator.generateRefreshToken();
            Assert.assertNotEquals(tokenAccess, tokenRefresh);
            if (tokenAccess.equals(tokenRefresh)) {
                Assert.assertEquals(tokenAccess, tokenRefresh);
            }
        }
    }
}