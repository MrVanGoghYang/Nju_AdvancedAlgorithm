package problem_1_10;
/*
书本分发
描述
You are given N number of books. Every ith book has Pi number of pages. You have to allocate books to M number of students.
There can be many ways or permutations to do so. In each permutation one of the M students will be allocated the maximum number of pages.
Out of all these permutations, the task is to find that particular permutation in which the maximum number of pages allocated to a student
is minimum of those in all the other permutations, and print this minimum value. Each book will be allocated to exactly one student.
Each student has to be allocated at least one book.每一个学生只能分配连续出现的书本。

输入
The first line contains 'T' denoting the number of testcases. Then follows description of T testcases:Each case begins with
a single positive integer N denoting the number of books.The second line contains N space separated positive integers denoting
the pages of each book.And the third line contains another integer M, denoting the number of students
Constraints:1<= T <=70，1<= N <=50，1<= A [ i ] <=250，1<= M <=50，
Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order (see explanation for better understanding)

输出
For each test case, output a single line containing minimum number of pages each student has to read for corresponding test case.

输入样例 1
1
4
12 34 67 90
2
输出样例 1
113

思路：同Proble 1-5，一模一样。。。
 */

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        int bookNum;
        int[] books;
        int stuNum;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            int max = 0;
            int sum = 0;
            bookNum = sc.nextInt();
            books = new int[bookNum];
            for(int i = 0; i < bookNum; i++)
            {
                books[i] = sc.nextInt();
                max = max > books[i] ? max : books[i];
                sum += books[i];
            }
            stuNum = sc.nextInt();
            // Process
            if(bookNum <= 0 || stuNum <= 0 || bookNum < stuNum)
            {
                System.out.print("-1");
            }
            else
            {
                System.out.print(findMinPages(books,stuNum,max,sum));
            }
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }
    private static int findMinPages(int[] books,int stuNum,int bot,int top)
    {
        int alloCnt = 1;
        int sum = 0;
        int left = bot;
        int right = top;
        if(bot > top || stuNum == 0 || books.length == 0)
            return -1;
        while(left < right)
        {
            int mid = left + (right - left) / 2;
            alloCnt = 1;
            sum = 0;
            for(int i = 0; i < books.length; i++)
            {
                sum += books[i];
                if(sum > mid)
                {
                    sum = books[i];
                    alloCnt += 1;
                }
            }
            if(alloCnt <= stuNum)
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        return left;
    }
}

