package problem_1_8;
/**
 * 描述
 * Implement pow(A, B) % C.In other words, given A, B and C, find (A^B)%C
 *
 * 输入
 * The first line of input consists number of the test cases. The following T lines consist of 3 numbers each separated
 * by a space and in the following order:A B C'A' being the base number, 'B' the exponent (power to the base number) and
 * 'C' the modular.Constraints:1 ≤ T ≤ 70,1 ≤ A ≤ 10^5,1 ≤ B ≤ 10^5,1 ≤ C ≤ 10^5
 *
 * 输入样例 1
 * 3
 * 3 2 4
 * 10 9 6
 * 450 768 517
 *
 * 输出
 * In each separate line print the modular exponent of the given numbers in the test case.
 * 输出样例 1
 * 1
 * 4
 * 34
 *
 * 思路：(A * B) % C = ((A % C) * B) % C
 */

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        int a;
        int b;
        int mod;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            a = sc.nextInt();
            b = sc.nextInt();
            mod = sc.nextInt();
            // Process
            calMod(a,b,mod);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }
    private static void calMod(int a,int b,int mod)
    {
        int res = 1;
        for(int i = 0; i < b; i++)
        {
            res = (res * a) % mod;
        }
        System.out.print(res);
    }
}

