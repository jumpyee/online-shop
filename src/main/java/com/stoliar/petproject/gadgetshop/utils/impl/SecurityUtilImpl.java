package com.stoliar.petproject.gadgetshop.utils.impl;

import com.stoliar.petproject.gadgetshop.utils.SecurityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class SecurityUtilImpl implements SecurityUtil {
    @Override
    public String hashPassword(String password) {
        MessageDigest sha256Digest = DigestUtils.getSha256Digest();
        sha256Digest.update(password.getBytes());
        return new String(sha256Digest.digest());
    }
}
