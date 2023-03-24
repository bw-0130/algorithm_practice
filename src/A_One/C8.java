package A_One;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 迪杰特斯拉算法
 * 从一个顶点到其余各顶点的最短路径算法，解决的是有权图中最短路径问题
 * 思路：
 * 从任意节点出发，节点到节点本身距离为0，先加入结果集
 * 根据结果集中距离选出权重最小的节点，排除已标记的节点
 * 循环最小节点后继节点，判断是否之前选出过如果选出过增加结果集中距离，没选出过则加入结果集
 * 把最小距离节点加入到已选出节点集合中
 * 再次从结果集中距离选出权重最小的节点，周而复始直到所有节点都被选出过为止
 */
public class C8 {

    public static HashMap<Node, Integer> job(Node start) {
        HashMap<Node, Integer> res = new HashMap<>();
        HashSet<Node> set = new HashSet<>();
        res.put(start, 0);
        Node minNode = getMinNode(res, set);
        while (minNode != null) {
            Integer num = res.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (!set.contains(to)) {
                    res.put(to, num + edge.weight);
                }else {
                    res.put(to, Math.min(res.get(to), num + edge.weight));
                }
            }
            set.add(minNode);
            minNode = getMinNode(res, set);
        }
        return res;
    }

    public static Node getMinNode(HashMap<Node, Integer> res, HashSet<Node> set) {
        Node node = null;
        int num = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> data : res.entrySet()) {
            Node node1 = data.getKey();
            Integer len = data.getValue();
            if (!set.contains(node1) && len < num) {
                node = node1;
                num = len;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][3];
        matrix[0][0] = 5;
        matrix[0][1] = 1;
        matrix[0][2] = 2;
        matrix[1][0] = 2;
        matrix[1][1] = 1;
        matrix[1][2] = 3;
        matrix[2][0] = 3;
        matrix[2][1] = 1;
        matrix[2][2] = 4;
        matrix[3][0] = 2;
        matrix[3][1] = 3;
        matrix[3][2] = 5;
        matrix[4][0] = 3;
        matrix[4][1] = 2;
        matrix[4][2] = 5;
        GraphGenerator graphGenerator = new GraphGenerator();
        Graph graph = graphGenerator.createGraph(matrix);
        HashMap<Node, Integer> res = job(graph.nodeMap.get(1));
        for (Map.Entry<Node, Integer> data : res.entrySet()){
            System.out.println(data.getKey().value+"  "+ data.getValue());
        }
    }

}
