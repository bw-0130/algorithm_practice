package A_One.T09;

import F09_binaryTree.T18_maxHappy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一颗多叉树的头节点head，
 * 树中每个节点有快乐值，
 * 现在举办一场聚会邀请条件：直接上下级关系节点不能同时邀请。
 * 求最大快乐值
 */
public class C18 {

    public static class Node {
        public int value;
        public List<Node> next;

        public Node(int value) {
            this.value = value;
            next = new ArrayList<>();
        }
    }

    public static int jobOne(Node head) {
        if (head == null) {
            return 0;
        }
        return processOne(head, false);
    }

    public static int processOne(Node head, boolean flag) {
        if (flag) {
            int res = 0;
            for (Node node : head.next) {
                res += processOne(node, false);
            }
            return res;
        } else {
            int res1 = head.value;
            int res2 = 0;
            for (Node node : head.next) {
                res1 += processOne(node, true);
                res2 += processOne(node, false);
            }
            return Math.max(res1, res2);
        }
    }

    //二叉树递归条路解
    public static class Info {
        public int comeMaxHappy;
        public int unComeMaxHappy;

        public Info(int comeMaxHappy, int unComeMaxHappy) {
            this.comeMaxHappy = comeMaxHappy;
            this.unComeMaxHappy = unComeMaxHappy;
        }
    }

    public static int jobTwo(Node head) {
        if (head == null) {
            return 0;
        }
        Info info = processTwo(head);
        return Math.max(info.comeMaxHappy, info.unComeMaxHappy);
    }

    public static Info processTwo(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        int comeMaxHappy = head.value;
        int unComeMaxHappy = 0;
        for (Node node : head.next) {
            Info info = processTwo(node);
            comeMaxHappy += info.unComeMaxHappy;
            unComeMaxHappy += Math.max(info.comeMaxHappy, info.unComeMaxHappy);
        }
        return new Info(comeMaxHappy, unComeMaxHappy);
    }

    // for test
    public static Node genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Node boss = new Node((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Node e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Node next = new Node((int) (Math.random() * (maxHappy + 1)));
            e.next.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (jobOne(boss) != jobTwo(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
