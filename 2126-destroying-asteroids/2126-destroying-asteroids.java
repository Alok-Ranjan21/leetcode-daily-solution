class Solution {
    
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        
        Arrays.sort(asteroids);

        long curr = mass;

        for (int a : asteroids) {

            if (curr < a) {
                return false;
            }

            curr += a;
        }

        return true;
    }
}