package problem_1_13;
/**
和为定值的子数组
描述
Given an array A of size N, find all combination of four elements in the array whose sum is equal to a given value K.
For example, if the given array is {10, 2, 3, 4, 5, 9, 7, 8} and K = 23, one of the quadruple is “3 5 7 8” (3 + 5 + 7 + 8 = 23).
输入
The first line of input contains an integer T denoting the no of test cases. Then T test cases follow.
Each test case contains two lines. The first line of input contains two integers N and K.
Then in the next line are N space separated values of the array.（1<=T<=100；1<=N<=100；-1000<=K<=1000；-100<=A[]<=100）
输出
For each test case in a new line print all the quadruples present in the array separated by space which sums up to value of K.
Each quadruple is unique which are separated by a delimeter "$" and are in increasing order.
输入样例 1
2
5 3
0 0 2 1 1
7 23
10 2 3 4 5 7 8
输出样例 1
0 0 1 2 $
2 3 8 10 $2 4 7 10 $3 5 7 8 $
思路：实质为4数和问题，和3数和，2数和问题一样。 递归解决太慢，Leetcode LTE但NJU OJ AC。
     该用更好的迭代解决的方法。参考Leetcode四数和题解，使用 “双指针”，先排序并固定最小两个指针ab在最左端，c=b+1,d=size-1,大了c右移，小了d左移，相遇则找到以ab为最小的全部组合结果，下一轮移动ab：
     https://leetcode-cn.com/problems/4sum/solution/shuang-zhi-zhen-jie-fa-can-zhao-san-shu-zhi-he-ge-/
     少递归，多迭代！阿弥陀佛
 */
import java.util.*;

class Main
{
    static String curOut = "";
    static HashSet<List<Integer>> outRes;
    public static void main(String[] args) {
        int[] arr;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int arrLen = sc.nextInt();
            arr = new int[arrLen];
            int sum = sc.nextInt();
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
            }
            // Process
            // 1. 迭代 2数和 -> 3数和 -> 4数和思路差不多，n数和递归转化为n-1数和
            Arrays.sort(arr);
            iterateFourSumTarget(arr,sum);
            // 2. 递归蠢比方法
//            outRes = new HashSet<>();
//            quickSort(arr,0,arrLen - 1);
//            findQuadruples(arr,0,4,sum);
            // 3. 修改为Leetcode 4数和  递归LTE...
//            fourSum(arr,sum);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void iterateFourSumTarget(int[] arr,int target)
    {
        for(int a = 0; a < arr.length - 3; a++)
        {
            // 剪枝
            if(arr[a] + arr[a + 1] + arr[a + 2] + arr[a + 3] > target)
                break;
            if(arr[a] + arr[arr.length - 1] + arr[arr.length - 2] + arr[arr.length - 3] < target)
                continue;
            for(int b = a + 1; b < arr.length - 2; b++)
            {
                // 剪枝
                if(arr[a] + arr[b] + arr[b + 1] + arr[b + 2] > target)
                    break;
                if(arr[a] + arr[b] + arr[arr.length - 1] + arr[arr.length -2] < target)
                    continue;
                int c = b + 1;
                int d = arr.length - 1;
                while(c < d)
                {
                    int left = arr[c];
                    int right = arr[d];
                    int sum = arr[a] + arr[b] + arr[c] + arr[d];
                    if (sum < target)
                    {
                        while(c < d && arr[c] == left)   c++;
                    }
                    else if(sum > target)
                    {
                        while(c < d && arr[d] == right)    d--;
                    }
                    else
                    {
                        System.out.print(arr[a] + " " + arr[b] + " " + arr[c] + " " + arr[d] + " $");
                        while(c < d && arr[c] == left)   c++;
                        while(c < d && arr[d] == right)    d--;
                    }
                }
                while(b + 1 < arr.length - 2 && arr[b + 1] == arr[b])    b++;
                if(arr[a] == -2 && arr[b] == 2)
                    System.out.print("");
            }
            while(a + 1 < arr.length - 3 && arr[a + 1] == arr[a])    a++;
        }
    }

    private static void findQuadruples(int[] arr,int startInd,int remainEle,int remainSum)
    {
        // 失败递归终止
        if(startInd >= arr.length || remainEle > arr.length - startInd || remainEle == 0 && remainSum != 0 || remainEle < 0)
            return;
        // 成功找到4元组递归终止
        if(remainEle == 1 && arr[startInd] == remainSum)
        {
            curOut += arr[startInd] + " ";
            String[] str = curOut.split(" ");
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < 4; i++)
            {
                list.add(Integer.valueOf(str[i]));
            }
            if(outRes.add(list))
                System.out.print(curOut + "$");
            return;
        }
        String before = "";
        // 选取当前扫描位置元素，前进一步
        if(remainEle >= 1)
        {
            // 记录递归前的curOut
            before = curOut;
            curOut += arr[startInd] + " ";
            findQuadruples(arr,startInd + 1,remainEle - 1,remainSum - arr[startInd]);
        }
        // 递归后还原curOut
        curOut = before;
        // 不选择当前扫描位置元素，从后一个开始
        if(arr.length - (startInd + 1) >= remainEle)
        {
            before = curOut;
            curOut = remainEle == 4 ? "" : curOut;
            findQuadruples(arr, startInd + 1, remainEle, remainSum);
        }
        curOut = before;
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

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> output = new ArrayList<>();
        outRes = new HashSet<>();
        Arrays.sort(nums);
        findQuadruples2(nums,0,4,target,output);
        return output;
    }

    private static void findQuadruples2(int[] arr,int startInd,int remainEle,int remainSum,List<List<Integer>> output)
    {
        // 失败递归终止
        if(startInd >= arr.length || remainEle > arr.length - startInd || remainEle <= 0 && remainSum != 0)
            return;
        // 成功找到4元组递归终止
        if(remainEle == 1 && arr[startInd] == remainSum)
        {
            curOut += arr[startInd] + " ";
            System.out.print(curOut + "$");
            String[] str = curOut.split(" ");
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < 4; i++)
            {
                list.add(Integer.valueOf(str[i]));
            }
            if(outRes.add(list))
                output.add(list);
            return;
        }
        String beforeOut = "";
        // 选取当前扫描位置元素，前进一步
        if(remainEle >= 1)
        {
            // 记录递归前的curOut
            beforeOut = curOut;
            curOut += arr[startInd] + " ";
            findQuadruples2(arr,startInd + 1,remainEle - 1,remainSum - arr[startInd],output);
        }
        // 递归后还原curOut
        curOut = beforeOut;
        // 不选择当前扫描位置元素，从后一个开始
        if(arr.length - (startInd + 1) >= remainEle)
        {
            beforeOut = curOut;
            curOut = remainEle == 4 ? "" : curOut;
            findQuadruples2(arr, startInd + 1, remainEle, remainSum,output);
        }
        curOut = beforeOut;
    }
}

