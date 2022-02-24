package problem_3_10;
/***
 整除查询
 描述
 Given an array of positive integers and many queries for divisibility. In every query Q[i], we are given an integer K , we need to count all elements in the array which are perfectly divisible by K.
 Constraints:1<=T<=1001<=N,M<=1051<=A[i],Q[i]<=105
 输入
 The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. Each test case consists of three lines.
 First line of each test case contains two integers N & M, second line contains N space separated array elements and third line contains M space separated queries.
 输出
 For each test case,In new line print the required count for each query Q[i].

 输入样例 1
 2
 6 3
 2 4 9 15 21 20
 2 3 5
 3 2
 3 4 6
 2 3

 输出样例 1
 3 3 2
 2 2

 思路：

 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Main
{
    private static int[] res = new int[106];
    private static int[] arr = new int[105];
    private static int[] que = new int[105];
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            Arrays.fill(res, 0);
            Arrays.fill(arr, 0);
            Arrays.fill(que, -1);
            int arrLen = sc.nextInt();
            int queLen = sc.nextInt();
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
            }
            for(int i = 0; i < queLen; i++)
            {
                que[i] = sc.nextInt();
            }
            // Process
            findDivCnt(arrLen, queLen);
            // Output
            for(int i = 0; i < queLen; i++)
            {
                System.out.print(res[que[i]]);
                if(i != queLen - 1)
                    System.out.print(" ");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");

        }

    }

    private static void findDivCnt(int arrLen, int queLen)
    {
        for(int i = 0; i < queLen; i++)
        {
            if(res[que[i]] != 0)
                continue;
            for(int j = 0; j < arrLen; j++)
            {
                if(que[i] != 0 && arr[j] % que[i] == 0)
                    res[que[i]]++;
            }
        }
    }
}
