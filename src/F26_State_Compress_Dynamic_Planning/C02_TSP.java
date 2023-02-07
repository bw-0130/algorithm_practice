package F26_State_Compress_Dynamic_Planning;

import java.util.ArrayList;
import java.util.List;

/**
 * 有N个城市，任何两个城市之间都有距离，任何一座城市到自己的距离都为0。
 * 所有点到点的距离都存在一个N*N的二维数组matrix里，也就是整张图由邻接矩阵表示。
 * 现在要求旅行商从k城市出发必须经过每一个城市且只在一个城市逗留一次，最后回到出发的k城市，返回总距离最短的路的距离？
 * 参数给定一个matrix，给定k。
 */
public class C02_TSP {

    public static int jobOne(int[][] matrix) {
        int N = matrix.length;//城市数量
        //list.get(i) != null 城市在集合中
        //list.get(i) == null 城市不在集合中
        List<Integer> list = new ArrayList<>();
        //初始化集合
        for (int i = 0; i < N; i++) {
            list.add(1);
        }
        return processOne(matrix, list, 0);
    }

    public static int processOne(int[][] matrix, List<Integer> list, int start) {
        int cityNum = 0;//城市数量
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                cityNum++;
            }
        }
        //只剩最后一个城市则回到开始位置
        if (cityNum == 1) {
            return matrix[start][0];
        }
        list.set(start, null);
        int ans = Integer.MAX_VALUE;
        //递归其他分支情况
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                int cur = matrix[start][i] + processOne(matrix, list, i);
                ans = Math.min(ans, cur);
            }
        }
        list.set(start, 1);//还原现场
        return ans;
    }

    //使用位信息代替list
    public static int jobTwo(int[][] matrix) {
        int N = matrix.length;
        int allCity = (1 << N) - 1;//1:存在 0：不存在
        return processTwo(matrix, allCity, 0);
    }

    public static int processTwo(int[][] matrix, int cityStatus, int start) {
        //base case 当cityStatus只剩一个城市时
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        cityStatus &= (~(1 << start));//去掉start位置的1
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if ((cityStatus & (1 << i)) != 0) {//start位置为1
                int cur = matrix[start][i] + processTwo(matrix, cityStatus, i);
                ans = Math.min(ans, cur);
            }
        }
        cityStatus |= (1 << start);//还原现场
        return ans;
    }

    //傻缓存方法
    public static int jobThree(int[][] matrix) {
        int N = matrix.length;
        int allCity = (1 << N) - 1;
        int[][] dp = new int[(1 << N)][N];//缓存表
        //初始化dp表
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return processThree(matrix, allCity, 0, dp);
    }

    public static int processThree(int[][] matrix, int cityStatus, int start, int[][] dp) {
        if (dp[cityStatus][start] != -1) {
            return dp[cityStatus][start];
        }
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            dp[cityStatus][start] = matrix[start][0];
        } else {
            // 把start位的1去掉，
            cityStatus &= (~(1 << start));
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                if ((cityStatus & (1 << i)) != 0) {//start位置为1
                    int cur = matrix[start][i] + processTwo(matrix, cityStatus, i);
                    ans = Math.min(ans, cur);
                }
            }
            cityStatus |= (1 << start);//还原现场
            dp[cityStatus][start] = ans;
        }
        return dp[cityStatus][start];
    }

    //动态规划方法
    public static int jobFour(int[][] matrix) {
        int N = matrix.length;
        int allCity = 1 << N;
        int[][] dpMap = new int[allCity][N];
        for (int status = 0; status < allCity; status++) {
            for (int start = 0; start < N; start++) {
                if ((status & (1 << start)) != 0) {//如果j位置不是0
                    if (status == (status & (~status + 1))) {
                        dpMap[status][start] = matrix[start][0];
                    } else {
                        int ans = Integer.MAX_VALUE;
                        int prei = status & (~(1 << start));
                        for (int i = 0; i < N; i++) {
                            if ((prei & (1 << i)) != 0) {
                                int cur = matrix[start][i] + dpMap[prei][i];
                                ans = Math.min(ans, cur);
                            }
                        }
                        dpMap[status][start] = ans;
                    }
                }
            }
        }
        return dpMap[allCity - 1][0];
    }

    //测试用
    public static int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 10; i++) {
            int[][] matrix = generateGraph(len, value);
            int one = jobOne(matrix);
            int two = jobTwo(matrix);
            int three = jobThree(matrix);
            int four = jobFour(matrix);
            if (one != two || two != three || three != four) {
                System.out.println(one + "~" + two + "~" + three + "~" + four);
                System.out.println("oops!");
            }
        }
        System.out.println("功能测试结束");
    }

}
