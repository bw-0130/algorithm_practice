package B_Two;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;

/**
 * 归并相关练习
 */
public class T05_mergePractice {

    public static void main(String[] args) {

       /* small small = new small();
        int[] data = new int[]{1, 3, 4, 2, 7};//正确答案16
        System.out.println(small.job(data));*/
        /*-------------------------------------------------------*/
        /*req req = new req();
        int[] data = new int[]{1, 3, 4, 2, 7};//正确答案2
        System.out.println(req.job(data));*/
        /*-------------------------------------------------------*/
        /*mult mult = new mult();
        int[] data = new int[]{6, 1, 2, 4, 7};//正确答案2
        System.out.println(mult.job(data));*/
        /*-------------------------------------------------------*/
        crs crs = new crs();
        /**
         * 输入：nums = [-2,5,-1], lower = -2, upper = 2
         * 输出：3
         * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
         */
        int[] data = new int[]{-2,5,-1};
        System.out.println(crs.job(data, -2, 2));

    }

    /**
     * 小和问题
     * 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组的小和。
     */
    public static class small {
        public int job(int[] data) {
            if (data == null || data.length < 2) {
                return -1;
            }
            return process(data, 0, data.length - 1);
        }

        public int process(int[] data, int l, int r) {
            if (l == r) {
                return 0;
            }
            int mid = l + ((r - l) >> 1);
            return process(data, l, mid) + process(data, mid + 1, r) + merge(data, l, mid, r);
        }

        public int merge(int[] data, int l, int mid, int r) {
            int[] temp = new int[r - l + 1];
            int winl = l;
            int winr = mid + 1;
            int res = 0;
            int index = 0;
            while (winl <= mid && winr <= r) {
                res += data[winl] < data[winr] ? (r - winr + 1) * data[winl] : 0;
                temp[index++] = data[winl] < data[winr] ? data[winl++] : data[winr++];
            }
            while (winl <= mid) {
                temp[index++] = data[winl++];
            }
            while (winr <= r) {
                temp[index++] = data[winr++];
            }
            for (int i = 0; i < temp.length; i++) {
                data[l + i] = temp[i];
            }
            return res;
        }
    }

    /**
     * 逆序对（求整个数组中有多少个逆序对）
     * 当左边数比右边数大即为逆序   例：4、1 逆序对， 4、5 不是逆序对
     */
    public static class req {
        public int job(int[] data) {
            if (data == null || data.length < 2) {
                return -1;
            }
            return process(data, 0, data.length - 1);
        }

        public int process(int[] data, int l, int r) {
            if (l == r) {
                return 0;
            }
            int mid = l + ((r - l) >> 1);
            return process(data, l, mid) + process(data, mid + 1, r) + merge(data, l, mid, r);
        }

        public int merge(int[] data, int l, int mid, int r) {
            int[] temp = new int[r - l + 1];
            int winl = mid;
            int winr = r;
            int res = 0;
            int index = temp.length - 1;
            while (winl >= l && winr > mid) {
                res += data[winl] > data[winr] ? winr - mid : 0;
                temp[index--] = data[winl] > data[winr] ? data[winl--] : data[winr--];
            }
            while (winl >= l) {
                temp[index--] = data[winl--];
            }
            while (winr > mid) {
                temp[index--] = data[winr--];
            }
            for (int i = 0; i < temp.length; i++) {
                data[l + i] = temp[i];
            }
            return res;
        }
    }

    /**
     * 求一个数右边数乘2都都小于该数的个数
     */
    public static class mult {
        public int job(int[] data) {
            if (data == null || data.length < 2) {
                return -1;
            }
            return process(data, 0, data.length - 1);
        }

        public int process(int[] data, int l, int r) {
            if (l == r) {
                return 0;
            }
            int mid = l + ((r - l) >> 1);
            return process(data, l, mid) + process(data, mid + 1, r) + merge(data, l, mid, r);
        }

        public int merge(int[] data, int l, int mid, int r) {
            int res = 0;
            int rr = mid + 1;
            for (int i = l; i <= mid; i++) {
                while (rr <= r && data[i] > (data[rr] << 1)) {
                    rr++;
                }
                res += rr - mid - 1;
            }
            int[] temp = new int[r - l + 1];
            int winl = l;
            int winr = mid + 1;
            int index = 0;
            while (winl <= mid && winr <= r) {
                temp[index++] = data[winl] < data[winr] ? data[winl++] : data[winr++];
            }
            while (winl <= mid) {
                temp[index++] = data[winl++];
            }
            while (winr <= r) {
                temp[index++] = data[winr++];
            }
            for (int i = 0; i < temp.length; i++) {
                data[l + i] = temp[i];
            }
            return res;
        }
    }

    /**
     * 给定一个数组arr，两个整数lower和upper，返回arr中有多少个子数组的累加和在[lower,upper]范围上。
     * <p>
     * https://leetcode.cn/problems/count-of-range-sum/
     */
    public static class crs {
        public int job(int[] data, int lower, int upper) {
            if (data == null || data.length < 2 || upper < lower) {
                return -1;
            }
            int[] sumArray = new int[data.length];
            sumArray[0] = data[0];
            for (int i = 1; i < sumArray.length; i++) {
                sumArray[i] = sumArray[i - 1] + data[i];
            }
            return process(sumArray, 0, sumArray.length - 1, lower, upper);
        }

        public int process(int[] data, int l, int r, int lower, int upper) {
            if (l == r) {
                return data[l] >= lower && data[l] <= upper ? 1 : 0;
            }
            int mid = l + ((r - l) >> 1);
            return process(data, l, mid, lower, upper) + process(data, mid + 1, r, lower, upper)
                    + merge(data, l, mid, r, lower, upper);
        }

        public int merge(int[] data, int l, int mid, int r, int lower, int upper) {
            int res = 0;
            int ll = l;
            int rr = l;
            for (int i = mid + 1; i <= r; i++) {
                int cur = data[i];
                int min = cur - upper;
                int max = cur - lower;
                while (ll <= mid && data[ll] < min) {
                    ll++;
                }
                while (rr <= mid && data[rr] <= max) {
                    rr++;
                }
                res += rr - ll;
            }
            int[] temp = new int[r - l + 1];
            int winl = l;
            int winr = mid + 1;
            int index = 0;
            while (winl <= mid && winr <= r) {
                temp[index++] = data[winl] < data[winr] ? data[winl++] : data[winr++];
            }
            while (winl <= mid) {
                temp[index++] = data[winl++];
            }
            while (winr <= r){
                temp[index++] = data[winr++];
            }
            for (int i = 0; i < temp.length; i++) {
                data[l+i] = temp[i];
            }
            return res;
        }
    }

}
