class Solution {
public:
    vector<vector<int>> res;
    
    void solve(int start, int k, int target, vector<int>& temp) {
        if(target == 0 && temp.size() == k) {
            res.push_back(temp);
            return;
        }
        
        if(target < 0 || temp.size() > k) return;
        
        for(int i = start; i <= 9; i++) {
            temp.push_back(i);
            solve(i + 1, k, target - i, temp);
            temp.pop_back();
        }
    }
    
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> temp;
        solve(1, k, n, temp);
        return res;
    }
};