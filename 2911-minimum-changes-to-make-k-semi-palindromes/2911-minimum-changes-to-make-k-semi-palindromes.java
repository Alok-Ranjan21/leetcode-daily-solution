
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000000;

        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int best = INF;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;
                    int groups = len / d;

                    for (int r = 0; r < d; r++) {
                        int l = 0;
                        int right = groups - 1;

                        while (l < right) {
                            if (s.charAt(i + r + l * d) !=
                                s.charAt(i + r + right * d)) {
                                changes++;
                            }
                            l++;
                            right--;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][i + len - 1] = best;
            }
        }

        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
