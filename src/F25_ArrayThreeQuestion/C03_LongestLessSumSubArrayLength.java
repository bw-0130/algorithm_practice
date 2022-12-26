package F25_ArrayThreeQuestion;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 * 返回其长度
 */
public class C03_LongestLessSumSubArrayLength {

    public static int jobOne(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] minSum = new int[arr.length];//以i开头往右扩能获得的最小累加和
        int[] minSumEnd = new int[arr.length];//以i开头往右扩能获得的最小累加和的有边界下标
        //初始化辅助数组
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSum[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] < 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        //正式逻辑开始
        int end = 0;//迟迟扩不进来的开头位置
        int sum = 0;
        int ans = 0;//最终返回的结果
        for (int i = 0; i < arr.length; i++) {
            //1、end不能大于数据最大长度
            //2、sum加上下一块不能超过k，如果超过不扩进来 i缩短
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            ans = Math.max(ans, end - i);//收集答案
            if (end > i) {//窗口中有数
                sum -= arr[i];
            } else {//窗口中没有数了
                end = i + 1;//因为i即将++所以end必须保持与i一致或比i大才能保证窗口
            }

        }
        return ans;
    }

    //对数器（暴力解）
    public static int rightJob(int[] arr, int k) {
        int[] maxSum = new int[arr.length + 1];
        int sum = 0;
        maxSum[0] = sum;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            maxSum[i + 1] = Math.max(sum, maxSum[i]);
        }
        sum = 0;//重置
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(maxSum, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (l <= r) {
            mid = (r - l) / 2;
            if (arr[mid] >= num) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    //测试用
    public static int[] createArray(int len, int value) {
        int size = (int) (Math.random() * len + 1);
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int) (Math.random() * value + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int k = 10;
        for (int i = 0; i < 200; i++) {
            int[] array = createArray(10, 30);
            int one = jobOne(array, k);
            int two = rightJob(array, k);
            if (one != two) {
                System.out.println("oops!");
            }
        }
        System.out.println("执行完成！");
    }

}
