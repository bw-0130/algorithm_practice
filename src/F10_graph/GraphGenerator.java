package F10_graph;

/**
 * 创建图结构
 * matrix 所有的边
 * N*3 的矩阵
 * [weight, from节点上面的值，to节点上面的值]
 * [ 5 , 0 , 7]
 * [ 3 , 0,  1]
 */
public class GraphGenerator {

    public Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodeMap.containsKey(from)){
                graph.nodeMap.put(from, new Node(from));
            }
            if (!graph.nodeMap.containsKey(to)){
                graph.nodeMap.put(to, new Node(to));
            }
            Node fromNode = graph.nodeMap.get(from);
            Node toNode = graph.nodeMap.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.edges.add(edge);
            toNode.in++;
            graph.edgeSet.add(edge);
        }
        return graph;
    }

}
