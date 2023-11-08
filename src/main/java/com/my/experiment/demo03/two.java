package com.my.experiment.demo03;

import java.util.Arrays;
import java.util.List;

public class two {
//partition函数：实现快速排序算法中的划分操作，将数组中的元素按照基准值进行划分
    public static int partition(List<Integer> a, int s, int t) {
        int i = s, j = t;
        int base = a.get(s);
        while (i < j) {
            while (j > i && a.get(j) >= base) {
                j--;
            }
            if (j > i) {
                a.set(i, a.get(j));
                i++;
            }
            while (i < j && a.get(i) <= base) {
                i++;
            }
            if (i < j) {
                a.set(j, a.get(i));
                j--;
            }
        }
        a.set(i, base);
        return i;
    }

    //使用分治算法来解决将整数数组划分为和差值最大的子数组的问题。
    public static int solve(List<Integer> a) {
        int n = a.size();
        int low = 0, high = n - 1;
        boolean flag = true;
        while (flag) {
            int i = partition(a, low, high);
            if (i == n / 2 - 1) {
                flag = false;
            } else if (i < n / 2 - 1) { // 划分位置小于数组长度的一半减1，则在右半部分继续划分
                low = i + 1;
            } else { // 划分位置大于数组长度的一半减1，则在左半部分继续划分
                high = i - 1;
            }
        }
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; i++) {
            s1 += a.get(i);
        }
        for (int j = n / 2; j < n; j++) {
            s2 += a.get(j);
        }
        return s2 - s1;
    }

    //遍历输出
    public static void display(List<Integer> a, int l, int h) {
        for (int i = l; i <= h; i++) {
            System.out.printf("%3d", a.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("实验结果:\n");

        // 第一个测试数据
        List<Integer> a = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8);
        System.out.print("初始序列 A: ");
        display(a, 0, a.size() - 1);
        System.out.printf("求解结果: %d\n", solve(a));
        System.out.print("划分结果 A1: ");
        display(a, 0, a.size() / 2 - 1);
        System.out.print("\tA2: ");
        display(a, a.size() / 2, a.size() - 1);

        // 第二个测试数据
        List<Integer> b = Arrays.asList(1, 3, 5, 7, 9, 10, 2, 4, 6, 8);
        System.out.print("\n初始序列 B: ");
        display(b, 0, b.size() - 1);
        System.out.printf("求解结果: %d\n", solve(b));
        System.out.print("划分结果 B1: ");
        display(b, 0, b.size() / 2 - 1);
        System.out.print("\tB2: ");
        display(b, b.size() / 2, b.size() - 1);
    }
}
