package problem_1_9;
/**
点的凸包
描述
Convex Hull of a set of points, in 2D plane, is a convex polygon with minimum area such that each point lies either on
the boundary of polygon or inside it. Now given a set of points the task is to find the convex hull of points.

输入
The first line of input contains an integer T denoting the no of test cases. Then T test cases follow.
Each test case contains an integer N denoting the no of points. Then in the next line are N*2 space separated values
denoting the points ie x and y.Constraints:1<=T<=100,1<=N<=100,1<=x,y<=1000

输出
For each test case in a new line print the points x and y of the convex hull separated by a space in sorted order
(increasing by x) where every pair is separated from the other by a ','. If no convex hull is possible print -1.

输入样例 1
2
3
1 2 3 1 5 6
3
1 2 4 4 5 1
输出样例 1
1 2, 3 1, 5 6
1 2, 4 4, 5 1

https://blog.csdn.net/lxt_Lucia/article/details/83116517
思路：上下包法，先找最左端点A与最右端点B为一条线，这两个点必为凸包点。然后根据线的上下分为上包和下包，分别找距离线最远的点C，C必为凸包点；
     连接AC与BC，分别再向上/向下找距AC BC最远的点，也必为凸包点；
     一个分治递归的过程，分别向上向下找。分治法。
     判断一个点在一条线的左侧（上）或右侧（下），使用向量叉乘判断，A(x1,y1) X B(x2,y2) = x1 * y2 - x2 * y1 = |A|*|B|*sinα，正则上，负则下；
     判断距离直线最远的点  上式 |A X B| =  ||A|*|B|*sinα| = S(平行四边形) = 1/2 * S(▲) = 1/2 * 1/2 * |A| * H = 1/4 * |A| * H，H为底边A的垂线长
     因此，找距离直线最远的点即找H最大值，即找|A X B|的最大值；
 */

import java.util.ArrayList;
import java.util.Scanner;
class Point
{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Main {
    static ArrayList<Integer> resIndex;
    static int[] visited;
    public static void main(String[] args) {
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            boolean oneLine = true;
            int pointNum = sc.nextInt();
            Point[] points = new Point[pointNum];
            visited = new int[pointNum];
            resIndex = new ArrayList<>();
            for(int pointIndex = 0; pointIndex < pointNum; pointIndex++)
            {
                points[pointIndex] = new Point(sc.nextInt(), sc.nextInt());
            }
            // Process
            findConvexHull(points);
            // 避免所有点共线时，无法构成封闭图形，将斜率相等的除法转换为乘法避免除0情况
            for (int i = 2; i < resIndex.size(); i++)
            {
                if((points[resIndex.get(i)].y - points[resIndex.get(i - 1)].y) * (points[resIndex.get(i)].x - points[resIndex.get(i - 2)].x)
                        != (points[resIndex.get(i)].y - points[resIndex.get(i - 2)].y) * (points[resIndex.get(i)].x - points[resIndex.get(i - 1)].x))
                {
                    oneLine = false;
                    break;
                }
            }
            // 避免少于3个点无法构成封闭图形，并将凸包点按x递增顺序排序并输出
            if(resIndex.size() >= 3 && !oneLine) {
                quickSort(points, resIndex, 0, resIndex.size() - 1);
                for (int i : resIndex) {
                    System.out.print(points[i].x + " " + points[i].y);
                    if (i != resIndex.get(resIndex.size() - 1))
                        System.out.print(", ");
                }
            }
            else
                System.out.print("-1");
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void findConvexHull(Point[] points)
    {
        // 最左端及最右端的点一定是凸包点
        int left = 0;
        int right = 0;
        for(int i = 0; i < points.length; i++)
        {
            if(points[i].x < points[left].x)
                left = i;
            if(points[i].x > points[right].x)
                right = i;
        }
        // 避免左右两个点相同或x相同
        if(left != right)
        {
            resIndex.add(left);
            visited[left] = 1;
            resIndex.add(right);
            visited[right] = 1;
            // 0代表向上找凸包，1代表向下找凸包
            findPoint(points, left, right, 0);
            findPoint(points, left, right, 1);
        }
    }

    private static void findPoint(Point[] points,int left,int right,int dir)
    {
        if(left == -1 || right == -1)
            return;
        int maxHeight = -1;
        int maxHeightIndex = -1;
        Point vectorA = new Point(points[right].x - points[left].x,points[right].y - points[left].y);
        // 找离向量A所在直线最远的点
        for(int i = 0; i < points.length; i++)
        {
            if(visited[i] == 0) {
                Point vectorB = new Point(points[i].x - points[left].x, points[i].y - points[left].y);
                int res = vectorA.x * vectorB.y - vectorA.y * vectorB.x;
                // 向量叉乘结果符合搜索方向
                if (res > 0 ? dir == 0 : dir == 1) {
                    if (Math.abs(res) > maxHeight) {
                        maxHeight = Math.abs(res);
                        maxHeightIndex = i;
                    }
                }
            }
        }
        // 若找得到最远点，则该点必为凸包点
        if(maxHeightIndex != -1) {
            resIndex.add(maxHeightIndex);
            visited[maxHeightIndex] = 1;
        }
        else
        {
            //若找不到最远点，则说明该条left-right线为一条边界，该条线上面的其他点也为凸包点
            for(int i = 0; i < points.length; i++)
            {
                if(visited[i] == 0) {
                    Point vectorB = new Point(points[i].x - points[left].x, points[i].y - points[left].y);
                    int res = vectorA.x * vectorB.y - vectorA.y * vectorB.x;
                    if(res == 0)
                    {
                        resIndex.add(i);
                        visited[i] = 1;
                    }
                }
            }
        }
        // 递归向同一个方向，沿两条线的垂线方向继续寻找
        findPoint(points,left,maxHeightIndex,dir);
        findPoint(points,maxHeightIndex,right,dir);
    }

    private static void quickSort(Point[] points,ArrayList<Integer> index,int left,int right)
    {
        if(left >= right || left < 0 || right >= index.size())
            return;
        int p = left;
        int q = right;
        int pivort = left;
        while(p < q)
        {
            while (points[index.get(q)].x >= points[index.get(pivort)].x && q > 0) {
                q--;
            }
            if (points[index.get(q)].x < points[index.get(pivort)].x && q > pivort) {
                index.set(q,index.get(q) + index.get(pivort));
                index.set(pivort,index.get(q) - index.get(pivort));
                index.set(q,index.get(q) - index.get(pivort));
                pivort = q;
            }
            while (points[index.get(p)].x <= points[index.get(pivort)].x && p < pivort) {
                p++;
            }
            if (points[index.get(p)].x > points[index.get(pivort)].x && p < pivort) {
                index.set(p,index.get(p) + index.get(pivort));
                index.set(pivort,index.get(p) - index.get(pivort));
                index.set(p,index.get(p) - index.get(pivort));
                pivort = p;
            }
        }
        quickSort(points,index,left,pivort - 1);
        quickSort(points,index,pivort + 1,right);
    }
}

