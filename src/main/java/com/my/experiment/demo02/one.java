package com.my.experiment.demo02;


public class one {
    public static int digui(int[] a) {
        int n = a.length;
        if (n == 1) {
            return a[0];
        } else {
            int[] b = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                b[i] = a[i] + a[i + 1];
            }
            return digui(b);
        }
    }

    public static void diedai(int[] a) {
        int n = a.length;
        int[][] triangle = new int[n][];
        triangle[0] = a;

        for (int i = 1; i < n; i++) {
            int[] row = new int[n - i];
            for (int j = 0; j < n - i; j++) {
                row[j] = triangle[i - 1][j] + triangle[i - 1][j + 1];
            }
            triangle[i] = row;
        }

        for (int i =n-1; i >=0; i--) {
            System.out.print(" ");
            for (int j = 0; j <triangle[i].length ; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int recursiveResult = digui(a);
        System.out.println("顶层: " + recursiveResult); // 输出 48
        diedai(a);
    }
}

