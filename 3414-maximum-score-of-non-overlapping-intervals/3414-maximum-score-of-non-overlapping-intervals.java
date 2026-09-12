import java.util.*;

class Solution {
    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) return Integer.compare(x[0], y[0]);
            if (x[1] != y[1]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[3], y[3]);
        });

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = a[i][0];
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new int[0]);

            int next = upperBound(starts, a[i][1]);

            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];
                State after = dp[next][k - 1];

                int[] takeIds = new int[after.ids.length + 1];
                takeIds[0] = a[i][3];

                for (int j = 0; j < after.ids.length; j++) {
                    takeIds[j + 1] = after.ids[j];
                }

                Arrays.sort(takeIds);

                State take = new State(
                    a[i][2] + after.score,
                    takeIds
                );

                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].ids;
    }

    private int upperBound(int[] a, int target) {
        int l = 0, r = a.length;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (a[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }

    private State better(State a, State b) {
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        return compare(a.ids, b.ids) < 0 ? a : b;
    }

    private int compare(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}