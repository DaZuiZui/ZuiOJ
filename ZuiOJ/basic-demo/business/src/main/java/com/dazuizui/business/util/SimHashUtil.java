package com.dazuizui.business.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SimHash查重算法工具类
 */
public class SimHashUtil {
    //越大查重越精细，但是计算慢，越小可以提高计算效率，但可能会降低特征表示的精度。
    private static final int HASH_SIZE = 64;
    //值越大查重越严，值越小查重越松
    private static final double SIMILARITY_THRESHOLD = 0.6; // 相似度阈值
    //白名单
    private static final Set<String> stringSet = new HashSet<>();

    public static BigInteger computeSimHash(String text) {
        if (text == null || text.isEmpty() || text.equals("") || text.trim().length() == 0 ||  text.length() == 0 ) {
            // 处理空字符串情况
            System.err.println("Error: Input text is empty.");
            return BigInteger.ZERO; // 或者返回一个默认的BigInteger值，具体根据你的需求决定
        }


        int[] featureVector = new int[HASH_SIZE];
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (word.length() == 0){
                continue;
            }

            if (whitelistLine(word,-1)) {
                continue;
            }

            BigInteger wordHash = hashWord(word);
            for (int i = 0; i < HASH_SIZE; i++) {
                BigInteger bitmask = BigInteger.ONE.shiftLeft(i);
                if (wordHash.and(bitmask).compareTo(BigInteger.ZERO) != 0) {
                    featureVector[i] += 1;
                } else {
                    featureVector[i] -= 1;
                }
            }
        }

        BigInteger simHash = BigInteger.ZERO;
        for (int i = 0; i < HASH_SIZE; i++) {
            if (featureVector[i] > 0) {
                simHash = simHash.add(BigInteger.ONE.shiftLeft(i));
            }
        }

        return simHash;
    }

    /**
     * 白名单
     * @return
     */
    public static boolean whitelistLine(String text,int line){
        System.err.println(text);
        if (stringSet.contains(text)){
            System.err.println("1");
            return true;
        }

        return false;
    }

    private static BigInteger hashWord(String word) {
        return new BigInteger(word.getBytes());
    }

    public static int hammingDistance(BigInteger hash1, BigInteger hash2) {
        return hash1.xor(hash2).bitCount();
    }

    public static double similarity(BigInteger hash1, BigInteger hash2) {
        int distance = hammingDistance(hash1, hash2);
        return 1.0 - (double) distance / HASH_SIZE;
    }

    public static void findAndPrintDuplicateWords(String text1, String text2) {
        String[] words1 = text1.split("\\s+");
        String[] words2 = text2.split("\\s+");
        List<String> duplicateWords = new ArrayList<>();

        for (int i = 0; i < words1.length; i++) {
            if (i < words2.length && words1[i].equals(words2[i])) {
                duplicateWords.add(words1[i]);
            }
        }

        if (!duplicateWords.isEmpty()) {
            System.out.println("Duplicate words found:");
            for (String word : duplicateWords) {
                int index1 = text1.indexOf(word);
                int index2 = text2.indexOf(word);
                System.out.println("Text1: \"" + word + "\" at position " + index1);
                System.out.println("Text2: \"" + word + "\" at position " + index2);
            }
        } else {
            System.out.println("No duplicate words found.");
        }
    }

    public static void findDuplicateText(String text1, String text2, int line1, int line2) {
        boolean isInWhitelist1 = false;
        boolean isInWhitelist2 = false;

        // 如果text1和text2都在白名单中，则都不检查
        if (isInWhitelist1 && isInWhitelist2) {
            System.out.println("Both Text1 and Text2 are in the whitelist. Skipping similarity check.");
            return;
        }

        // 如果text2在白名单中，对text1不进行相似性检查
        if (isInWhitelist2) {
            System.out.println("Text2 is in the whitelist. Skipping similarity check for Text1.");
            return;
        }

        BigInteger simHash1 = computeSimHash(text1);
        BigInteger simHash2 = computeSimHash(text2);

        double similarity = similarity(simHash1, simHash2);

        System.out.println("SimHash1: " + simHash1.toString(16));
        System.out.println("SimHash2: " + simHash2.toString(16));
        System.out.println("Similarity: " + similarity);

        if (!isInWhitelist1 && similarity > SIMILARITY_THRESHOLD) {
            // 对text1和text2进行相似性检查
            findAndPrintDuplicateWords(text1, text2);
        }
    }




    public static void main(String[] args) {
        String text1 = "This is a sample text for testing.";
        String text2 = "This is another sample text for testing.";

        stringSet.add("int");
        stringSet.add("{");
        stringSet.add("}");
        stringSet.add("[");
        stringSet.add("]");
        stringSet.add("import");
        stringSet.add("java.math.BigInteger;");
        stringSet.add("java.util.Scanner;");
        stringSet.add("public");
        stringSet.add("class");
        stringSet.add("class");
        stringSet.add("Main{");
        stringSet.add("public");
        stringSet.add("static");
        stringSet.add("void");
        stringSet.add("args)");
        stringSet.add("{");
        stringSet.add("=");
        stringSet.add("}");
        stringSet.add("1");
        stringSet.add("new");
        stringSet.add("sc.nextInt();");
        stringSet.add("i;");
        stringSet.add("int");
        stringSet.add("i");
        stringSet.add("}");

        findDuplicateText(text1, text2,0,0);
    }
}
