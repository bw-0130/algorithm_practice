package B_Two;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 链表相关
 */
public class T03_linkAbout {
    //单链表节点
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //双向链表节点
    public static class doubleNode {
        public int value;
        public doubleNode next;
        public doubleNode pre;

        public doubleNode(int value) {
            this.value = value;
        }
    }

    public static Node createLink(int len, int maxValue) {
        Node head = new Node((int) (Math.random() * maxValue + 1));
        Node pre = head;
        for (int i = 0; i < len; i++) {
            Node cur = new Node((int) (Math.random() * maxValue + 1));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static void printLink(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static doubleNode[] createDoubleLink(int len, int maxValue) {
        doubleNode head = new doubleNode((int) (Math.random() * maxValue + 1));
        doubleNode pre = head;
        for (int i = 0; i < len; i++) {
            doubleNode cur = new doubleNode((int) (Math.random() * maxValue + 1));
            cur.pre = pre;
            pre.next = cur;
            pre = cur;
        }
        return new doubleNode[]{head, pre};
    }

    public static void printDoubleLink(doubleNode[] data) {
        doubleNode head = data[0];
        doubleNode end = data[1];
        System.out.print("正向打印： ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        System.out.print("反向打印： ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node link = createLink(10, 100);
        System.out.print("原始单链表打印：");
        printLink(link);
        doubleNode[] doubleLink = createDoubleLink(10, 100);
        System.out.println("原始双向链表打印：");
        printDoubleLink(doubleLink);

        //printLink(linkReversal.singlelink(link));
        /*---------------------------------------------*/
        //printDoubleLink(linkReversal.doubleLink(doubleLink));
        /*---------------------------------------------*/
        //printLink(deleteLink.job(link, 10));
        /*---------------------------------------------*/
        /*doubleLinkQueue queue = new doubleLinkQueue();
        queue.push(50);
        queue.push(40);
        queue.push(30);
        queue.printQueue();
        System.out.println(queue.poll());
        queue.printQueue();*/
        /*---------------------------------------------*/
        /*doubleLinkStack stack = new doubleLinkStack();
        stack.push(50);
        stack.push(40);
        stack.push(30);
        stack.printStack();
        System.out.println(stack.poll());*/
        /*---------------------------------------------*/
       /* arrayQueue queue = new arrayQueue(2);
        queue.push(10);
        queue.push(20);
        System.out.println(queue.poll());*/
        /*---------------------------------------------*/
        /*arrayStack stack = new arrayStack(2);
        stack.push(10);
        stack.push(20);
        System.out.println(stack.poll());*/
        /*---------------------------------------------*/
        /*minStack stack = new minStack();
        System.out.println(stack.getMinData());
        stack.push(90);
        stack.push(50);
        stack.push(30);
        System.out.println(stack.getMinData());
        stack.pop();
        System.out.println(stack.getMinData());*/
        /*---------------------------------------------*/
        /*usiq queue = new usiq();
        queue.push(10);
        queue.push(20);
        queue.push(40);
        System.out.println(queue.pop());
        System.out.println(queue.pop());*/
        /*---------------------------------------------*/
        uqis stack = new uqis();
        stack.push(10);
        stack.push(20);
        stack.push(40);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }

    public static class linkReversal {
        public static Node singlelink(Node head) {
            Node pre = null;
            Node next = null;
            while (head != null) {
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }

        public static doubleNode[] doubleLink(doubleNode[] data) {
            doubleNode end = data[0];
            doubleNode pre = null;
            doubleNode next = null;
            doubleNode cur = end;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                cur.pre = next;
                pre = cur;
                cur = next;
            }
            return new doubleNode[]{pre, end};
        }
    }

    public static class deleteLink {
        public static Node job(Node head, int deleteValue) {
            while (head != null) {
                if (head.value != deleteValue) {
                    break;
                }
                head = head.next;
            }
            Node cur = head;
            Node pre = head;
            while (cur != null) {
                if (cur.value == deleteValue) {
                    pre.next = cur.next;
                } else {
                    pre = cur;
                }
                cur = cur.next;
            }
            return head;
        }
    }

    public static class doubleLinkQueue {
        public int size;
        public doubleNode head;
        public doubleNode end;

        public void push(int value) {
            doubleNode cur = new doubleNode(value);
            if (size == 0) {
                head = cur;
                end = cur;
            } else {
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
            size++;
        }

        public int poll() {
            if (end == null) {
                return -1;
            }
            doubleNode pre = end.pre;
            doubleNode res = end;
            end = pre;
            end.next = null;
            res.pre = null;
            size--;
            return res.value;
        }

        public int getSize() {
            return size;
        }

        public void printQueue() {
            doubleNode cur = head;
            while (cur != null) {
                System.out.print(cur.value + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static class doubleLinkStack {
        public int size;
        public doubleNode head;
        public doubleNode end;

        public void push(int value) {
            doubleNode cur = new doubleNode(value);
            if (size == 0) {
                head = cur;
                end = cur;
            } else {
                end.next = cur;
                cur.pre = end;
                end = cur;
            }
            size++;
        }

        public int poll() {
            if (end == null) {
                return -1;
            }
            doubleNode res = end;
            end = res.pre;
            end.next = null;
            res.pre = null;
            size--;
            return res.value;
        }

        public int getSize() {
            return size;
        }

        public void printStack() {
            doubleNode cur = head;
            while (cur != null) {
                System.out.print(cur.value + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static class arrayQueue {
        public int limit;
        public int[] array;
        public int size;
        public int startIndex;
        public int endIndex;

        public arrayQueue(int limit) {
            this.limit = limit;
            array = new int[limit];
            size = 0;
            startIndex = 0;
            endIndex = 0;
        }

        public int getNextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列已满！");
            }
            size++;
            array[endIndex] = value;
            endIndex = getNextIndex(endIndex);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列已空！");
            }
            size--;
            int res = array[startIndex];
            startIndex = getNextIndex(startIndex);
            return res;
        }

    }

    public static class arrayStack {
        public int limit;
        public int size;
        public int[] array;
        public int startIndex;
        public int endIndex;

        public arrayStack(int limit) {
            this.limit = limit;
            array = new int[limit];
            size = 0;
            startIndex = 0;
            endIndex = 0;
        }

        public int getNextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }

        public int getPreIndex(int index) {
            return index > 0 ? index - 1 : limit - 1;
        }

        public void push(int value){
            if (size == limit){
                throw new RuntimeException("栈已满！");
            }
            size++;
            array[endIndex] = value;
            endIndex = getNextIndex(endIndex);
        }
        public int poll(){
            if (size==0){
                throw new RuntimeException("栈已空！");
            }
            size--;
            endIndex = getPreIndex(endIndex);
            int res = array[endIndex];
            return res;
        }
    }

    public static class minStack{
        public Stack<Integer> data = new Stack<>();
        public Stack<Integer> minData = new Stack<>();
        public void push(int value){
            data.push(value);
            if (!minData.isEmpty()){
                if (minData.peek()>value){
                    minData.push(value);
                }
            }else {
                minData.push(value);
            }
        }
        public Integer pop(){
            if (data.isEmpty()){
                return null;
            }
            Integer res = data.pop();
            if (!minData.isEmpty()){
                if (minData.peek()==res){
                    minData.pop();
                }
            }
            return res;
        }
        public Integer peek(){
            return data.peek();
        }
        public Integer getMinData(){
            if (minData.isEmpty()){
                return null;
            }
            return minData.peek();
        }
    }

    public static class usiq{
        private Stack<Integer> stackGet = new Stack<>();
        private Stack<Integer> stackSet = new Stack<>();
        private void move(){
            while (!stackSet.isEmpty()){
                stackGet.push(stackSet.pop());
            }
        }
        public void push(int value){
            stackSet.push(value);
        }
        public int pop(){
            move();
            return stackGet.pop();
        }
    }

    public static class uqis{
        private Queue<Integer> queue = new LinkedList<>();
        private Queue<Integer> help = new LinkedList<>();
        public void push(int value){
            queue.offer(value);
        }
        public Integer pop(){
            if (queue.isEmpty()){
                return null;
            }
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            int res = queue.poll();
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }
        public Integer peek(){
            if (queue.isEmpty()){
                return null;
            }
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            int res = queue.poll();
            help.offer(res);
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }
    }

}
