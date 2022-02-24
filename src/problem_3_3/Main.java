package problem_3_3;
/***
 子矩阵问题
 描述
 给定一个矩形区域，每一个位置上都是1或0，求该矩阵中每一个位置上都是1的最大子矩形区域中的1的个数。
 输入
 输入第一行为测试用例个数。每一个用例有若干行，第一行为矩阵行数n和列数m，下面的n行每一行是用空格隔开的0或1。
 输出
 输出一个数值。

 输入样例 1
 1
 3 4
 1 0 1 1
 1 1 1 1
 1 1 1 0

 输出样例 1
 6

 思路：

 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

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
            int rowLen = sc.nextInt();;
            int colLen = sc.nextInt();
            int[][] arr = new int[rowLen][colLen];
            for(int i = 0; i < rowLen; i++)
            {
                for(int j = 0; j < colLen; j++)
                {
                    arr[i][j] = sc.nextInt();
                }
            }
            // Process
            System.out.print(findMaxArea(arr, rowLen, colLen));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }

    }

    private static int findMaxArea(int[][] arr, int rowLen, int colLen)
    {
        int res = 0;
        for (int i = 0; i < rowLen; i++)
        {
            for (int j = 0; j < colLen; j++)
            {
                if(arr[i][j] == 1)
                {
                    int item = findTowardsAround(arr, i, j);
                    res = item > res ? item : res;
                }

            }
        }
        return res;
    }


    private static int findTowardsAround(int[][] arr, int beginx, int beginy)
    {
        int maxArea = 0;
        for(int i = beginx; i < arr.length; i++)
        {
            for(int j = beginy; j < arr[0].length; j++)
            {
                if(allOneBetween(arr, beginx, beginy, i ,j))
                {
                    int nowArea = (i - beginx + 1) * (j - beginy + 1);
                    maxArea = maxArea > nowArea ? maxArea :nowArea;
                }

            }
        }
        return maxArea;
    }

    private static boolean allOneBetween(int[][] arr, int x1, int y1, int x2, int y2)
    {
        for(int i = x1; i <= x2; i++)
        {
            for(int j = y1; j <= y2; j++)
            {
                if(arr[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
