package problem_2_7;
/***
 Searching_3
 描述
 They declared Sonam as bewafa. Although she is not, believe me! She asked a number of queries to people regrading their position in a test.Now its your duty to remove her bewafa tag by answering simple queries.
 All the students who give test can score from 1 to 10^18. Lower the marks, better the rank.Now instead of directly telling the marks of student they have been assigned groups where marks are distributed in continuous intervals,
 you have been given l(i) lowest mark of interval i and r(i) highest marks in interval i.So marks distribution in that interval is given as l(i), l(i)+1, l(i)+2 . . . r(i)
 Now Sonam ask queries in which she gives rank of the student (x) and you have to tell marks obtained by that student

 Note: rank1 is better than rank2 and rank2 is better than rank3 and so on and the first interval starts from 1.
 Constraints:1<=T<=50,1<=N<=10^5,1<=Q<=10^5,1<= l(i) < r(i) <=10^18,1<=x<=10^18

 输入
 The first line of input contains an integer T, denoting the no of test cases. Then T test cases follow.
 Each test case contains two space separated values N and Q denoting the no of groups and number of queries asked respectively.
 The next line contains N group of two integers separated by space which shows lowest marks in group i ie l(i) and highest marks in group i ie r(i) such that if i < j then r(i) < l(j).
 The next lines contain Q space separated integers x, denoting rank of student.

 输出
 For each query output marks obtain by student whose rank is x(1<=x<=10^18).

 输入样例 1
 1
 3 3
 1 10 12 20 22 30
 5 15 25

 输出样例 1
 5 16 27

 思路： 统计每个区间的个数即为之前的排名数  找到该排名所在区间直接从区间起点加（排名 - 之前排名数） - 1 即可，简单数组访问
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
            int interCnt = sc.nextInt();
            int queCnt = sc.nextInt();
            long[][] interArr = new long[interCnt][2];
            long[] queArr = new long[queCnt];
            for(int i = 0; i < interCnt; i++)
            {
                interArr[i][0] = sc.nextLong();
                interArr[i][1] = sc.nextLong();
            }
            for(int i = 0; i < queCnt; i++)
            {
                queArr[i] = sc.nextLong();
            }
            // Process
            printMarks(interArr, queArr);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void printMarks(long[][] interArr, long[] rank)
    {
        for(int j = 0; j < rank.length; j++)
        {
            long item = rank[j];
            long beforeMarks = 0;
            int i = 0;
            for (i = 0; i < interArr.length; i++)
            {
                if (item > beforeMarks + interArr[i][1] - interArr[i][0] + 1)
                    beforeMarks += interArr[i][1] - interArr[i][0] + 1;
                else
                    break;
            }
            if(j != 0)
                System.out.print(" ");
            System.out.print(interArr[i][0] + item - beforeMarks - 1);
        }
    }
}
