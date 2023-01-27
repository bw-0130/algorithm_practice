package A_One;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现偶数次怎么找到并打印这个数
 * 24/01/2023 bianwei
 */
public class C2 {
    public static void job(int[] array){
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            res ^= array[i];
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        int[] array = {1,2,2,4,4,7,8,8,7,1,8};
        job(array);
    }
}