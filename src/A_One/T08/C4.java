package A_One.T08;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛问题扩展（LeetCode305题）
 * <p>
 * 给定二维数组matrix:行和列，连续空降（r，l）把坐标记为1，返回每次空降后的岛数量。
 */
public class C4 {

    public static class UnionFind {
        public int[] parent;
        public int[] sizes;
        public int[] help;
        public int num;
        public int cols;
        public int rows;

        public UnionFind(int row, int col) {
            int n = row * col;
            parent = new int[n];
            sizes = new int[n];
            help = new int[n];
            num = 0;
            cols = col;
            rows = row;
        }

        public int getIndex(int row, int col) {
            return row * cols + col;
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
            if (row1 < 0 || row1 == rows || col1 < 0 || col1 == cols || row2 < 0 || row2 == rows || col2 < 0 || col2 == cols) {
                return;
            }
            int indexA = getIndex(row1, col1);
            int indexB = getIndex(row2, col1);
            int parentA = parent[indexA];
            int parentB = parent[indexB];
            if (parentA != parentB) {
                int sizeA = sizes[parentA];
                int sizeB = sizes[parentB];
                if (sizeA < sizeB) {
                    parent[parentA] = parentB;
                    sizes[parentB] += sizeA;
                } else {
                    parent[parentB] = parentA;
                    sizes[parentA] += sizeB;
                }
                num--;
            }
        }

        public int add(int row, int col) {
            int index = getIndex(row, col);
            if (sizes[index] == 0) {
                parent[index] = index;
                sizes[index] = 1;
                num++;
                union(row, col, row + 1, col);
                union(row, col, row - 1, col);
                union(row, col, row, col + 1);
                union(row, col, row, col - 1);
            }
            return num;
        }
    }

    public static List<Integer> job(int rows, int cols, int[][] datas){
        UnionFind unionFind = new UnionFind(rows, cols);
        List<Integer> res = new ArrayList<>();
        for (int[] data : datas){
            res.add(unionFind.add(data[0], data[1]));
        }
        return res;
    }

}
