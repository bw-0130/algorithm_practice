package F08_unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛问题扩展（LeetCode305题）
 * <p>
 * 给定二维数组matrix:行和列，连续空降（r，l）把坐标记为1，返回每次空降后的岛数量。
 */
public class T04_numberOfIslandsII {

    public static class unionFind {
        public int[] parent;
        public int[] sizes;
        public int[] help;
        public int num;
        public int rows;
        public int cols;

        public unionFind(int r, int l) {
            rows = r;
            cols = l;
            int len = r * l;
            parent = new int[len];
            sizes = new int[len];
            help = new int[len];
            num = 0;
        }

        public int getIndex(int row, int col) {
            return row * cols + col;
        }

        public int fatherFind(int index) {
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

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == rows || c1 < 0 || c1 == cols || r2 < 0 || r2 == rows || c2 < 0 || c2 == cols) {
                return;
            }
            int indexA = getIndex(r1, c1);
            int indexB = getIndex(r2, c2);
            if (sizes[indexA] == 0 || sizes[indexB] == 0) {
                return;
            }
            int fatherA = fatherFind(indexA);
            int fatherB = fatherFind(indexB);
            if (fatherA != fatherB) {
                if (sizes[fatherA] >= sizes[fatherB]) {
                    parent[fatherB] = fatherA;
                    sizes[fatherA] += sizes[fatherB];
                } else {
                    parent[fatherA] = fatherB;
                    sizes[fatherB] += sizes[fatherA];
                }
                num--;
            }
        }

        public int add(int r, int c) {
            int index = getIndex(r, c);
            if (sizes[index] == 0) {
                parent[index] = index;
                sizes[index] = 1;
                num++;
                union(r + 1, c, r, c);
                union(r - 1, c, r, c);
                union(r, c + 1, r, c);
                union(r, c - 1, r, c);
            }
            return num;
        }
    }

    public static List<Integer> job(int m, int n, int[][] positions){
        unionFind unionFind = new unionFind(m, n);
        List<Integer> res = new ArrayList<>();
        for (int[] data : positions){
            res.add(unionFind.add(data[0], data[1]));
        }
        return res;
    }

}
