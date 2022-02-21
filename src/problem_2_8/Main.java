package problem_2_8;
/***
 有9个因数的数
 描述
 Find the count of numbers less than N having exactly 9 divisors
 1<=T<=1000,1<=N<=10^12
 输入
 First Line of Input contains the number of testcases. Only Line of each testcase contains the number of members N in the rival gang.
 输出
 Print the desired output.

 输入样例 1
 2
 40
 5

 输出样例 1
 1
 0

 思路： 对于这一类求 有n个因数的数 的题目，其本质是数学问题，即分解质因数 —— 任何一个正整数均可以分解为任意多个质数的次方乘积的形式，0和1不是质数
       ！！！！！Eg: N = P1 ^ a1 * P2 ^ a2 * ... * Pn ^ an,其中P1到Pn为互不相同的质数，a1到an为各自的次方数，分解之后 N 有 (a1 + 1) * (a2 + 1) * ... *(an + 1)个因数！！！！！
        因此若求10个因数的数，要么：（1）(a1 + 1)*(a2 + 1)*...*(an + 1) = 10，而积为10的组合数只有2 * 5，因此a1 = 1, a2 = 4，那么N = P1 ^ 1 * P2 ^ 4； 要么: (2) N = P1 ^ 9，则N的10个因数为P1 ^ 0, P1 ^ 1,...P1 ^ 9共10个；
        求某个范围内的素数的方法：（1）埃及筛选（假设初始全为素数，0 1除外，若扫描到为素数则将所有倍数改为非素数） （2）欧式筛选（设置非素数标记数组和储存素数数组，对当前扫描的数 * 已有素数表中所有素数 设为非素数，和素数的倍数为非素数一样的道理）
        https://blog.csdn.net/timelessx_x/article/details/112059930
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

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
