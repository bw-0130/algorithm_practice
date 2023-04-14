package A_One.T10;

import java.util.HashSet;
import java.util.Stack;

public class C2 {

    public static void job(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.print(start.value + " ");
        while (!stack.isEmpty()){
            Node popNode = stack.pop();
            for (Node node : popNode.nexts){
                if (!set.contains(node)){
                    stack.push(popNode);
                    stack.push(node);
                    set.add(node);
                    System.out.print(node.value+" ");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][3];
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 3;

        matrix[0][0] = 2;
        matrix[0][1] = 1;
        matrix[0][2] = 4;

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
        job(graph.nodeMap.get(1));
    }

}
