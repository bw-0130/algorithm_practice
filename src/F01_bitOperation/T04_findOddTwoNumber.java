package F01_bitOperation;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class T04_findOddTwoNumber {

    public static int[] find(int[] data){
        int temp = 0;
        for (int i = 0; i < data.length; i++) {
            temp ^= data[i];
        }

        int fTemp = ~temp + 1;
        int rightOne = temp & fTemp;
        int resA = 0;
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & rightOne) != 0){
                resA ^= data[i];
            }
        }
        int resB = resA ^ temp;
        return new int[] {resA, resB};
    }

    public static void main(String[] args) {
        int[] data = new int[] {1,1,5,5,8,8,12,12,12,4,4,2,2,0,0,0};
        int[] ints = find(data);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
