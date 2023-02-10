package A_One;

/**
 * 快排
 */
public class CS4 {
    public static void sortJob(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        process(data, 0, data.length - 1);
    }

    public static void process(int[] data, int l, int r) {
        if (l > r) {
            return;
        }
        int[] partition = partition(data, l, r);
        process(data, l, partition[0] - 1);
        process(data, partition[1]+1, r);
    }

    public static int[] partition(int[] data, int l, int r) {
        if (l == r) {
            return new int[]{l,r};
        }
        if (l > r) {
            return new int[]{-1,-1};
        }
        int winl = l - 1;
        int winr = r;
        int index = l;
        while (index < winr) {
            if (data[index] < data[r]) {
                sort_util.swap(data, ++winl, index++);
            } else if (data[index] > data[r]) {
                sort_util.swap(data, --winr, index);
            } else {
                index++;
            }
        }
        sort_util.swap(data, winr, r);
        return new int[]{winl + 1,winr};
    }
    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }
}
