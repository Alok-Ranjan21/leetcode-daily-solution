class Solution {
    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, n - 1);
    }

    private int solve(int l, int r) {
        if (l >= r) return 0;

        if (dp[l][r] != -1)
            return dp[l][r];

        int ans = 0;
        int leftSum = 0;
        int total = prefix[r + 1] - prefix[l];

        for (int k = l; k < r; k++) {
            leftSum += prefix[k + 1] - prefix[k];
            int rightSum = total - leftSum;

            if (leftSum < rightSum) {
                ans = Math.max(ans, leftSum + solve(l, k));
            } 
            else if (leftSum > rightSum) {
                ans = Math.max(ans, rightSum + solve(k + 1, r));
            } 
            else {
                ans = Math.max(ans,
                    Math.max(
                        leftSum + solve(l, k),
                        rightSum + solve(k + 1, r)
                    )
                );
            }
        }

        return dp[l][r] = ans;
    }
}