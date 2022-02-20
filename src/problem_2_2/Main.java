package problem_2_2;
/***
 广度优先遍历图
 描述
 按照给定的起始顶点广度优先遍历图，每一次通过字母顺序选择顶点查找下一层邻接点，打印遍历顺序。
 输入
 输入第一行为测试用例个数，后面每一个用例用多行表示，用例第一行是节点个数n和开始顶点，用空格隔开，后面n+1行为图的邻接矩阵，其中第一行为节点名称。值之间使用空格隔开。
 输出
 输出遍历顺序，用空格隔开

 输入样例 1
 1
 4 a
 a b c d
 a 0 1 1 0
 b 1 0 1 0
 c 1 1 0 1
 d 0 0 1 0

 输出样例 1
 a b c d

 思路：bfs 遍历使用队列 private static Queue<Integer> queue = new LinkedList<>(); 使用LinkedList
      注意 优先队列 private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(int capacity,Comparator c);
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Main
{
    private static Queue<Integer> queue = new LinkedList<>();
    private static int[] visited;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            queue.clear();
            int verCnt = sc.nextInt();
            visited = new int[verCnt];
            int[][] graph = new int[verCnt][verCnt];
            char beginVer = sc.nextLine().replace(" ","").toCharArray()[0];
            char[] verArr = new char[verCnt];
            int i = 0;
            for(char c :sc.nextLine().replace(" ","").toCharArray())
            {
                verArr[i] = c;
                i++;
            }
            for(i = 0; i < verCnt; i++)
            {
                String[] item = sc.nextLine().split(" ");
                for(int j = 0; j < verCnt; j++)
                {
                    graph[i][j] = Integer.valueOf(item[j + 1]);
                }
            }
            // Process
            int beginIdx = findIdxByChar(verArr, beginVer);
            List<Integer> resList = bfs(graph, beginIdx);
            for(Integer item : resList)
            {
                System.out.print(verArr[item]);
                if(item != resList.get(resList.size() - 1))
                    System.out.print(" ");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static List<Integer> bfs(int[][] graph, int begin)
    {
        List<Integer> resList = new ArrayList<>();

        queue.offer(begin);
        visited[begin] = 1;
        resList.add(begin);
        while(!queue.isEmpty())
        {
            int top = queue.poll();
            for(int i = 0; i < graph.length; i++)
            {
                if(graph[top][i] == 1 && visited[i] == 0)
                {
                    queue.offer(i);
                    visited[i] = 1;
                    resList.add(i);
                }
            }

        }
        return resList;
    }

    private static int findIdxByChar(char[] arr, char c)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == c)
                return i;
        }
        return -1;
    }
}
