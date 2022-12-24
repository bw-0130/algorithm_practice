package F10_graph;

/**
 * 边描述
 */
public class Edge {
    public int weight;//边权重
    public Node from;//开始节点
    public Node to;//结束节点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
