package F10_graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS（宽度优先遍历）
 */
public class C01_BFS {

    public static void traversal(Node start){
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            System.out.println(pollNode.value);
            for (Node node : pollNode.nexts){
                if (!set.contains(node)){
                    queue.add(node);
                    set.add(node);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][3];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 3;
        matrix[1][0] = 2;
        matrix[1][1] = 1;
        matrix[1][2] = 4;
        matrix[2][0] = 3;
        matrix[2][1] = 3;
        matrix[2][2] = 4;
        matrix[3][0] = 4;
        matrix[3][1] = 4;
        matrix[3][2] = 5;
        GraphGenerator graphGenerator = new GraphGenerator();
        Graph graph = graphGenerator.createGraph(matrix);
        traversal(graph.nodeMap.get(1));
    }

}
