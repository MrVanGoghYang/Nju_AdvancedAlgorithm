package problem_3_8;
/***
 距离问题
 描述
 In a given cartesian plane, there are N points. We need to find the Number of Pairs of points(A,B) such that
 Point A and Point B do not coincide.
 coincide 重合
 Manhattan Distance and the Euclidean Distance between the points should be equal.
 Note : Pair of 2 points(A,B) is considered same as Pair of 2 points(B,A).
 Manhattan Distance = |x2-x1|+|y2-y1|
 Euclidean Distance = ((x2-x1)^2 + (y2-y1)^2)^0.5 where points are (x1,y1) and (x2,y2).
 Constraints:1<=T <= 50, 1<=N <= 2*10 ^ 5, 0<=(|Xi|, |Yi|) <= 10^9
 输入
 First Line Consist of T - number of test cases. For each Test case:First Line consist of N , Number of points.
 Next line contains N pairs contains two integers Xi and Yi，i.e, X coordinate and the Y coordinate of a Point
 输出
 Print the number of pairs as asked above.

 输入样例 1
 1
 2
 1 1
 7 5

 输出样例 1
 0

 思路：|x2-x1|+|y2-y1| = ((x2-x1)^2 + (y2-y1)^2)^0.5  =>  |x2-x1| * |y2-y1| = 0  =>  x1 == x2 || y1 == y2.

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
            int pointNum = sc.nextInt();
            int[][] pointaArr = new int[pointNum][2];
            for(int i = 0; i < pointNum; i++)
            {
                pointaArr[i][0] = sc.nextInt();
                pointaArr[i][1] = sc.nextInt();
            }
            // Process
            System.out.print(findEqlDisPairs(pointaArr));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static int findEqlDisPairs(int[][] arr)
    {
        int res = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                if(arr[i][0] == arr[j][0] || arr[i][1] == arr[j][1])
                {
                    res++;
                }
            }
        }
        return res;
    }

}
