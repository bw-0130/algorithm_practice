package A_One.T04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 */
public class C2 {

    public static class myHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> com;

        public myHeap(Comparator<? super T> com) {
            this.com = com;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
        }

        public void swap(int a, int b) {
            T dataA = heap.get(a);
            T dataB = heap.get(b);
            indexMap.put(dataA, b);
            indexMap.put(dataB, a);
            heap.set(a, dataB);
            heap.set(b, dataA);
        }

        public void heapInsert(int index) {
            while (com.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int temp = left + 1 < heapSize && (com.compare(heap.get(left + 1), heap.get(left)) < 0) ? left + 1 : left;
                temp = com.compare(heap.get(index), heap.get(temp)) < 0 ? index : temp;
                if (temp == index) {
                    break;
                }
                swap(temp, index);
                index = temp;
                left = index * 2 + 1;
            }
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public int size(){
            return heapSize;
        }

        //堆中是否包含某个值
        public boolean contains(T data){
            return indexMap.containsKey(data);
        }

        //返回堆顶元素
        public T peek(){
            return heap.get(0);
        }

        //向堆中加入元素
        public void push(T data){
            heap.add(data);
            indexMap.put(data, heapSize);
            heapInsert(heapSize++);//调整堆
        }

        //弹出堆顶元素
        public T pop(){
            T res = heap.get(0);
            swap(0, heapSize-1);
            indexMap.remove(res);
            heap.remove(--heapSize);
            heapify(0);//调整堆
            return res;
        }

        //重新整理堆
        public void resign(T obj){
            heapInsert(indexMap.get(obj));
            heapify(indexMap.get(obj));
        }

        public void remove(T data){
            Integer dataIndex = indexMap.get(data);
            indexMap.remove(data);
            T endData = heap.get(heapSize - 1);
            heap.remove(--heapSize);
            if (data != endData){
                indexMap.put(endData, dataIndex);
                heap.set(dataIndex, endData);
                resign(endData);
            }
        }

        //返回堆中所有元素
        public List<T> getAllElements(){
            List<T> res = new ArrayList<>();
            for (int i = 0; i < heap.size(); i++) {
                res.add(heap.get(i));
            }
            return res;
        }
    }

    public static void main(String[] args) {
        myHeap<Integer> heap = new myHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        heap.push(3);
        heap.push(4);
        heap.push(1);
        heap.push(11);
        System.out.println(heap.peek());
        heap.remove(1);
        System.out.println(heap.peek());
    }

}
