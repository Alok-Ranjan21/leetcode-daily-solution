class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] seen = new boolean[101];

        for (int x : nums) {
            seen[x] = true;
        }

        for (int i = 1; ; i++) {
            int x = i * k;

            if (x >= seen.length || !seen[x]) {
                return x;
            }
        }
    }
}