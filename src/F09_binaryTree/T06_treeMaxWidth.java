package F09_binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点（按层遍历）
 */
public class T06_treeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int job(Node head){
        if (head == null){
            return 0;
        }
        return process(head);
    }
    public static int process(Node head){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        HashMap<Node, Integer> keyHash = new HashMap<>();
        keyHash.put(head, 1);
        int maxWidth = 0;//最宽层节点数
        int levelIndex = 1;//当前层数
        int levelNum = 0;//当前层节点数
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            Integer curLevelNum = keyHash.get(pollNode);
            if (pollNode.left != null){
                queue.offer(pollNode.left);
                keyHash.put(pollNode.left, curLevelNum + 1);
            }
            if (pollNode.right != null){
                queue.offer(pollNode.right);
                keyHash.put(pollNode.right, curLevelNum + 1);
            }
            if (curLevelNum == levelIndex){
                levelNum++;
            }else {
                maxWidth = Math.max(maxWidth, levelNum);
                levelIndex++;
                levelNum = 1;//由于当次循环没有计数所以从1开始
            }
        }
        maxWidth = Math.max(maxWidth, levelNum);//最后一层数量和之前最宽层数对比
        return maxWidth;
    }

    public static int jobTwo(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEndNode = head;//当前层最后节点
        Node nextEndNode = null;//下一层最后节点
        int maxWidth = 0;//最宽层节点数
        int levelNum = 0;//当前层节点数
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            if (pollNode.left != null){
                queue.add(pollNode.left);
                nextEndNode = pollNode.left;
            }
            if (pollNode.right != null){
                queue.add(pollNode.right);
                nextEndNode = pollNode.right;
            }
            levelNum++;
            if (pollNode == curEndNode){
                maxWidth = Math.max(maxWidth, levelNum);
                levelNum = 0;
                curEndNode = nextEndNode;
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        System.out.println(job(head));
        System.out.println(jobTwo(head));
    }

}
