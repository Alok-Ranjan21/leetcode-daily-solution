class Solution {
public:
    int sumNumbers(TreeNode* root) {
        return dfs(root, 0);
    }
    
    int dfs(TreeNode* node, int current) {
        if (node == NULL) return 0;
        
        current = current * 10 + node->val;
        
        
        if (node->left == NULL && node->right == NULL) {
            return current;
        }
        
        return dfs(node->left, current) + dfs(node->right, current);
    }
};