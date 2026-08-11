class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1)
                sum += nums[i];
            else
                break;
        }

        java.util.HashSet<Integer> set = new java.util.HashSet<>();

        for (int x : nums)
            set.add(x);

        while (set.contains(sum))
            sum++;

        return sum;
    }
}