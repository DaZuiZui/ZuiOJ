package com.dazuizui.business.util;

import java.math.BigInteger;
import java.util.*;

public class CodeDuplicationChecker {
    private static Set<String> whitelist; // 白名单集合，存放不需要查重的代码片段

    static {
        whitelist = new HashSet<>();
        // 在白名单中添加不需要查重的代码片段
        whitelist.add("import");
        whitelist.add("package");
        whitelist.add("public");
        whitelist.add("private");
        whitelist.add("protected");
        whitelist.add("class");
        whitelist.add("interface");
        whitelist.add("extends");
        whitelist.add("implements");
        whitelist.add("void");
        whitelist.add("static");
        whitelist.add("new");
        whitelist.add("this");
        whitelist.add("super");
        whitelist.add("if");
        whitelist.add("else");
        whitelist.add("for");
        whitelist.add("while");
        whitelist.add("do");
        whitelist.add("switch");
        whitelist.add("case");
        whitelist.add("break");
        whitelist.add("continue");
        whitelist.add("return");
        whitelist.add("try");
        whitelist.add("catch");
        whitelist.add("finally");
        whitelist.add("throw");
        whitelist.add("throws");
        whitelist.add("assert");
        whitelist.add("true");
        whitelist.add("false");
        whitelist.add("null");
        // 添加更多白名单条目...
    }


    public static void main(String[] args) {
        // 假设您有一批学生提交的代码，存放在一个列表中
        List<String> studentCodes = new ArrayList<>();
        studentCodes.add("你好");
        studentCodes.add("你好");


        // 检查每对学生代码的相似性，并输出结果
        int totalComparisons = 0;
        int similarPairs = 0;

        for (int i = 0; i < studentCodes.size(); i++) {
            for (int j = i + 1; j < studentCodes.size(); j++) {
                totalComparisons++;

                String code1 = studentCodes.get(i);
                String code2 = studentCodes.get(j);

                // 检查代码片段是否在白名单中
                if (isWhitelisted(code1) || isWhitelisted(code2)) {
                    continue; // 在白名单中的代码片段不需要查重，跳过
                }

                // 计算SimHash相似性
                double simHashSimilarity = calculateSimHashSimilarity(code1, code2);

                // 计算Jaccard相似性
                double jaccardSimilarity = calculateJaccardSimilarity(code1, code2);

                // 计算编辑距离
                int editDistance = calculateEditDistance(code1, code2);

                // 综合考虑相似性度量，并根据需要进行权衡
                double combinedSimilarity = combineSimilarities(simHashSimilarity, jaccardSimilarity, editDistance);

                // 如果相似性超过阈值，认为存在查重
                double threshold = 0.5; // 设置相似性阈值
                if (combinedSimilarity >= threshold) {
                    similarPairs++;
                    System.out.println("存在代码查重：");
                    System.out.println("代码片段1：");
                    System.out.println(code1);
                    System.out.println("代码片段2：");
                    System.out.println(code2);

                }
                System.out.println("综合相似性：" + combinedSimilarity);
                System.out.println("------------------------");
            }
        }

        // 计算最终查重率
        double duplicationRate = (double) similarPairs / totalComparisons;
        System.out.println("最终查重率：" + duplicationRate);
    }

    // 计算SimHash相似性的方法
    private static double calculateSimHashSimilarity(String code1, String code2) {
        BigInteger simHash1 = calculateSimHash(code1);
        BigInteger simHash2 = calculateSimHash(code2);

        int hammingDistance = calculateHammingDistance(simHash1, simHash2);

        return calculateSimilarityScore(hammingDistance);
    }

    // 计算SimHash值
    private static BigInteger calculateSimHash(String code) {
        // 设置SimHash的位数（根据需要调整）
        int bitCount = 64;
        int[] simHashArray = new int[bitCount];

        // 分词并计算每个词的哈希值
        String[] tokens = code.split("\\s+");
        Map<Integer, Integer> wordFrequencyMap = new HashMap<>();

        for (String token : tokens) {
            int hash = token.hashCode();
            wordFrequencyMap.put(hash, wordFrequencyMap.getOrDefault(hash, 0) + 1);
        }

        // 计算SimHash值
        for (int hash : wordFrequencyMap.keySet()) {
            int hashValue = hash;
            for (int i = 0; i < bitCount; i++) {
                int bit = (hashValue & 1) == 1 ? 1 : -1;
                simHashArray[i] += bit;
                hashValue >>= 1;
            }
        }

        BigInteger simHash = BigInteger.ZERO;
        for (int i = 0; i < bitCount; i++) {
            if (simHashArray[i] > 0) {
                simHash = simHash.setBit(i);
            }
        }

        return simHash;
    }

    // 计算汉明距离
    private static int calculateHammingDistance(BigInteger simHash1, BigInteger simHash2) {
        BigInteger xor = simHash1.xor(simHash2);
        int hammingDistance = xor.bitCount();
        return hammingDistance;
    }

    // 计算相似性分数
    private static double calculateSimilarityScore(int hammingDistance) {
        return 1.0 / (hammingDistance + 1);
    }

    // 计算Jaccard相似性的方法
    private static double calculateJaccardSimilarity(String code1, String code2) {
        Set<String> words1 = new HashSet<>(Arrays.asList(code1.split("\\s+")));
        Set<String> words2 = new HashSet<>(Arrays.asList(code2.split("\\s+")));

        // 计算交集大小
        int intersectionSize = 0;
        for (String word : words1) {
            if (isWhitelisted(word)) {
                continue; // 如果单词在白名单中，跳过
            }
            if (words2.contains(word)) {
                intersectionSize++;
            }
        }

        // 计算并集大小
        int unionSize = words1.size() + words2.size() - intersectionSize;

        // 计算Jaccard相似性
        if (unionSize == 0) {
            return 0.0; // 避免除以零错误
        } else {
            return (double) intersectionSize / unionSize;
        }
    }

    // 检查单词是否在白名单中
    private static boolean isWhitelisted(String word) {
        return whitelist.contains(word);
    }


    // 计算编辑距离的方法
    private static int calculateEditDistance(String code1, String code2) {
        // 使用动态规划计算编辑距离
        int m = code1.length();
        int n = code2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (code1.charAt(i - 1) == code2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // 综合考虑相似性度量并根据需要进行权衡
    private static double combineSimilarities(double simHashSimilarity, double jaccardSimilarity, int editDistance) {
        // 这里可以根据需要进行权衡不同相似性度量的贡献
        // 这个示例中，平均了三个相似性分数，您可以根据实际情况进行调整
        return (simHashSimilarity + jaccardSimilarity + (1.0 / (editDistance + 1))) / 3.0;
    }


}

