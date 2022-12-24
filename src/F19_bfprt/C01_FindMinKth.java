package F19_bfprt;

/**
 * 在无序数组中求第K小的数
 * 1)改写快排的方法
 * 2）bfprt算法（就是解决在无序数组中找到第K小的数）,改写快排区别是P的选择
 * 思路：使用荷兰国旗问题来解决
 */
public class C01_FindMinKth {

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //改写快排的方法
    public static int jobOne(int[] arr, int K) {
        int[] array = copyArray(arr);
        return process(array, 0, array.length - 1, K - 1);
    }

    public static int process(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int pivot = arr[l + (int) (Math.random() * (r - l + 1))];//随机数
        int[] partation = partation(arr, l, r, pivot);
        if (k >= partation[0] && k <= partation[1]) {
            return arr[k];
        } else if (k < partation[0]) {
            return process(arr, l, partation[0] - 1, k);
        } else {
            return process(arr, partation[1] + 1, r, k);
        }
    }

    public static int[] partation(int[] arr, int l, int r, int pivot) {
        int winl = l - 1;
        int winr = r + 1;
        int index = l;
        while (index < winr) {
            if (arr[index] < pivot) {
                swap(arr, index++, ++winl);
            } else if (arr[index] > pivot) {
                swap(arr, index, --winr);
            } else {
                index++;
            }
        }
        return new int[]{winl + 1, winr - 1};
    }

    //bfprt算法
    public static int jobTwo(int[] arr, int k) {
        int[] array = copyArray(arr);
        return process2(array, 0, array.length - 1, k - 1);
    }

    public static int process2(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int pivot = getBfprtPivot(arr, l, r);
        int[] partation = partation(arr, l, r, pivot);
        if (k >= partation[0] && k <= partation[1]) {
            return arr[k];
        } else if (k < partation[0]) {
            return process2(arr, l, partation[0] - 1, k);
        } else {
            return process2(arr, partation[1] + 1, r, k);
        }
    }

    public static int getBfprtPivot(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = (size % 5) == 0 ? 0 : 1;
        int[] list = new int[size + offset];
        for (int i = 0; i < list.length; i++) {
            int winl = l + (i * 5);
            list[i] = getMid(arr, winl, Math.min(r, winl + 4));
        }
        return process2(list, 0, list.length - 1, list.length / 2);
    }

    public static int getMid(int[] arr, int l, int r) {
        insertionSort(arr, l, r);
        return arr[(l + r) / 2];
    }

    //插入排序
    public static void insertionSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 9, 45, 36, 85, 7, 12, 57, 33};
        int k = 3;
        System.out.println(jobOne(arr, k));
        System.out.println(jobTwo(arr, k));
    }

}
