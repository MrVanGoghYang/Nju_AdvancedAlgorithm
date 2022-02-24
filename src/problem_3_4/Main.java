package problem_3_4;
/***
 能否成环
 描述
 Given an array of strings A[ ], determine if the strings can be chained together to form a circle.
 A string X can be chained together with another string Y if the last character of X is same as first character of Y.
 If every string of the array can be chained, it will form a circle.
 For example, for the array arr[] = {"for", "geek", "rig", "kaf"} the answer will be Yes as the given strings can be chained as "for", "rig", "geek" and "kaf".

 输入
 The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
 The first line of each test case contains a positive integer N, denoting the size of the array.
 The second line of each test case contains a N space seprated strings, denoting the elements of the array A[ ].
 1 <= T
 0 < N
 0 < A[i]

 输出
 If chain can be formed, then print 1, else print 0.

 输入样例 1
 2
 3
 abc bcd cdf
 4
 ab bc cd da

 输出样例 1
 0
 1

 思路：

 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


class Main
{
    private static Map<Character,List<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            map.clear();
            int arrLen = sc.nextInt();
            sc.nextLine();
            char[][] arr = new char[arrLen][2];
            int[] visited = new int[arrLen];
            String[] inStrArr = sc.nextLine().split(" ");
            for(int i = 0; i < inStrArr.length; i++)
            {
                arr[i][0] = inStrArr[i].charAt(0);
                arr[i][1] = inStrArr[i].charAt(inStrArr[i].length() - 1);
                if(!map.containsKey(arr[i][0]))
                {
                    ArrayList<Integer> item = new ArrayList<>();
                    item.add(i);
                    map.put(arr[i][0], item);
                }
                else
                    map.get(arr[i][0]).add(i);
            }
            // Process
            System.out.print(canBeCycle(arr, arrLen, visited));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static int canBeCycle(char[][] arr, int arrLen, int[] visited)
    {
        // 尝试从每一个串开始能否成环
        for(int i = 0; i < arrLen; i++)
        {
            Arrays.fill(visited, 0);
            if(tryLinkNext(arr, arrLen, visited, i, 0))
                return 1;
        }
        return 0;
    }

    private static boolean tryLinkNext(char[][] arr, int arrLen, int[] visited, int start, int cycleLen)
    {
        // 退出
        if(cycleLen == arrLen)
            return true;
        if(visited[start] == 1)
            return false;
        // 初始
        visited[start] = 1;
        List<Integer> nextlist = map.get(arr[start][1]);
        // 所有情况
        if(nextlist != null)
        {
            for (int item : nextlist)
            {
                // 递归
                if (tryLinkNext(arr, arrLen, visited, item, cycleLen + 1)) return true;
            }
        }
        return false;
    }
}
