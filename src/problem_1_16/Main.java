package problem_1_16;
/**
插入排序
描述
实现插入排序。
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
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
            }
            // Process
            insertSort(arr);
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

    private static void insertSort(int[] arr)
    {
        int len = arr.length;
        if(len == 1)
            return;
        for(int i = 1; i < len; i++)
        {
            int item = arr[i];
            int insertPos = findPos(arr, i - 1, item);
            for(int j = i - 1; j >= insertPos; j--)
            {
                arr[j + 1] = arr[j];
            }
            arr[insertPos] = item;
        }
    }
    // 在arr数组的[0,i]有序子数组中二分查找target元素应该插入的位置
    private static int findPos(int[] arr,int i,int target)
    {
        if(i == 0)
            return target < arr[0] ? 0 : 1;
        if(target > arr[i])
            return i + 1;
        int left = 0;
        int right = i;
        while(left < right)
        {
            int mid = left + (right - left) / 2;
            if(arr[mid] < target)
            {
                left = mid + 1;
            }
            else if(arr[mid] > target)
            {
                right = mid;
            }
            else
            {
                return mid;
            }
        }
        return left;
    }
}

