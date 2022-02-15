package problem_1_23;
/***
 棋盘覆盖问题
 描述
 棋盘覆盖问题：给定一个大小为2^n2^n个小方格的棋盘，其中有一个位置已经被填充，现在要用一个L型（22个小方格组成的大方格中去掉其中一个小方格）形状去覆盖剩下的小方格。求出覆盖方案，即哪些坐标下的小方格使用同一个L型格子覆盖。
 注意：坐标从0开始。左上方的第一个格子坐标为(0,0)，第一行第二个坐标为(0,1)，第二行第一个为(1,0)，以此类推。
 输入
 输入第一行为测试用例个数，后面每一个用例有两行，第一行为n值和特殊的格子的坐标（用空格隔开），第二行为需要查找其属于同一个L型格子的格子坐标。
 输出
 输出每一行为一个用例的解，先按照行值从小到大、再按照列值从小到大的顺序输出每一个用例的两个坐标；用逗号隔开。
 输入样例 1
 1
 1 1 1
 0 0
 输出样例 1
 0 1,1 0

 思路：理解题意，共4^n个格子得到方格棋盘 需要使用L型形状填充 (4^n - 1) / 3 必除尽且只有一种填充方式 那么对于每一个格子 必然只有唯一两个其余格子和它在一个L型上 需要找到和第二行所给格子相同L型的另外两个格子
      2^n * 2^n 个格子的棋盘，每次都可以沿中间十字划分为四个子棋盘，对十字中心周围的4个格子使用一个L型去填充（所使用的L型应横跨特殊格子不在的其余3个象限，唯一）  被填充的新的3个格式也编程特殊格子
      然后对四个子棋盘采用同样的划分方式  填充十字中心附近不包含特殊格子的3个格子  递归下去直到填充完所有格子   然后去找与目标格子在同一个L型的其余两个格子坐标
 */

import java.util.Scanner;

class Main
{
    private static int fillNo = 1;
    private static int[][] dirXY = {{-1, -1}, {-1, 0}, {0, -1}, {0, 0}}; // 左上、右上、左下、右下，用于找每个子棋盘起始X和起始Y
    private static int[][] dirRD = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // 左上、右上、左下、右下，用于找每个子棋盘起始X和起始Y
    public static void main(String[] args) {
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            fillNo = 1;
            // Data input
            int n = sc.nextInt();
            int initX = sc.nextInt();
            int initY = sc.nextInt();
            int tarX = sc.nextInt();
            int tarY = sc.nextInt();
            int[][] chess = new int[(int) Math.pow(2,n)][(int) Math.pow(2,n)];
            //初始特殊格子标记为 5，用4种L型填充的格子分别标记为1(中心点左上) 2(中心点右上) 3(中心点左下) 4(中心点右下)
            chess[initX][initY] = fillNo;
            fillNo++;
            // Process
            // ***下标处理
            fillChessWithL(chess, n, (int) Math.pow(2,n - 1), (int) Math.pow(2,n - 1));
            printRes(chess, tarX, tarY);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    /**
     * 使用L型填充边长为edge的棋盘chess，为递归调用转换为小棋盘子问题
     * @param chess 待填充棋盘
     * @param dim 当前处理方格的维度，如当前边长为4则dim为2
     * @param rightDownX 当前棋盘划分的十字点的右下方的方格X坐标
     * @param rightDownY 当前棋盘划分的十字点的右下方的方格Y坐标
     */
    private static void fillChessWithL(int[][] chess, int dim, int rightDownX, int rightDownY)
    {
        if(dim < 1)
            return;
        int filledArea = 0; // 记录划分之后哪个子棋盘有特殊格子，从左上开始顺时针的4个区域为1 2 3 4
        filledArea = findFilledArea(chess, dim, rightDownX, rightDownY);
        fillCenter(chess, dim, rightDownX, rightDownY, filledArea);
        // 划分四个子棋盘递归求解
        for(int[] dir : dirRD)
        {
            // ***下标处理
            fillChessWithL(chess, dim - 1, rightDownX + dir[0] * (int) Math.pow(2,dim - 2), rightDownY + dir[1] * (int) Math.pow(2,dim - 2));
        }
    }

    /**
     * 找到当前划分的4个子棋盘中哪个中含有特殊格子
     * @return 四个子棋盘中有特殊格子的区域，左上1 右上2 左下3 右下4
     */
    private static int findFilledArea(int[][] chess, int dim, int rightDownX, int rightDownY)
    {
        for(int i = 1; i <= 4; i++)
        {
            // ***下标处理
            for(int x = rightDownX + dirXY[i - 1][0] * (int) Math.pow(2,dim - 1); x < rightDownX + (dirXY[i - 1][0] + 1) * (int) Math.pow(2,dim - 1); x++)
            {
                for(int y = rightDownY + dirXY[i - 1][1] * (int) Math.pow(2,dim - 1); y < rightDownY + (dirXY[i - 1][1] + 1) * (int) Math.pow(2,dim - 1); y++)
                {
                    if(chess[x][y] != 0)
                    {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 根据所找到的填充区域对中间4块格子进行填充
     */
    private static void fillCenter(int[][]chess, int dim, int rightDownX, int rightDownY, int filledArea)
    {
        for(int i = 1; i <= 4; i++)
        {
            if(i != filledArea && rightDownX + dirXY[i - 1][0] >= 0 && rightDownX + dirXY[i - 1][0] < chess.length
                && rightDownY + dirXY[i - 1][1] >= 0 && rightDownY + dirXY[i - 1][1] < chess.length)
            {
                chess[rightDownX + dirXY[i - 1][0]][rightDownY + dirXY[i - 1][1]] = fillNo;
            }
        }
        fillNo++;
    }

    /**
     * 最后打印与目标点(tarX,tarY)处于同一个L型的其余两个点
     */
    private static void printRes(int[][]chess, int tarX, int tarY)
    {
        int cnt = 0;
        for(int x = tarX - 1; x <= tarX + 1; x++)
        {
            for(int y = tarY - 1; y <= tarY + 1; y++)
            {
                if(x >= 0 && x < chess.length && y >=0 && y < chess.length && chess[x][y] == chess[tarX][tarY] && (x != tarX || y != tarY))
                {
                    System.out.print(x + " " + y);
                    if(cnt == 0)
                    {
                        System.out.print(",");
                    }
                    cnt++;
                }
            }
        }
    }
}

