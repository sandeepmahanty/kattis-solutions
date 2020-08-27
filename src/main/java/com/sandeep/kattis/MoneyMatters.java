package com.sandeep.kattis;

import java.util.*;

/**
 * This is a solution for the Money Matters Problem from {@code Kattis}
 *
 * @see <a href="https://open.kattis.com/problems/moneymatters">
 * Money Matters Problem</a>
 */
public class MoneyMatters {
    static List<List<Integer>> graph = new ArrayList<>();
    static int sum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] money = new int[n];
        int m = scanner.nextInt();
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            money[i] = scanner.nextInt();
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            sum = 0;
            dfs(i, visited, money);
            if (sum != 0) {
                break;
            }
        }
        if (sum != 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println("POSSIBLE");
        }
    }

    private static void dfs(int parent, boolean[] visited, int[] money) {
        if (visited[parent]) {
            return;
        }
        sum += money[parent];
        visited[parent] = true;
        List<Integer> adjacency = graph.get(parent);

        for (int neighbor : adjacency) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, money);
            }
        }
    }
}