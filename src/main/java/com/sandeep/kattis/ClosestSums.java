package com.sandeep.kattis;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a solution for the Closest sums Problem from {@code Kattis}
 * 
 * @see <a href="https://open.kattis.com/problems/closestsums"> Closest Sums</a>
 */
public class ClosestSums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = 1;
        while (sc.hasNext()) {
            int numCount = sc.nextInt();
            int[] nums = new int[numCount];
            for (int i = 0; i < numCount; i++) {
                nums[i] = sc.nextInt();
            }
            Arrays.sort(nums);
            int queryCount = sc.nextInt();
            System.out.println("Case " + cases + ":");
            for (int j = 0; j < queryCount; j++) {
                int k = sc.nextInt();
                System.out.println(String.format("Closest sum to %d is %d.", k, closest(nums, k)));
            }
            cases++;
        }
        sc.close();
    }

    public static int closest(int[] nums, int k) {
        int i = 0;
        int j = nums.length - 1;
        int min = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (Math.abs(sum - k) < min) {
                min = Math.abs(sum - k);
                minSum = sum;
            }
            if (k == sum) {
                return sum;
            } else if (k > sum) {
                i++;
            } else {
                j--;
            }
        }
        return minSum;
    }
}