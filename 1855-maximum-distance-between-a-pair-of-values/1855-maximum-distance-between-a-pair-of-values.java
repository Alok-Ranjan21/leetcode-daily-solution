class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int n = nums1.length, m = nums2.length;
        int res = 0;

        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                res = Math.max(res, j - i);
                j++;
            } else {
                i++;
            }
        }

        return res;
    }
}