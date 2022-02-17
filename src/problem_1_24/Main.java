package problem_1_24;
/***
 分治法解最近对问题
 描述
 最近对问题：使用分治算法解决最近对问题。
 输入
 第一行为测试用例个数。后面每一行表示一个用例，一个用例为一些平面上点的集合，点与点之间用逗号隔开，一个点的两个坐标用空格隔开。坐标值都是正数。
 输出
 对每一个用例输出两个距离最近的点（坐标使用空格隔开），用逗号隔开，先按照第一个坐标大小排列，再按照第二个坐标大小排列。如果有多个解，则按照每个解的第一个点的坐标排序，连续输出多个解，用逗号隔开。
 输入样例 1
 1
 1 1,2 2,3 3,4 4,5 5,1.5 1.5
 输出样例 1
 1 1,1.5 1.5,1.5 1.5,2 2

 思路：分治法： 1.对所有输入的点按照x坐标从小到大排序，为分治做预处理；  2.写递归函数求区域内的最小距离点对，可按点的个数取中点划分为左右区域，分别递归求左右区域内的最小距离点对；
              3.各求出左右区域内的最小距离点对后，求跨越左右区域的最小距离点对，注意搜索范围只可能在中轴线的左右minDis组成的矩形中搜索；如果有更小的距离点对则更新；
              https://juejin.cn/post/6878277257362833415
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class comparator implements Comparator<Object[]>
{
    @Override
    public int compare(Object[] a, Object[] b)
    {
        if(Float.valueOf(a[0].toString()) < Float.valueOf(b[0].toString()))
            return -1;
        else if(Float.valueOf(a[0].toString()) > Float.valueOf(b[0].toString()))
            return 1;
        else
        {
            if(Float.valueOf(a[1].toString()) < Float.valueOf(b[1].toString()))
                return -1;
            else if(Float.valueOf(a[1].toString()) > Float.valueOf(b[1].toString()))
                return 1;
        }
        return 0;
    }
}

class Main
{
    public static void main(String[] args) throws FileNotFoundException {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        sc.nextLine();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            // Data input
            String pointsStr = sc.nextLine();
            String[] pointStrArr = pointsStr.split(",");
            Float[][] points = new Float[pointStrArr.length][2];
            for(int i = 0; i < pointStrArr.length; i++)
            {
                String[] cordinate = pointStrArr[i].split(" ");
                if(cordinate.length == 2)
                {
                    points[i][0] = Float.valueOf(cordinate[0]);
                    points[i][1] = Float.valueOf(cordinate[1]);
                }
            }
            // Process
            Arrays.sort(points,new comparator());
            ArrayList<Integer[]> res = getMinDisPair(points, 0, points.length - 1);
            Collections.sort(res, new comparator());
            for(int i = 0; i < res.size(); i++)
            {
                Integer[] item = res.get(i);
                String firXStr = convert2Str(points[item[0]][0]);
                System.out.print(firXStr + " ");
                String firYStr = convert2Str(points[item[0]][1]);
                System.out.print(firYStr + ",");
                String secXStr = convert2Str(points[item[1]][0]);
                System.out.print(secXStr + " ");
                String secYStr = convert2Str(points[item[1]][1]);
                System.out.print(secYStr);
                if(i != res.size() - 1)
                    System.out.print(",");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static ArrayList<Integer[]> getMinDisPair(Float[][] points, int beginIndex, int endIndex)
    {
        ArrayList<Integer[]> res = new ArrayList<>();
        float minDis = Float.MAX_VALUE;
        // 递归退出条件，当前区域点的数量小于等于4，迭代求minDis
        if(endIndex - beginIndex + 1 <= 4)
        {
            for(int i = beginIndex; i <= endIndex; i++)
            {
                for(int j = i + 1; j <= endIndex; j++)
                {
                    float curDis = calDis(points, i, j);
                    if(i != j && curDis <= minDis)
                    {
                        if(res.size() != 0 && curDis != minDis)
                            res.clear();
                        minDis = curDis;
                        res.add(new Integer[]{i,j});
                    }
                }
            }
        }
        else
        {
            // 分别求左半和右半区域的最小距离对，可从中得最小距离
            ArrayList<Integer[]> leftMinDisPair = getMinDisPair(points, beginIndex, (beginIndex + endIndex) / 2);
            float leftMinDis = leftMinDisPair.size() == 0 ? Float.MAX_VALUE : calDis(points, leftMinDisPair.get(0)[0], leftMinDisPair.get(0)[1]);
            ArrayList<Integer[]> rightMinDisPair = getMinDisPair(points, (beginIndex + endIndex) / 2 + 1, endIndex);
            float rightMinDis = rightMinDisPair.size() == 0 ? Float.MAX_VALUE : calDis(points, rightMinDisPair.get(0)[0], rightMinDisPair.get(0)[1]);
            // 取左右最小距离的小值为最小距离
            minDis = Float.min(leftMinDis, rightMinDis);
            // 将最小距离那一侧区域的点对加入结果
            if(minDis == leftMinDis)
                res.addAll(leftMinDisPair);
            if(minDis == rightMinDis)
                res.addAll(rightMinDisPair);
            // 考虑中轴线往左右minDis长度的长方形区域
            // 确定中轴线x坐标，奇数个点则去中间点x为中轴线，偶数点则取中间两个点的平均值为中轴线
            float midAxis = (beginIndex + endIndex) % 2 == 0 ? points[(beginIndex + endIndex) / 2][0] :
                    (points[(beginIndex + endIndex) / 2][0] + points[(beginIndex + endIndex) / 2 + 1][0]) / 2;
            float oldMinDis = minDis;
            // 循环扫描中轴线左侧区域内且距离中轴线在minDis的点
            for(int i = (beginIndex + endIndex) / 2; i >= beginIndex && Math.abs(points[i][0] - midAxis) <= oldMinDis; i--)
            {
                // 循环臊面中轴线右侧区域内距离中轴线在minDis的点
                for (int j = (beginIndex + endIndex) / 2 + 1; j <= endIndex && Math.abs(points[j][0] - midAxis) <= oldMinDis; j++)
                {
                    float curDis = calDis(points, i, j);
                    if (i != j && curDis <= minDis)
                    {
                        if (res.size() != 0 && curDis != minDis)
                            res.clear();
                        minDis = curDis;
                        res.add(new Integer[]{i, j});
                    }
                }
            }

        }
        return res;
    }

    private static float calDis(Float[][] points, int i, int j)
    {
        return (float) Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
    }

    private static String convert2Str(float a)
    {
        if(Math.round(a) == a)
        {
            return Integer.valueOf((int)(float)Float.valueOf(a)).toString();
        }
        else
        {
            return Float.valueOf(a).toString();
        }
    }
}

