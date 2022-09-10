// 41. First Missing Positive
// https://leetcode.com/problems/first-missing-positive/
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0 || nums[i] > n){
                nums[i] = n + 1;
            }
        }
        for(int i = 0; i < nums.length; i++){
            int val = Math.abs(nums[i]);
            if(val <= n){
                int idx = val - 1;
                if(nums[idx] > 0){
                    nums[idx] = -1 * nums[idx];
                }
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return n + 1;
    }
}

// 1010. Pairs of Songs With Total Durations Divisible by 60
// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] fmap = new int[60];
        int res = 0;
        for(int t: time){
            int val = t % 60;
            if(val == 0){
                res += fmap[0];
            } else {
                res += fmap[60 - val];
            }
            fmap[val]++;
        }
        return res;
    }
}