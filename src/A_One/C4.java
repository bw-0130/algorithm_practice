package A_One;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 图的拓扑排序算法
 * 思路二：
 * 统计每个节点的深度，深度大的一定排在深度小的之前
 */
public class C4 {

    //图节点
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    //封装节点深度
    public static class Info {
        public DirectedGraphNode node;
        public int deep;

        public Info(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static Info processNodeDeep(DirectedGraphNode node, HashMap<DirectedGraphNode, Info> hashMap) {
        if (hashMap.containsKey(node)) {
            return hashMap.get(node);
        }
        int deep = 0;
        for (DirectedGraphNode node1 : node.neighbors){
            deep = Math.max(deep, processNodeDeep(node1, hashMap).deep);
        }
        Info info = new Info(node, deep+1);
        hashMap.put(node, info);
        return info;
    }

    public static class myComparator implements Comparator<Info>{

        @Override
        public int compare(Info o1, Info o2) {
            return o2.deep - o1.deep;
        }
    }

    public static List<DirectedGraphNode> job(List<DirectedGraphNode> list){
        HashMap<DirectedGraphNode, Info> hashMap = new HashMap<>();
        for (DirectedGraphNode node : list){
            processNodeDeep(node, hashMap);
        }
        List<Info> infoList = new ArrayList<>();
        for (Info info : hashMap.values()){
            infoList.add(info);
        }
        infoList.sort(new myComparator());
        List<DirectedGraphNode> res = new ArrayList<>();
        for (Info info : infoList){
            res.add(info.node);
        }
        return res;
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
