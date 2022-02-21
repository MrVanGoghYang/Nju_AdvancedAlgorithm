package problem_2_5;
/***
 Searching_4
 描述
 Given n Magnets which are placed linearly, with each magnet to be considered as of point object. Each magnet suffers force from its left sided magnets such that they repel it to the right and vice versa.
 All forces are repulsive. The force being equal to the distance (1/d , d being the distance). Now given the positions of the magnets, the task to find all the points along the linear line where net force is ZERO.
 Note: Distance have to be calculated with precision of 0.0000000000001.
 Constraints:1<=T<=100,1<=N<=100,1<=M[]<=1000
 输入
 The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. The second line of each test case contains an integer N.
 Then in the next line are N space separated values of the array M[], denoting the positions of the magnet.
 输出
 For each test case in a new line print the space separated points having net force zero till precised two decimal places.
 输入样例 1
 2
 2
 1 2
 4
 0 10 20 30
 输出样例 1
 1.50
 3.82 15.00 26.18

 思路： 在每两个数之间找到一个符合要求的小数  使用二分查找，注意处理mid不再改变的情况避免死循环  主要是小数位数的处理上，使用 DecimalFormat类可以限定输出的double类型的小数点后位数
        private static DecimalFormat jdgDc = new DecimalFormat("0.0000000000000");
        jdgDc.format(d);
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

class Main
{
    private static DecimalFormat resDc = new DecimalFormat("0.00");
    private static DecimalFormat jdgDc = new DecimalFormat("0.0000000000000");
    private static double lastMid;
    public static void main(String[] args) throws FileNotFoundException
    {
        // Common input process
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("C:\\Users\\97267\\Desktop\\input.txt"));
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int magCnt = sc.nextInt();
            int[] magArr = new int[magCnt];
            for(int i = 0; i < magCnt; i++)
            {
                magArr[i] = sc.nextInt();
            }
            // Process
            Set<Double> res = findZeroForceP(magArr, magCnt);
            int cursor = 0;
            for(Double d : res)
            {
                if(cursor != 0)
                    System.out.print(" ");
                System.out.print(resDc.format(d));
                cursor++;
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static Set<Double> findZeroForceP(int[] arr, int len)
    {
        Set<Double> res = new TreeSet<>();
        for(int i = 0; i < len - 1; i++)
        {
            lastMid = Double.MAX_VALUE;
            res.add(binSearch(arr, i, arr[i], arr[i + 1]));
        }
        return res;
    }

    private static double binSearch(int[] arr, int leftIdx, double left, double right)
    {
        double mid = Double.valueOf(resDc.format((left + right) / 2));
        double leftForce = 0;
        double rightForce = 0;
        double res[] = calForce(arr, leftIdx, mid);
        leftForce = Double.valueOf(jdgDc.format(res[0]));
        rightForce = Double.valueOf(jdgDc.format(res[1]));
        if(mid == Double.valueOf(resDc.format(lastMid)))
        {
            double[] item;
            if(mid == left)
            {
                item = calForce(arr, leftIdx, right);
                return Math.abs(leftForce - rightForce) < Math.abs(item[0] - item[1]) ? mid : right;
            }
            else
            {
                item = calForce(arr, leftIdx, left);
                return Math.abs(leftForce - rightForce) < Math.abs(item[0] - item[1]) ? mid : left;
            }
        }
        lastMid = mid;
        if(leftForce == rightForce)
            return mid;
        else if(leftForce < rightForce)
            return binSearch(arr, leftIdx, left, mid);
        else
            return binSearch(arr, leftIdx, mid, right);
    }

    private static double[] calForce(int[] arr, int leftIdx, double mid)
    {
        double[] res = new double[2];
        for(int i = 0; i < arr.length && mid != arr[i]; i++)
        {
            if(i <= leftIdx)
                res[0] += 1 / Math.abs(mid - arr[i]);
            else
                res[1] += 1/ Math.abs(arr[i] - mid);
        }
        return res;
    }
}
