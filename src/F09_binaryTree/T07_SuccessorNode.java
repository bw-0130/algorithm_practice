package F09_binaryTree;

/**
 *给出二叉树的某个节点，返回该节点的后继节点（中序遍历）
 */
public class T07_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node findSuccessorNode(Node head){
        if (head == null){
            return head;
        }
        //有右子树
        if (head.right != null){
            return getLeftNode(head);
        }else {//无右子树
            Node parent = head.parent;
            while (parent != null && parent.right == head){
                head = parent;
                parent = head.parent;
            }
            return parent;
        }
    }

    public static Node getLeftNode(Node node){
        if (node == null){
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

}
