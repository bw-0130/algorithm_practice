package A_One.T10;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS（宽度优先遍历）
 */
public class C1 {
    
    public static void job(Node start){
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        HashSet<Node> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            System.out.print(pollNode.value+" ");
            for (Node node : pollNode.nexts){
                if (!set.contains(node)){
                    queue.offer(node);
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
        job(graph.nodeMap.get(1));
    }
    
}
