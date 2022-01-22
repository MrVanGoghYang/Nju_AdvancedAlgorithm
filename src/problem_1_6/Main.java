package problem_1_6;
/**
 *描述
 * There are Infinite People Standing in a row, indexed from 1.
 * A person having index 'i' has strength of (i*i).
 * You have Strength 'P'. You need to tell what is the maximum number of People You can Kill With your Strength P.
 * You can only Kill a person with strength 'X' if P >= 'X' and after killing him, Your Strength decreases by 'X'.
 *
 * 输入
 * First line contains an integer 'T' - the number of testcases,Each of the next 'T' lines contains an integer 'P'-
 * Your Power.Constraints:1<=T<=100001<=P<=1000000000000000
 *
 * 1
 * 14
 *
 * 输出
 * For each testcase Output The maximum Number of People You can kill.
 *
 * 3
 */

/**
 * 计算递增和比较大小：TLE超出时间限制
 * 改进：思考使用二分法查找该数  那么查找范围为[1,根号strength]，使用二分查找，结合平方和公式 i * (i + 1) * (2 * i + 1) / 6，考虑平方和超出long存储范围
 */

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        long strength = 0;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();

        int[] res = new int[caseNum];
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            strength = sc.nextLong();
            // Process
            System.out.print(binSearch(strength));
            //System.out.print(increaseSum(strength));
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static long increaseSum(long strength)
    {
        long i = 0;
        while(strength > 0)
        {
            ++i;
            strength -= i * i;
        }
        return strength == 0 ? i : i - 1;
    }

    private static long binSearch(long strength)
    {
        long right = (long)Math.sqrt((double) strength);
        long left = 1;
        while(left < right)
        {
            long mid = left + (right - left) / 2;
            long powSum = mid * (mid + 1) * (2 * mid + 1) / 6;
            if(powSum > 0 && powSum < strength)
            {
                left = mid + 1;
            }
            else
            {
                right = mid;
            }
        }
        return left * (left + 1) * (2 * left + 1) / 6 == strength ? left : left - 1;
    }
}

