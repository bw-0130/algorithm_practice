package A_One.T13;

/**
 * 给定一个正数1，裂开方法有一种{1}，给定一个正数2，裂开方法有两种{1、1}{2}，给定一个正数3，裂开方法有三种{1、1、1}{1、2}{3}
 * 给定一个正整数n，求裂开的方法数。
 */
public class C17 {

    public static int jobOne(int data){
        if (data == 0){
            return 0;
        }
        return process(1, data);
    }

    public static int process(int pre, int rest){
        if (rest == 0){
            return 1;
        }
        if (pre > rest){
            return 0;
        }
        int res = 0;
        for (int i = pre; i <= rest; i++) {
            res += process(i, rest-i);
        }
        return res;
    }

    public static int jobTwo(int data){
        if (data == 0){
            return 0;
        }
        int[][] dpMap = new int[data+1][data+1];
        for (int i = 1; i <= data; i++) {
            dpMap[i][0] = 1;
            dpMap[i][i] = 1;
        }
        for (int i = data-1; i >= 0; i--) {
            for (int j = i+1; j <= data; j++) {
                int res = 0;
                for (int first = i; first <= j; first++) {
                    res += dpMap[first][j-first];
                }
                dpMap[i][j] = res;
            }
        }
        return dpMap[1][data];
    }

    public static int jobThree(int data){
        if (data == 0){
            return 0;
        }
        int[][] dpMap = new int[data+1][data+1];
        for (int i = 1; i <= data; i++) {
            dpMap[i][0] = 1;
            dpMap[i][i] = 1;
        }
        for (int i = data-1; i >= 0; i--) {
            for (int j = i+1; j <= data; j++) {
                dpMap[i][j] = dpMap[i+1][j] + dpMap[i][j-i];
            }
        }
        return dpMap[1][data];
    }

    public static void main(String[] args) {
        int aim = 10;
        System.out.println(jobOne(aim));
        System.out.println(jobTwo(aim));
        System.out.println(jobThree(aim));
    }

}
