package problem_1_12;
/*
客流量

描述
Consider a big party where a log register for guest’s entry and exit times is maintained. Find the time at which there
are maximum guests in the party. Note that entries in register are not in any order.

输入
The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
Each test case contains an integer n denoting the size of the entry and exit array.
Then the next two line contains the entry and exit array respectively.(1<=T<=10^5;1<=N<=10^5;1<=entry[i],exit[i]<=10^5)

输出
Print the maximum no of guests and the time at which there are maximum guests in the party.

输入样例 1
2
5
1 2 10 5 5
4 5 12 9 12
7
13 28 29 14 40 17 3
107 95 111 105 70 127 74

输出样例 1
3 5
7 40

思路：设置一个入场时间entryTime和出场时间exitTime数组，排序并扫描entry与exit数组，对其时间对应的Time数组元素+1，最后entryTime与exitTime会
     记录下每个时间点的入场人数和出场人数。然后遍历寻找entryTime - exitTime累计的最大值及其序号。
 */

import java.util.Scanner;
public class Main
{
    public static void main(String[] args) {
        int[] entry;
        int[] exit;
        int[] entryTime;
        int[] exitTime;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int guestNum = sc.nextInt();
            int maxValue = 0;
            int max = 0;
            int maxTime = 1;
            entry = new int [guestNum];
            exit= new int[guestNum];
            for(int i = 0; i < guestNum; i++)
            {
                entry[i] = sc.nextInt();
                maxValue  = maxValue > entry[i] ? maxValue : entry[i];
            }
            for(int i = 0; i < guestNum; i++)
            {
                exit[i] = sc.nextInt();
                maxValue  = maxValue > exit[i] ? maxValue : exit[i];
            }
            entryTime = new int[maxValue];
            exitTime = new int[maxValue];
            // Process
            quickSort(entry,0,guestNum - 1);
            quickSort(exit,0,guestNum - 1);
            for(int i = 0; i < guestNum; i++)
            {
                entryTime[entry[i] - 1] += 1;
                exitTime[exit[i] - 1] += 1;
            }
            max = entryTime[0];
            maxTime = 1;
            for(int i = 0; i < maxValue; i++)
            {
                if(i > 0 && entryTime[i] - exitTime[i - 1] > 0)
                {
                    max += entryTime[i] - exitTime[i - 1];
                    maxTime = i + 1;
                }
            }
            System.out.print(max + " " + maxTime);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void quickSort(int[] tree,int left,int right)
    {
        if(left < right)
        {
            int pivot = partition(tree,left,right);
            quickSort(tree,left,pivot - 1);
            quickSort(tree,pivot + 1,right);
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
