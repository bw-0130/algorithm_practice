package F09_binaryTree;

/**
 * 二叉树递归方式遍历
 */
public class T01_recursiveTraversal {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    //前序遍历
    public static void preTraversal(Node head){
        if (head!=null){
            System.out.print(head.value+" ");
            preTraversal(head.left);
            preTraversal(head.right);
        }
    }
    //中序遍历
    public static void midTraversal(Node head){
        if (head == null){
            return;
        }
        midTraversal(head.left);
        System.out.print(head.value+" ");
        midTraversal(head.right);
    }
    //后序遍历
    public static void endTraversal(Node head){
        if (head == null){
            return;
        }
        endTraversal(head.left);
        endTraversal(head.right);
        System.out.print(head.value+" ");
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);

        preTraversal(head);
        System.out.println();
        midTraversal(head);
        System.out.println();
        endTraversal(head);
    }

}
