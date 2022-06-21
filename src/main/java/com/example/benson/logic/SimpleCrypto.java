package com.example.benson.logic;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto {
    private static Cipher encrypter;

    static {
        try {
            SecretKeySpec key = new SecretKeySpec("BensonSecretKey!".getBytes(), "AES");

            encrypter = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encrypter.init(Cipher.ENCRYPT_MODE, key);
        } catch (Exception e) {
            throw new IllegalStateException("Can't set up encrypter", e);
        }
    }

    public static String encrypt(final String s) {
        try {
            return Base64.getEncoder().encodeToString(encrypter.doFinal(s.getBytes()));
        } catch (Exception e) {
            throw new IllegalStateException("Can't encrypt", e);
        }
    }
}
