package problem_3_1;
/***
 最长公共子序列
 描述
 给定两个字符串，返回两个字符串的最长公共子序列（不是最长公共子字符串），可能是多个。
 输入
 输入第一行为用例个数， 每个测试用例输入为两行，一行一个字符串
 输出
 如果没有公共子序列，不输出，如果有多个则分为多行，按字典序排序。

 输入样例 1
 1
 1A2BD3G4H56JK
 23EFG4I5J6K7

 输出样例 1
 23G456K
 23G45JK

 思路：
 自己瞎鸡儿用栈写回溯查找 AC 了NJU OJ，但是LeetCode超时，垃圾做法。
 正解： 经典动态规划（还有最长子串、回文串问题）。对于两个字符串求子序列的问题，都是用两个指针i和j分别在两个字符串上移动，大概率是动态规划思路。
 自顶向下：即从右下往左上填表，类似于递归，要递归调用设置终止条件及有值直接从表中返回；
 自底向上：即从左上往右下填表，直接迭代；
 https://mp.weixin.qq.com/s/ZhPEchewfc03xWv9VP3msg
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Main
{
    private static Set<String> res = new TreeSet<>();
    private static int maxLen;
    private static Set<String>[] sets;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        StringBuilder out = new StringBuilder();
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            res.clear();
            maxLen = 0;
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            // Process
            Set<String> resSet = longestCommonSerial(s1, s2);
            for(String s : resSet)
            {
                if(out.length() != 0 && s.length() != 0)
                    out.append("\n");
                out.append(s);
            }
        }
        System.out.print(out.toString());
    }
    // 动态规划填表，返回最右下角的表的值
    private static Set<String> longestCommonSerial(String s1, String s2)
    {
        sets = new TreeSet[(s1.length() + 1) * (s2.length() + 1)];
        for(int i = 0; i < (s1.length() + 1) * (s2.length() + 1); i++)
        {
            sets[i] = new TreeSet<>();
            sets[i].add("");
        }
        return dp(s1, s2);
    }
    /*
    动态规划过程：从左上往右下填表，
    如果 i 与 j 位置的字符相同，那么该字符必然位于他们的最大公共子序列中，对[i-1][j-1]的表遍历，将最长的那些拼接上该字符加入[i][j]
    如果 i 与 j 位置的字符不同，那么[i][j]的值等于[i-1][j]与[i][j-1]的并集中最长的那些串
     */

    private static Set<String> dp(String s1, String s2)
    {
        for(int i = 1; i <= s1.length(); i++)
        {
            for(int j = 1; j <= s2.length(); j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    int maxLenth1 = 0;
                    for(String s : sets[(i - 1) * (s2.length() + 1) + (j - 1)])
                    {
                        maxLenth1 = maxLenth1 > s.length() ? maxLenth1 : s.length();
                    }
                    for(String s : sets[(i - 1) * (s2.length() + 1) + (j - 1)])
                    {
                        if(s.length() == maxLenth1)
                            sets[i * (s2.length() + 1) + j].add(String.valueOf(s) + String.valueOf(s1.charAt(i - 1)));
                    }
                }
                else
                {
                    Set<String> itemSet;
                    if(sets[i * (s2.length() + 1) + (j - 1)].size() > 1 && sets[(i - 1) * (s2.length() + 1) + j].size() > 1)
                    {
                        int maxLenth1 = 0;
                        int maxLength2 = 0;
                        for(String s : sets[i * (s2.length() + 1) + (j - 1)])
                        {
                            maxLenth1 = maxLenth1 > s.length() ? maxLenth1 : s.length();
                        }
                        for(String s : sets[(i - 1) * (s2.length() + 1) + j])
                        {
                            maxLength2 = maxLength2 > s.length() ? maxLength2 : s.length();
                        }
                        for(String s : sets[i * (s2.length() + 1) + (j - 1)])
                        {
                            if(s.length() == Math.max(maxLenth1, maxLength2))
                                sets[i * (s2.length() + 1) + j].add(String.valueOf(s));
                        }
                        for(String s : sets[(i - 1) * (s2.length() + 1) + j])
                        {
                            if(s.length() == Math.max(maxLenth1, maxLength2))
                                sets[i * (s2.length() + 1) + j].add(String.valueOf(s));
                        }
                    }
                    else if(sets[i * (s2.length() + 1) + (j - 1)].size() == 1 && sets[(i - 1) * (s2.length() + 1) + j].size() > 1)
                    {
                        for(String s : sets[(i - 1) * (s2.length() + 1) + j])
                        {
                            sets[i * (s2.length() + 1) + j].add(String.valueOf(s));
                        }
                    }
                    else
                    {
                        for(String s : sets[i * (s2.length() + 1) + (j - 1)])
                        {
                            sets[i * (s2.length() + 1) + j].add(String.valueOf(s));
                        }
                    }
                }
            }
        }
        return sets[s1.length() * (s2.length() + 1) + s2.length()];
    }


//    private static void findMaxLenComSubSeri(String s1, String s2)
//    {
//        int len1 = s1.length();
//        int len2 = s2.length();
//        int p = 0;
//        int q = 0;
//        StringBuilder sb = new StringBuilder();
//        // 设置两个栈储存子序列在两个串中的下标的后一位，方便回溯搜索
//        Stack<Integer> stack1 = new Stack<>();
//        Stack<Integer> stack2 = new Stack<>();
//        // 对较短的字符串搜索每个字符往后的最长子序列，每轮循环得到的子序列第一个字符均为s1.charAt(i)
//        for(int i = 0; i < len1 && (len1 - i) >= maxLen; i++)
//        {
//            // 设置搜索指针p q分别指向s1的搜索起始位置和s2的第一个字符
//            p = i;
//            q = 0;
//            sb.delete(0, sb.length());
//            //
//            while (p != len1 || !stack1.isEmpty())
//            {
//                // 对s2搜索到最后,尝试将本轮搜索的串加入结果中
//                if (q == len2)
//                {
//                    // 满足条件可加入结果
//                    if (sb.length() >= maxLen && !res.contains(sb.toString()))
//                    {
//                        if (sb.length() > maxLen)
//                            res.clear();
//                        res.add(sb.toString());
//                        maxLen = sb.length();
//                    }
//                    // 不满足条件，对p q做更改，此处q到结尾后有两种情况：
//                    if(stack1.isEmpty())
//                        break;
//                    // 1.对当前p指向的字符，q一直搜索到最后都没有匹配到，那么p++，q回溯但不弹出因为需要与stack1的长度匹配；
//                    if (p < len1 && !s2.substring(stack2.peek(), q).contains(String.valueOf(s1.charAt(p))))
//                    {
//                        p++;
//                        q = stack2.peek();
//                    }
//                    // 2.q在本轮搜索中曾经找到过q，那么p q都出栈且删除公共序列最后一位，继续找最后一位的其他可能情况。
//                    // 注意此处的q应取栈中的第2个而非栈顶元素，因为比如ABCEF与A1B2C3D，当前公共为ABC，当p指针回溯后指向C后面一个E，那么q应该回溯指向B后面的2开始搜索而非和p一样指向C后面的3开始搜索。
//                    else
//                    {
//                        p = stack1.pop();
//                        stack2.pop();
//                        q = stack2.size() > 0 ? stack2.peek() : 0;
//                        sb.deleteCharAt(sb.length() - 1);
//                    }
//                }
//                // 对字符做比较处理
//                // 字符相等 两个指针都后移 且将两个字符所在位置入栈记录
//                if (p < len1 && q < len2 && s1.charAt(p) == s2.charAt(q))
//                {
//                    sb.append(s1.charAt(p));
//                    p++;
//                    stack1.push(p);
//                    q++;
//                    stack2.push(q);
//                }
//                // 否则 s2指针后移
//                else
//                {
//                    if(q < len2)
//                        q++;
//                }
//            }
//        }
//    }
}
