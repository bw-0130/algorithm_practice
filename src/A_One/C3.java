package A_One;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 思路一
 * 1、在图中找到所有入度为0的点输出
 * 2、把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始。
 * 3、图的所有点都被删除后，依次输出的顺序就是拓扑排序
 */
public class C3 {

    //图节点
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static List<DirectedGraphNode> job(List<DirectedGraphNode> list) {
        HashMap<DirectedGraphNode, Integer> hashMap = new HashMap<>();
        for (DirectedGraphNode node : list) {
            hashMap.put(node, 0);
        }
        //统计入度
        for (DirectedGraphNode node : list) {
            for (DirectedGraphNode node1 : node.neighbors) {
                if (hashMap.containsKey(node1)) {
                    hashMap.put(node1, hashMap.get(node1) + 1);
                }
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : hashMap.keySet()){
            if (hashMap.get(node) == 0){
                queue.offer(node);
            }
        }
        List<DirectedGraphNode> resList = new ArrayList<>();
        while (!queue.isEmpty()){
            DirectedGraphNode pollNode = queue.poll();
            resList.add(pollNode);
            for (DirectedGraphNode node : pollNode.neighbors){
                hashMap.put(node, hashMap.get(node)-1);
                if (hashMap.get(node) == 0){
                    queue.offer(node);
                }
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        DirectedGraphNode node1 = new DirectedGraphNode(1);
        DirectedGraphNode node2 = new DirectedGraphNode(2);
        DirectedGraphNode node3 = new DirectedGraphNode(3);
        DirectedGraphNode node4 = new DirectedGraphNode(4);
        DirectedGraphNode node5 = new DirectedGraphNode(5);
        DirectedGraphNode node6 = new DirectedGraphNode(6);

        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node1.neighbors.add(node4);
        node2.neighbors.add(node5);
        node3.neighbors.add(node5);
        node3.neighbors.add(node6);
        node4.neighbors.add(node5);
        node4.neighbors.add(node6);
        ArrayList<DirectedGraphNode> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        List<DirectedGraphNode> result = job(list);
        for (DirectedGraphNode directedGraphNode : result){
            System.out.print(directedGraphNode.label+" ");
        }
    }

}
