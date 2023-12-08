package B_Two;

import java.util.*;

/**
 * 加强堆练习
 */
public class T04_heapPractice {

    public static class myComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        /*coincideLine test1 = new coincideLine();
        int[][] data = test1.generateLines(10, 0, 20);
        System.out.println(test1.job(data));
        System.out.println(test1.maxCover1(data));*/
        /*-------------------------------------------------------------*/
        greaterHeap<Integer> heap = new greaterHeap<>(new myComparator());
        heap.push(50);
        heap.push(10);
        heap.push(20);
        heap.push(30);
        heap.push(40);

        List<Integer> list1 = heap.getAllElements();
        list1.forEach(e->{
            System.out.print(e+" ");
        });
        System.out.println();
        heap.remove(30);
        System.out.println(heap.getHeapSize());
        List<Integer> list2 = heap.getAllElements();
        list2.forEach(e->{
            System.out.print(e+" ");
        });
    }

    public static class coincideLine {
        public static class data {
            public int start;
            public int end;

            public data(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public static class myComparator implements Comparator<data> {

            @Override
            public int compare(data o1, data o2) {
                return o1.start - o2.start;
            }
        }

        public Integer job(int[][] data) {
            if (data == null || data.length < 2) {
                return null;
            }
            int N = data.length;
            data[] datas = new data[N];
            for (int i = 0; i < N; i++) {
                datas[i] = new data(data[i][0], data[i][1]);
            }
            Arrays.sort(datas, new myComparator());
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int res = 0;
            for (int i = 0; i < datas.length; i++) {
                data cur = datas[i];
                while (!heap.isEmpty() && heap.peek() <= cur.start) {
                    heap.poll();
                }
                heap.offer(cur.end);
                res = Math.max(res, heap.size());
            }
            return res;
        }

        // for test
        public int[][] generateLines(int N, int L, int R) {
            int size = (int) (Math.random() * N) + 1;
            int[][] ans = new int[size][2];
            for (int i = 0; i < size; i++) {
                int a = L + (int) (Math.random() * (R - L + 1));
                int b = L + (int) (Math.random() * (R - L + 1));
                if (a == b) {
                    b = a + 1;
                }
                ans[i][0] = Math.min(a, b);
                ans[i][1] = Math.max(a, b);
            }
            return ans;
        }

        //对数器
        public int maxCover1(int[][] lines) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < lines.length; i++) {
                min = Math.min(min, lines[i][0]);
                max = Math.max(max, lines[i][1]);
            }
            int cover = 0;
            for (double p = min + 0.5; p < max; p += 1) {
                int cur = 0;
                for (int i = 0; i < lines.length; i++) {
                    if (lines[i][0] < p && lines[i][1] > p) {
                        cur++;
                    }
                }
                cover = Math.max(cover, cur);
            }
            return cover;
        }
    }

    public static class greaterHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private Comparator<? super T> comp;
        private int heapSize;

        public greaterHeap(Comparator<? super T> comp) {
            this.comp = comp;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
        }

        public void swap(int a, int b) {
            T A = heap.get(a);
            T B = heap.get(b);
            indexMap.put(A, b);
            indexMap.put(B, a);
            heap.set(a, B);
            heap.set(b, A);
        }

        private void heapInsert(int index) {
            while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int last = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
                last = comp.compare(heap.get(index), heap.get(last)) < 0 ? index : last;
                if (last == index) {
                    break;
                }
                swap(last, index);
                index = last;
                left = index * 2 + 1;
            }
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int getHeapSize() {
            return heapSize;
        }

        public boolean contains(T data) {
            return indexMap.containsKey(data);
        }

        public T peek() {
            return heap.get(0);
        }

        public void push(T data) {
            heap.add(data);
            indexMap.put(data, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T res = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(res);
            heap.remove(--heapSize);
            heapify(0);
            return res;
        }

        public void remove(T data) {
            Integer resIndex = indexMap.get(data);
            indexMap.remove(data);
            T endData = heap.get(heapSize - 1);
            heap.remove(--heapSize);
            if (data != endData) {
                indexMap.put(endData, resIndex);
                heap.set(resIndex, endData);
                resign(endData);
            }
        }

        private void resign(T data) {
            heapInsert(indexMap.get(data));
            heapify(indexMap.get(data));
        }

        public List<T> getAllElements() {
            int N = heap.size();
            List<T> res = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                res.add(heap.get(i));
            }
            return res;
        }

    }

}
