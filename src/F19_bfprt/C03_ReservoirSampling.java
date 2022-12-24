package F19_bfprt;

/**
 * 蓄水池算法：
 * 假设有一个源源不断吐出不同球的机器，
 * 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉。如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 */
public class C03_ReservoirSampling {
    //等概率获取1~i中一个数
    public static int getRadom(int i){
        return (int) (Math.random()*i)+1;
    }

    public static void main(String[] args) {
        int times = 10000;
        int ballNum = 17;
        int[] count = new int[ballNum+1];
        for (int i = 0; i < times; i++) {
            int[] backage = new int[10];//容量为10的袋子
            int index = 0;
            for (int j = 1; j <= ballNum; j++) {
                if (j<=10){
                    backage[index++] = j;
                }else {
                    if (getRadom(j)<=10){
                        int v = (int) (Math.random() * 10);
                        backage[v] = j;
                    }
                }
            }
            for (int j = 0; j < backage.length; j++) {
                count[backage[j]]++;
            }
        }
        for (int i = 0; i <= ballNum; i++) {
            System.out.println(count[i]);
        }
    }


}
