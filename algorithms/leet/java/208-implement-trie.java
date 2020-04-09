class TrieNode {
  boolean isEndOfWord;
  TrieNode[] children;

  public TrieNode() {
    this.isEndOfWord = false;
    this.children = new TrieNode[26];
  }

  public int getIndex(char ch) {
    return ch - 'a';
  }

  public boolean hasChild(char ch) {
    int index = this.getIndex(ch);
    if (children[index] != null) {
      return true;
    } else {
      return false;
    }
  }

  public TrieNode getChildren(char c) {
    int index = this.getIndex(c);
    return children[index];
  }

  public void insert(char c) {
    int index = this.getIndex(c);
    children[index] = new TrieNode();
  }
}

class Trie {

  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode current = root;
    for (char c : word.toCharArray()) {
      if (current.hasChild(c) == false) {
        current.insert(c);
      }
      current = current.getChildren(c);
    }
    current.isEndOfWord = true;
  }

  public boolean search(String word) {
    TrieNode current = root;
    for (char c : word.toCharArray()) {
      if (current.hasChild(c)) {
        current = current.getChildren(c);
      } else {
        return false;
      }
    }
    return current.isEndOfWord;
  }

  public boolean startsWith(String prefix) {
    TrieNode current = root;
    for (char c : prefix.toCharArray()) {
      if (current.hasChild(c)) {
        current = current.getChildren(c);
      } else {
        return false;
      }
    }
    return current.isEndOfWord;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */