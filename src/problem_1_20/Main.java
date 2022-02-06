package problem_1_20;
/***
非递归合并排序
描述
合并（归并）排序的核心思想是：每次从中间位置将数组分组再分别排序。请实现其非递归方案。
输入
输入的每一行表示一个元素为正整数的数组，所有值用空格隔开，第一个值为数值长度，其余为数组元素值。
输出
输出的每一行为排序结果，用空格隔开，末尾不要空格。
输入样例 1
13 24 3 56 34 3 78 12 29 49 84 51 9 100
输出样例 1
3 3 9 12 24 29 34 49 51 56 78 84 100
 */

import java.util.Scanner;
import java.util.Stack;

class Main
{
    public static void main(String[] args) {
        // Common input process
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int arrLen = sc.nextInt();
            int[] arr = new int[arrLen];
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
            }
            // Process
            int [] res = mergeSortIteration(arr, 0 ,arrLen - 1);
            for(int i = 0; i < arrLen; i++)
            {
                System.out.print(res[i]);
                if(i != arrLen - 1)
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static int[] mergeSortRecursion(int[] arr, int left, int right)
    {
        if(left >= right)
            return new int[]{arr[left]};
        int mid = left + (right - left) / 2;
        int[] leftArr = mergeSortRecursion(arr,left,mid);
        int[] rightArr = mergeSortRecursion(arr,mid + 1,right);
        return merge(leftArr,rightArr);
    }

    private static int[] mergeSortIteration(int[] arr, int left, int right)
    {
        Stack<Integer> indexStack = new Stack<>();
        Stack<int[]> arrStack = new Stack<>();
        indexStack.push(right);
        indexStack.push(left);
        while (!indexStack.isEmpty() || arrStack.size() != 1)
        {
            if(indexStack.isEmpty())
            {
                arrStack.push(merge(arrStack.pop(), arrStack.pop()));
                continue;
            }
            int l = indexStack.pop();
            int r = indexStack.pop();
            if (l < r)
            {
                int mid = l + (r - l) / 2;
                indexStack.push(mid);
                indexStack.push(l);
                indexStack.push(r);
                indexStack.push(mid + 1);
                if(arrStack.size() >= 2)
                    arrStack.push(merge(arrStack.pop(), arrStack.pop()));
            }
            else if (l == r)
            {
                arrStack.push(new int[]{arr[l]});
            }
        }
        return arrStack == null ? null : arrStack.pop();
    }

    private static int[] merge(int[] a,int[] b)
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

