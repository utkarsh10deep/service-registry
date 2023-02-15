package com.wings.serviceregistry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static com.wings.serviceregistry.constants.AppConstants.*;

@Configuration
public class StartupConfig {
    @Value("${secret.key}")
    private String key;
    @Value("${db.location}")
    private String dbLocation;
    @Value("${service.key}")
    private Integer serviceKey;
    @Value("${directory.separator}")
    private String directorySeparator;

    @Bean
    public SecretKey getKeyFromPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_FACTORY_ALGORITHM);
        KeySpec spec = new PBEKeySpec(this.key.toCharArray(), ENCRYPTION_SALT.getBytes(), KEY_ITERATION_COUNT, KEY_LENGTH);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
        return secret;
    }

    @Bean
    public IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public String getKey() {
        return this.key;
    }

    public String getDbLocation() {
        return this.dbLocation;
    }

    public Integer getServiceKey() {
        return this.serviceKey;
    }

    public String getDirectorySeparator() {
        return this.directorySeparator;
    }
}

