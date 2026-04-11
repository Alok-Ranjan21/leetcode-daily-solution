class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        java.util.HashMap<Integer, java.util.ArrayList<Integer>> map = new java.util.HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new java.util.ArrayList<>()).add(i);
        }

        for (java.util.ArrayList<Integer> list : map.values()) {
            int m = list.size();
            if (m < 3) continue;

            for (int i = 0; i + 2 < m; i++) {
                int dist = 2 * (list.get(i + 2) - list.get(i));
                ans = Math.min(ans, dist);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}