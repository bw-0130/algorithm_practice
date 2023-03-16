package A_One.T08;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 并查集
 * findFather  isSameSet union
 */
public class C1 {

    public static class Node<T> {
        public T value;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class Union<T> {
        public HashMap<T, Node<T>> nodes;
        public HashMap<Node<T>, Node<T>> parents;
        public HashMap<Node<T>, Integer> sizes;

        public Union(List<T> datas) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (T data : datas) {
                Node<T> tNode = new Node<>(data);
                nodes.put(data, tNode);
                parents.put(tNode, tNode);
                sizes.put(tNode, 1);
            }
        }

        public Node findFather(Node<T> node) {
            Stack<Node<T>> stack = new Stack<>();
            while (parents.get(node) != node) {
                stack.push(node);
                node = parents.get(node);
            }
            //加速
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), node);
            }
            return node;
        }

        //判断两个节点是否为一组
        public boolean isSameSet(T a, T b) {
            Node<T> nodeA = nodes.get(a);
            Node<T> nodeB = nodes.get(b);
            Node<T> parentA = parents.get(nodeA);
            Node<T> parentB = parents.get(nodeB);
            return parentA == parentB;
        }

        //合并
        public void union(T a, T b) {
            Node<T> nodeA = nodes.get(a);
            Node<T> nodeB = nodes.get(b);
            Node<T> parentA = parents.get(nodeA);
            Node<T> parentB = parents.get(nodeB);
            if (parentA != parentB) {
                Integer sizeA = sizes.get(nodeA);
                Integer sizeB = sizes.get(nodeB);
                Node<T> shortNode = sizeA < sizeB ? parentA : parentB;
                Node<T> longNode = shortNode == parentA ? parentB : parentA;
                parents.put(shortNode, longNode);
                sizes.put(longNode, sizeA+sizeB);
                sizes.remove(shortNode);
            }
        }

    }

}
