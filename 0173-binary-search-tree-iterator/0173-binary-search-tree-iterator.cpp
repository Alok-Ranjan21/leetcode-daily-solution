class BSTIterator {
public:
    stack<TreeNode*> st;
    
    BSTIterator(TreeNode* root) {
        pushLeft(root);
    }
    
    void pushLeft(TreeNode* node) {
        while(node) {
            st.push(node);
            node = node->left;
        }
    }
    
    int next() {
        TreeNode* node = st.top();
        st.pop();
        
        if(node->right) pushLeft(node->right);
        
        return node->val;
    }
    
    bool hasNext() {
        return !st.empty();
    }
};