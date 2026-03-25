class WordDictionary {
public:
    struct Node {
        Node* links[26];
        bool isEnd = false;
    };
    
    Node* root;
    
    WordDictionary() {
        root = new Node();
    }
    
    void addWord(string word) {
        Node* node = root;
        for(char c : word) {
            int idx = c - 'a';
            if(!node->links[idx]) {
                node->links[idx] = new Node();
            }
            node = node->links[idx];
        }
        node->isEnd = true;
    }
    
    bool search(string word) {
        return dfs(word, 0, root);
    }
    
    bool dfs(string& word, int i, Node* node) {
        if(!node) return false;
        
        if(i == word.size()) return node->isEnd;
        
        if(word[i] == '.') {
            for(int k = 0; k < 26; k++) {
                if(dfs(word, i+1, node->links[k])) return true;
            }
            return false;
        } else {
            int idx = word[i] - 'a';
            return dfs(word, i+1, node->links[idx]);
        }
    }
};