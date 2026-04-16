import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();

        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);

            if (list.size() == 1) {
                res.add(-1);
                continue;
            }

            int idx = Collections.binarySearch(list, q);
            int m = list.size();

            int left = list.get((idx - 1 + m) % m);
            int right = list.get((idx + 1) % m);

            int distLeft = Math.abs(q - left);
            int distRight = Math.abs(q - right);

            distLeft = Math.min(distLeft, n - distLeft);
            distRight = Math.min(distRight, n - distRight);

            res.add(Math.min(distLeft, distRight));
        }

        return res;
    }
}