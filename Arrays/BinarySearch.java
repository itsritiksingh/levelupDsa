// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// 153. Find Minimum in Rotated Sorted Array
class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(nums[mid] < nums[hi]){
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo];
    }
}
// https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
// 1283. Find the Smallest Divisor Given a Threshold
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1;
        int hi = Integer.MIN_VALUE;
        for(int i : nums) if(i > hi) hi = i;
        while(lo < hi){
            int mid = (lo + hi) / 2;
            int sum = 0;
            for(int val: nums){
                sum += (val + mid - 1) / mid;
            }
            if(sum > threshold){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
// https://leetcode.com/problems/split-array-largest-sum/
// 410. Split Array Largest Sum
class Solution {
    public int splitArray(int[] nums, int m) {
        int highest_sum_allowed = 0;
        int lowest_sum_allowed = Integer.MIN_VALUE;
        
        for(int num: nums){
            highest_sum_allowed += num;
            lowest_sum_allowed = Math.max(lowest_sum_allowed, num);
        }
        
        while(lowest_sum_allowed < highest_sum_allowed){
            int limit = (lowest_sum_allowed + highest_sum_allowed) / 2;
            
            int countParts = 1;
            int currPartSum = 0;
            for(int num: nums){
                if(currPartSum + num <= limit){
                    currPartSum += num; 
                } else {
                    currPartSum = num;
                    countParts++;
                }
            }
            
            if(countParts > m){
                lowest_sum_allowed = limit + 1;
            } else {
                highest_sum_allowed = limit;
            }
        }
        
        return lowest_sum_allowed;
    }
}
// https://leetcode.com/problems/koko-eating-bananas/submissions/
// 875. Koko Eating Bananas
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int las = 1;
        int has = 0;
        for(int pile: piles){
            has = Math.max(pile, has);
        }
        while(las < has){
            int as = (las + has) / 2;
            int totalTime = 0;
            for(int pile: piles){
                int time = (pile + as - 1) / as;
                totalTime += time;
            }
            if(totalTime > h){
                las = as + 1;
            } else {
                has = as;
            }
        }
        return las;
    }
}
// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
 class Solution{
    static long minTime(int[] arr,int n,int k){
        long lta = Long.MIN_VALUE;
        long hta = 0;
        for(int val: arr){
            hta += val;
            lta = Math.max(val, lta);
        }
        while(lta < hta){
            long ta = (lta + hta) / 2;
            int paintersReqd = 1;
            int currPainterWork = 0;
            for(int val: arr){
                if(currPainterWork + val <= ta){
                    currPainterWork += val;
                } else {
                    paintersReqd++;
                    currPainterWork = val;
                }
            }
            if(paintersReqd > k){
                lta = ta + 1;
            } else {
                hta = ta;
            }
        }
        return lta;
    }
}
// https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/
// 1760. Minimum Limit of Balls in a Bag
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int lams = 1;
        int hams = 0;
        for(int num: nums){
            hams = Math.max(num, hams);
        }
        while(lams < hams){
            int ams = (lams + hams) / 2;
            int opsReqd = 0;
            for(int ball: nums){
                if(ball > ams){
                    int spare = ball - ams;
                    int ops = (spare + ams - 1) / ams;
                    opsReqd += ops;
                }
            }
            if(opsReqd > maxOperations){
                lams = ams + 1;
            } else {
                hams = ams;
            }
        }
        return lams;
    }
}