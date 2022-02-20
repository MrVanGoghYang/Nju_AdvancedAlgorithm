package problem_2_1;
/***
 拓扑排序解的个数
 描述
 给定有向无环图中所有边，计算图的拓扑排序解的个数。
 输入
 输入第一行为用例个数，后面每一行表示一个图中边的数量和所有边具体信息；边的数量和边信息间用空格隔开；边的起点和终点用空格隔开，边之间使用逗号隔开。
 输出
 输出拓扑排序解的个数。
 输入样例 1
 1
 7 a c,b c,c d,d e,d f,e g,f g
 输出样例 1
 4

 思路： 拓扑排序：选取有向图中入度为0的点，删除点及出度，更新各个点的入度，直至最后所有点都被选中； 实质就是DFS遍历数，使用DFS模板
       注意： DFS不会在调用函数及递归函数中都有 循环选择及选择撤销动作， 一般只在递归函数中写，在调用函数中不用写 直接调用，除非有起始点的区别。
       定义点和边的数据结构用于存储  图的存储有邻接表（常用）和邻接矩阵
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Vertex
{
    char id;
    int inCnt;
    int visited;
    Vertex nextNeighbor;
    Vertex(char id)
    {
        this.id = id;
    }
}

class Main
{
    private static int topoCnt = 0;
    private static int visitedCnt = 0;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            topoCnt = 0;
            visitedCnt = 0;
            int edgeLen = sc.nextInt();
            Map<Character,Vertex> graph = new HashMap<>();

            String edgeStr = sc.nextLine().replaceAll(",", " ").replace(" ","").toLowerCase(Locale.ROOT);
            char[] edgeArr = edgeStr.toCharArray();
            for(int i = 0; i < edgeLen * 2; i++)
            {
                char firVtx = edgeArr[i];
                char secVtx = edgeArr[i + 1];
                // 入度节点不存在的话先创建
                if(!graph.containsKey(secVtx))
                {
                    graph.put(secVtx, new Vertex(secVtx));
                }
                // Graph中存在边的第一个端点，则将第二个端点插入第一个端点的邻接链表的最后，并修改后一个节点的入度
                if(!graph.containsKey(firVtx))
                {
                    graph.put(firVtx, new Vertex(firVtx));
                    graph.get(firVtx).nextNeighbor = new Vertex(secVtx);
                }
                else
                {
                    Vertex curVtx = graph.get(firVtx);
                    while(curVtx.nextNeighbor != null)
                    {
                        curVtx = curVtx.nextNeighbor;
                    }
                    curVtx.nextNeighbor = new Vertex(secVtx);
                }
                graph.get(secVtx).inCnt++;
                i++;
            }
            // Process
            topoSort(graph);
            System.out.print(topoCnt);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }
    private static void topoSort(Map<Character,Vertex> graph)
    {
        // 递归退出条件
        if(visitedCnt == graph.size())
        {
            for(Map.Entry<Character,Vertex> entry : graph.entrySet())
            {
                if(entry.getValue().visited == 0)
                    return;
            }
            topoCnt++;
            return;
        }
        // 对于所有可能的下一步选择
        for(Character c : graph.keySet())
        // 1. 选择一个
        {
            Vertex v = graph.get(c);
            if(v.inCnt == 0 && v.visited == 0)
            {
                v.visited = 1;
                Vertex vChg = v;
                while (vChg.nextNeighbor != null)
                {
                    graph.get(vChg.nextNeighbor.id).inCnt--;
                    vChg = vChg.nextNeighbor;
                }
                visitedCnt++;
                // 2.从该选择往下递归
                topoSort(graph);
                // 3.撤销选择
                visitedCnt--;
                v.visited = 0;
                while (v.nextNeighbor != null)
                {
                    graph.get(v.nextNeighbor.id).inCnt++;
                    v = v.nextNeighbor;
                }
            }
        }
    }
}
