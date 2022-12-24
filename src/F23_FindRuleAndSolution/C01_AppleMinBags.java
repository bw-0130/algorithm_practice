package F23_FindRuleAndSolution;

/**
 * 根据规律猜解法：先使用暴力方式找规律，再优化解法
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型的都有任意数量。
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须是满的。
 * 给定一个正整数N，返回至少需要使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1。
 */
public class C01_AppleMinBags {

    //暴力方法
    public static int violenceMethod(int N) {
        if (N < 0) {
            return -1;
        }
        int bag8 = N / 8;//需要容量为8袋子数量
        int rest = N - (bag8 * 8);//剩余苹果数
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + (rest / 6);
            } else {
                bag8 -= 1;
                rest += 8;
            }
        }
        return -1;
    }

    //找到规律后方法
    //前18次没有规律
    public static int engMethod(int N) {
        if ((N & 1) != 0) {//奇数
            return -1;
        }
        if (N < 18){
            return N == 0 ? 0 : (N == 6 || N == 8? 1 : (N == 12 || N == 14 || N == 16? 2 : -1));
        }
        return (N-18)/8 + 3;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("N1:" + i + " ~~ " + violenceMethod(i));
            if (violenceMethod(i) != engMethod(i)){
                System.out.println("oops！");
            }
        }
        System.out.println("执行结束！");
    }

}
