
// Searching a key on a B-tree in Java 

public class BTree {

  public static int T;
  private Node root;

  public BTree(int t) {
    T = t;
    root = new Node();
    root.n = 0;
    root.leaf = true;
  }

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
      }
      ;
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
    System.out.println();
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

  // Delete
  private void Remove(Node node, int key) {
    int pos = node.Find(key);
    if (pos != -1) {
      if (node.leaf) {
        int i = 0;
        for (i = 0; i < node.n && node.key[i] != key; i++) {
        }
        ;
        for (; i < node.n; i++) {
          if (i != 2 * T - 2) {
            node.key[i] = node.key[i + 1];
          }
        }
        node.n--;
        return;
      }
      if (!node.leaf) {

        Node pred = node.child[pos];
        int predKey = 0;
        if (pred.n >= T) {
          for (;;) {
            if (pred.leaf) {
              System.out.println(pred.n);
              predKey = pred.key[pred.n - 1];
              break;
            } else {
              pred = pred.child[pred.n];
            }
          }
          Remove(pred, predKey);
          node.key[pos] = predKey;
          return;
        }

        Node nextNode = node.child[pos + 1];
        if (nextNode.n >= T) {
          int nextKey = nextNode.key[0];
          if (!nextNode.leaf) {
            nextNode = nextNode.child[0];
            for (;;) {
              if (nextNode.leaf) {
                nextKey = nextNode.key[nextNode.n - 1];
                break;
              } else {
                nextNode = nextNode.child[nextNode.n];
              }
            }
          }
          Remove(nextNode, nextKey);
          node.key[pos] = nextKey;
          return;
        }

        int temp = pred.n + 1;
        pred.key[pred.n++] = node.key[pos];
        for (int i = 0, j = pred.n; i < nextNode.n; i++) {
          pred.key[j++] = nextNode.key[i];
          pred.n++;
        }
        for (int i = 0; i < nextNode.n + 1; i++) {
          pred.child[temp++] = nextNode.child[i];
        }

        node.child[pos] = pred;
        for (int i = pos; i < node.n; i++) {
          if (i != 2 * T - 2) {
            node.key[i] = node.key[i + 1];
          }
        }
        for (int i = pos + 1; i < node.n + 1; i++) {
          if (i != 2 * T - 1) {
            node.child[i] = node.child[i + 1];
          }
        }
        node.n--;
        if (node.n == 0) {
          if (node == root) {
            root = node.child[0];
          }
          node = node.child[0];
        }
        Remove(pred, key);
        return;
      }
    } else {
      for (pos = 0; pos < node.n; pos++) {
        if (node.key[pos] > key) {
          break;
        }
      }
      Node tmp = node.child[pos];
      if (tmp.n >= T) {
        Remove(tmp, key);
        return;
      }
      if (true) {
        Node nb = null;
        int devider = -1;

        if (pos != node.n && node.child[pos + 1].n >= T) {
          devider = node.key[pos];
          nb = node.child[pos + 1];
          node.key[pos] = nb.key[0];
          tmp.key[tmp.n++] = devider;
          tmp.child[tmp.n] = nb.child[0];
          for (int i = 1; i < nb.n; i++) {
            nb.key[i - 1] = nb.key[i];
          }
          for (int i = 1; i <= nb.n; i++) {
            nb.child[i - 1] = nb.child[i];
          }
          nb.n--;
          Remove(tmp, key);
          return;
        } else if (pos != 0 && node.child[pos - 1].n >= T) {

          devider = node.key[pos - 1];
          nb = node.child[pos - 1];
          node.key[pos - 1] = nb.key[nb.n - 1];
          Node child = nb.child[nb.n];
          nb.n--;

          for (int i = tmp.n; i > 0; i--) {
            tmp.key[i] = tmp.key[i - 1];
          }
          tmp.key[0] = devider;
          for (int i = tmp.n + 1; i > 0; i--) {
            tmp.child[i] = tmp.child[i - 1];
          }
          tmp.child[0] = child;
          tmp.n++;
          Remove(tmp, key);
          return;
        } else {
          Node lt = null;
          Node rt = null;
          boolean last = false;
          if (pos != node.n) {
            devider = node.key[pos];
            lt = node.child[pos];
            rt = node.child[pos + 1];
          } else {
            devider = node.key[pos - 1];
            rt = node.child[pos];
            lt = node.child[pos - 1];
            last = true;
            pos--;
          }
          for (int i = pos; i < node.n - 1; i++) {
            node.key[i] = node.key[i + 1];
          }
          for (int i = pos + 1; i < node.n; i++) {
            node.child[i] = node.child[i + 1];
          }
          node.n--;
          lt.key[lt.n++] = devider;

          for (int i = 0, j = lt.n; i < rt.n + 1; i++, j++) {
            if (i < rt.n) {
              lt.key[j] = rt.key[i];
            }
            lt.child[j] = rt.child[i];
          }
          lt.n += rt.n;
          if (node.n == 0) {
            if (node == root) {
              root = node.child[0];
            }
            node = node.child[0];
          }
          Remove(lt, key);
          return;
        }
      }
    }
  }

  public void Remove(int key) {
    Node x = Search(root, key);
    if (x == null) {
      return;
    }
    Remove(root, key);
  }

  // Check if present
  public boolean Contain(int k) {
    if (this.Search(root, k) != null) {
      return true;
    } else {
      return false;
    }
  }

}