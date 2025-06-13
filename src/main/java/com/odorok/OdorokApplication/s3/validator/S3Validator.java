package com.odorok.OdorokApplication.s3.validator;

import com.odorok.OdorokApplication.s3.util.S3Util;

public class S3Validator {
    public static boolean isOwner(String url, String userId) {
        String hashed = S3Util.sha256(userId);
        return url.contains(hashed);
    }
}
