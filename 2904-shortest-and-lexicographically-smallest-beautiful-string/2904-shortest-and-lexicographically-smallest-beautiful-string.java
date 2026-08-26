class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int ones = 0;
        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            while (ones == k && left < right && s.charAt(left) == '0') {
                left++;
            }

            if (ones == k) {
                int len = right - left + 1;
                String current = s.substring(left, right + 1);

                if (len < minLen || (len == minLen && current.compareTo(ans) < 0)) {
                    minLen = len;
                    ans = current;
                }
            }
        }

        return ans;
    }
}