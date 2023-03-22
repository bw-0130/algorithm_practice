package A_One.T09;

import F09_binaryTree.T06_treeMaxWidth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点（按层遍历）
 */
public class C6 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int jobOne(Node head){
        if (head == null){
            return 0;
        }
        return process(head);
    }

    public static int process(Node head){
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> hashMap = new HashMap<>();
        hashMap.put(head, 1);//当前节点所处层数
        int maxNum = 0;//最宽层节点数
        int levelNum = 1;//当前层
        int nodeNum = 0;//层节点数
        queue.offer(head);
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            Integer curLevelNum = hashMap.get(pollNode);
            if (pollNode.left!= null){
                queue.offer(pollNode.left);
                hashMap.put(pollNode.left,curLevelNum+1);
            }
            if (pollNode.right != null){
                queue.offer(pollNode.right);
                hashMap.put(pollNode.right, curLevelNum+1);
            }
            if (curLevelNum == levelNum){
                nodeNum++;
            }else {
                maxNum = Math.max(maxNum, nodeNum);
                levelNum++;
                nodeNum = 1;
            }
        }
        maxNum = Math.max(maxNum, nodeNum);//最后一次循环后的统计
        return maxNum;
    }

    public static int jobTwo(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node curEndNode = head;//当前层最后一个节点
        Node nextEndNode = null; //下一层最后一个节点
        int maxNum = 0;//最大节点数量
        int levelNum = 0;//节点数量
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            if (pollNode.left != null){
                queue.offer(pollNode.left);
                nextEndNode = pollNode.left;
            }
            if (pollNode.right != null){
                queue.offer(pollNode.right);
                nextEndNode = pollNode.right;
            }
            levelNum++;
            if (pollNode == curEndNode){
                maxNum = Math.max(maxNum, levelNum);
                curEndNode = nextEndNode;
                levelNum = 0;//还原现场
            }
        }
        return maxNum;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        System.out.println(jobOne(head));
        System.out.println(jobTwo(head));
    }

}
