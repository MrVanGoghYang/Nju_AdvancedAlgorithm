package algorithm_1_5;
/**
 *描述
 * Dilpreet wants to paint his dog- Buzo's home that has n boards with different lengths[A1, A2,..., An]. He hired k painters for this work and each painter takes 1 unit time to paint 1 unit of the board.The problem is to find the minimum time to get this job done under the constraints that any painter will only paint continuous sections of boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
 *
 * 输入
 * The first line consists of a single integer T, the number of test cases. For each test case, the first line contains an integer k denoting the number of painters and integer n denoting the number of boards. Next line contains n- space separated integers denoting the size of boards.Constraints:1<=T<=1001<=k<=301<=n<=501<=A[i]<=500
 *
 * 2
 * 2 4
 * 10 10 10 10
 * 2 4
 * 10 20 30 40
 *
 * 输出
 * For each test case, the output is an integer displaying the minimum time for painting that house.
 *
 * 20
 * 60
 */

/**
 * 题解410. 分割数组的最大值：https://leetcode-cn.com/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-liweiwei1419-4/
 * 二分查找  搜索空间为 [max,sum]，只需要找到最小值即可，那么从可能的取值范围中找。对于某个值k（如初始为中间值），看是否有划分方法可以满足
 * 划分之后各个子数组和的最大值在该值范围内（即<k）。若存在，则这个值较大，继续递减；若不存在，则说明上一个值已经是最小。
 */
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        int divideNum = 0;
        int arrLength = 0;
        int[] arr;
        int max = 0;
        int sum = 0;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        int[] res = new int[caseNum];
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            max = 0;
            sum = 0;
            divideNum = sc.nextInt();
            arrLength = sc.nextInt();
            arr = new int[arrLength];
            for(int arrIndex = 0; arrIndex < arrLength; arrIndex++)
            {
                arr[arrIndex] = sc.nextInt();
                sum += arr[arrIndex];
                max = Math.max(max, arr[arrIndex]);
            }

            // Process
            int left = max;
            int right = sum;
            while(left < right)
            {
                int mid = left + (right - left) / 2;
                if(canSplit(mid,arr,divideNum))     // if can use "mid" to split "arr" to "divideNum" sub-arrays.
                {
                    right = mid;
                }
                else
                {
                    left = mid + 1;
                }
            }
            res[caseIndex] = left;
            System.out.print(res[caseIndex]);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    public static boolean canSplit(int value,int[] arr,int divideNum)
    {
        int sum = 0;
        int count = 1;
        for(int i = 0; i < arr.length; i++)
        {
            if(sum + arr[i] <= value)
            {
                sum += arr[i];
            }
            else
            {
                count++;
                sum = arr[i];
            }
        }
        return count <= divideNum;
    }
}
