package A_One.T10;

import java.util.HashMap;

public class GraphGenerator {

    public Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            HashMap<Integer, Node> nodeMap = graph.nodeMap;
            if (!nodeMap.containsKey(from)){
                nodeMap.put(from, new Node(from));
            }
            if (!nodeMap.containsKey(to)){
                nodeMap.put(to, new Node(to));
            }
            Node fromNode = nodeMap.get(from);
            Node toNode = nodeMap.get(to);
            fromNode.outNum++;
            toNode.inNum++;
            Edge edge = new Edge(fromNode, toNode, weight);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            graph.edgeSet.add(edge);
        }
        return graph;
    }

}
