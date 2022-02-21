package problem_2_4;
/***
 分配问题
 描述
 对给定的n个任务与n个人之间的成本矩阵完成成本最低的任务分配策略。【每一个人在每一个任务上都会给出对应的成本】
 输入
 输入：第一行为用例个数，之后为每一个用例；用例的第一行为任务个数，即n；用例的第二行为使用逗号隔开的人员完成任务的成本【共n*n条】；每一个成本描述包括人员序号、任务序号和成本，使用空格隔开。人员序号和任务序号都是从1到n的整数，序号出现的次序没有固定规则。
 输出
 输出：每一个用例输出一行，从序号为1的人员开始，给出其分配的任务序号，使用空格隔开；使用逗号将多个解隔开。结果按照人员分配的任务序号大小排，第一个人员的任务序号大的放在前面，如果相同则看第二个人员的任务，以此类推。

 输入样例 1
 1
 4
 2 1 6,1 2 2,1 3 7,1 4 8,1 1 9,2 2 4,2 3 3,2 4 7,3 1 5,3 2 8,3 3 1,3 4 8,4 1 7,4 2 6,4 3 9,4 4 4

 输出样例 1
 2 1 3 4

 思路：就是全排列问题求最小代价的排列方式 简单DFS即可
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
class Main
{
    private static int[] allocated;
    private static int minCost;
    private static List<List<Integer>> res;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int taskCnt = sc.nextInt();
            allocated = new int[taskCnt];
            minCost = Integer.MAX_VALUE;
            res = new ArrayList<>();
            int[][] matrix = new int[taskCnt][taskCnt];
            sc.nextLine();
            String[] taskArr = sc.nextLine().split(",");
            for(String s : taskArr)
            {
                String[] relation = s.split(" ");
                matrix[Integer.valueOf(relation[0]) - 1][Integer.valueOf(relation[1]) - 1] = Integer.valueOf(relation[2]);
            }
            // Process
            allocateTaskDFS(matrix, taskCnt, 0, new ArrayList<>(), 0);
            Collections.sort(res, new Comparator<List<Integer>>()
            {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2)
                {
                    for(int i = 0; i < taskCnt; i++)
                    {
                        if(o1.get(i) == o2.get(i))
                            continue;
                        else
                            return o1.get(i) - o2.get(i) > 0 ? -1 : 1;
                    }
                    return 0;
                }
            });
            for(int i = 0; i < res.size(); i++)
            {
                for(int j = 0; j < taskCnt; j++)
                {
                    if(j % taskCnt != 0)
                        System.out.print(" ");
                    System.out.print(res.get(i).get(j));
                }
                if(i != res.size() - 1)
                    System.out.print(",");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    /**
     * 递归函数，即求所有可能的分配方式，同时记录下分配成本最小的分配方式，返回分配的最小代价
     *     private static int[] allocated;
     *     private static int minCost;
     *     private static List<List<Integer>> res;
     */
    private static void allocateTaskDFS(int[][] matrix, int peopleCnt, int beginPeople, List<Integer> curRes,int curCost)
    {
        // 递归退出条件
        if(curCost > minCost)
            return;
        if(beginPeople == peopleCnt)
        {
            if(curCost < minCost)
            {
                res.clear();
                minCost = curCost;
            }
            res.add(new ArrayList<>(curRes));
            return;
        }
        // 对于所有可能的选择
        for(int i = 0; i < peopleCnt; i++)
        {
            // 1. 选择一种
            if(allocated[i] == 0)
            {
                allocated[i] = 1;
                curRes.add(i + 1);
                curCost += matrix[beginPeople][i];
                // 2.对子问题递归处理
                allocateTaskDFS(matrix, peopleCnt, beginPeople + 1, curRes, curCost);
                // 3.撤销选择
                curCost -= matrix[beginPeople][i];
                curRes.remove(curRes.size() - 1);
                allocated[i] = 0;
            }
        }
    }
}
