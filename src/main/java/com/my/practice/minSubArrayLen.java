package com.my.practice;

//209. 长度最小的子数组
public class minSubArrayLen {
    public static void main(String[] args) {
     int[] nums=new int[]{2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7,nums));

    }

    public static int minSubArrayLen(int target, int[] nums) {

        int left = 0;

        int result = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
                sum = sum + nums[right];
            while (sum>=target){
                int t=right - left + 1;
                result = Math.min(result, t);
                sum = sum - nums[left];
                left++;
            }
        }
        if (result==Integer.MAX_VALUE){return 0;}
        return result;
    }
}
