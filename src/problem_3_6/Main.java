package problem_3_6;
/***
 牛的繁殖问题
 描述
 Cows in the FooLand city are interesting animals. One of their specialties is related to producing offsprings.
 A cow in FooLand produces its first calve (female calf) at the age of two years and proceeds to produce other calves (one female calf a year).
 Now the farmer Harold wants to know how many animals would he have at the end of N years, if we assume that none of the calves die, given that initially, he has only one female calf?
 explanation:At the end of 1 year, he will have only 1 cow, at the end of 2 years he will have 2 animals(one parent cow C1 and other baby calf B1 which is the offspring of cow C1).
 At the end of 3 years, he will have 3 animals (one parent cow C1 and 2 female calves B1 and B2, C1 is the parent of B1 and B2).
 At the end of 4 years, he will have 5 animals (one parent cow C1, 3 offsprings of C1 i.e. B1, B2, B3 and one offspring of B1).

 输入
 The first line contains a single integer T denoting the number of test cases. Each line of the test case contains a single integer N as described in the problem.

 输出
 For each test case print in new line the number of animals expected at the end of N years modulo 10^9 + 7.

 输入样例 1
 2
 2
 4

 输出样例 1
 2
 5

 思路：Fibnacci数列，测试用例有问题，使用网上找打的可以AC的代码打印 1 - 1000 时的结果发现AC代码输出的很多结果都超过了 10^9 + 7，应该是测试用例的问题并未取模？？不纠结了睡觉

 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Main
{
    private static final int MOD = 1000000007;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            long N = Long.valueOf(sc.nextLine());
            // Process
//            for(int i = 2; i <= N; i++)
//            {
//                long res = findNthYearCows(i);
//                long res2 = pow(i);
//
//                System.out.print("n = " + i + " " + res + " " + res2);
//                System.out.print("\n");
//            }
            long res = findNthYearCows(N);
            System.out.print(res);
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static long findNthYearCows(long n)
    {
        long fx1 = 1;
        long fx2 = 2;
        if(n <= 2)
            return n;
        for(int i = 3; i <= n; i++)
        {
            long item = fx1;
            fx1 = fx2 % MOD;
            fx2 = (item % MOD + fx2 % MOD) % MOD;
        }
        return fx2;
    }

    private static long pow(long n) {
        long[][] multiplier = {{1, 1}, {1, 0}};
        long[][] ans = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = multiply(ans, multiplier);
            }
            n >>= 1;
            multiplier = multiply(multiplier, multiplier);
        }
        return ans[0][0];
    }

    private static long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] ans = new long[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    ans[i][j] += a[i][k] * b[k][j] % MOD;
                }
            }
        }
        return ans;
    }


}
