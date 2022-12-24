package F21_segmentTree;

/**
 * 线段树，用于数组中一定范围内的add、update、query
 * 注：线段树数组从1开始不从0开始
 */
public class C01_segmentTree {

    //线段树类
    public static class segementTree {
        private int MAXN;//线段树长度
        private int[] arr;//复制出来的新数组,下标从1开始
        private int[] sum;//模拟线段树维护 区间和
        private int[] lazy;//累加和懒标记数组
        private int[] change;//更新值懒数组
        private boolean[] update;//更新值懒标记（主要为邠更新值懒数组中的数字0含义的歧义）

        public segementTree(int[] data) {
            MAXN = data.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = data[i - 1];
            }
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
            change = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
        }

        //rt左孩子 rt*2 右孩子rt*2+1
        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        //初始化线段树
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        //懒数据下发一层
        public void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int res = 0;
            if (L <= mid) {
                res += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                res += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 1, 1, 2, 3, 4, 5};
        segementTree tree = new segementTree(data);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = data.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        tree.build(S, N, root);//构建线段树
        System.out.println(tree.query(L, R, S, N, root));
        tree.add(L, R, C, S, N, root);
        System.out.println(tree.query(L, R, S, N, root));
        tree.update(L, R, C, S, N, root);
        System.out.println(tree.query(L, R, S, N, root));
    }

}
