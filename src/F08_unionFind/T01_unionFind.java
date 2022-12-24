package F08_unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 */
public class T01_unionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class union<V>{
        public HashMap<V, Node<V>> nodes;//集合元素表
        public HashMap<Node<V>, Node<V>> parents;//父子关系表
        public HashMap<Node<V>, Integer> sizeMap;//集合大小

        public union(List<V> datas) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : datas){
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }
        //找到代表节点
        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> stack = new Stack<>();
            while (cur!= parents.get(cur)){
                stack.push(cur);
                cur = parents.get(cur);
            }
            //优化点
            while (!stack.isEmpty()){
                parents.put(stack.pop(),cur);
            }
            return cur;
        }
        //判断两个元素是否在同一集合内
        public boolean isSameSet(V a, V b){
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> fatherA = findFather(nodeA);
            Node<V> fatherB = findFather(nodeB);
            return fatherA == fatherB;
        }
        //把两个元素所在的集合合并
        public void union(V a, V b){
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> fatherA = findFather(nodeA);
            Node<V> fatherB = findFather(nodeB);
            if (fatherA != fatherB){
                Integer sizeA = sizeMap.get(nodeA);
                Integer sizeB = sizeMap.get(nodeB);
                Node<V> shortNode = sizeA < sizeB?fatherA:fatherB;
                Node<V> longNode = shortNode == fatherA?fatherB:fatherA;
                parents.put(shortNode,longNode);
                sizeMap.put(longNode, sizeA+sizeB);
                sizeMap.remove(shortNode);
            }
        }
    }
}
