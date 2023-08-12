package com.dazuizui.business.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Jaccard 查重算法
 */
public class TextSimilarityCheckerUtil {
    public static void main(String[] args) {
        String text1 = "This is a sample text for testing.";
        String text2 = "This is another sample text for testing.";

        double similarity = calculateJaccardSimilarity(text1, text2);
        System.out.println("Jaccard Similarity between the two texts: " + similarity);
    }

    private static double calculateJaccardSimilarity(String text1, String text2) {
        Set<String> words1 = new HashSet<>();
        Set<String> words2 = new HashSet<>();

        // 使用 HanLP 进行分词
        List<Term> termList1 = HanLP.segment(text1);
        List<Term> termList2 = HanLP.segment(text2);

        // 提取分词结果中的词语
        for (Term term : termList1) {
            words1.add(term.word);
        }
        for (Term term : termList2) {
            words2.add(term.word);
        }

        // 计算 Jaccard 相似度
        Set<String> union = new HashSet<>(words1);
        union.addAll(words2);

        Set<String> intersection = new HashSet<>(words1);
        intersection.retainAll(words2);

        return (double) intersection.size() / union.size();
    }
}
