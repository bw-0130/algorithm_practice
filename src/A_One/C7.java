package A_One;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树（P算法）
 * 1、可以从任意节点出发来寻找最小生成树
 * 2、某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3、在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4、如果会，不要当前边，继续考察剩下解锁的边中的最小边重复步骤3
 * 5、如果不会，要当前边，将边的指向点加入到被选取的点中，重复步骤2
 * 6、当所有点都被选取，最小生成树就得到了
 */
public class C7 {
    
    public static class myComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    
    public static Set<Edge> job(Graph graph){
        PriorityQueue<Edge> heap = new PriorityQueue<>(new C6.myComparator());
        HashSet<Node> set = new HashSet<>();
        HashSet<Edge> res = new HashSet<>();
        for (Node node : graph.nodeMap.values()){
            set.add(node);
            for (Edge edge : node.edges){
                heap.offer(edge);
            }
            while (!heap.isEmpty()){
                Edge pollEdge = heap.poll();
                Node to = pollEdge.to;
                if (!set.contains(to)){
                    set.add(to);
                    res.add(pollEdge);
                   for (Edge edge : to.edges){
                       heap.offer(edge);
                   }
                }
            }
            break;
        }
        return res;
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
        matrix[3][0] = 4;
        matrix[3][1] = 3;
        matrix[3][2] = 5;
        matrix[4][0] = 1;
        matrix[4][1] = 5;
        matrix[4][2] = 2;
        GraphGenerator graphGenerator = new GraphGenerator();
        Graph graph = graphGenerator.createGraph(matrix);
        Set<Edge> edges = job(graph);
        for (Edge edge : edges){
            System.out.println(edge.weight);
        }
    }
    
}
