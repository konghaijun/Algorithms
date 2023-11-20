package com.my.practice;
 public class hello {

  public static void main(String[] args) {
int[] nums=new int[]{3,2,2,3,3};
    int val=3;
    System.out.print(removeElement(nums,val));
  
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
    if (nums[middle]!=targer) return -1;
    return middle;
  }
   }
