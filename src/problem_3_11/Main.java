package problem_3_11;
/***
 对称子字符串
 描述
 Given a string ‘str’ of digits, find length of the longest substring of ‘str’, such that the length of the substring is 2k digits and sum of left k digits is equal to the sum of right k digits.
 输入
 输入第一行是测试用例的个数，后面每一行表示一个数字组成的字符串，例如："123123"
 输出
 输出找到的满足要求的最长子串的长度。例如，给定的例子长度应该是 6。每行对应一个用例的结果。

 输入样例 1
 1
 1538023

 输出样例 1
 4

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
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            String inStr = sc.nextLine();
            // Process
            System.out.print(findRequiredSubString(inStr));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }

    }

    private static int findRequiredSubString(String inStr)
    {
        int strLen = inStr.length();
        int leftSum = 0;
        int rightSum = 0;
        for (int i = strLen % 2 == 0 ? strLen : strLen - 1; i > 0; i -= 2)
        {
            for (int j = 0; j <= strLen - i; j++)
            {
                leftSum = 0;
                rightSum = 0;
                String leftStr = inStr.substring(j, j + i / 2);
                char[] lCharArr = leftStr.toCharArray();
                String rightStr = inStr.substring(j + i / 2, j + i);
                char[] rCharArr = rightStr.toCharArray();
                for(int k = 0; k < lCharArr.length; k++)
                {
                    leftSum += lCharArr[k] - '0';
                    rightSum += rCharArr[k] - '0';
                }
                if(leftSum == rightSum)
                    return i;
            }
        }
        return 0;
    }
}
