package A_One.T09;

/**
 * 给出二叉树的某个节点，返回该节点的后继节点（中序遍历顺序）
 */
public class C7 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;//父节点

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node job(Node head){
        if (head == null){
            return null;
        }
        if (head.right != null){
            return findLeftNode(head);
        }else {
            Node parent = head.parent;
            while (head != null && parent.right == head){
                head = parent;
                parent = head.parent;
            }
            return parent;
        }
    }

    public static Node findLeftNode(Node head){
        if (head == null){
            return null;
        }
        while (head.left != null){
            head = head.left;
        }
        return head;
    }

}
