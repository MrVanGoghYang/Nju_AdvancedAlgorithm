package problem_1_19;
/***
非递归快排
描述
快速排序的核心思想是使用元素的值对数组进行划分。实现其非递归方案。
输入
输入第一行为用例个数， 每个测试用例输入的每一行代表一个数组，其中的值用空格隔开，第一个值表示数组的长度。
输出
输出排序的数组，用空格隔开，末尾不要空格。
输入样例 1
1
13 24 3 56 34 3 78 12 29 49 84 51 9 100
输出样例 1
3 3 9 12 24 29 34 49 51 56 78 84 100
思路: WDNMD输入不给case数了是吧，都是排序题你还改Input格式？？！！！给爷百思不得其解了半个多小时，玩阴的玩不起，焯！！！
     递归改迭代：使用栈，栈非空时：出栈左右index,然后partition，然后将新的左右index进栈。注意进栈和出栈的顺序相反。
***/

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
            quickSort(arr, 0 ,arrLen - 1);
            for(int i = 0; i < arrLen; i++)
            {
                System.out.print(arr[i]);
                if(i != arrLen - 1)
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static void quickSort(int[] tree,int left,int right)
    {
        Stack<Integer> stack = new Stack<>();
        if(left < right)
        {
            stack.push(right);
            stack.push(left);
            while (!stack.isEmpty())
            {
                int l = stack.pop();
                int r = stack.pop();
                int pivot = partition(tree, l, r);
                if (l < pivot - 1)
                {
                    stack.push(pivot - 1);
                    stack.push(l);
                }
                if (r > pivot + 1)
                {
                    stack.push(r);
                    stack.push(pivot + 1);
                }
            }
        }
    }

    private static int partition(int[] tree,int left,int right)
    {
        int p = left;
        int q = right;
        int pivort = tree[left];
        while(p < q)
        {
            while (p < q && q >= 0 && tree[q] >= pivort) {
                q--;
            }
            tree[p] = tree[q];
            while (p < q && p < tree.length && tree[p] <= pivort) {
                p++;
            }
            tree[q] = tree[p];
        }
        tree[p] = pivort;
        return p;
    }
}

