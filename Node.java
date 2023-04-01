public class Node {

  int n;
  int key[] = new int[2 * BTree.T - 1];
  Node child[] = new Node[2 * BTree.T];
  boolean leaf = true;

  public int Find(int k) {
    for (int i = 0; i < this.n; i++) {
      if (this.key[i] == k) {
        return i;
      }
    }
    return -1;
  };

  public String toString() {
    String str = "";
    for (int i = 0; i < this.n; i++) {
      System.out.print(this.key[i] + " ");
      str = this.key[i] + " ";
    }
    return str;
  }
}
