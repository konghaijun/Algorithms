package com.my.practice;


public class hello {

  public static void main(String[] args) {
int[] nums=new int[]{2,3,1,2,4,3};
    //int val=3;
    //System.out.print(removeElement(nums,val));
 // System.out.print(maxSubArray(nums));
  System.out.print(minSubArrayLen(7,nums));
  }


public static int minSubArrayLen(int target, int[] nums) {

  int left=0;
  int right=0;
  int result=Integer.MAX_VALUE;
  int sum=nums[0];
  //int start=-1;
  for(int i=1;i<nums.length;i++){
    if(sum<target){
      right++;
      sum=sum+nums[right];
    }else {
      result=Math.min(result,right-left+1);
      sum=sum-nums[left];
      left++;
    }
  }
  
  return result;
    }



   


//最大子序列和
    public static   int maxSubArray(int[] nums) {
      //int[] dp=new int[nums.length];
      int left=nums[0];
      int result=nums[0]; 
      for(int i=1;i<nums.length;i++){
      left=Math.max(left+nums[i],nums[i]);
      result=Math.max(left,result);
        }
      return result;
    }
   
   
//有序平方数组
public static int[] sortedSquares(int[] nums) {
   int left=0; int right=nums.length-1;
   int[] num=new int[nums.length];
   int i=nums.length-1;
  while(left<=right){
    if(nums[left]*nums[left]>=nums[right]*nums[right]){
      num[i]=nums[left]*nums[left];
      i--;
      left++;
    }else{
      num[i]=nums[right]*nums[right];
      i--;
      right--;
    }
  }
  
  return num;
    }


   
   //移除元素
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




  //二分查找
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
    if (nums[middle]!=targer) return -1;
    return middle;
  }
   }
