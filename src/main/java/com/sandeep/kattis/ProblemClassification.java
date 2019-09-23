package com.sandeep.kattis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This is a solution for the Classification Problem from {@code Kattis}
 * 
 * @see <a href="https://open.kattis.com/problems/problemclassification">
 *      Classification Problem</a>
 */
class ProblemClassification {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int categoryCount = scanner.nextInt();
        scanner.nextLine();
        String[] categories = new String[categoryCount];
        for (int i = 0; i < categoryCount; i++) {
            categories[i] = scanner.nextLine();
        }

        HashMap<String, Integer> wordCountMap = new HashMap<>();
        while (scanner.hasNext()) {
            String paragraph = scanner.nextLine();

            String[] words = paragraph.split(" ");
            for (String word : words) {
                if (wordCountMap.containsKey(word)) {
                    wordCountMap.put(word, wordCountMap.get(word) + 1);
                } else {
                    wordCountMap.put(word, 1);
                }
            }
        }
        scanner.close();
        TreeMap<Integer, List<String>> classification = new TreeMap<>();

        for (String categoryLine : categories) {
            String[] tokens = categoryLine.split(" ");
            String category = tokens[0];
            int score = 0;
            for (int j = 2; j < tokens.length; j++) {
                if (wordCountMap.containsKey(tokens[j])) {
                    score += wordCountMap.get(tokens[j]);
                }
            }
            if (classification.containsKey(score)) {
                classification.get(score).add(category);
            } else {
                List<String> classCategory = new ArrayList<>();
                classCategory.add(category);
                classification.put(score, classCategory);
            }
        }
        List<String> classifiedCategories = classification.get(classification.lastKey());
        Collections.sort(classifiedCategories);
        for (String cat : classifiedCategories) {
            System.out.println(cat);
        }
    }
}