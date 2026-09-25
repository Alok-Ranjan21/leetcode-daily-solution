import java.util.*;

class Solution {
    private TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(set);
    }

    private void dfs(String exp) {
        int j = exp.indexOf('}');

        if (j == -1) {
            set.add(exp);
            return;
        }

        int i = exp.lastIndexOf('{', j);

        String left = exp.substring(0, i);
        String right = exp.substring(j + 1);

        String[] parts = exp.substring(i + 1, j).split(",");

        for (String part : parts) {
            dfs(left + part + right);
        }
    }
}