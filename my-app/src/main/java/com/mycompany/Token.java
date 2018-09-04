package com.mycompany;

import com.google.gson.Gson;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;


public class Token {
    Date date;
    long unixTime;
    String userName;
    String refreshToken;
    String accessToken;
    int refreshTokenExpiry = 336; // 14 days in hours
    int accessTokenExpiry = 1; // 1 hour
    String uuid;
    String checkSum;

    /**
     * This method create the access and refresh token for the user.
     * It prints out the date, unixTime, username and the two random generated tokens.
     * at the end it will get an signature so we can check if the token is real.
     *
     * @param userName Display the username in the token
     * @version 1.0
     * @author Casper Bøgeskov Hansen
     * @author Danial Virk
     * @author Morten Allan Jensen
     */
    public Token(String userName) {

        date = new Date();
        unixTime = System.currentTimeMillis() / 1000L;
        this.userName = userName;

        TokenGenerator tokenGenerator = new TokenGenerator();

        refreshToken = tokenGenerator.generateRefreshToken();
        accessToken = tokenGenerator.generateAccessToken();

        uuid = UUID.randomUUID().toString();

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(refreshToken));
            refreshToken = String.format("%032x", new BigInteger(1, md5.digest()));
            md5.update(StandardCharsets.UTF_8.encode(accessToken));
            accessToken = String.format("%032x", new BigInteger(1, md5.digest()));
            System.out.println(accessToken + refreshToken);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void Signature() {
        String accessTtoMD5 = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String accessTAndPass = accessToken + "secretpassword";
            md5.update(StandardCharsets.UTF_8.encode(accessTAndPass));
            accessTtoMD5 = String.format("%032x", new BigInteger(1, md5.digest()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        checkSum = accessTtoMD5;
    }

    public String toString() {
        String prettyPrint = "";
        Signature();
        Gson gson = new Gson();

        prettyPrint = gson.toJson(this).toString();
        return prettyPrint;
    }
}