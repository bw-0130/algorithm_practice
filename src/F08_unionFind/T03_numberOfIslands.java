package F08_unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 岛问题
 * 给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量
 */
public class T03_numberOfIslands {

    //感染算法
    public static int jobOne(char[][] matrix) {
        int islandNum = 0;
        int rows = matrix.length;//行
        int col = matrix[0].length;//列
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    islandNum++;
                    infect(matrix, i, j);
                }
            }
        }
        return islandNum;
    }

    //感染
    public static void infect(char[][] matrix, int i, int j) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[0].length || matrix[i][j] != '1') {
            return;
        }
        matrix[i][j] = 0;
        infect(matrix, i - 1, j);
        infect(matrix, i + 1, j);
        infect(matrix, i, j - 1);
        infect(matrix, i, j + 1);
    }

    //并查集算法一
    public static int jobTwo(char[][] matrix) {
        int rows = matrix.length;//行
        int col = matrix[0].length;//列
        Dot[][] dots = new Dot[rows][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        unionFindOne unionFindOne = new unionFindOne(dotList);
        for (int i = 1; i < col; i++) {
            if (matrix[0][i - 1] == '1' && matrix[0][i] == '1') {
                unionFindOne.union(dots[0][i - 1], dots[0][i]);
            }
        }
        for (int i = 1; i < rows; i++) {
            if (matrix[i - 1][0] == '1' && matrix[i][0] == '1') {
                unionFindOne.union(dots[i - 1][0], dots[i][0]);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (matrix[i][j - 1] == '1') {
                        unionFindOne.union(dots[i][j - 1], dots[i][j]);
                    }
                    if (matrix[i - 1][j] == '1') {
                        unionFindOne.union(dots[i - 1][j], dots[i][j]);
                    }
                }
            }
        }
        return unionFindOne.sizes.size();
    }

    public static class Dot {
    }

    public static class Node<V> {

        V value;

        public Node(V v) {
            value = v;
        }

    }

    public static class unionFindOne<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizes;

        public unionFindOne(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        public Node<V> fatherFind(Node<V> node) {
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

        public void union(V a, V b) {
            Node<V> fatherA = fatherFind(nodes.get(a));
            Node<V> fatherB = fatherFind(nodes.get(b));
            if (fatherA != fatherB) {
                Integer sizeA = sizes.get(fatherA);
                Integer sizeB = sizes.get(fatherB);
                Node<V> big = sizeA >= sizeB ? fatherA : fatherB;
                Node<V> small = big == fatherA ? fatherB : fatherA;
                parents.put(small, big);
                sizes.put(big, sizeA + sizeB);
                sizes.remove(small);
            }
        }
    }

    //并查集算法二
    public static int jobThree(char[][] matrix) {
        unionFindTwo union = new unionFindTwo(matrix);
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 1; i < rows; i++) {
            if (matrix[0][i] == '1' && matrix[0][i - 1] == '1') {
                union.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < cols; i++) {
            if (matrix[i][0] == '1' && matrix[i - 1][0] == '1') {
                union.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (matrix[i - 1][j] == '1') {
                        union.union(i - 1, j, i, j);
                    }
                    if (matrix[i][j - 1] == '1') {
                        union.union(i, j - 1, i, j);
                    }
                }
            }
        }
        return union.num;
    }

    public static class unionFindTwo {
        public int[] parent;//父子关系数组
        public int[] help;//辅助数组
        public int[] size;//集合大小
        public int num;//集合数量
        public int col;//列数

        public unionFindTwo(char[][] matrix) {
            int row = matrix.length;
            col = matrix[0].length;
            int len = row * col;
            parent = new int[len];
            help = new int[len];
            size = new int[len];
            num = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int index = getIndex(i, j);
                    parent[i] = i;
                    size[i] = 1;
                    num++;
                }
            }
        }

        public int getIndex(int rowNum, int colNum) {
            return rowNum * col + colNum;
        }

        public int findFather(int index) {
            int helpIndex = 0;
            while (index != parent[index]) {
                help[helpIndex++] = index;
                index = parent[index];
            }
            for (int i = helpIndex; i >= 0; i--) {
                parent[help[i]] = index;
            }
            return index;
        }

        public void union(int row1, int col1, int row2, int col2) {
            int indexA = getIndex(row1, col1);
            int indexB = getIndex(row2, col2);
            int fatherA = findFather(indexA);
            int fatherB = findFather(indexB);
            if (fatherA != fatherB) {
                if (size[fatherA] >= size[fatherB]) {
                    parent[fatherB] = fatherA;
                    size[fatherA] += size[fatherB];
                } else {
                    parent[fatherA] = fatherB;
                    size[fatherB] += size[fatherA];
                }
                num--;
            }
        }

    }

}
