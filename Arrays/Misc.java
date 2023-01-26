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

// https://leetcode.com/problems/majority-element-ii/description/
// 229. Majority Element II
