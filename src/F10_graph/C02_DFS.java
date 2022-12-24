package F10_graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * DFS（深度优先遍历）
 */
public class C02_DFS {

    public static void traversal(Node start){
        if (start == null){
            return;
        }
        HashSet<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        set.add(start);
        stack.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()){
            Node popNode = stack.pop();
            for (Node node : popNode.nexts){
                if (!set.contains(node)){
                    stack.push(popNode);
                    stack.push(node);
                    set.add(node);
                    System.out.println(node.value);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][3];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 3;
        matrix[1][0] = 2;
        matrix[1][1] = 1;
        matrix[1][2] = 4;
        matrix[2][0] = 3;
        matrix[2][1] = 3;
        matrix[2][2] = 1;
        matrix[3][0] = 4;
        matrix[3][1] = 3;
        matrix[3][2] = 5;
        matrix[4][0] = 5;
        matrix[4][1] = 5;
        matrix[4][2] = 6;
        GraphGenerator graphGenerator = new GraphGenerator();
        Graph graph = graphGenerator.createGraph(matrix);
        traversal(graph.nodeMap.get(1));
    }

}
