package problem_3_7;
/***
 和最大的连续降序字符
 描述
 Archana is very fond of strings. She likes to solve many questions related to strings. She comes across a problem which she is unable to solve. Help her to solve.
 The problem is as follows: Given is a string of length L. Her task is to find the longest string from the given string with characters arranged in descending order of their ASCII code and in arithmetic progression.
 arithmetic progression 算术级数 等差数列  common difference 公差
 She wants the common difference should be as low as possible(at least 1) and the characters of the string to be of higher ASCII value.

 输入
 The first line of input contains an integer T denoting the number of test cases. Each test contains a string s of lengthL.
 1<= T <= 100
 3<= L <=1000
 A<=s[i]<=Z
 The string contains minimum three different characters.

 输出
 For each test case print the longest string.Case 1:Two strings of maximum length are possible- “CBA” and “RPQ”. But he wants the string to be of higher ASCII value therefore, the output is “RPQ”.Case 2:The String of maximum length is “JGDA”.

 输入样例 1
 2
 ABCPQR
 ADGJPRT

 输出样例 1
 RQP
 JGDA

 思路： 题目敢不敢再含糊不清一点？？！！ 焯

 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Main
{
    private static boolean[] letterArr = new boolean[26];
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            Arrays.fill(letterArr, false);
            char[] inArr =sc.nextLine().toCharArray();
            for(int i = 0; i < inArr.length; i++)
            {
                if(!letterArr[inArr[i] - 'A'])
                    letterArr[inArr[i] - 'A'] = true;
            }
            // Process
            printLongestString();
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void printLongestString()
    {
        int longestLen = 0;
        int longestDif = 0;
        int maxAscii = 0;
        StringBuilder sb;
        String res = "";
        for(int i = 25; i >= 0; i--)
        {
            if(!letterArr[i])
                continue;
            for(int step = 1; step <= 25; step++)
            {
                char watch = (char)('A' + i );
                sb = new StringBuilder();
                sb.append((char)('A' + i));
                int curestLen = 1;
                int curAscii = i + 'A';
                int nowIdx = i;
                while(nowIdx - step >=0 && letterArr[nowIdx - step])
                {
                    curestLen += 1;
                    curAscii += nowIdx - step + 'A';
                    sb.append((char)('A' + nowIdx - step));
                    nowIdx -= step;
                }
                if(curestLen > longestLen || (curestLen == longestLen && step < longestDif) || (curestLen == longestLen && step == longestDif && curAscii > maxAscii))
                {
                    longestLen = curestLen;
                    longestDif = step;
                    maxAscii = curAscii;
                    res = sb.toString();
                }
            }
        }
        System.out.print(res);
    }
}
