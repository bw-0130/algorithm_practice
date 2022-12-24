package F10_graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点
 */
public class Node {
    public int value;//节点值
    public int in;//节点入度
    public int out;//节点出度
    public List<Node> nexts;//后继节点集合
    public List<Edge> edges;//后继边集合

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
