package A_One.T08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 岛问题
 * 给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量
 */
public class C3 {

    //传染算法
    public static int jobOne(int[][] matrix) {
        int islandNum = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    islandNum++;
                    infect(matrix, i, j);
                }
            }
        }
        return islandNum;
    }

    public static void infect(int[][] matrix, int i, int j) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[0].length || matrix[i][j] != 1) {
            return;
        }
        matrix[i][j] = 0;
        infect(matrix, i - 1, j);
        infect(matrix, i + 1, j);
        infect(matrix, i, j - 1);
        infect(matrix, i, j + 1);
    }

    //并查集算法
    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionFindOne<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parent;
        public HashMap<Node<V>, Integer> sizes;

        public UnionFindOne(List<V> datas) {
            nodes = new HashMap<>();
            parent = new HashMap<>();
            sizes = new HashMap<>();
            for (V data : datas) {
                Node<V> node = new Node<>(data);
                nodes.put(data, node);
                parent.put(node, node);
                sizes.put(node, 1);
            }
        }

        public Node findFarther(Node<V> node) {
            Node<V> cur = node;
            Stack<Node<V>> stack = new Stack<>();
            while (parent.get(cur) != cur) {
                stack.push(cur);
                cur = parent.get(cur);
            }
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> parentA = parent.get(nodeA);
            Node<V> parentB = parent.get(nodeB);
            if (parentA != parentB) {
                Integer sizeA = sizes.get(parentA);
                Integer sizeB = sizes.get(parentB);
                Node<V> shortNode = sizeA < sizeB ? parentA : parentB;
                Node<V> longNode = shortNode == parentA ? parentB : parentA;
                parent.put(shortNode, longNode);
                sizes.put(longNode, sizeA + sizeB);
                sizes.remove(shortNode);
            }
        }

    }

    public static class Help {
    }//协助类

    public static int jobTwo(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        Help[][] help = new Help[row][col];
        List<Help> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    help[i][j] = new Help();
                    list.add(help[i][j]);
                }
            }
        }
        UnionFindOne<Help> unionFindOne = new UnionFindOne<>(list);
        for (int i = 1; i < col; i++) {
            if (matrix[0][i] == 1 && matrix[0][i - 1] == 1) {
                unionFindOne.union(help[0][i], help[0][i - 1]);
            }
        }
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 1 && matrix[i - 1][0] == 1) {
                unionFindOne.union(help[i][0], help[i - 1][0]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 1) {
                    if (matrix[i - 1][j] == 1) {
                        unionFindOne.union(help[i][j], help[i - 1][j]);
                    }
                    if (matrix[i][j - 1] == 1) {
                        unionFindOne.union(help[i][j], help[i][j - 1]);
                    }
                }
            }
        }
        return unionFindOne.sizes.size();
    }

    //并查集算法二
    public static class UnionFindTwo {
        public int[] parent;
        public int[] sizes;
        public int[] help;
        public int num;
        public int col;

        public UnionFindTwo(int[][] matrix) {
            col = matrix[0].length;
            int row = matrix.length;
            int N = row * col;
            parent = new int[N];
            sizes = new int[N];
            help = new int[N];
            num = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int index = getIndex(i, j);
                    parent[index] = index;
                    sizes[index] = 1;
                    num++;
                }
            }
        }

        public int getIndex(int rowNum, int colNum) {
            return rowNum * col + colNum;
        }

        public int findFarther(int data) {
            int index = 0;
            while (parent[data] != data) {
                help[index++] = data;
                data = parent[data];
            }
            for (int i = 0; i < index; i++) {
                parent[help[i]] = data;
            }
            return data;
        }

        public void union(int row1, int col1, int row2, int col2) {
            int indexA = getIndex(row1, col1);
            int indexB = getIndex(row2, col2);
            int fatherA = findFarther(indexA);
            int fatherB = findFarther(indexB);
            if (fatherA != fatherB) {
                if (sizes[fatherA] < sizes[fatherB]) {
                    parent[fatherA] = fatherB;
                    sizes[fatherB] += sizes[fatherA];
                } else {
                    parent[fatherB] = fatherA;
                    sizes[fatherA] += sizes[fatherB];
                }
                num--;
            }
        }

    }

    public static int jobThree(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        UnionFindTwo unionFindTwo = new UnionFindTwo(matrix);
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 1 && matrix[i - 1][0] == 1) {
                unionFindTwo.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < col; i++) {
            if (matrix[0][i] == 1 && matrix[0][i - 1] == 1) {
                unionFindTwo.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j]==1){
                    if (matrix[i-1][j]==1){
                        unionFindTwo.union(i, j, i - 1, j);
                    }
                    if (matrix[i][j-1]==1){
                        unionFindTwo.union(i, j, i, j-1);
                    }
                }
            }
        }
        return unionFindTwo.num;
    }

}
