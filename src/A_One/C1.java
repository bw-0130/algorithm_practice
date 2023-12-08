package A_One;

/**
 * 在无序数组中求第K小的数
 * 1)改写快排的方法
 * 2）bfprt算法（就是解决在无序数组中找到第K小的数）,改写快排区别是P的选择
 * 思路：使用荷兰国旗问题来解决
 */
public class C1 {

    public static int[] copyArray(int[] data) {
        int[] res = new int[data.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = data[i];
        }
        return res;
    }

    public static int[] partation(int[] data, int l, int r, int pivot) {
        int winl = l - 1;
        int winr = r + 1;
        int index = l;
        while (index < winr) {
            if (data[index] < data[pivot]) {
                sort_util.swap(data, ++winl, index++);
            } else if (data[index] > data[pivot]) {
                sort_util.swap(data, --winr, index);
            } else {
                index++;
            }
        }
        return new int[]{winl + 1, winr - 1};
    }

    public static int jobOne(int[] data, int k) {
        int[] ints = copyArray(data);
        return processOne(ints, 0, ints.length - 1, k - 1);
    }

    public static int processOne(int[] data, int l, int r, int k) {
        if (l == r) {
            return data[l];
        }
        int pivot = l + (int) (Math.random() * (r - l + 1));
        int[] partation = partation(data, l, r, pivot);
        if (k >= partation[0] && k <= partation[1]) {
            return data[k];
        } else if (k < partation[0]) {
            return processOne(data, l, partation[0] - 1, k);
        } else {
            return processOne(data, partation[1] + 1, r, k);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 45, 36, 85, 7, 12, 57, 33};
        int k = 3;
        System.out.println(jobOne(arr, k));
        //System.out.println(jobTwo(arr, k));
    }

}
