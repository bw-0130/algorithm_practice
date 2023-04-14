package A_One.T10;

import java.util.ArrayList;
import java.util.List;

//图节点
public class Node {

    public int value;
    public int inNum;
    public int outNum;
    public List<Node> nexts;
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        inNum = 0;
        outNum = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
