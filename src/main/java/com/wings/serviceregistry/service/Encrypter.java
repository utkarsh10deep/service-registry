package com.wings.serviceregistry.service;

import com.wings.serviceregistry.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.wings.serviceregistry.constants.AppConstants.ENCRYPTION_ALGORITHM;

@Service
public class Encrypter {
    @Autowired
    private AESUtil aesUtil;
    @Autowired
    private SecretKey secretKey;
    @Autowired
    private IvParameterSpec ivParameterSpec;

    public String encrypt(String input) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return aesUtil.encrypt(ENCRYPTION_ALGORITHM,input,secretKey,ivParameterSpec);
    }
}
