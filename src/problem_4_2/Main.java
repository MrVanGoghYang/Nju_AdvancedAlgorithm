package problem_4_2;
/***
 矩阵计算
 描述
 Let's define a Series Whose recurrence formula is as follows :
 C(n)= 3C(i-1) + 4C(i-2) + 5C(i-3) + 6C(i-4)
 C(0)= 2
 C(1)= 0
 C(2)= 1
 C(3)= 7
 Now based on this Series a Matrix Mi,j of size nn is to be formed.The top left cell is(1,1) and the bottom right corner is (n,n).
 Each cell (i,j) of the Matrix contains either 1 or 0. If C( (i*j)^3 ) is odd, Mi,j is 1, otherwise, it's 0.Count the total number of ones in the Matrix.
 输入
 First Line Of the input will contain an integer 'T'- the number of test cases . Each of the next 'T' lines consists of an integer 'n'.-denoting the size of the matrix.
 Constraints :
 1 ≤ T ≤ 1000
 1 ≤ n ≤ 1000
 输出
 For each test case, output a single Integer -the taste value fo the dish of size-n*n.
 输入样例 1
 1
 2
 输出样例 1
 0

 思路：数学周期规律题，打印1-100的C(n)可发现C(n)的奇偶具有周期性，呈现 偶 偶 奇 奇 奇 偶 奇 的变化，那么计算C(n)是奇数还是偶数只需要看 n % 7即可；
      (a * b) % M = (a % M * b % M) % M,由此可快速计算 (i * i) ^ 3 % 7由此可快速知道[i][j]位置是填0还是1，若为1则结果加即可。
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int dim = sc.nextInt();
            // Process
            System.out.print(dp(dim));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }

    }

    private static int dp(int dim)
    {
        int[] oddArr = new int[]{0, 0, 1, 1, 1, 0, 1};
        int[][] matrix = new int[dim][dim];
        int res = 0;
        for(int i = 0; i < dim; i++)
        {
            for(int j = i; j < dim; j++)
            {
                int xMod = (i + 1) * (j + 1) % 7;
                int powMod = ((xMod * xMod) % 7 * xMod) % 7;
                matrix[i][j] =oddArr[powMod];
                if(matrix[i][j] == 1)
                {
                    if(i == j)
                        res += 1;
                    else
                        res += 2;
                }
            }
        }
        return res;
    }
}
