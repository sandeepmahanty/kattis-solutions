package com.sandeep.kattis;

import java.util.*;

/**
 * This is a solution for the GetShorty Problem from {@code Kattis}
 *
 * @see <a href="https://open.kattis.com/problems/getshorty"> GetShorty
 * Problem</a>
 */
public class GetShorty {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        while (n != 0 && m != 0) {

            List<List<Pair>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                float val = scanner.nextFloat();
                graph.get(x).add(new Pair(y, val));
                graph.get(y).add(new Pair(x, val));
            }
            n = scanner.nextInt();
            m = scanner.nextInt();
            System.out.println(String.format("%.4f", getMaxHeight(graph)));
        }
    }

    private static float getMaxHeight(List<List<Pair>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        float[] dist = new float[n];

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> Float.compare(b.getVal(),a.getVal()));
        pq.add(new Pair(0, 1));

        while (!pq.isEmpty()) {
            Pair node = pq.poll();
            int current = node.getEnd();
            float val = node.getVal();

            if (visited[current]) {
                continue;
            }

            visited[current] = true;
            dist[current] = val;

            List<Pair> adjacency = graph.get(current);

            for (Pair vertex : adjacency) {
                int v = vertex.getEnd();
                float fac = vertex.getVal();
                pq.add(new Pair(v, fac * val));
            }
        }
        return dist[dist.length - 1];
    }
}

class Pair {
    private int end;
    private float val;

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    public Pair(int end, float val) {
        this.end = end;
        this.val = val;
    }
}
