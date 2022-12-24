package F23_FindRuleAndSolution;

/**
 * 定义一种数：可以表示成若干(数量>1)连续正数和的数
 * 比如：
 * 5=2+3,5就是这样的数
 * 12=3+4+5,12就是这样的数
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * 2=1+1,2也不是，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数。
 */
public class C03_MSumToN {

    public static boolean violenceMethod(int N) {
        for (int i = 1; i <= N; i++) {
            int sum = i;
            for (int j = sum + 1; j <= N; j++) {
                if (sum + j > N) {
                    break;
                }
                if (sum + j == N) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    //规律：2的N次幂为false
    // 判断2的n次幂：取N最右侧的1，与N相等标识N的二进制标识只有一个1则是2的N次幂的数
    public static boolean engMethod(int N){
        return N != (N & (~N +1));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println("N: " + i + "  " + engMethod(i));
            if (violenceMethod(i) != engMethod(i)){
                System.out.println("oops!");
            }
        }
        System.out.println("执行结束！");
    }

}
