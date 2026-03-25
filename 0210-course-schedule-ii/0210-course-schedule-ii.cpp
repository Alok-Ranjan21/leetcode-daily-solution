class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> adj(numCourses);
        vector<int> indegree(numCourses, 0);
        
        for(auto &p : prerequisites) {
            adj[p[1]].push_back(p[0]);
            indegree[p[0]]++;
        }
        
        queue<int> q;
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) q.push(i);
        }
        
        vector<int> order;
        
        while(!q.empty()) {
            int node = q.front(); q.pop();
            order.push_back(node);
            
            for(int nei : adj[node]) {
                if(--indegree[nei] == 0) {
                    q.push(nei);
                }
            }
        }
        
        if(order.size() == numCourses) return order;
        return {};
    }
};