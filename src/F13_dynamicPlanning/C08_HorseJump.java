package F13_dynamicPlanning;

/**
 * 请同学们自行搜索或者想象一个象棋棋盘
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是（0,0）位置
 * 那么整个棋盘的横坐标上9条线、纵坐标上10条线的区域
 * 给你三个参数x,y,k
 * 返回“马”从（0,0）位置触发，必须走k步
 * 最后落在（x，y）上的方法数有多少种？
 */
public class C08_HorseJump {

    public static int jobOne(int x, int y, int k) {
        return process(0, 0, x, y, k);
    }

    public static int process(int i, int j, int x, int y, int rest) {
        if (i < 0 || i > 8 || j < 0 || j > 9) {
            return 0;
        }
        if (rest == 0) {
            return i == x && j == y ? 1 : 0;
        }
        int way = process(i + 2, j + 1, x, y, rest - 1);
        way += process(i + 2, j - 1, x, y, rest - 1);
        way += process(i + 1, j + 2, x, y, rest - 1);
        way += process(i + 1, j - 2, x, y, rest - 1);
        way += process(i - 2, j + 1, x, y, rest - 1);
        way += process(i - 2, j - 1, x, y, rest - 1);
        way += process(i - 1, j + 2, x, y, rest - 1);
        way += process(i - 1, j - 2, x, y, rest - 1);
        return way;
    }

    public static int jobTwo(int x, int y, int k){
        int[][][] dpMap = new int[9][10][k+1];
        dpMap[x][y][0] = 1;
        for (int z = 1; z <= k; z++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    int way = getInt(i + 2, j + 1, z - 1, dpMap);
                    way += getInt(i + 2, j - 1, z - 1, dpMap);
                    way += getInt(i + 1, j + 2, z - 1, dpMap);
                    way += getInt(i + 1, j - 2, z - 1, dpMap);
                    way += getInt(i - 2, j + 1, z - 1, dpMap);
                    way += getInt(i - 2, j - 1, z - 1, dpMap);
                    way += getInt(i - 1, j + 2, z - 1, dpMap);
                    way += getInt(i - 1, j - 2, z - 1, dpMap);
                    dpMap[i][j][z] = way;
                }
            }
        }
        return dpMap[0][0][k];
    }

    public static int getInt(int i, int j, int z, int[][][] dpMap){
        if (i < 0 || i > 8 || j < 0 || j > 9) {
            return 0;
        }
        return dpMap[i][j][z];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jobOne(x, y, step));
        System.out.println(jobTwo(x,y,step));
    }

}
