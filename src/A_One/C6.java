package A_One;

import java.util.*;

/**
 * 最小生成树（K算法）
 * 定义：连接所有节点的最小权重边
 * 思路：
 * 1、总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2、当前的边要么进入最小生成树集合，要么丢弃
 * 3、如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4、如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5、考察完所有边之后，最小生成树集合也得到了
 * 注：判断是否形成环通过并查集判断，如果两个边已经是一个集合则会形成环，不是一个集合则不会形成环。
 */
public class C6 {

    //并查集
    public static class UnionFind {
        public HashMap<Node, Node> parent;
        public HashMap<Node, Integer> sizes;

        public UnionFind() {
            parent = new HashMap<>();
            sizes = new HashMap<>();
        }

        public void init(Collection<Node> datas) {
            parent.clear();
            sizes.clear();
            for (Node node : datas) {
                parent.put(node, node);
                sizes.put(node, 1);
            }
        }

        public Node findFarther(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parent.get(node)) {
                stack.push(node);
                node = parent.get(node);
            }
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSame(Node a, Node b) {
            return findFarther(a) == findFarther(b);
        }

        public void union(Node a, Node b) {
            Node nodeA = findFarther(a);
            Node nodeB = findFarther(b);
            if (nodeA != nodeB) {
                Integer sizeA = sizes.get(nodeA);
                Integer sizeB = sizes.get(nodeB);
                Node shortNode = sizeA < sizeB ? nodeA : nodeB;
                Node longNode = shortNode == nodeA ? nodeB : nodeA;
                parent.put(shortNode, longNode);
                sizes.put(longNode, sizeA+sizeB);
                sizes.remove(shortNode);
            }
        }

    }

    public static class myComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> job(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.init(graph.nodeMap.values());
        PriorityQueue<Edge> heap = new PriorityQueue<>(new myComparator());
        Set<Edge> resSet = new HashSet<>();
        for (Edge edge : graph.edgeSet){
            heap.offer(edge);
        }
        while (!heap.isEmpty()){
            Edge pollEdge = heap.poll();
            Node from = pollEdge.from;
            Node to = pollEdge.to;
            if (!unionFind.isSame(from, to)){
                resSet.add(pollEdge);
                unionFind.union(from, to);
            }
        }
        return resSet;
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
