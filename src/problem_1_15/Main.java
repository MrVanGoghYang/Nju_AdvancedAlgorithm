package problem_1_15;
/*
最小交换次数
描述
Given an array of N distinct elementsA[ ], find the minimum number of swaps required to sort the array.Your are required
to complete the function which returns an integer denoting the minimum number of swaps, required to sort the array.
输入
The first line of input contains an integer T denoting the no of test cases . Then T test cases follow .
Each test case contains an integer N denoting the no of element of the array A[ ].
In the next line are N space separated values of the array A[ ] .(1<=T<=100;1<=N<=100;1<=A[] <=1000)
输出
For each test case in a new line output will be an integer denoting minimum umber of swaps that are required to sort the array.
输入样例 1
2
4
4 3 2 1
5
1 5 4 3 2
输出样例 1
2
2
思路：最小交换次数的方法：每次交换都使一个元素被交换到最终位置，中途若满足有序则退出。使用HashMap记录上输入的值与数组序号的对应关系，按照排序数组的元素顺序
     查hashmap找对应元素的下标并交换到正确位置。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
class Main
{
    public static void main(String[] args) {
        // Common input process
        HashMap<Integer,Integer> valueMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int res = 0;
            int arrLen = sc.nextInt();
            int[] arr = new int[arrLen];
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
                valueMap.put(arr[i],i);
            }
            // Process
            if(arrLen == 1)
               res = 0;
            else
                res = calMinSwap(arr,valueMap);
            System.out.print(res);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static int calMinSwap(int[] arr,HashMap<Integer,Integer> valueMap)
    {
        int res = 0;
        int[] sortArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortArr);
        String sortedStr = Arrays.toString(sortArr);
        for(int i = 0; i < arr.length; i++)
        {
            int index = valueMap.get(sortArr[i]);
            if(index != i)
            {
                swap(arr,i,index,valueMap);
                res += 1;
            }
            if(Arrays.toString(arr).equals(sortedStr))
                break;
        }
        return res;
    }

    private static void swap(int[] arr,int j,int k,HashMap<Integer,Integer> map)
    {
        map.replace(arr[k],j);
        map.replace(arr[j],k);
        arr[j] += arr[k];
        arr[k] = arr[j] - arr[k];
        arr[j] -= arr[k];
    }
}

