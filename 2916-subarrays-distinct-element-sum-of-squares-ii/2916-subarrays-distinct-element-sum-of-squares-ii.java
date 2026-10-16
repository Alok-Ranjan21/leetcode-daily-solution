class Solution {
    static final int MOD = 1000000007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] tree = new long[4 * n];
        long[] lazy = new long[4 * n];
        long[] sum = new long[4 * n];
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);
        long ans = 0;
        long cur = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int l = last[x] + 1;
            update(tree, lazy, sum, 1, 0, n - 1, l, i);
            cur = query(tree, lazy, sum, 1, 0, n - 1, 0, i);
            ans = (ans + cur) % MOD;
            last[x] = i;
        }

        return (int) ans;
    }

    void update(long[] tree, long[] lazy, long[] sum, int node, int start, int end, int l, int r) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            sum[node] = (sum[node] + 2 * tree[node] + (end - start + 1)) % MOD;
            tree[node] = (tree[node] + (end - start + 1)) % MOD;
            lazy[node]++;
            return;
        }

        push(tree, lazy, sum, node, start, end);
        int mid = (start + end) / 2;
        update(tree, lazy, sum, node * 2, start, mid, l, r);
        update(tree, lazy, sum, node * 2 + 1, mid + 1, end, l, r);
        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
    }

    void push(long[] tree, long[] lazy, long[] sum, int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        apply(tree, lazy, sum, node * 2, start, mid, lazy[node]);
        apply(tree, lazy, sum, node * 2 + 1, mid + 1, end, lazy[node]);
        lazy[node] = 0;
    }

    void apply(long[] tree, long[] lazy, long[] sum, int node, int start, int end, long val) {
        sum[node] = (sum[node] + 2 * val * tree[node] + val * val * (end - start + 1)) % MOD;
        tree[node] = (tree[node] + val * (end - start + 1)) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    long query(long[] tree, long[] lazy, long[] sum, int node, int start, int end, int l, int r) {
        if (l > end || r < start) return 0;
        if (l <= start && end <= r) return sum[node];

        push(tree, lazy, sum, node, start, end);
        int mid = (start + end) / 2;
        return (query(tree, lazy, sum, node * 2, start, mid, l, r)
              + query(tree, lazy, sum, node * 2 + 1, mid + 1, end, l, r)) % MOD;
    }
}