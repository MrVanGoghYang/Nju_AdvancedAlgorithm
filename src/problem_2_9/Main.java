package problem_2_9;
/***
 漆狗屋
 描述
 Dilpreet wants to paint his dog- Buzo's home that has n boards with different lengths[A1, A2,..., An].
 He hired k painters for this work and each painter takes 1 unit time to paint 1 unit of the board.
 The problem is to find the minimum time to get this job done under the constraints that any painter will only paint continuous sections of boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
 Constraints:1<=T<=100,1<=k<=30,1<=n<=50,1<=A[i]<=500

 输入
 The first line consists of a single integer T, the number of test cases. For each test case, the first line contains an integer k denoting the number of painters and integer n denoting the number of boards.
 Next line contains n- space separated integers denoting the size of boards.

 输出
 For each test case, the output is an integer displaying the minimum time for painting that house.

 输入样例 1
 2
 2 4
 10 10 10 10
 2 4
 10 20 30 40

 输出样例 1
 20
 60

 思路： 同1-5
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main
{
    private static int[] prime = new int[1000000];
    private static int primeIdx;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            long max = sc.nextLong();
            // Process
            calPrimeArr((int)Math.sqrt(max));
            System.out.print(getRequiredCnt(max));
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    // 求[2,max]内的素数存入prime数组,此处采用埃及筛选，即将所有素数的倍数均改为非素数
    private static void calPrimeArr(int max)
    {
        // 用于存储是否为素数，先初始化全为0即首先假设全为素数，后续再对素数的倍数修改为1代表非素数
        int[] isPrime = new int[max + 1];
        // 0 1非素数
        isPrime[0] = 1;
        isPrime[1] = 1;
        primeIdx = 0;
        for(int i = 2; i <= max; i++)
        {
            if(isPrime[i] == 0)
            {
                // 将i记录进素数数组
                prime[primeIdx++] = i;
                // 素数的倍数为非素数
                for(int j = 2; i * j <= max; j++)
                {
                    isPrime[i * j] = 1;
                }
            }
        }
    }

    private static int getRequiredCnt(long max)
    {
        int res = 0;
        // 1.计算两个素数的平方积是否小于max,采用双指针扫描对所有素数两两互异组合
        for(int i = 0; i < primeIdx - 1; i++)
        {
            for(int j = i + 1; j < primeIdx; j++)
            {
                if(Math.pow(prime[i], 2) * Math.pow(prime[j], 2) < max)
                    res++;
                else
                    break;
            }
        }
        // 2.计算素数的8次方是否小于max
        for(int i = 0; i < primeIdx && Math.pow(prime[i], 8) < max; i++)
            res++;
        return res;
    }
}
