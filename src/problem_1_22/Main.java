package problem_1_22;
/***
 实现Shell排序
 描述
 实现Shell排序，对给定的无序数组，按照给定的间隔变化（间隔大小即同组数字index的差），打印排序结果，注意不一定是最终排序结果！
 输入
 输入第一行表示测试用例个数，后面为测试用例，每一个用例有两行，第一行为给定数组，第二行为指定间隔，每一个间隔用空格隔开。
 输出
 输出的每一行为一个用例对应的指定排序结果。
 输入样例 1
 1
 49 38 65 97 76 13 27 49 55 4
 5 3
 输出样例 1
 13 4 49 38 27 49 55 65 97 76

 思路：Shell排序即： 步长从len / 2 + 1开始每次 / 2直至最后为1，每次按步长对数组元素分组，如步长为2，则分为[49,65,76,27,55]与[38,97,13,49,4]
      两组，步长越大组数越多每组中元素越少，当最后步长为1则有序。
 */

import java.util.Scanner;

class Main
{
    public static void main(String[] args) {
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            // Data input
            String arrStr = sc.nextLine();
            String[] arrStrs = arrStr.split(" ");
            int[] arr = new int[arrStrs.length];
            for(int i = 0; i < arrStrs.length; i++)
            {
                arr[i] = Integer.valueOf(arrStrs[i]);
            }
            String stepStr = sc.nextLine();
            String[] stepStrs = stepStr.split(" ");
            int[] step = new int[stepStrs.length];
            for(int i = 0; i < stepStrs.length; i++)
            {
                step[i] = Integer.valueOf(stepStrs[i]);
            }
            // Process
            for(int i = 0; i < step.length; i++)
            {
                shellSort(arr, 0, arr.length - 1, step[i]);
            }
            for(int i = 0; i < arr.length; i++)
            {
                System.out.print(arr[i]);
                if(i != arr.length - 1)
                    System.out.print(" ");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }


    /**
     * 以step为步长对arr数组[left,right]范围进行一次Shell排序，按step划分为n组后，每一组内部使用插入排序
     * @param arr
     * @param left
     * @param right
     * @param step
     */
    private static void shellSort(int[] arr, int left, int right, int step)
    {
        if(step > (right - left + 1) / 2 || step <= 0)
            return;
        for(int i = left; i < left + step; i++)  //执行step组 插入排序
        {
            // 插入排序
            for(int j = i + step; j <= right; j += step)
            {
                int item = arr[j];
                int index = j;
                while(index - step >= left && item < arr[index - step])
                {
                    arr[index] = arr[index - step];
                    index -= step;
                }
                if(index != j && index >= left)
                    arr[index] = item;
            }
        }
    }
}

