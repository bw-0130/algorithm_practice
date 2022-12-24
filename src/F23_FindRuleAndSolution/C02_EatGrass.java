package F23_FindRuleAndSolution;

import F10_graph.Graph;

/**
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里，有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草不管是牛还是羊，
 * 每一轮能吃的草量必须是：1,4,16,64·······（4的某次方）谁最先把草吃完谁获胜，假设牛和羊都绝顶聪明，
 * 都想赢都会做出理性的决定根据唯一的参数N，返回谁会赢？
 */
public class C02_EatGrass {

    //暴力方法
    public static String violenceMethod(int N) {
        if (N < 5) {
            return N == 0 || N == 2 ? "后手" : "先手";
        }
        int index = 1;
        while (index <= N) {
            if (violenceMethod(N - index).equals("后手")) {
                return "先手";
            }
            if (index <= (N / 4)) {//防止溢出
                index *= 4;
            } else {
                break;
            }
        }
        return "后手";
    }

    public static String engMethod(int N) {
        if (N % 5 == 0 || N % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("N: " + i + "  " + violenceMethod(i));
            if (!violenceMethod(i).equals(engMethod(i))){
                System.out.println("oops!");
            }
        }
        System.out.println("执行完成！");
    }

}
