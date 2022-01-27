package problem_1_14;
/*
合并数组
描述
Given K sorted arrays arranged in a form of a matrix, your task is to merge them. You need to complete mergeKArrays function
which takes 2 arguments, an arr[k][k] 2D Matrix containing k sorted arrays and an integer k denoting the no. of sorted arrays.
The function should return a pointer to the merged sorted arrays. There are multiple test cases.
For each test case, this method will be called individually.
输入
The first line of input will denote the no of Test cases then T test cases will follow .
Each test cases will contain an integer N denoting the no of sorted arrays.
Then in the next line contains all the elements of the array separated by space.（1<=T<=50；1<=N<=10）
输出
The output will be the sorted merged array.
输入样例 1
1
3
1 2 3 4 5 6 7 8 9
输出样例 1
1 2 3 4 5 6 7 8 9
思路：两两归并排序，注意当归并的数组数目为奇数时，需要将中间数组放到归并结果的最后，并更新归并结果数组的长度
 */
import java.util.*;
class Main
{
    public static void main(String[] args) {
        int[][] arr;
        int[] res;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int arrLen = sc.nextInt();
            arr = new int[arrLen][arrLen];
            for(int i = 0; i < arrLen; i++)
            {
                for(int j = 0; j < arrLen; j++)
                {
                    arr[i][j] = sc.nextInt();
                }
            }
            // Process
            res = merge(arr,arrLen);
            for(int i = 0; i < res.length; i++)
            {
                System.out.print(res[i]);
                if(i != res.length - 1)
                    System.out.print(" ");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static int[] merge(int[][] arr,int arrLen)
    {
        if(arrLen == 1)
            return arr[0];
        int[] res;
        int resArr = arrLen;
        int[][] itemArr = Arrays.copyOf(arr,arrLen);
        while(resArr > 1)
        {
            int row = 0;
            for (row = 0; row < resArr / 2; row++)
            {
                itemArr[row] = mergeSort(itemArr[row], itemArr[resArr - 1 - row]);
            }
            // 若归并数组数目为奇数，则对中间数组做拷贝特殊处理，并对结果数组长度做处理是否+1
            if(resArr % 2 != 0)
                itemArr[row] = Arrays.copyOf(itemArr[row],itemArr[row].length);
            resArr = resArr % 2 == 0 ? resArr / 2 : resArr / 2 + 1;
        }
        return itemArr[0];
    }

    private static int[] mergeSort(int[] a,int[] b)
    {
        int[] res = new int[a.length + b.length];
        int p = 0;
        int q = 0;
        int resIndex = 0;
        while(p < a.length && q < b.length)
        {
            while(p < a.length && q < b.length && a[p] <= b[q])
            {
                res[resIndex++] = a[p++];
            }
            while(p < a.length && q < b.length && a[p] > b[q])
            {
                res[resIndex++] = b[q++];
            }
        }
        if(p == a.length)
        {
            while(q != b.length)
            {
                res[resIndex++] = b[q++];
            }
        }
        else if(q == b.length)
        {
            while(p != a.length)
            {
                res[resIndex++] = a[p++];
            }
        }
        return res;
    }
}

