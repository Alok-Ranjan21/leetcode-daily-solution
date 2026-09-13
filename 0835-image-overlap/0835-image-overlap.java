class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        int ans = 0;

        for (int i = 0; i < img1.length; i++) {
            for (int j = 0; j < img1.length; j++) {
                if (img1[i][j] == 1) {
                    for (int x = 0; x < img2.length; x++) {
                        for (int y = 0; y < img2.length; y++) {
                            if (img2[x][y] == 1) {
                                String key = (i - x) + "," + (j - y);
                                int count = map.getOrDefault(key, 0) + 1;
                                map.put(key, count);
                                ans = Math.max(ans, count);
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}