package F05_mergePractice;

/**
 * 给定一个数组arr，两个整数lower和upper，返回arr中有多少个子数组的累加和在[lower,upper]范围上。
 *
 * https://leetcode.cn/problems/count-of-range-sum/
 */
public class T04_countRangeSum {

    public static int job(int[] data, int lower, int upper) {
        if (data == null || data.length < 2) {
            return 0;
        }
        //生成前缀和数组
        int[] sum = new int[data.length];
        sum[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            sum[i] = sum[i-1] + data[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(int[] data, int l, int r, int lower, int upper) {
        if (l == r) {
            return data[l] >= lower && data[r] <= upper ? 1 : 0;
        }
        int mid = l + ((r-l)>>1);
        return process(data, l, mid, lower, upper)+process(data, mid+1, r, lower, upper)+merge(data, l, mid, r, lower, upper);
    }

    public static int merge(int[] data, int l, int mid, int r, int lower, int upper){
        //计算范围上的字段数量
        int res = 0;
        int winl = l;
        int winr = l;
        for (int i = mid+1; i <= r; i++) {
            int cur = data[i];
            int min = cur-upper;
            int max = cur-lower;
            while (winl <= mid && data[winl]<min){
                winl++;
            }
            while (winr <= mid && data[winr]<=max){
                winr++;
            }
            System.out.println("winl:"+winl+" winr:"+winr);
            res += winr-winl;
        }

        int[] temp = new int[r-l+1];
        int ll = l;
        int rr = mid+1;
        int index = 0;
        while (ll<=mid && rr<=r){
            temp[index++] = data[ll]<data[rr]?data[ll++]:data[rr++];
        }
        while (ll<=mid){
            temp[index++] = data[ll++];
        }
        while (rr<=r){
            temp[index++] = data[rr++];
        }
        for (int i = 0; i < temp.length; i++) {
            data[l+i] = temp[i];
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入：nums = [-2,5,-1], lower = -2, upper = 2
         * 输出：3
         * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
         */
        int[] data = new int[]{-2,5,-1};
        System.out.println(job(data, -2, 2));

        /**
         * 输入：nums = [0], lower = 0, upper = 0
         * 输出：1
         */
        int[] data2 = new int[]{0};
        System.out.println(job(data2, 0, 0));
    }

}
