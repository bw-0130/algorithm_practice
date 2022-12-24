package F10_graph;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 思路一
 * 1、在图中找到所有入度为0的点输出
 * 2、把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始。
 * 3、图的所有点都被删除后，依次输出的顺序就是拓扑排序
 */
public class C03_TopologicalOrderBFS {

    //图节点
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static List<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> list){
        HashMap<DirectedGraphNode, Integer> hashMap = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : list){
            hashMap.put(directedGraphNode, 0);
        }
        for (DirectedGraphNode directedGraphNode : list){
            for (DirectedGraphNode directedGraphNode1 : directedGraphNode.neighbors){
                if (hashMap.containsKey(directedGraphNode1)){
                    hashMap.put(directedGraphNode1, hashMap.get(directedGraphNode1)+1);
                }
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode directedGraphNode : hashMap.keySet()){
            if (hashMap.get(directedGraphNode)==0){
                queue.add(directedGraphNode);
            }
        }
        List<DirectedGraphNode> resList = new ArrayList<>();
        while (!queue.isEmpty()){
            DirectedGraphNode pollNode = queue.poll();
            resList.add(pollNode);
            for (DirectedGraphNode directedGraphNode : pollNode.neighbors){
                hashMap.put(directedGraphNode, hashMap.get(directedGraphNode)-1);
                if (hashMap.get(directedGraphNode) == 0){
                    queue.offer(directedGraphNode);
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
        List<DirectedGraphNode> result = topSort(list);
        for (DirectedGraphNode directedGraphNode : result){
            System.out.print(directedGraphNode.label+" ");
        }
    }


}
