package com.backend.invest.userpreference.adapter.out.persistence;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BinanceApiKeyEncryptor {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String PREFIX = "enc:v1:";
    private static final int GCM_IV_LENGTH_BYTES = 12;
    private static final int GCM_TAG_LENGTH_BITS = 128;

    private final SecretKey secretKey;
    private final SecureRandom secureRandom = new SecureRandom();

    public BinanceApiKeyEncryptor(@Value("${BINANCE_KEY_ENCRYPTION_SECRET}") String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException(
                    "BINANCE_KEY_ENCRYPTION_SECRET must be configured and non-blank");
        }
        this.secretKey = deriveAes256Key(secret);
    }

    public String encrypt(String plaintext) {
        if (plaintext == null) {
            return null;
        }
        try {
            byte[] iv = new byte[GCM_IV_LENGTH_BYTES];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            ByteBuffer buffer = ByteBuffer.allocate(iv.length + ciphertext.length);
            buffer.put(iv);
            buffer.put(ciphertext);

            return PREFIX + Base64.getEncoder().encodeToString(buffer.array());
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Failed to encrypt Binance API key", e);
        }
    }

    public String decrypt(String encryptedValue) {
        if (encryptedValue == null) {
            return null;
        }
        if (!encryptedValue.startsWith(PREFIX)) {
            throw new IllegalStateException("Invalid encrypted Binance API key format");
        }
        try {
            byte[] decoded = Base64.getDecoder().decode(encryptedValue.substring(PREFIX.length()));
            if (decoded.length <= GCM_IV_LENGTH_BYTES) {
                throw new IllegalStateException("Invalid encrypted Binance API key payload");
            }

            byte[] iv = new byte[GCM_IV_LENGTH_BYTES];
            byte[] ciphertext = new byte[decoded.length - GCM_IV_LENGTH_BYTES];
            System.arraycopy(decoded, 0, iv, 0, GCM_IV_LENGTH_BYTES);
            System.arraycopy(decoded, GCM_IV_LENGTH_BYTES, ciphertext, 0, ciphertext.length);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            byte[] plaintext = cipher.doFinal(ciphertext);

            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (GeneralSecurityException | IllegalArgumentException e) {
            throw new IllegalStateException("Failed to decrypt Binance API key", e);
        }
    }

    private static SecretKey deriveAes256Key(String secret) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha256.digest(secret.getBytes(StandardCharsets.UTF_8));
            return new SecretKeySpec(keyBytes, "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }
}