package F01_bitOperation;

/**
 * 不使用额外变量交换两个数的值
 */
public class T01_numericaExchange {

    public static void swap(int a, int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a:"+a+" b:"+b);
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 40;
        swap(a, b);
    }

}
