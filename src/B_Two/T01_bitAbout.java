package B_Two;

/**
 * 位运算相关
 */
public class T01_bitAbout {
    //主方法
    public static void main(String[] args) {
        int a = 10;
        int b = 30;
        //T011.swap(a,b);
        /*-----------------------------------*/
        int[] array1 = {1, 1, 1, 1, 5, 12, 5, 5, 5, 6, 6, 12, 8, 8, 8, 8, 12};
        //T012.findNum(array1);
        /*-----------------------------------*/
        int data = 7;
        //T013.job(data);
        /*-----------------------------------*/
        int[] array2 = {15, 1, 1, 1, 1, 5, 7, 5, 5, 15, 5, 6, 6, 7, 15, 8, 8, 8, 8, 7};
        //T014.job(array2);
        /*-----------------------------------*/
        int[] array3 = {15, 1, 1, 1, 1, 5, 5, 5, 15, 5, 15, 8, 8, 8, 8};
        int m = 4;
        int k = 3;
        T015.job(array3, m, k);
    }

    //两个数就交换
    public static class T011 {
        public static void swap(int a, int b) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
            System.out.println("a:" + a + " b:" + b);
        }
    }

    //一个数组中有一种数出现了奇数次，其他数都出现偶数次怎么找到并打印这个数
    public static class T012 {
        public static void findNum(int[] array) {
            int N = array.length;
            int res = 0;
            for (int i = 0; i < N; i++) {
                res = res ^ array[i];
            }
            System.out.println(res);
        }
    }

    //怎么把一个int类型的数，提取出最右侧的1来
    public static class T013 {
        public static void job(int data) {
            int res = data & (~data + 1);
            System.out.println(res);
        }
    }

    //一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
    public static class T014 {
        public static void job(int[] array) {
            int temp1 = 0;
            int N = array.length;
            for (int i = 0; i < N; i++) {
                temp1 = temp1 ^ array[i];
            }
            int getRightOne = temp1 & (~temp1 + 1);
            int res1 = 0;
            for (int i = 0; i < N; i++) {
                if ((array[i] & getRightOne) != 0) {
                    res1 = res1 ^ array[i];
                }
            }
            int res2 = temp1 ^ res1;
            System.out.println("res1:" + res1 + " res2:" + res2);
        }
    }

    //一个数组中有一种数出现了k次，其他数都出现了m次，m>1,k<m，找到出现了k次的数。要求，额外空间复杂度O（1） 时间复杂度O（N）
    public static class T015 {
        public static void job(int[] array, int m, int k) {
            int[] tempArray = new int[32];
            for (int i = 0; i < tempArray.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (((array[j] >> i) & 1) != 0) {
                        tempArray[i]++;
                    }
                }
            }

            int res = 0;
            int temp = 1;
            for (int i = 0; i < tempArray.length; i++) {
                if (tempArray[i] % m != 0 /*&& tempArray[i] % k == 0*/) {
                    System.out.print(tempArray[i]+" i："+i+" ");
                    res |= temp << i;
                }
            }
            System.out.println(res);
        }
    }

}
