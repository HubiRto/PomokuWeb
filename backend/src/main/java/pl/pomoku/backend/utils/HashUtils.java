package pl.pomoku.backend.utils;

import pl.pomoku.backend.algorithm.stackSequence.Sequence;
import pl.pomoku.backend.dto.request.StackSequenceRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class HashUtils {
    public static String generateHash(List<Integer> arr, int k) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (Integer item : arr) {
                md.update(Objects.requireNonNull(item).byteValue());
            }
            md.update(Integer.valueOf(k).byteValue());
            return getString(md);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateHash(StackSequenceRequest request) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (Integer item : request.initArray()) {
                md.update(Objects.requireNonNull(item).byteValue());
            }
            
            for (Sequence sequence : request.sequences()) {
                md.update(sequence.getSequenceType().name().getBytes());
                if (sequence.getNumber() != null) {
                    md.update(Objects.requireNonNull(sequence.getNumber()).byteValue());
                }
            }

            return getString(md);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm for generating hash not found", e);
        }
    }

    private static String getString(MessageDigest md) {
        byte[] digest = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }

    public static String generateGraphHash(int[][] graph, int startVertex) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            for (int[] row : graph) {
                for (int cell : row) {
                    digest.update((byte) cell);
                }
            }

            digest.update((byte) startVertex);

            byte[] hash = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm for generating hash not found", e);
        }
    }
}
