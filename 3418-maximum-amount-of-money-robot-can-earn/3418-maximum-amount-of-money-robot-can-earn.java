class Solution {
    public int maximumAmount(int[][] coins) {
        int k = 2;
        int m = coins.length;
        int n = coins[0].length;
        Integer[][][] dp = new Integer[m][n][k + 1];
        return dfs(0, 0, k, coins, dp);
    }

    private int dfs(int i, int j, int k, int[][] coins, Integer[][][] dp) {
        int m = coins.length, n = coins[0].length;

        if (i >= m || j >= n) return Integer.MIN_VALUE;

        if (i == m - 1 && j == n - 1) {
            if (coins[i][j] < 0 && k > 0) return 0;
            return coins[i][j];
        }

        if (dp[i][j][k] != null) return dp[i][j][k];

        int val = coins[i][j];

        int down = dfs(i + 1, j, k, coins, dp);
        int right = dfs(i, j + 1, k, coins, dp);

        int best = Math.max(down, right);

        int take = val + best;

        int skip = Integer.MIN_VALUE;
        if (val < 0 && k > 0) {
            int downSkip = dfs(i + 1, j, k - 1, coins, dp);
            int rightSkip = dfs(i, j + 1, k - 1, coins, dp);
            skip = Math.max(downSkip, rightSkip);
        }

        return dp[i][j][k] = Math.max(take, skip);
    }
}