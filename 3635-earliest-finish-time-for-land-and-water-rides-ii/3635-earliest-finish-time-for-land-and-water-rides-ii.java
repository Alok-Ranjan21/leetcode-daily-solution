class Solution {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans1 = solve(landStartTime, landDuration,
                         waterStartTime, waterDuration);

        int ans2 = solve(waterStartTime, waterDuration,
                         landStartTime, landDuration);

        return Math.min(ans1, ans2);
    }

    private int solve(int[] s1, int[] d1,
                      int[] s2, int[] d2) {

        int minFinish = Integer.MAX_VALUE;

        
        for (int i = 0; i < s1.length; i++) {
            minFinish = Math.min(minFinish, s1[i] + d1[i]);
        }

        int ans = Integer.MAX_VALUE;

        
        for (int i = 0; i < s2.length; i++) {
            int start = Math.max(minFinish, s2[i]);
            ans = Math.min(ans, start + d2[i]);
        }

        return ans;
    }
}