package com.my.experiment.demo02;


 public  class two {
    public static void reverseString(char[] s) {
        reverse(s, 0, s.length - 1);
    }

    private static void reverse(char[] s, int i, int j) {
        if (i >= j) {
            return;
        }
        // 交换 s[i] 和 s[j]，再递归反转 s[i+1..j-1]
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        reverse(s, i + 1, j - 1);
    }

    public static void main(String[] args) {
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("yuan: " + String.valueOf(input));
        reverseString(input);
        System.out.println("fan: " + String.valueOf(input));
    }
}

