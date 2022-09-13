// https://leetcode.com/problems/max-consecutive-ones-iii/
// 1004. Max Consecutive Ones III
class Solution {
    public int longestOnes(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int res = 0;
        int countz = 0;
        while(i < nums.length){
            if(nums[i] == 0){
                countz++;
            }
            while(countz > k){
                if(nums[j] == 0){
                    countz--;
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
            i++;
        }
        return res;
    }
}
// https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1
class Solution
{
    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        Collections.sort(a);
        int ans = Integer.MAX_VALUE;
        for(int e = m - 1; e < a.size(); e++){
            int s = e - m + 1;
            int diff = a.get(e) - a.get(s);
            ans = Math.min(ans, diff);
        } 
        return ans;
    }
}