package F01_bitOperation;

/**
 * 一个数组中有一种数出现了k次，其他数都出现了m次，m>1,k<m，找到出现了k次的数。要求，额外空间复杂度O（1），
 * 时间复杂度O（N）
 */
public class T05_findOddTwoNumber2 {

    public static int find(int[] data, int m, int k){
        int[] temp = new int[32];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (((data[j]>>i) & 1) !=0){
                    temp[i]++;
                }
            }
        }
        int res = 0;
        int t = 1;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i]%m != 0){
                res |= t<<i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = new int[]{3,3,3,4,4,4,5,5,5,6,6,6,13,13};
        System.out.println(find(data, 3, 2));
    }

}
