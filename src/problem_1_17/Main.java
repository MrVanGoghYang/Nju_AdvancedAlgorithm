package problem_1_17;
/**
冒泡排序
描述
实现冒泡排序。
输入
输入第一行为用例个数， 每个测试用例输入的每一行代表一个数组，其中的值用空格隔开，第一个值表示数组的长度。
输出
输出排序的数组，用空格隔开，末尾不要空格。
输入样例 1
1
13 24 3 56 34 3 78 12 29 49 84 51 9 100
输出样例 1
3 3 9 12 24 29 34 49 51 56 78 84 100
思路：
**/
import java.util.Arrays;
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
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
            }
            // Process
            bubbleSort(arr);
            for(int i = 0; i < arrLen; i++)
            {
                System.out.print(arr[i]);
                if(i != arrLen - 1)
                    System.out.print(" ");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void bubbleSort(int[] arr)
    {
        int len = arr.length;
        int[] sortedArr = Arrays.copyOf(arr,len);
        Arrays.sort(sortedArr);
        String sortedStr = Arrays.toString(sortedArr);
        if(len == 1)
            return;
        for(int i = len - 1; i >= 0; i--)
        {
            for(int j = i - 1; j >= 0; j--)
            {
                if(arr[i] < arr[j])
                    swap(arr,i,j);
            }
            if(Arrays.toString(arr).equals(sortedStr))
                break;
        }
    }

    private static void swap(int[] arr,int i,int j)
    {
        arr[i] += arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] -= arr[j];
    }
}

