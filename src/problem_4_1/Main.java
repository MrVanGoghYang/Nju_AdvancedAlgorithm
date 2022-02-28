package problem_4_1;
/***
 最小化初始点
 描述
 Given a grid with each cell consisting of positive, negative or no points i.e, zero points. We can move across a cell only if we have positive points ( > 0 ).
 Whenever we pass through a cell, points in that cell are added to our overall points. We need to find minimum initial points to reach cell (m-1, n-1) from (0, 0) by following these certain set of rules :
 1.From a cell (i, j) we can move to (i+1, j) or (i, j+1).
 2.We cannot move from (i, j) if your overall points at (i, j) is <= 0.
 3.We have to reach at (n-1, m-1) with minimum positive points i.e., > 0.

 输入
 The first line contains an integer 'T' denoting the total number of test cases.In each test cases, the first line contains two integer 'R' and 'C' denoting the number of rows and column of array.
 The second line contains the value of the array i.e the grid, in a single line separated by spaces in row major order.
 Constraints:
 1 ≤ T ≤ 30
 1 ≤ R,C ≤ 10
 -30 ≤ A[R][C] ≤ 30
 Input: points[m][n] = { {-2, -3, 3},
 {-5, -10, 1},
 {10, 30, -5}
 };

 输出
 Print the minimum initial points to reach the bottom right most cell in a separate line.
 7
 Explanation:
 7 is the minimum value to reach destination with
 positive throughout the path. Below is the path.
 (0,0) -> (0,1) -> (0,2) -> (1, 2) -> (2, 2)
 We start from (0, 0) with 7, we reach(0, 1)
 with 5, (0, 2) with 2, (1, 2) with 5, (2, 2)with and finally we have 1 point (we needed
 greater than 0 points at the end).

 输入样例 1
 1
 3 3
 -2 -3 3 -5 -10 1 10 30 -5

 输出样例 1
 7

 思路：倒推的动态规划，动态规划一般最后结果返回dp[0][0]或dp[m][n]，所以首先定义dp[i][j]的含义，再定义状态转移方程，最后确定从左上往右下还是从右下往左上填表。
      如本题，如果结果定义为返回dp[m][n]的话，那么dp[i][j]应该定义为到达并经过点[i][j]所需要的最小代价或生命值，但是若这样定义的话，就存在一个问题：
      只知道到达一个点所需的最小代价，如果该点为正数，那么经过该点最小代价应不变，但是其实生命值是加上了这个正数了，这会导致后续如果遇到负数时，无法判断，比如：
        [[-2 -3 10],
         [-5 -10 -7],
         [10 30 -1]]
      填表为[[3 6 *]，此时填dp[0][2]因为为10，所以所需代价也为dp[0][1] = 6，但是这样的话，往下扫描到dp[1][2]=-7，那么就会得到负数，认为6无法通过[1][2]。但其实此时的生命值已经加上了[0][2]的10，肯定是够通过的，就会产生问题。
            [* * *]
            [* * *]]

    所以该题从左上往右下填表不能解决，就尝试从右下往左上填表，那么就应该返回dp[0][0]，dp[i][j]就定义为：从[i][j]出发到达最右下角所需的最小代价或最小生命值；
    那么就[i][j]的值就需要从[i+1][j]和[i][j+1]取较小的值，然后只需计算计算从[i][j]到这个最小值点的代价，如果arr[i][j]为正，那么dp[i][j]应减小，最小为1；若为负，dp[i][j]应增大；
    即状态转移方程为    dp[i][j] = max ( min(dp[i+1][j], dp[i][j+1]) - arr[i][j], 1 )

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
            int row = sc.nextInt();
            int col = sc.nextInt();
            int[][] grid = new int[row][col];
            for(int i = 0; i < row; i ++)
            {
                for(int j = 0; j < col; j++)
                {
                    grid[i][j] = sc.nextInt();
                }
            }
            // Process
            System.out.print(dp(grid, row, col));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }

    }

    private static int dp(int[][] arr, int row, int col)
    {
        int[][] dp = new int[row + 1][col + 1];
        for(int i = row; i > 0; i--)
        {
            for(int j = col; j > 0; j--)
            {
                if(i == row && j == col)
                    dp[i][j] = arr[i - 1][j - 1] < 0 ? Math.abs(arr[i - 1][j - 1]) + 1 : 1;
                else if(i == row)
                    dp[i][j] = Math.max(dp[i][j + 1] - arr[i - 1][j - 1], 1);
                else if(j == col)
                    dp[i][j] = Math.max(dp[i + 1][j] - arr[i - 1][j - 1], 1);
                else
                {
                    int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(min - arr[i - 1][j - 1], 1);
                }
            }
        }
        return dp[1][1];
    }
}
