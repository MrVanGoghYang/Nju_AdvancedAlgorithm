package problem_1_11;
/*
课程分数
描述
You are provided with marks of N students. A student's Marks in Physics, Chemistry and Maths are provided to you.
You want to sort the Student's Numbers in ASCENDING Order of their Physics Marks. Now, Once this is done, Only those students who have
Same Marks in Physics have to be sorted in theDESCENDING order of their Chemistry Marks. And now finally, Once this is done too.
You need to Sort all those Students who have Same Marks in Physics and Chemistry in ASCENDING ORDER Of their Maths marks.

输入
The First line contains an integer T, the number of testcases.The first line of each testcase contains an integer N ,
the total number of students. The Next N lines each contains 3 integers P,C,M containing the Physics,
Chemistry and Maths' marks of ith student.（1<=T<=10；1<= N <=10000；1<=Pi,Ci,Mi<=1000）

输出
Print the Required Sorted Array of Marks.（Note: While swapping two student's data(while sorting),you need to swap all the P,C,M marks
of the students. That is You need to swap the whole tuple in a swap and not just the marks of any 1 subject.）

输入样例 1
2
10
4 5 12
1 2 3
10 9 6
4 6 5
4 3 2
4 10 10
1 2 16
10 9 32
1 14 10
10 10 4
2
20 3 4
20 3 4

输出样例 1
1 14 10
1 2 3
1 2 16
4 10 10
4 6 5
4 5 12
4 3 2
10 10 4
10 9 6
10 9 32
20 3 4
20 3 4

思路：使用快排，先对第一列进行第一次排序，然后扫描第一次排序的起始位置，对起始位置之间数值相同的部分再对第二列进行第二次排序，递归依次进行。
 */

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        int[][] marks;
        int stuNum;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
//            int max = 0;
//            int sum = 0;
            stuNum = sc.nextInt();
            marks = new int[3][stuNum];
            for(int i = 0; i < stuNum; i++)
            {
                marks[0][i] = sc.nextInt();
                marks[1][i] = sc.nextInt();
                marks[2][i] = sc.nextInt();
            }
            // Process
            int left = 0;
            int right = marks[0].length - 1;
            // 对第0行（即输入第1列）进行 快排
            quickSort(marks,left,right,0);
            // 从第0行开始扫描数值相同的区间，对区间进行第2次排序并递归对子区间进行操作
            conditionalSort(marks,left,right,0);
            for(int i = 0; i < stuNum; i++)
            {
                System.out.print(marks[0][i] + " " + marks[1][i] + " " + marks[2][i]);
                if(i != stuNum - 1)
                    System.out.print("\n");
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }

    private static void quickSort(int[][] marks,int left,int right,int pos)
    {
        if(left < right)
        {
            int pivot = partition(marks,left,right,pos);
            quickSort(marks,left,pivot - 1,pos);
            quickSort(marks,pivot + 1,right,pos);
        }
    }

    private static int partition(int[][] marks,int left,int right,int pos)
    {
        int p = left;
        int q = right;
        int[] base = new int[3];
        for(int i = 0; i < 3; i++)
        {
            base[i] = marks[i][p];
        }
        while(p < q)
        {
            while (p < q && q >= 0 && (pos % 2 == 0 ? marks[pos][q] >= base[pos] : marks[pos][q] <= base[pos]))
            {
                q--;
            }
            for (int i = 0; i < 3; i++)
            {
                marks[i][p] = marks[i][q];
            }
            while (p < q && p < marks[pos].length && (pos % 2 == 0 ? marks[pos][p] <= base[pos] : marks[pos][p] >= base[pos]))
            {
                p++;
            }
            for (int i = 0; i < 3; i++)
            {
                marks[i][q] = marks[i][p];
            }
        }
        for(int i = 0; i < 3; i++)
        {
            marks[i][p] = base[i];
        }
        return p;
    }

    private static void conditionalSort(int[][] marks,int left,int right,int pos)
    {
        // 扫描第pos行的相同区间，进行快排
        if(pos >= 2)
            return;
        int p = left;
        int q = p;
        for(int i = left; i <= right; i++)
        {
            if(i > 0 && marks[pos][i] == marks[pos][i - 1])
            {
                q = i;
                // 如果上一层全是一样的数，则不会进入else分支继续排序，添加分支进行子排序
                if (q == right)
                {
                    quickSort(marks,p,q,pos + 1);
                    conditionalSort(marks,p,q,pos + 1);
                }
            }
            // 直至出现不相同时，对这之前的一个数值全相同区间按pos + 1进行排序
            else if (i > 0 && marks[pos][i] != marks[pos][i - 1])
            {
                // 对pos + 1行的数据进行快排
                quickSort(marks,p,q,pos + 1);
                // 递归对pos + 1行扫描相同区间并执行区间内快排
                conditionalSort(marks,p,q,pos + 1);
                p = i;
            }
        }
    }
}

