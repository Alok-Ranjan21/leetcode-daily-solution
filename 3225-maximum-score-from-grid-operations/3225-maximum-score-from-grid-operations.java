class Solution {

    public long maximumScore(int[][] a) {

        int n = a.length, m = a[0].length;
        if (m == 1) return 0;

        long[][] ps = new long[m][n + 1];

        for (int j = 0; j < m; j++) {
            long s = 0;
            for (int i = 0; i < n; i++) {
                s += a[i][j];
                ps[j][i + 1] = s;
            }
        }

        long[][] dp = new long[n + 1][n + 1];
        long[][] pre = new long[n + 1][n + 1];
        long[][] suf = new long[n + 1][n + 1];

        for (int j = 1; j < m; j++) {

            long[][] ndp = new long[n + 1][n + 1];

            for (int cur = 0; cur <= n; cur++) {
                for (int prv = 0; prv <= n; prv++) {

                    if (cur <= prv) {

                        long add = ps[j][prv] - ps[j][cur];
                        ndp[cur][prv] = Math.max(ndp[cur][prv], suf[prv][0] + add);

                    } else {

                        long add = ps[j - 1][cur] - ps[j - 1][prv];

                        long v1 = suf[prv][cur];
                        long v2 = pre[prv][cur] + add;

                        ndp[cur][prv] = Math.max(ndp[cur][prv], Math.max(v1, v2));
                    }
                }
            }

            for (int cur = 0; cur <= n; cur++) {

                long best = ndp[cur][0];
                pre[cur][0] = best;

                for (int prv = 1; prv <= n; prv++) {

                    long cut = (prv > cur) ? (ps[j][prv] - ps[j][cur]) : 0;
                    best = Math.max(best, ndp[cur][prv] - cut);
                    pre[cur][prv] = best;
                }

                best = ndp[cur][n];
                suf[cur][n] = best;

                for (int prv = n - 1; prv >= 0; prv--) {
                    best = Math.max(best, ndp[cur][prv]);
                    suf[cur][prv] = best;
                }
            }

            dp = ndp;
        }

        long res = 0;
        for (int i = 0; i <= n; i++) {
            res = Math.max(res, dp[0][i]);
            res = Math.max(res, dp[n][i]);
        }

        return res;
    }
}