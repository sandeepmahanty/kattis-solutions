package com.sandeep.kattis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This is a solution for the Dyslectionary Problem from {@code Kattis}
 * 
 * @see <a href="https://open.kattis.com/problems/dyslectionary"> Dyslectionary
 *      Problem</a>
 */
public class Dyslectionary {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isEndOfInput = false;
        while (sc.hasNext()) {
            int maxLength = -1;
            List<String> list = new ArrayList<>();
            String line = sc.nextLine();
            while (!line.equals("")) {
                if (line.length() > maxLength) {
                    maxLength = line.length();
                }
                list.add(line);
                if (sc.hasNext()) {
                    line = sc.nextLine();
                } else {
                    line = "";
                    isEndOfInput = true;
                }
            }
            Collections.sort(list, new Comparator<String>() {
                public int compare(String one, String two) {
                    int l1 = one.length() - 1;
                    int l2 = two.length() - 1;

                    for (; l1 >= 0 && l2 >= 0; l1--, l2--) {
                        if (one.charAt(l1) != two.charAt(l2)) {
                            return one.charAt(l1) - two.charAt(l2);
                        }
                    }

                    return 0;
                }
            });
            printRightJustified(list, maxLength);
            if (!isEndOfInput) {
                System.out.println();
            }
        }
        sc.close();
    }

    private static void printRightJustified(List<String> items, int maxLength) {
        for (String s : items) {
            int lengthDiff = maxLength - s.length();
            for (int i = 0; i < lengthDiff; i++) {
                System.out.print(" ");
            }
            System.out.println(s);
        }
    }
}