package F01_bitOperation;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现偶数次怎么找到并打印这个数
 */
public class T02_findOddNumber {

    public static int findNum(int[] data){
        int res = data[0];
        for (int i = 1; i < data.length; i++) {
            res = res ^ data[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {1,1,3,3,4,4,5,5,6,6,6};
        System.out.println(findNum(data));
    }

}
