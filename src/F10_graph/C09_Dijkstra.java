package F10_graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 迪杰特斯拉算法
 * 从一个顶点到其余各顶点的最短路径算法，解决的是有权图中最短路径问题
 * 思路：
 * 使用加强堆实现（小根堆：根据元素权重判断）
 * 特殊：加强堆中弹出元素后，indexMap中不删除标记为-1，用于判断节点是否曾经加入过堆中
 * 主方法：每次从堆中去除一个元素循环其后继节点并加入堆中，并记录当前弹出的节点到结果集中。
 * 直到堆中元素为空 返回记录的结果集。
 */
public class C09_Dijkstra {

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    //自定义加强堆
    public static class myHeap {
        private Node[] nodes;//堆元素存放位置
        private HashMap<Node, Integer> indexMap;//堆元素在堆中位置map
        private HashMap<Node, Integer> weightMap;//堆中元素权重
        private int heapSize;//堆大小

        public myHeap(int size) {
            nodes = new Node[size];
            indexMap = new HashMap<>();
            weightMap = new HashMap<>();
            heapSize = 0;
        }

        public void swap(int a, int b) {
            indexMap.put(nodes[a], b);
            indexMap.put(nodes[b], a);
            Node temp = nodes[a];
            nodes[a] = nodes[b];
            nodes[b] = temp;
        }

        public void heapInsert(int index) {
            while (weightMap.get(nodes[index]) < weightMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int last = left + 1 < heapSize && weightMap.get(nodes[left + 1]) < weightMap.get(nodes[left]) ? left + 1 : left;
                last = weightMap.get(nodes[index]) < weightMap.get(nodes[last]) ? index : last;
                if (last == index) {
                    break;
                }
                swap(last, index);
                index = last;
                left = index * 2 + 1;
            }
        }

        public NodeRecord pop() {
            NodeRecord res = new NodeRecord(nodes[0], weightMap.get(nodes[0]));
            swap(0, heapSize - 1);
            indexMap.put(nodes[heapSize - 1], -1);
            weightMap.remove(nodes[heapSize - 1]);
            heapify(--heapSize);
            return res;
        }

        public boolean isInHeap(Node node) {
            return indexMap.containsKey(node);
        }

        public boolean isInHeapAndHis(Node node) {
            return isInHeap(node) && indexMap.get(node) != -1;
        }

        public void addOrUpdateOrIgnore(Node node, int weight) {
            if (isInHeapAndHis(node)) {//节点在堆中存在并且曾经没有弹出过
                weightMap.put(node, Math.min(weightMap.get(node), weight));
                heapInsert(indexMap.get(node));
            }
            if (!isInHeap(node)) {//节点在堆中不存在
                nodes[heapSize] = node;
                indexMap.put(node, heapSize);
                weightMap.put(node, weight);
                heapInsert(heapSize++);
            }
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }
    }

    public static HashMap<Node, Integer> dijkstra(Node start, int nodeNum) {
        HashMap<Node, Integer> res = new HashMap<>();
        myHeap heap = new myHeap(nodeNum);
        heap.addOrUpdateOrIgnore(start, 0);
        while (!heap.isEmpty()) {
            NodeRecord popData = heap.pop();
            Node node = popData.node;
            int weight = popData.distance;
            for (Edge edge : node.edges) {
                Node to = edge.to;
                heap.addOrUpdateOrIgnore(to, edge.weight + weight);
            }
            res.put(node, weight);
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
        matrix[3][0] = 2;
        matrix[3][1] = 3;
        matrix[3][2] = 5;
        matrix[4][0] = 3;
        matrix[4][1] = 2;
        matrix[4][2] = 5;
        GraphGenerator graphGenerator = new GraphGenerator();
        Graph graph = graphGenerator.createGraph(matrix);
        HashMap<Node, Integer> res = dijkstra(graph.nodeMap.get(1), 5);
        for (Map.Entry<Node, Integer> data : res.entrySet()) {
            System.out.println(data.getKey().value + "  " + data.getValue());
        }
    }

}
