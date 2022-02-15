package problem_1_21;
/***
 倒置个数
 描述
 有一个由N个实数构成的数组，如果一对元素A[i]和A[j]是倒序的，即i<j但是A[i]>A[j]则称它们是一个倒置，设计一个计算该数组中所有倒置数量的算法。要求算法复杂度为O(nlogn)
 输入
 输入有多行，第一行整数T表示为测试用例个数，后面是T个测试用例，每一个用例包括两行，第一行的一个整数是元素个数，第二行为用空格隔开的数组值。
 输出
 输出每一个用例的倒置个数，一行表示一个用例。
 输入样例 1
 1
 8
 8 3 2 9 7 1 5 4
 输出样例 1
 17

 思路： 逆序数的实质是 归并排序，归并排序分为 分 与 合 两个阶段，在合并两个有序数组时，可以计算出这两个数组的逆序对个数。如 2 3 4 与 1 2 3，每当选中右边元素时，逆序对的数目即为左边剩余元素个数（如选中1，左边剩3个，逆序对 +3）。
 https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/

 */

import java.util.Scanner;

class Main
{
    public static void main(String[] args) {
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int arrLen = sc.nextInt();
            int[] arr = new int[arrLen];
            int[] copyArr = new int[arrLen];
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
                copyArr[i] = arr[i];
            }
            // Process
            int res = mergeSort(arr, 0, arrLen - 1, (arrLen - 1) >> 1, copyArr);
            System.out.print(res);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    /**
     * [left,right]范围内通过归并排序的思想求逆序数，关键在可以在两个有序数组合并的过程中利用两个数组中元素的前后相对位置不变来简化逆序数计算过程
     * @param arr
     * @param left
     * @param right
     * @param copyArr
     * @return
     */
    private static int mergeSort(int[] arr, int left, int right, int mid, int[] copyArr)
    {
        if(left >= right) {
            return 0;
        }
        int cnt = 0;
        int l = left;
        int r = mid + 1;
        int index = left;
        // 递归划分，计算左右递归过程产生的逆序对
        cnt = mergeSort(arr, left, mid, (left + mid) / 2, copyArr) + mergeSort(arr, mid + 1, right, (mid + 1 + right) / 2, copyArr);
        // 计算当前合并两个有序数组arr[left,mid]与arr[mid+1,right]时所产生的逆序对
        while(l <= mid && r <= right)
        {
            if (copyArr[l] <= copyArr[r]) {
                arr[index++] = copyArr[l++];
            } else {
                arr[index++] = copyArr[r++];
                cnt += (mid - l + 1);
            }
        }
        // 左数组剩余部分拷贝
        while(l <= mid)
        {
            arr[index++] = copyArr[l++];
        }
        // 右数组剩余部分拷贝
        while(r <= right)
        {
            arr[index++] = copyArr[r++];
        }
        // 拷贝合并完成的有序部分到copy数组用于后续判断
        for(int i = left; i <= right; i++)
        {
            copyArr[i] = arr[i];
        }
        // 返回3个部分的和的cnt
        return cnt;
    }
}

