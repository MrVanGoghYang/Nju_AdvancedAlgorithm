package problem_2_3;
/***
 深度优先遍历
 描述
 按照给定的起始顶点深度优先遍历给定的无向图，尝试所有可能的遍历方式，打印遍历过程中出现的最大深度。
 输入
 输入第一行是用例个数，后面每个用例使用多行表示，用例的第一行是图中节点的个数n和起始点，用空格隔开，后面n+1行为图的邻接矩阵，其中第一行为节点名称。值之间使用空格隔开。
 输出
 输出深度优先遍历中遇到的最大深度。

 输入样例 1
 1
 4 a
 a b c d
 a 0 1 1 0
 b 1 0 1 0
 c 1 1 0 1
 d 0 0 1 0

 输出样例 1
 4

 思路：写了很久 无向图的DFS递归改迭代写法:
    使用栈 两个标志数组visited（记录节点曾经有一次被访问过，解决非连通图）和onPath（记录该条路径上的节点的深度，用于入栈判断以及深度到底后返回重置用）
      1. 初始节点入栈；
      2.当栈非空时：栈顶元素出栈，
        （1）判断该元素depth是否比maxDepth小，如果小则说明以及产生了回溯，则需要修改onpath的值，对于所有大于等于它的都置 0；
        （2）进行访问 visited = 1；  onpath = 该元素.depth；  更改maxDepth；
        （3）不在onPath上的该元素的邻居入栈，注意节点可以重复入栈，因为当有环的时候同一节点可能在不同的访问路径上有不同的depth
    递归写法：深度优先遍历使用递归函数,dfs模板
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Node
{
    int id;
    int depth;
    Node(int id,int depth)
    {
        this.id = id;
        this.depth = depth;
    }

    public int getId()
    {
        return id;
    }

    public int getDepth()
    {
        return depth;
    }
}
class Main
{
    private static int maxDep = 0;
    private static Stack<Node> stack = new Stack<>();
    private static int[] visited;
    private static int[] onPath;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            maxDep = 0;
            int verCnt = sc.nextInt();
            visited = new int[verCnt];
            onPath = new int[verCnt];
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
            int res = dfsIRecurse(graph, beginIdx);
            for(int j = 0; j < graph.length; j++)
            {
                if(visited[j] == 0)
                    res = Integer.max(res, dfsIRecurse(graph, j));
            }
            System.out.print(res);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    /**
     * 递归求从begin点开始遍历图所达的最大深度
     **/
    private static int dfsIRecurse(int[][] graph, int begin)
    {
        // 初始处理
        int res = 1;
        visited[begin] = 1;
        onPath[begin] = 1;
        // 递归退出条件
        int i = 0;
        for(i = 0; i < graph.length; i++)
        {
            if (graph[begin][i] == 1 && onPath[i] == 0)
            {
                break;
            }
        }
        if(i == graph.length)
            return 1;
        // 对于所有可能的选择
        for(i = 0; i < graph.length; i++)
        {
            // 1. 选择一种
            if(graph[begin][i] == 1 && onPath[i] == 0)
            {
                // 2.对子问题递归处理
                int subRes = dfsIRecurse(graph, i);
                res = Integer.max(subRes + 1 , res);
                // 3.撤销选择
                onPath[i] = 0;
            }
        }
        return res;
    }

    private static void dfsIterate(int[][] graph, int begin)
    {
        stack.push(new Node(begin, 1));
//        System.out.print(begin + "," + 1 +" ");
        while (!stack.isEmpty())
        {
            Node top = stack.pop();
            // 当前元素的depth变小，则说明该顶点为回溯后的点，把当前访问路径上深度更深的全部置0表示被回溯过
            if(top.getDepth() < maxDep)
            {
                for(int i = 0; i < graph.length; i++)
                {
                    if(onPath[i] >= top.getDepth())
                        onPath[i] = 0;
                }
            }
            // 记录当前访问路径上各个点的访问深度
            onPath[top.getId()] = top.getDepth();
            visited[top.getId()] = 1;
            maxDep = maxDep > top.getDepth() ? maxDep : top.getDepth();
            // 对于所有邻接点
            for(int i = 0; i < graph.length; i++)
            {
                // 如果未在路径上则入栈，注意入栈顶点可重复因为当有环的时候同一个顶点会在不同路径访问到产生不同的深度，全部邻接点都要入栈
                if(graph[top.getId()][i] == 1 && onPath[i] == 0)
                {
                    stack.push(new Node(i, top.getDepth() + 1));
//                    System.out.print(i + "," + (top.getDepth() + 1) + " ");
                }
            }
        }
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
