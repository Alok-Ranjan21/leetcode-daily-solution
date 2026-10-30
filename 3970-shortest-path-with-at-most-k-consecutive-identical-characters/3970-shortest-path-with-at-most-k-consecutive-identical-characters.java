import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long cost = cur[0];
            int u = (int) cur[1];
            int count = (int) cur[2];

            if (cost != dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return (int) cost;
            }

            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];

                int nextCount = labels.charAt(u) == labels.charAt(v)
                    ? count + 1 : 1;

                if (nextCount > k) {
                    continue;
                }

                long nextCost = cost + w;

                if (nextCost < dist[v][nextCount]) {
                    dist[v][nextCount] = (int) nextCost;
                    pq.offer(new long[]{nextCost, v, nextCount});
                }
            }
        }

        return -1;
    }
}