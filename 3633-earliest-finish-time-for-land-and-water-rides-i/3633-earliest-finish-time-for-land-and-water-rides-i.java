class Solution {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans1 = solve(landStartTime, landDuration,
                         waterStartTime, waterDuration);

        int ans2 = solve(waterStartTime, waterDuration,
                         landStartTime, landDuration);

        return Math.min(ans1, ans2);
    }

    private int solve(int[] start1, int[] dur1,
                      int[] start2, int[] dur2) {

        int firstFinish = Integer.MAX_VALUE;

        // Earliest finishing ride from first category
        for (int i = 0; i < start1.length; i++) {
            firstFinish = Math.min(firstFinish,
                                   start1[i] + dur1[i]);
        }

        int ans = Integer.MAX_VALUE;

        // Try second category after first
        for (int i = 0; i < start2.length; i++) {

            int start = Math.max(firstFinish, start2[i]);

            ans = Math.min(ans, start + dur2[i]);
        }

        return ans;
    }
}