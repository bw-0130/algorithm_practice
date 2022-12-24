package F05_mergePractice;

/**
 * 逆序对（求整个数组中有多少个逆序对）
 *当左边数比右边数大即为逆序   例：4、1 逆序对， 4、5 不是逆序对
 */
public class T02_reversedSequence {

    public static int job(int[] data){
        if (data == null || data.length < 2){
            return 0;
        }
        return process(data, 0, data.length - 1);
    }

    public static int process(int[] data, int l, int r){
        if (l==r){
            return 0;
        }
        int mid = l + ((r-l)>>1);
        return process(data, l, mid)
        + process(data, mid+1, r)
        + merge(data, l, mid, r);
    }

    public static int merge(int[] data, int l, int mid, int r){
        int[] temp = new int[r-l+1];
        int winl = mid;
        int winr = r;
        int index = temp.length-1;
        int res = 0;
        while (winl >= l && winr > mid){
            res += data[winl]>data[winr]?winr-mid:0;
            temp[index--] = data[winl]>data[winr]?data[winl--]:data[winr--];
        }
        while (winl >= l){
            temp[index--] = data[winl--];
        }
        while (winr >= mid+1){
            temp[index--] = data[winr--];
        }
        for (int i = 0; i < temp.length; i++) {
            data[l+i] = temp[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1,3,4,2,7};//正确答案2
        System.out.println(job(data));
    }

}
