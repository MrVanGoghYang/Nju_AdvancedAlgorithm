package problem_3_9;
/***
 字符串匹配问题
 描述
 Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. You may assume that n > m.
 输入
 输入第一行是用例个数，后面一行表示一个用例；用例包括两部分，第一部分为给定文本，第二部分为搜索串，两部分使用","隔开。
 输出
 每一个用例输出一行，每行按照找到的位置先后顺序排列，使用空格隔开。

 输入样例 1
 2
 THIS IS A TEST TEXT,TEST
 AABAACAADAABAABA,AABA

 输出样例 1
 10
 0 9 12

 思路
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
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            String[] inStrArr = sc.nextLine().split(",");
            String text = inStrArr[0];
            String pat = inStrArr[1];
            // Process
            System.out.print(dp(text, pat));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static String dp(String text, String pat)
    {
        int[][] dpArr = new int[text.length() + 1][pat.length() + 1];
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= text.length(); i++)
        {
            for(int j = 1; j <= pat.length(); j++)
            {
                if(i < j)
                {
                    dpArr[i][j] = 0;
                    continue;
                }
                if(text.charAt(i - 1) == pat.charAt(j - 1))
                {
                    if(j == 1)
                    {
                        dpArr[i][j] = dpArr[i - 1][j] + 1;
                        continue;
                    }
                    if(i >= 2)
                    {
                        if (dpArr[i - 1][j - 1] - dpArr[i - 2][j - 1] == 1)
                        {
                            dpArr[i][j] = dpArr[i - 1][j] + 1;
                            if (j == pat.length())
                            {
                                sb.append(i - pat.length());
                                sb.append(" ");
                            }
                        }
                        else
                        {
                            dpArr[i][j] = dpArr[i - 1][j];
                        }
                    }
                }
                else
                {
                    dpArr[i][j] = dpArr[i - 1][j];
                }
            }
        }
        if(sb.length() >= 1)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
