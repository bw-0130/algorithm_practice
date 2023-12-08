package B_Two;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*并查集相关*/
public class T08_unionFindAbout {

    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }

    public static class unionFind<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        private HashMap<Node<V>, Integer> sizes;

        public unionFind(List<V> datas) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (V v : datas) {
                Node cur = new Node(v);
                nodes.put(v, cur);
                parents.put(cur, cur);
                sizes.put(cur, 1);
            }
        }

        public Node<V> findParents(Node<V> node) {
            Stack<Node<V>> stack = new Stack<>();
            while (node != parents.get(node)) {
                stack.push(node);
                node = parents.get(node);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(V a, V b) {
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> parA = parents.get(nodeA);
            Node<V> parB = parents.get(nodeB);
            return parA == parB;
        }

        public void union(V a, V b) {
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> parA = parents.get(nodeA);
            Node<V> parB = parents.get(nodeB);
            if (parA != parB) {
                Integer sizeA = sizes.get(nodeA);
                Integer sizeB = sizes.get(nodeB);
                Node<V> shortNode = sizeA < sizeB ? nodeA : nodeB;
                Node<V> lontNode = shortNode == nodeA ? nodeB : nodeA;
                parents.put(shortNode, lontNode);
                sizes.put(lontNode, sizeA + sizeB);
                sizes.remove(shortNode);
            }
        }
    }

    public static class friendCircles {
        public static class unionFind {
            private int[] parent;
            private int[] helps;
            private int[] sizes;
            public int num;

            public unionFind(int N) {
                parent = new int[N];
                helps = new int[N];
                sizes = new int[N];
                this.num = N;
                for (int i = 0; i < N; i++) {
                    parent[i] = i;
                    sizes[i] = 1;
                }
            }

            public int findFarther(int data) {
                int index = 0;
                while (data != parent[data]) {
                    helps[index++] = data;
                    data = parent[data];
                }
                for (int i = index; i >= 0; i--) {
                    parent[helps[i]] = data;
                }
                return data;
            }

            public boolean isSameSet(int a, int b) {
                return parent[a] == parent[b];
            }

            public void union(int a, int b) {
                int parA = parent[a];
                int parB = parent[b];
                if (parA != parB) {
                    int sizeA = sizes[a];
                    int sizeB = sizes[b];
                    if (sizeA < sizeB) {
                        parent[a] = b;
                        sizes[b] += sizes[a];
                    } else {
                        parent[b] = a;
                        sizes[a] += sizes[b];
                    }
                    num--;
                }
            }
        }

        public static int job(int[][] isConnected) {
            int N = isConnected.length;
            unionFind unf = new unionFind(N);
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (isConnected[i][j] == 1) {
                        unf.union(i, j);
                    }
                }
            }
            return unf.num;
        }
    }

}
