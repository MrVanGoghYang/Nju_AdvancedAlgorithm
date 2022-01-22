package problem_1_7;
/**
 * 描述
 * Given a Complete Binary tree, print the level order traversal in sorted order.
 *
 * 输入
 * The first line of the input contains integer T denoting the number of test cases. For each test case,
 * the first line takes an integer n denoting the size of array
 * i.e number of nodes followed by n-space separated integers denoting the nodes of the tree in level order fashion.
 * (1<=T<=100；1<=n<=10^5）
 *
 * 2
 * 7
 * 7 6 5 4 3 2 1
 * 6
 * 5 6 4 9 2 1
 *
 * 输出
 * For each test case, the output is the level order sorted tree. ( Note: For every level, we only print distinct elements.)
 *
 * 7
 * 5 6
 * 1 2 3 4
 * 5
 * 4 6
 * 1 2 9
 *
 *
 * 递归思想：对于树的每一层，根据层数确定最多需要打印多少个元素，进行排序后打印，然后递归到下一层；
 */

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        int arrLen;
        int[] binTree;
        // Common input process
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        //int[] res = new int[caseNum];
        for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
        {
            arrLen = sc.nextInt();
            binTree = new int[arrLen];
            for(int arrIndex = 0; arrIndex < arrLen; arrIndex++)
            {
                binTree[arrIndex] = sc.nextInt();
            }
            // Process
            printLevelInOrder(0,binTree);
            if(caseIndex != caseNum - 1)
                System.out.print("\n");
        }
    }
    private static void printLevelInOrder(int level,int[] tree)
    {
        int left = (1 << level) - 1;
        if(left >= tree.length)
            return;
        if(level != 0)
            System.out.print("\n");
        int right = (1 << (level + 1)) - 2;
        right = right >= tree.length ? tree.length - 1 : right;
        quickSort(tree, left, right);
        for(int i = left; i <= right; i++)
        {
            // 去重输出
            if(i == left || (i > left && tree[i] != tree[i - 1]))
            {
                if(i > left)
                    System.out.print(" ");
                System.out.print(tree[i]);
            }
        }
        printLevelInOrder(level + 1,tree);
    }

    private static void quickSort(int[] tree,int left,int right)
    {
        if(left >= right || left < 0 || right >= tree.length)
            return;

        int p = left;
        int q = right;
        int pivort = left;
        while(p < q)
        {
            while (tree[q] >= tree[pivort] && q > 0) {
                q--;
            }
            if (tree[q] < tree[pivort] && q > pivort) {
                tree[q] += tree[pivort];
                tree[pivort] = tree[q] - tree[pivort];
                tree[q] -= tree[pivort];
                pivort = q;
            }
            while (tree[p] <= tree[pivort] && p < pivort) {
                p++;
            }
            if (tree[p] > tree[pivort] && p < pivort) {
                tree[p] += tree[pivort];
                tree[pivort] = tree[p] - tree[pivort];
                tree[p] -= tree[pivort];
                pivort = p;
            }
        }
        quickSort(tree,left,pivort - 1);
        quickSort(tree,pivort + 1,right);
    }
}

