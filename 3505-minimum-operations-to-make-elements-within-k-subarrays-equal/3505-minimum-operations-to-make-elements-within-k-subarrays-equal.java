import java.util.*;

class Solution {
    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();
    long lowSum = 0, highSum = 0;
    int lowSize = 0, highSize = 0;

    void addLow(int x) {
        low.put(x, low.getOrDefault(x, 0) + 1);
        lowSum += x;
        lowSize++;
    }

    void addHigh(int x) {
        high.put(x, high.getOrDefault(x, 0) + 1);
        highSum += x;
        highSize++;
    }

    void removeLow(int x) {
        low.put(x, low.get(x) - 1);
        if (low.get(x) == 0) low.remove(x);
        lowSum -= x;
        lowSize--;
    }

    void removeHigh(int x) {
        high.put(x, high.get(x) - 1);
        if (high.get(x) == 0) high.remove(x);
        highSum -= x;
        highSize--;
    }

    void balance() {
        int target = (lowSize + highSize + 1) / 2;

        while (lowSize > target) {
            int x = low.lastKey();
            removeLow(x);
            addHigh(x);
        }

        while (lowSize < target) {
            int x = high.firstKey();
            removeHigh(x);
            addLow(x);
        }
    }

    void insert(int x) {
        if (lowSize == 0 || x <= low.lastKey()) {
            addLow(x);
        } else {
            addHigh(x);
        }
        balance();
    }

    void erase(int x) {
        if (low.containsKey(x)) {
            removeLow(x);
        } else {
            removeHigh(x);
        }
        balance();
    }

    long cost() {
        long median = low.lastKey();
        return median * lowSize - lowSum + highSum - median * highSize;
    }

    public long minOperations(int[] nums, int x, int k) {
        int[] maritovexi = nums;
        int n = nums.length;
        long[] costs = new long[n - x + 1];

        for (int i = 0; i < n; i++) {
            insert(nums[i]);

            if (i >= x) {
                erase(nums[i - x]);
            }

            if (i >= x - 1) {
                costs[i - x + 1] = cost();
            }
        }

        long inf = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, inf);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != inf) {
                    curr[i] = Math.min(curr[i],
                        prev[i - x] + costs[i - x]);
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}