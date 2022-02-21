package problem_2_6;
/***
 * 21:48
 按照另一个数组排序
 描述
 Given two array A1[] and A2[], sort A1 in such a way that the relative order among the elements will be same as those in A2. For the elements not present in A2.
 Append them at last in sorted order. It is also given that the number of elements in A2[] are smaller than or equal to number of elements in A1[] and A2[] has all distinct elements.
 Input:A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8} A2[] = {2, 1, 8, 3}Output: A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}
 Since 2 is present first in A2[], all occurrences of 2s should appear first in A[], then all occurrences 1s as 1 comes after 2 in A[].
 Next all occurrences of 8 and then all occurrences of 3. Finally we print all those elements of A1[] that are not present in A2[]
 Constraints:1 ≤ T ≤ 501 ≤ M ≤ 501 ≤ N ≤ 10 & N ≤ M1 ≤ A1[i], A2[i] ≤ 1000
 输入
 The first line of input contains an integer T denoting the number of test cases. The first line of each test case is M and N.
 M is the number of elements in A1 and N is the number of elements in A2.The second line of each test case contains M elements. The third line of each test case contains N elements.
 输出
 Print the sorted array according order defined by another array.

 输入样例 1
 1
 11 4
 2 1 2 5 7 1 9 3 6 8 8
 2 1 8 3

 输出样例 1
 2 2 1 1 8 8 3 5 6 7 9

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
            int len1 = sc.nextInt();
            Integer[] a1 = new Integer[len1];
            int len2 = sc.nextInt();
            Map<Integer, Integer> a2 = new HashMap<>();
            for(int i = 0; i < len1; i++)
            {
                a1[i] = sc.nextInt();
            }
            for(int i = 0; i < len2; i++)
            {
                a2.put(sc.nextInt(), i);
            }
            // Process
            Arrays.sort(a1, new Comparator<Integer>(){
                @Override
                public int compare(Integer a, Integer b)
                {
                    if(a2.containsKey(a) && a2.containsKey(b))
                    {
                        if(a2.get(a) < a2.get(b))
                            return 1;
                        else if(a2.get(a) > a2.get(b))
                            return -1;
                        else
                            return 0;
                    }
                    else if(a2.containsKey(a) && !a2.containsKey(b))
                        return 1;
                    else if(!a2.containsKey(a) && a2.containsKey(b))
                        return -1;
                    else
                    {
                        if(a > b)
                            return -1;
                        else if(a < b)
                            return 1;
                        else
                            return 0;
                    }
                }
            });
            for(int i = len1 - 1; i >= 0; i--)
            {
                if(i != len1 - 1)
                    System.out.print(" ");
                System.out.print(a1[i]);
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }
}
