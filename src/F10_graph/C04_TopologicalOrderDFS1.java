package F10_graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 图的拓扑排序算法
 * 思路二：
 * 统计每个节点的深度，深度大的一定排在深度小的之前
 */
public class C04_TopologicalOrderDFS1 {

    //图节点
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    //节点对应深度封装
    public static class Info {
        public DirectedGraphNode directedGraphNode;
        public int deep;

        public Info(DirectedGraphNode directedGraphNode, int deep) {
            this.directedGraphNode = directedGraphNode;
            this.deep = deep;
        }
    }

    //自定义比较器
    public static class myComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.deep - o1.deep;
        }
    }

    //拓扑排序
    public static List<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> list) {
        HashMap<DirectedGraphNode, Info> hashMap = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : list) {
            processNodeDeep(directedGraphNode, hashMap);
        }
        List<Info> infoList = new ArrayList<>();
        for (Info info : hashMap.values()){
            infoList.add(info);
        }
        infoList.sort(new myComparator());
        List<DirectedGraphNode> resList = new ArrayList<>();
        for (Info info: infoList){
            resList.add(info.directedGraphNode);
        }
        return resList;
    }

    //递归获取每个节点深度
    public static Info processNodeDeep(DirectedGraphNode directedGraphNode, HashMap<DirectedGraphNode, Info> hashMap) {
        //计算过深度直接返回之前计算的结果
        if (hashMap.containsKey(directedGraphNode)){
            return hashMap.get(directedGraphNode);
        }
        //没有计算过
        int deep = 0;
        for (DirectedGraphNode node : directedGraphNode.neighbors){
            deep = Math.max(deep, processNodeDeep(node, hashMap).deep);
        }
        Info info = new Info(directedGraphNode, deep+1);
        hashMap.put(directedGraphNode,info);
        return info;
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
