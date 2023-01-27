package A_One;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 * 25/01/2023 bianwei
 */
public class C4 {

    public static void job(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }

        int rightOne = temp & (~temp + 1);
        int res1 = 0;
        for (int i = 0; i < array.length; i++) {
            if ((rightOne & array[i]) != 0) {
                res1 ^= array[i];
            }
        }
        int res2 = temp ^ res1;
        System.out.println("res1:"+res1+" res2:"+res2);
    }

    public static void main(String[] args) {
        int[] array = {1,1,2,2,3,3,3,4,4,5,5,6,6,7,7,7,8,8};
        job(array);
    }

}