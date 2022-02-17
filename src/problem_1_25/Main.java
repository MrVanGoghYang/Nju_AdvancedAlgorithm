package problem_1_25;
/***
 按照数值个数排序
 描述
 对给定数组中的元素按照元素出现的次数排序，出现次数多的排在前面，如果出现次数相同，则按照数值大小排序。例如，给定数组为{2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}，则排序后结果为{3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}。
 输入
 输入的第一行为用例个数；后面每一个用例使用两行表示，第一行为数组长度，第二行为数组内容，数组元素间使用空格隔开。
 输出
 每一个用例的排序结果在一行中输出，元素之间使用空格隔开。
 输入样例 1
 1
 4
 2 3 2 5
 输出样例 1
 2 2 3 5

 思路：HashMap去重，然后使用ArrayList排序
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class Main
{

    private static class DefinedComparator implements Comparator<Map.Entry<Integer, Integer>>
    {
        @Override
        public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b)
        {
            if(a.getValue() != b.getValue())
                return a.getValue() - b.getValue() > 0 ? -1 : 1;
            else if(a.getKey() != b.getKey())
                return a.getKey() - b.getKey() > 0 ? 1 : -1;
            else
                return 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int arrLen = sc.nextInt();
            int[] arr = new int[arrLen];

            HashMap<Integer, Integer> resMap = new HashMap<>();
            for(int i = 0; i < arrLen; i++)
            {
                arr[i] = sc.nextInt();
                if(!resMap.containsKey(arr[i]))
                {
                    resMap.put(arr[i], 1);
                }
                else
                {
                    resMap.replace(arr[i], resMap.get(arr[i]) + 1);
                }
            }
            ArrayList<Map.Entry<Integer, Integer>> resList = new ArrayList<>(resMap.entrySet());
            Collections.sort(resList, new Main.DefinedComparator());
            for(int i = 0; i < resList.size(); i++)
            {
                Map.Entry<Integer, Integer> entry = resList.get(i);
                for(int j = 0; j < entry.getValue(); j++)
                {
                    System.out.print(entry.getKey());
                    if(i != resList.size() - 1 || j != entry.getValue() - 1)
                        System.out.print(" ");
                }
            }
            // Process
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }
}

