// boyer-moore voting algo
// https://leetcode.com/problems/majority-element/
// 169. Majority Element
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
// https://leetcode.com/problems/max-chunks-to-make-sorted/
// 769. Max Chunks To Make Sorted
class Solution {
    public int maxChunksToSorted(int[] arr) {
        if(arr.length == 0){
            return 0;
        }
        
        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            if(max == i){
                count++;
            }
        }
        
        return count;
    }
}
// https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
// 768. Max Chunks To Make Sorted II
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] minfr = new int[arr.length];
        
        minfr[arr.length - 1] = arr[arr.length - 1];
        for(int i = arr.length - 2; i >= 0; i--){
            minfr[i] = Math.min(arr[i], minfr[i + 1]);
        }
        
        int res = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length - 1; i++){
            max = Math.max(max, arr[i]);
            if(max <= minfr[i + 1]){
                res++;
            }
        }
        return res + 1;
    }
} 
// 795. Number of Subarrays with Bounded Maximum
// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int s = -1;
        int e = -1;
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= left && nums[i] <= right){
                e = i;
            } else if(nums[i] > right){
                e = s = i;
            } 
            res += e - s;
        }
        return res;
    }
}