class Solution {
    int[][] dist = new int[26][26];
    int[][] dp;

    public int minimumDistance(String word) {
        int n = word.length();
        dp = new int[n][27];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return helper(word, 0, 26);
    }

    private int helper(String word, int index, int finger) {
        if (index == word.length()) return 0;

        if (dp[index][finger] != -1) return dp[index][finger];

        int curr = word.charAt(index) - 'A';
        int next = (index == 0) ? 26 : word.charAt(index - 1) - 'A';

        int useSame = helper(word, index + 1, finger) + distance(next, curr);
        int useOther = helper(word, index + 1, next) + distance(finger, curr);

        return dp[index][finger] = Math.min(useSame, useOther);
    }

    private int distance(int a, int b) {
        if (a == 26 || b == 26) return 0;
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}