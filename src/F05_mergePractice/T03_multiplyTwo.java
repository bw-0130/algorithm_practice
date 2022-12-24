package F05_mergePractice;

/**
 * 求一个数右边数乘2都都小于该数的个数
 */
public class T03_multiplyTwo {

    public static int job(int[] data) {
        if (data == null || data.length < 2) {
            return 0;
        }
        return process(data, 0, data.length - 1);
    }

    public static int process(int[] data, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(data, l, mid) + process(data, mid + 1, r) + merge(data, l, mid, r);
    }

    public static int merge(int[] data, int l, int mid, int r) {
        int res = 0;
        int resr = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (resr <= r && data[i] > data[resr] * 2) {
                resr++;
            }
            res += resr - mid - 1;
        }

        int[] temp = new int[r - l + 1];
        int winl = l;
        int winr = mid + 1;
        int index = 0;
        while (winl <= mid && winr <= r) {
            temp[index++] = data[winl] <= data[winr] ? data[winl++] : data[winr++];
        }
        while (winl <= mid){
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

    public static void main(String[] args) {
        int[] data = new int[]{6,1,2,4,7};//正确答案2
        System.out.println(job(data));
    }

}
