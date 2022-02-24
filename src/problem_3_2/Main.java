package problem_3_2;
/***
 区间第k最小
 描述
 找到给定数组的给定区间内的第K小的数值。
 输入
 输入第一行为用例个数， 每个测试用例输入的第一行为数组，每一个数用空格隔开；第二行是区间（第几个数到第几个数，两头均包含），两个值用空格隔开；第三行为K值。
 输出
 结果。

 输入样例 1
 1
 1 2 3 4 5 6 7
 3 5
 2

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
            String[] arrStr = sc.nextLine().split(" ");
            int[] arr = new int[arrStr.length];
            for(int i = 0; i < arrStr.length; i++)
            {
                arr[i] = Integer.valueOf(arrStr[i]);
            }
            int left = sc.nextInt() - 1;
            int right = sc.nextInt() - 1;
            int k = sc.nextInt();
            sc.nextLine();
            // Process
            System.out.print(findKthMinNum(arr, left, right, k));
            // Output
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }

    }

    private static int findKthMinNum(int[] arr, int left, int right, int k)
    {
        Arrays.sort(arr, left, right);
        return arr[left + k - 1];
    }
}
