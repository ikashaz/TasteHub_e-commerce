package com.e_commerce.FoodCat.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class KeyGenerators {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println("Secret Key: " + key.getEncoded()); // Print base64-encoded key for storage
    }
}
