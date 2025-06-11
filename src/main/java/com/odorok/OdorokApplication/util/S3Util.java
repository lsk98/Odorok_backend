package com.odorok.OdorokApplication.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class S3Util {

    //도메인별 키 생성
    public static String generateKey(String domain, String userId, String fileName) {
        return domain + "/" + sha256(userId) + "/" + fileName;
    }

    //userId 해싱
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 해싱 실패", e);
        }
    }
    public static String extractKeyFromUrl(String url) {
        try {
            URI uri = new URI(url);
            return uri.getPath().substring(1); // 앞에 '/' 제거
        } catch (URISyntaxException e) {
            throw new RuntimeException("잘못된 URL 형식", e);
        }
    }

    public static String generateUrl(String bucket, String region, String key) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
    }
}