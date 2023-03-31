
// Searching a key on a B-tree in Java 

public class BTree {

    private int T;
  
    // Node creation
    public class Node {
      int n;
      int key[] = new int[2 * T - 1];
      Node child[] = new Node[2 * T];
      boolean leaf = true;
  
      public int Find(int k) {
        for (int i = 0; i < this.n; i++) {
          if (this.key[i] == k) {
            return i;
          }
        }
        return -1;
      };
    }
  
    public BTree(int t) {
      T = t;
      root = new Node();
      root.n = 0;
      root.leaf = true;
    }
  
    private Node root;
  
    // Search key
    private Node Search(Node node, int key) {
      int i = 0;
      if (node == null)
        return node;
      for (i = 0; i < node.n; i++) {
        if (key < node.key[i]) {
          break;
        }
        if (key == node.key[i]) {
          return node;
        }
      }
      if (node.leaf) {
        return null;
      } else {
        return Search(node.child[i], key);
      }
    }
  
    // Splitting the node
    private void Split(Node nodeX, int pos, Node nodeY) {
      Node nodeZ = new Node();
      nodeZ.leaf = nodeY.leaf;
      nodeZ.n = T - 1;
      for (int j = 0; j < T - 1; j++) {
        nodeZ.key[j] = nodeY.key[j + T];
      }
      if (!nodeY.leaf) {
        for (int j = 0; j < T; j++) {
          nodeZ.child[j] = nodeY.child[j + T];
        }
      }
      nodeY.n = T - 1;
      for (int j = nodeX.n; j >= pos + 1; j--) {
        nodeX.child[j + 1] = nodeX.child[j];
      }
      nodeX.child[pos + 1] = nodeZ;
  
      for (int j = nodeX.n - 1; j >= pos; j--) {
        nodeX.key[j + 1] = nodeX.key[j];
      }
      nodeX.key[pos] = nodeY.key[T - 1];
      nodeX.n = nodeX.n + 1;
    }
  
    // Inserting a value
    public void Insert(final int key) {
      Node r = root;
      if (r.n == 2 * T - 1) {
        Node s = new Node();
        root = s;
        s.leaf = false;
        s.n = 0;
        s.child[0] = r;
        Split(s, 0, r);
        insertValue(s, key);
      } else {
        insertValue(r, key);
      }
    }
  
    // Insert the node
    final private void insertValue(Node node, int k) {

      if (node.leaf) {
        int i = 0;
        for (i = node.n - 1; i >= 0 && k < node.key[i]; i--) {
          node.key[i + 1] = node.key[i];
        }
        node.key[i + 1] = k;
        node.n = node.n + 1;
      } else {
        int i = 0;
        for (i = node.n - 1; i >= 0 && k < node.key[i]; i--) {
        };
        i++;
        Node tmp = node.child[i];
        if (tmp.n == 2 * T - 1) {
          Split(node, i, tmp);
          if (k > node.key[i]) {
            i++;
          }
        }
        insertValue(node.child[i], k);
      }

    }
  
    public void Show() {
      Show(root);
    }
  
    // Display
    private void Show(Node node) {
      assert (node == null);
      for (int i = 0; i < node.n; i++) {
        System.out.print(node.key[i] + " ");
      }
      if (!node.leaf) {
        System.out.println("");
        for (int i = 0; i < node.n + 1; i++) {
          Show(node.child[i]);
          System.out.print(" - ");
        }
      }
    }
  
    // Check if present
    public boolean Contain(int k) {
      if (this.Search(root, k) != null) {
        return true;
      } else {
        return false;
      }
    }
  
    public static void main(String[] args) {
      BTree b = new BTree(3);
      b.Insert(8);
      b.Insert(9);
      b.Insert(10);
      b.Insert(11);
      b.Insert(15);
      b.Insert(20);
      b.Insert(17);
      b.Insert(5);
      b.Insert(3);
      b.Insert(13);
      b.Insert(21);
      b.Insert(22);
      b.Show();
  
      if (b.Contain(12)) {
        System.out.println("\nfound");
      } else {
        System.out.println("\nnot found");
      }
      ;
    }
  }