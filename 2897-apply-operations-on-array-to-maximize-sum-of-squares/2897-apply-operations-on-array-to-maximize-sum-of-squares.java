import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bits = new int[31];

        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if ((num & (1 << i)) != 0) {
                    bits[i]++;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int j = 0; j < 31; j++) {
                if (bits[j] > 0) {
                    x |= (1L << j);
                    bits[j]--;
                }
            }

            ans = (ans + x * x) % 1000000007;
        }

        return (int) ans;
    }
}