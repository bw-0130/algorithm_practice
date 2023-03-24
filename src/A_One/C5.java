package A_One;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 图的拓扑排序算法
 * 思路三：
 * 统计每个节点后的节点数量，数量多的节点一定排在数量少节点前
 */
public class C5 {

    //图节点
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Info {
        public DirectedGraphNode node;
        public int NodeNum;

        public Info(DirectedGraphNode node, int nodeNum) {
            this.node = node;
            NodeNum = nodeNum;
        }
    }

    public static class myComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o2.NodeNum - o1.NodeNum;
        }
    }
    
    public static Info process(DirectedGraphNode node, HashMap<DirectedGraphNode, Info> hashMap){
        if (hashMap.containsKey(node)){
            return hashMap.get(node);
        }
        int nodeNum = 0;
        for (DirectedGraphNode node1 : node.neighbors){
            nodeNum += process(node1, hashMap).NodeNum;
        }
        Info info = new Info(node, nodeNum+1);
        hashMap.put(node, info);
        return info;
    }
    
    public static List<DirectedGraphNode> job(List<DirectedGraphNode> data){
        HashMap<DirectedGraphNode, Info> hashMap = new HashMap<>();
        for (DirectedGraphNode node : data){
            process(node, hashMap);
        }
        List<Info> infos = new ArrayList<>();
        for (Info info : hashMap.values()){
            infos.add(info);
        }
        infos.sort(new myComparator());
        List<DirectedGraphNode> ress = new ArrayList<>();
        for (Info info : infos){
            ress.add(info.node);
        }
        return ress;
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
