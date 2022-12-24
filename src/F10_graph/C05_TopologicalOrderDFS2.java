package F10_graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 图的拓扑排序算法
 * 思路三：
 * 统计每个节点后的节点数量，数量多的节点一定排在数量少节点前
 */
public class C05_TopologicalOrderDFS2 {
    //图节点
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    //每个节点后继节点数封装
    public static class Info{
        public DirectedGraphNode directedGraphNode;
        public int nodeNum;

        public Info(DirectedGraphNode directedGraphNode, int nodeNum) {
            this.directedGraphNode = directedGraphNode;
            this.nodeNum = nodeNum;
        }
    }
    //自定义比较器
    public static class myComparator implements Comparator<Info>{
        @Override
        public int compare(Info o1, Info o2) {
            return o2.nodeNum-o1.nodeNum;
        }
    }
    //拓扑排序
    public static List<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> list){
        HashMap<DirectedGraphNode, Info> hashMap = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : list){
            processNodeNum(directedGraphNode, hashMap);
        }
        List<Info> infoList = new ArrayList<>();
        for (Info info : hashMap.values()){
            infoList.add(info);
        }
        infoList.sort(new myComparator());
        List<DirectedGraphNode> resList = new ArrayList<>();
        for (Info info : infoList){
            resList.add(info.directedGraphNode);
        }
        return resList;
    }
    //递归计算节点后继节点数量
    public static Info processNodeNum(DirectedGraphNode directedGraphNode, HashMap<DirectedGraphNode, Info> hashMap){
        if (hashMap.containsKey(directedGraphNode)) {
            return hashMap.get(directedGraphNode);
        }
        int nodeNum = 0;
        for (DirectedGraphNode node : directedGraphNode.neighbors){
            nodeNum += processNodeNum(node, hashMap).nodeNum;
        }
        Info info = new Info(directedGraphNode, nodeNum+1);
        hashMap.put(directedGraphNode, info);
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
