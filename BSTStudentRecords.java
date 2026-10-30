
import java.util.Scanner;

// Node class
class Node {
    int rollNo;
    String name;
    Node left, right;

    Node(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
        left = right = null;
    }
}

// Binary Search Tree class
class BinarySearchTree {

    Node root;

    // Insert student record
    Node insert(Node root, int rollNo, String name) {

        if (root == null) {
            return new Node(rollNo, name);
        }

        if (rollNo < root.rollNo) {
            root.left = insert(root.left, rollNo, name);
        } else if (rollNo > root.rollNo) {
            root.right = insert(root.right, rollNo, name);
        } else {
            System.out.println("Roll number already exists!");
        }

        return root;
    }

    // Search student record
    Node search(Node root, int rollNo) {

        if (root == null || root.rollNo == rollNo) {
            return root;
        }

        if (rollNo < root.rollNo) {
            return search(root.left, rollNo);
        }

        return search(root.right, rollNo);
    }

    // Find minimum node
    Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Delete student record
    Node delete(Node root, int rollNo) {

        if (root == null) {
            return null;
        }

        if (rollNo < root.rollNo) {
            root.left = delete(root.left, rollNo);
        } else if (rollNo > root.rollNo) {
            root.right = delete(root.right, rollNo);
        } else {

            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            Node temp = findMin(root.right);

            root.rollNo = temp.rollNo;
            root.name = temp.name;

            root.right = delete(root.right, temp.rollNo);
        }

        return root;
    }

    // Inorder traversal
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.rollNo + " - " + root.name);
            inorder(root.right);
        }
    }

    // Preorder traversal
    void preorder(Node root) {
        if (root != null) {
            System.out.println(root.rollNo + " - " + root.name);
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder traversal
    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.rollNo + " - " + root.name);
        }
    }
}

// Main class
public class BSTStudentRecords {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    bst.root = bst.insert(bst.root, roll, name);
                    System.out.println("Student inserted successfully!");
                    break;

                case 2:
                    System.out.print("Enter Roll Number to Search: ");
                    roll = sc.nextInt();

                    Node result = bst.search(bst.root, roll);

                    if (result != null) {
                        System.out.println("Student Found!");
                        System.out.println("Roll Number: " + result.rollNo);
                        System.out.println("Name: " + result.name);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to Delete: ");
                    roll = sc.nextInt();

                    if (bst.search(bst.root, roll) != null) {
                        bst.root = bst.delete(bst.root, roll);
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    System.out.println("\nInorder Traversal:");
                    bst.inorder(bst.root);
                    break;

                case 5:
                    System.out.println("\nPreorder Traversal:");
                    bst.preorder(bst.root);
                    break;

                case 6:
                    System.out.println("\nPostorder Traversal:");
                    bst.postorder(bst.root);
                    break;

                case 7:
                    System.out.println("Exiting Student Record System...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
