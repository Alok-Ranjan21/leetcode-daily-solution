class Solution {
    class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        BIT bit = new BIT(2 * n + 5);

        int prefix = n + 2;
        bit.update(prefix, 1);

        long ans = 0;

        for (int x : nums) {
            if (x == target)
                prefix++;
            else
                prefix--;

            ans += bit.query(prefix - 1);
            bit.update(prefix, 1);
        }

        return ans;
    }
}