import java.util.*;

class Main {
    //75 https://leetcode.com/problems/sort-colors/ //sort 0,1,2
    public void sortColors(int[] nums) {
        int start = 0,end = nums.length -1,curr = 0;
        while( curr <= end) { 
            if( nums[curr] == 2) {
                swap(nums,curr, end );
                end--;
            }
            else if(nums[curr] == 1) {
                curr++;
            }
            else{
                swap(nums, curr, start);
                curr++;
                start ++;
            }
        }
    }
    void swap(int[] arr, int a ,int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void main(String ...args){

    }
}


