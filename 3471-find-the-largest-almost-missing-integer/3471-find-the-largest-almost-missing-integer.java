class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];

        for (int x : nums) {
            count[x]++;
        }

        if (k == n) {
            int ans = -1;
            for (int x : nums) {
                ans = Math.max(ans, x);
            }
            return ans;
        }

        if (k == 1) {
            int ans = -1;
            for (int x : nums) {
                if (count[x] == 1) {
                    ans = Math.max(ans, x);
                }
            }
            return ans;
        }

        int ans = -1;

        if (count[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }

        if (count[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}