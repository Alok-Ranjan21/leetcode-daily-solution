class Trie {
public:
    struct Node {
        Node* links[26];
        bool isEnd = false;
    };
    
    Node* root;
    
    Trie() {
        root = new Node();
    }
    
    void insert(string word) {
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
        Node* node = root;
        for(char c : word) {
            int idx = c - 'a';
            if(!node->links[idx]) return false;
            node = node->links[idx];
        }
        return node->isEnd;
    }
    
    bool startsWith(string prefix) {
        Node* node = root;
        for(char c : prefix) {
            int idx = c - 'a';
            if(!node->links[idx]) return false;
            node = node->links[idx];
        }
        return true;
    }
};