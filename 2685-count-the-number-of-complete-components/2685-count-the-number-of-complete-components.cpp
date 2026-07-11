class Solution {
public:
    void dfs(int node, vector<vector<int>>& graph, vector<bool>& vis, vector<int>& comp) {
        vis[node] = true;
        comp.push_back(node);

        for (int nei : graph[node]) {
            if (!vis[nei]) {
                dfs(nei, graph, vis, comp);
            }
        }
    }

    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        vector<vector<int>> graph(n);

        for (auto &e : edges) {
            graph[e[0]].push_back(e[1]);
            graph[e[1]].push_back(e[0]);
        }

        vector<bool> vis(n, false);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vector<int> comp;
                dfs(i, graph, vis, comp);

                int sz = comp.size();
                int degreeSum = 0;

                for (int node : comp)
                    degreeSum += graph[node].size();

                if (degreeSum == sz * (sz - 1))
                    ans++;
            }
        }

        return ans;
    }
};