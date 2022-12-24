package F11_greedyAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱（利润）
 * K表示你只能串行的最多做K个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数
 * 思路：
 * 按项目花费建立小根堆，根据M找出所有能做的项目放入大根堆（根据利润），取出大根堆堆顶的项目做获得利润后继续上述循环直到项目数达到K停止，返回最后获得的利润。
 */
public class T04_IPO {

    public static class Info {
        public int cost;//项目花费
        public int profit;//利润

        public Info(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    //按项目花费正序比较器
    public static class myComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.cost - o2.cost;
        }
    }

    //按利润倒序排列
    public static class myComparator2 implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int job(int[] costs, int[] profits, int K, int M) {
        if (costs == null || costs.length == 0 || profits == null || profits.length == 0 || K <= 0) {
            return 0;
        }
        PriorityQueue<Info> minHeap = new PriorityQueue<>(new myComparator());
        PriorityQueue<Info> maxHeap = new PriorityQueue<>(new myComparator2());
        for (int i = 0; i < costs.length; i++) {
            minHeap.add(new Info(costs[i], profits[i]));
        }
        while (K != 0) {
            while (!minHeap.isEmpty() && minHeap.peek().cost<=M){
                maxHeap.add(minHeap.poll());
            }
            if (!maxHeap.isEmpty()){
                M += maxHeap.poll().profit;
            }else {
                return M;
            }
            K--;
        }
        return M;
    }

}
