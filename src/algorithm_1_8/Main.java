package algorithm_1_8;
/**
 * 描述
 * Implement pow(A, B) % C.In other words, given A, B and C, find (A^B)%C
 *
 * 输入
 * The first line of input consists number of the test cases. The following T lines consist of 3 numbers each separated
 * by a space and in the following order:A B C'A' being the base number, 'B' the exponent (power to the base number) and
 * 'C' the modular.Constraints:1 ≤ T ≤ 70,1 ≤ A ≤ 10^5,1 ≤ B ≤ 10^5,1 ≤ C ≤ 10^5
 *
 * 输入样例 1
 * 3
 * 3 2 4
 * 10 9 6
 * 450 768 517
 *
 * 输出
 * In each separate line print the modular exponent of the given numbers in the test case.
 * 输出样例 1
 * 1
 * 4
 * 34
 *
 *
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

