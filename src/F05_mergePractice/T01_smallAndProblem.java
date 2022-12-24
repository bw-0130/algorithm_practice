package F05_mergePractice;

/**
 * 小和问题
 * 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组的小和。
 */
public class T01_smallAndProblem {

    public static int job(int[] data){
        if (data == null || data.length < 2){
            return 0;
        }
        return process(data, 0, data.length-1);
    }

    public static int process(int[] data, int l, int r){
        if (l == r){
            return 0;
        }
        int mid = l + ((r-l)>>1);
        return process(data, l, mid)
        + process(data, mid+1, r)
        + meger(data, l, mid, r);
    }

    public static int meger(int[] data, int l, int mid, int r){
        int[] temp = new int[r-l+1];
        int winl = l;
        int winr = mid+1;
        int index = 0;
        int res = 0;
        while (winl<=mid && winr<=r){
            res += data[winl]<data[winr]?(r-winr+1)*data[winl]:0;
            temp[index++] = data[winl]<data[winr]?data[winl++]:data[winr++];
        }
        while (winl<=mid){
            temp[index++] = data[winl++];
        }
        while (winr<=r){
            temp[index++] = data[winr++];
        }
        for (int i = 0; i < temp.length; i++) {
            data[l+i] = temp[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1,3,4,2,7};//正确答案16
        System.out.println(job(data));
    }

}
