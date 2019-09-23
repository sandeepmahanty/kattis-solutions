package com.sandeep.kattis;

import java.util.Arrays;

import java.util.Scanner;

/**
 * This is a solution for the Loowater Dragon Problem from {@code Kattis}
 * 
 * @see <a href="https://open.kattis.com/problems/loowater"> Loowater Dragon
 *      Problem</a>
 */
public class LoowaterDragon {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int headCount = sc.nextInt();
        int knightCount = sc.nextInt();

        while (headCount != 0 && knightCount != 0) {
            String result = "Loowater is doomed!";
            sc.nextLine();

            int[] heads = new int[headCount];
            int[] knights = new int[knightCount];

            for (int i = 0; i < headCount; i++) {
                heads[i] = sc.nextInt();
            }

            for (int i = 0; i < knightCount; i++) {
                knights[i] = sc.nextInt();
            }
            if (headCount > knightCount) {
                System.out.println(result);
            } else {
                Arrays.sort(knights);
                Arrays.sort(heads);
                int score = 0;
                int count = 0;
                int i = 0;
                int j = 0;
                while (i < headCount && j < knightCount) {
                    if (knights[j] < heads[i]) {
                        j++;
                    } else {
                        score += knights[j];
                        count++;
                        j++;
                        i++;
                    }
                }

                if (count == headCount) {
                    System.out.println(score);
                } else {
                    System.out.println(result);
                }
            }
            headCount = sc.nextInt();
            knightCount = sc.nextInt();
        }
        sc.close();
    }
}