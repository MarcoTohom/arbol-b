public class Main{
    public static void main(String[] args) {
    
        BTree bTree = new BTree(3);
        bTree.Insert(8);
        bTree.Insert(9);
        bTree.Insert(10);
        bTree.Insert(11);
        bTree.Insert(15);
        bTree.Insert(20);
        bTree.Insert(17);
        bTree.Insert(5);
        bTree.Insert(3);
        bTree.Insert(13);
        bTree.Insert(21);
        bTree.Insert(22);
        bTree.Show();
    
        if (bTree.Contain(12)) {
          System.out.println("\nfound");
        } else {
          System.out.println("\nnot found");
        }
        ;
    }
}