package A_One.T10;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图表达
 */
public class Graph {

    public HashMap<Integer, Node> nodeMap;
    public HashSet<Edge> edgeSet;

    public Graph() {
        nodeMap = new HashMap<>();
        edgeSet = new HashSet<>();
    }
}
