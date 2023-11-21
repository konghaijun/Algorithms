package com.my.practice;
 public class hello {

  public static void main(String[] args) {
int[] nums=new int[]{3,2,2,3,3};
    int val=3;
    System.out.print(removeElement(nums,val));

  }




     /**
      * 1.dp[i]代表当前下标对应的最大值
      * 2.递推公式 dp[i] = max (dp[i-1]+nums[i],nums[i]) res = max(res,dp[i])
      * 3.初始化 都为 0
      * 4.遍历方向，从前往后
      * 5.举例推导结果。。。
      */

     /*
     问题理解：最大子数组和问题是一个经典的算法问题，要求在一个给定的整数数组中找到一个连续的子数组，使得子数组元素之和最大。这个问题的目标是找到最大的子数组和，并返回这个和值。

     问题分析：在动态规划中，我们通常会定义一个状态数组来存储中间状态，然后通过状态转移方程来更新状态数组，最终得到问题的解。
     对于最大子数组和问题，我们可以定义一个状态数组 dp[i]，表示以第 i 个元素结尾的子数组的最大和。然后我们需要找到 dp[i] 和 dp[i-1] 的关系，以及初始状态下 dp[0] 的取值。

     数学建模：数学建模阶段主要涉及状态的定义和状态转移方程的建立。
     对于最大子数组和问题，可以将状态 dp[i] 定义为以第 i 个元素结尾的子数组的最大和，
     状态转移方程可以定义为：dp[i] = max(nums[i], dp[i-1] + nums[i])，其中 nums[i] 表示第 i 个元素的值。初始状态下，可以将 dp[0] 的值设为 nums[0]。

     */
     public  int maxSubArray(int[] nums) {
         if (nums.length == 0) {
             return 0;
         }
         int res = nums[0];
         int[] dp = new int[nums.length];
         dp[0] = nums[0];
         for (int i = 1; i < nums.length; i++) {
             dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
             res=Math.max(res,dp[i]);
         }
         return res;
     }



 public static int removeElement(int[] nums, int val) {
       int left =0;
       int right=0;
      for(int i=0;i<nums.length;i++){
        if(nums[i]!=val){
          nums[left]=nums[right];
          left++;
        }
        right++;
      }


      return left;
    }





  public static int search(int[] nums,int targer){
    int left =0;
    int right=nums.length-1;

    int middle=(left+right)/2;
    while(nums[middle]!=targer&&left<=right){
      if(nums[middle]<targer){
        left=middle+1;
      }else{
        right=middle-1;
      }
      middle=(left+right)/2;
    }
    if (nums[middle]!=targer) {
        return -1;
    }
    return middle;
  }
   }
