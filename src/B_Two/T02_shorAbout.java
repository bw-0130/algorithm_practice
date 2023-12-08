package B_Two;

/**
 * 排序相关
 */
public class T02_shorAbout {

    public static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static int[] createArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (1 + Math.random() * maxValue);
        }
        return res;
    }

    public static void printArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = createArray(10, 100);
        printArray(data);

        //Bubble.job(data);
        /*-------------------------------*/
        //select.job(data);
        /*-------------------------------*/
        //insert.job(data);
        /*-------------------------------*/
        //quick.job(data);
        /*-------------------------------*/
        //merge.job(data);
        /*-------------------------------*/
        //heap.job(data);
        /*-------------------------------*/
        count.job(data);

        printArray(data);
    }

    //冒泡排序
    public static class Bubble {
        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length - i - 1; j++) {
                    if (data[j] > data[j + 1]) {
                        swap(data, j, j + 1);
                    }
                }
            }
        }
    }

    //选择排序
    public static class select {
        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            for (int i = 0; i < data.length; i++) {
                int minNum = i;
                for (int j = i + 1; j < data.length; j++) {
                    if (data[minNum] > data[j]) {
                        minNum = j;
                    }
                }
                swap(data, i, minNum);
            }

        }
    }

    //插入排序
    public static class insert {
        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            for (int i = 1; i < data.length; i++) {
                for (int j = i - 1; j >= 0 && data[j] > data[j + 1]; j--) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    //快排
    public static class quick {
        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            process(data, 0, data.length - 1);
        }

        public static void process(int[] data, int l, int r) {
            if (l > r) {
                return;
            }
            int[] partation = partation(data, l, r);
            process(data, l, partation[0] - 1);
            process(data, partation[1] + 1, r);
        }

        public static int[] partation(int[] data, int l, int r) {
            if (l == r) {
                return new int[]{l, r};
            }
            if (l > r) {
                return new int[]{-1, -1};
            }
            int winl = l - 1;
            int winr = r;
            int index = l;
            while (index < winr) {
                if (data[index] < data[r]) {
                    swap(data, ++winl, index++);
                } else if (data[index] > data[r]) {
                    swap(data, index, --winr);
                } else {
                    index++;
                }
            }
            swap(data, winr, r);
            return new int[]{winl + 1, winr};
        }
    }

    //归并排序
    public static class merge {
        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            process(data, 0, data.length - 1);
        }

        public static void process(int[] data, int l, int r) {
            if (l == r) {
                return;
            }
            int mid = l + (r - 1) >> 1;
            process(data, l, mid);
            process(data, mid + 1, r);
            meger(data, l, r, mid);
        }

        public static void meger(int[] data, int l, int r, int mid) {
            int[] temp = new int[r - l + 1];
            int winl = l;
            int winr = mid + 1;
            int index = 0;
            while (winl <= mid && winr <= r) {
                if (data[winl] <= data[winr]) {
                    temp[index++] = data[winl++];
                } else {
                    temp[index++] = data[winr++];
                }
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
        }
    }

    //堆排序
    public static class heap {
        public static void heapInsert(int[] data, int index) {
            while (data[index] > data[(index - 1) / 2]) {
                swap(data, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public static void heapify(int[] data, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int last = (left + 1) < heapSize && data[left + 1] > data[left] ? left + 1 : left;
                last = data[index] > data[last] ? index : last;
                if (last == index) {
                    break;
                }
                swap(data, index, last);
                index = last;
                left = index * 2 + 1;
            }
        }

        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            for (int i = 0; i < data.length; i++) {
                heapInsert(data, i);
            }
            int heapSize = data.length;
            while (heapSize > 0) {
                swap(data, 0, heapSize - 1);
                heapify(data, 0, --heapSize);
            }
        }
    }

    public static class count {
        public static void job(int[] data) {
            if (data == null || data.length <= 1) {
                return;
            }
            int N = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i] > N) {
                    N = data[i];
                }
            }
            int[] temp = new int[N + 1];
            for (int i = 0; i < data.length; i++) {
                temp[data[i]]++;
            }
            int index = 0;
            for (int i = 0; i < temp.length; i++) {
                while (temp[i]-- > 0) {
                    data[index++] = i;
                }
            }
        }
    }

}
