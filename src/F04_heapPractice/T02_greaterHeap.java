package F04_heapPractice;

import java.util.*;

/**
 * 加强堆
 */
public class T02_greaterHeap {

    public static class myHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comp;

        public myHeap(Comparator<? super T> comp) {
            this.comp = comp;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
        }

        //交换方法
        private void swap(int a, int b) {
            T dataA = heap.get(a);
            T dataB = heap.get(b);
            indexMap.put(dataA, b);
            indexMap.put(dataB, a);
            heap.set(a, dataB);
            heap.set(b, dataA);
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

        //堆是否为空
        public boolean isEmpty() {
            return heapSize == 0;
        }

        //堆大小
        public int size(){
            return heapSize;
        }

        //堆中是否包含某个值
        public boolean contains(T obj){
            return  indexMap.containsKey(obj);
        }

        //返回堆顶元素
        public T peek(){
            return heap.get(0);
        }

        //向堆中加入元素
        public void push(T obj){
            heap.add(obj);
            indexMap.put(obj, heapSize);
            heapInsert(heapSize++);
        }

        //弹出堆顶元素
        public T pop(){
            T t = heap.get(0);
            swap(0, heapSize-1);
            indexMap.remove(t);
            heap.remove(--heapSize);
            heapify(0);
            return t;
        }

        //删除堆中元素
        public void remove(T obj){
            Integer objIndex = indexMap.get(obj);
            indexMap.remove(obj);
            T endObj = heap.get(heapSize - 1);
            heap.remove(--heapSize);
            if (endObj != obj){
                indexMap.put(endObj, objIndex);
                heap.set(objIndex, endObj);
                resign(endObj);
            }
        }

        //重新整理堆
        public void resign(T obj){
            heapInsert(indexMap.get(obj));
            heapify(indexMap.get(obj));
        }

        //返回堆中所有元素
        public List<T> getAllElements(){
            int size = heap.size();
            List<T> res = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                res.add(heap.get(i));
            }
            return res;
        }
    }

    public static class myComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        myHeap<Integer> myHeap = new myHeap<>(new myComparator());
        myHeap.push(10);
        myHeap.push(45);
        myHeap.push(50);
        myHeap.push(34);
        myHeap.push(57);
        System.out.println(myHeap.peek());
        //myHeap.remove(50);
        List<Integer> allElements = myHeap.getAllElements();
        for (int i = 0; i < allElements.size(); i++) {
            System.out.println(allElements.get(i));
        }
    }

}
