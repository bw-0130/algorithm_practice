package F26_State_Compress_Dynamic_Planning;

/**
 * 在"100 game"这个游戏中，两名玩家轮流选择从1到10的任意整数，累计整数和
 * 先使得累计整数和达到或超过100的玩家，即为胜者，如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从1到15的整数（不放回），直到累计整数和 >= 100
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和）
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * Leetcode题目：https://leetcode.com/problems/can-i-win/
 * 14/01/2023 bianwei
 */
public class C01_CanIWin {

    // 1~choose 拥有的数字
    // total 一开始的剩余
    // 返回先手会不会赢
    public static boolean jobOne(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        //arr[i] = -1 表示该位置值被用过
        int[] arr = new int[choose];
        for (int i = 0; i < choose; i++) {
            arr[i] = i + 1;
        }
        return processOne(arr, total);
    }

    public static boolean processOne(int[] arr, int rest) {
        if (rest < 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                int cur = arr[i];
                arr[i] = -1;
                boolean res = processOne(arr, rest - cur);
                arr[i] = cur;
                if (!res) {
                    return true;
                }
            }
        }
        return false;
    }

    //方法二使用位信息代替数组
    public static boolean jobTwo(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int status = 0;//二进制0表示未使用过1表示使用过
        return processTwo(choose, status, total);
    }

    public static boolean processTwo(int choose, int status, int rest) {
        if (rest<0){
            return false;
        }
        for (int i = 1; i <= choose; i++) {
            if (((1<<i)&status)==0){//当前位上为0
                //注：不用恢复现场因为int是值传递
                boolean res = processTwo(choose, (1 << i) | status, rest - i);
                if (!res){
                    return true;
                }
            }
        }
        return false;
    }

}