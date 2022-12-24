package F03_dataStructureExercise;

/**
 * 用数组实现队列和栈
 */
public class T06_arrayImplementQueueStack {

    public static class myArrayQueue {

        public int limit;
        public int[] arr;
        public int startIndex;
        public int endIndex;
        public int size;

        public myArrayQueue(int limit) {
            this.limit = limit;
            arr = new int[limit];
            startIndex = 0;
            endIndex = 0;
            size = 0;
        }

        public int getNextIndex(int index) {
            return index < limit-1 ? index + 1 : 0;
        }

        public void push(int data){
            if (size == limit){
                throw  new RuntimeException("队列已满");
            }
            size++;
            arr[endIndex] = data;
            endIndex = getNextIndex(endIndex);
        }

        public int pop(){
            if (size == 0){
                throw  new RuntimeException("队列已空");
            }
            size--;
            int res = arr[startIndex];
            startIndex = getNextIndex(startIndex);
            return res;
        }
    }

    public static class myArrayStack{
        public int limit;
        public int[] arr;
        public int startIndex;
        public int endIndex;
        public int size;

        public myArrayStack(int limit) {
            this.limit = limit;
            arr = new int[limit];
            startIndex = 0;
            endIndex = 0;
            size = 0;
        }

        public int getNextIndex(int index) {
            return index < limit-1 ? index + 1 : 0;
        }

        public int getPreIndex(int index) {
            return index > 0 ? index - 1 : limit-1;
        }

        public void push(int data){
            if (size == limit){
                throw  new RuntimeException("队列已满");
            }
            size++;
            arr[endIndex] = data;
            endIndex = getNextIndex(endIndex);
        }

        public int pop(){
            if (size == 0){
                throw  new RuntimeException("队列已空");
            }
            size--;
            endIndex = getPreIndex(endIndex);
            int res = arr[endIndex];
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println("数组实现队列结构：");
        myArrayQueue myArrayQueue = new myArrayQueue(3);
        myArrayQueue.push(10);
        myArrayQueue.push(5);
        myArrayQueue.push(2);
        //System.out.println(myArrayQueue.pop());
        //myArrayQueue.push(56);
        System.out.println(myArrayQueue.pop());
        System.out.println(myArrayQueue.pop());
        System.out.println(myArrayQueue.pop());
        System.out.println("数组实现栈结构：");
        myArrayStack myArrayStack = new myArrayStack(3);
        myArrayStack.push(10);
        myArrayStack.push(5);
        myArrayStack.push(2);
        //System.out.println(myArrayStack.pop());
        //myArrayStack.push(56);
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.pop());
    }

}
