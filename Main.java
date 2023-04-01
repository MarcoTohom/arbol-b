import java.util.Scanner;

public class Main {
  static BTree bTree;
  static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    int t = 0;
    String option = null;
    do{
      System.out.println("Ingrese el grado del árbol (mayor que dos):");
      t = sc.nextInt();
      bTree = new BTree(t);
    }while(t<=2);
    do{
      System.out.println("\n1. Inserción de una clave");
      System.out.println("2. Eliminación de una clave");
      System.out.println("3. Búsqueda");
      System.out.println("0. Salir");
      Scanner scan = new Scanner(System.in);
      option = scan.nextLine();
      switch(option.charAt(0)){
        case '1': insert(); break;
        case '2': delete(); break;
        case '3': search(); break;
        case '0': option = null;
        default: System.out.println("Opción inválida");
      }
    }while(option!=null);

  }

  public static void insert(){
    System.out.println("Ingrese la clave:");
    int key = sc.nextInt();
    bTree.Insert(key);
    bTree.Show();
  }
  public static void delete(){
    System.out.println("Ingrese la clave:");
    int key = sc.nextInt();
    bTree.Remove(key);
    bTree.Show();
  }
  public static void search(){
    System.out.println("Ingrese la clave:");
    int key = sc.nextInt();
    Node node = bTree.Search(bTree.getRoot(), key);
    System.out.println(node);
  }
}