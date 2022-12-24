package F09_binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一颗多叉树的头节点head，树中每个节点有快乐值，现在举办一场聚会邀请条件：直接上下级关系节点不能同时邀请。求最大快乐值
 */
public class T18_maxHappy {

    public static class Node {
        public int happy;
        public List<Node> nexts;

        public Node(int happy) {
            this.happy = happy;
            nexts = new ArrayList<>();
        }
    }

    //递归方法
    public static int jobOne(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head, false);
    }

    /**
     * @param node 当前节点
     * @param bo   上一个节点是否来
     * @return
     */
    public static int process(Node node, boolean bo) {
        if (bo) {//上级来
            int res = 0;
            for (Node data : node.nexts) {
                res += process(data, false);
            }
            return res;
        } else {//上级不来
            int p1 = node.happy;//当前节点来
            int p2 = 0;//当前节点不来
            for (Node data : node.nexts) {
                p1 += process(data, true);
                p2 += process(data, false);
            }
            return Math.max(p1, p2);
        }
    }

    //二叉树递归套路方法
    public static int jobTwo(Node node) {
        if (node == null) {
            return 0;
        }
        Info info = process2(node);
        return Math.max(info.comeHappy, info.unComeHappy);
    }

    public static class Info {
        public int comeHappy;//头节点来最大快乐值
        public int unComeHappy;//头结点不来最大快乐值

        public Info(int comeHappy, int unComeHappy) {
            this.comeHappy = comeHappy;
            this.unComeHappy = unComeHappy;
        }
    }

    public static Info process2(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        int comeHappy = node.happy;//头节点来最大快乐值
        int unComeHappy = 0;//头结点不来最大快乐值
        for (Node data : node.nexts) {
            Info info = process2(data);
            comeHappy += info.unComeHappy;
            unComeHappy += Math.max(info.comeHappy, info.unComeHappy);
        }
        return new Info(comeHappy, unComeHappy);
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
            e.nexts.add(next);
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
