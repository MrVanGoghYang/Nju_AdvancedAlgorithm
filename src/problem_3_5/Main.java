package problem_3_5;
/***
 数字重组整除问题
 描述
 Babul’s favourite number is 17. He likes the numbers which are divisible by 17.
 This time what he does is that he takes a number N and tries to find the largest number which is divisible by 17, by rearranging the digits.
 As the number increases he gets puzzled with his own task. So you as a programmer have to help him to accomplish his task.
 Note: If the number is not divisible by rearranging the digits, then print “Not Possible”. N may have leading zeros.
 输入
 The first line of input contains an integer T denoting the no of test cases. Each of the next T lines contains the number N.
 输出
 For each test case in a new line print the desired output.

 输入样例 1
 4
 17
 43
 15
 16

 输出样例 1
 17
 34
 51
 Not Possible

 思路：
 2015993900449
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
class Main
{
    private static Map<Character,Integer> map = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            map.clear();
            char[] inputStr = sc.nextLine().toCharArray();
            for(int i = 0; i < inputStr.length; i++)
            {
                if(!map.containsKey(inputStr[i]))
                    map.put(inputStr[i],1);
                else
                    map.replace(inputStr[i], map.get(inputStr[i]) + 1);
            }
            // Process
            long res = findMaxNum(inputStr);
            if(res != 0 && res % 17 == 0)
                System.out.print(res);
            else
                System.out.print("Not Possible");
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static long findMaxNum(char[] inputStr)
    {
        Arrays.sort(inputStr);
        long max = Long.valueOf(new StringBuilder(String.valueOf(inputStr)).reverse().toString());
        long res = 0;
        long curDivisiable;
        if(max % 17 == 0)
        {
            res = max;
            return res;
        }
        else
        {
            curDivisiable = max - max % 17;
        }
        while (!strContains(curDivisiable) && curDivisiable > 0)
        {
            curDivisiable -= 17;
        }
        return curDivisiable == 0 ? 0 : curDivisiable;
    }

    private static boolean strContains(long target)
    {
        char[] tarArr = String.valueOf(target).toCharArray();
        Arrays.sort(tarArr);
        int cnt = 1;
        for(int i = tarArr.length - 1; i >= 0; i--)
        {
            if(i < tarArr.length - 1)
            {
                if (tarArr[i] != tarArr[i + 1])
                {
                    if (!map.containsKey(tarArr[i + 1]) || cnt != map.get(tarArr[i + 1]))
                    {
                        return false;
                    }
                    cnt = 1;
                }
                else
                {
                    cnt++;
                }
            }
        }
        if (!map.containsKey(tarArr[0]) ||cnt != map.get(tarArr[0]))
        {
            return false;
        }
        return true;
    }
}
