// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// 153. Find Minimum in Rotated Sorted Array
class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(nums[mid] < nums[hi]){
                hi = mid;
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
// https://www.interviewbit.com/problems/allocate-books/
// Ques : You have to allocate books to B number of students so that maximum number of pages alloted to a student is minimum.
public class Solution {
    public int books(ArrayList<Integer> A, int B) {
        if(A.size() < B){
            return -1;
        }
        int lo = Integer.MIN_VALUE;
        int hi = 0;
        for(int val: A){
            lo = Math.max(val, lo);
            hi += val;
        }
        while(lo < hi){
            int allot = (lo + hi) / 2;
            int reqd = 1;
            int curr = 0;
            for(int val: A){
                if(curr + val <= allot){
                    curr = curr + val;
                } else {
                    curr = val;
                    reqd++;
                }
            }
            if(reqd > B){
                lo = allot + 1;
            }  else {
                hi = allot;
            }
        }
        return lo;
    }
}
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int lo = matrix[0][0];
        int hi = matrix[m - 1][n - 1];
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            int j = n - 1;
            for(int i = 0; i < m; i++){
                while(j >= 0 && matrix[i][j] > mid){
                    j--;
                }    
                count += (j + 1);
            }    
            if(count < k){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
// https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/ 
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m * n;
        
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            
            int count = 0;
            int j = n;
            for(int i = 1; i <= m; i++){
                while(j >= 1 && i * j > mid){
                    j--;
                }  
                count += j;
            }
            if(count < k){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        
        return lo;
    }
}
// https://leetcode.com/problems/find-k-th-smallest-pair-distance
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int lo = 0;
        for(int i = 0; i < nums.length - 1; i++){
            lo = Math.min(lo, nums[i + 1] - nums[i]);
        }
        
        int hi = nums[nums.length - 1] - nums[0];
        while(lo < hi){
            int mid = (lo + hi) / 2;
            
            int count = 0;
            
            int j = 0;
            for(int i = 0; i < nums.length; i++){
                while(j < nums.length && nums[j] - nums[i] <= mid){
                    j++;
                }
                
                count += (j - i - 1);
            }
            
            if(count < k){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        
        return lo;
    }
}
// https://leetcode.com/problems/k-th-smallest-prime-fraction/
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        double lo = 0.0;
        double hi = 1.0;
        
        while(lo < hi){
            double mid = (lo + hi) / 2;
            int p = 0;
            int q = 1;
            int count = 0;
            int j = 0;
            for(int i = 0; i < arr.length; i++){
                while(j < arr.length && arr[i] * 1.0 / arr[j] > mid){
                    j++;
                }
                if(j == arr.length){
                    break;
                }
                count += (arr.length - j);   
                if(p * arr[j] < q * arr[i]){
                    p = arr[i];
                    q = arr[j];
                }
            }
            if(count < k){
                lo = mid;
            } else if(count > k){
                hi = mid;
            } else {
                return new int[]{p, q};
            }
        }
        return null;
    }
}
// https://leetcode.com/problems/search-in-rotated-sorted-array/
// 33. Search in Rotated Sorted Array
class Solution {
    public int search(int[] nums, int target) {
        int i = search(nums, 0, nums.length - 1, target);
        return i;
    }
     int search(int arr[], int l, int h, int key)
    {
        if (l > h)
            return -1;
        int mid = (l + h) / 2;
        if (arr[mid] == key)
            return mid;
        if (arr[l] <= arr[mid]) {
            if (key >= arr[l] && key <= arr[mid])
                return search(arr, l, mid - 1, key);
            return search(arr, mid + 1, h, key);
        }
        if (key >= arr[mid] && key <= arr[h])
            return search(arr, mid + 1, h, key);
        return search(arr, l, mid - 1, key);
    }
}

// https://leetcode.com/problems/median-of-two-sorted-arrays/description/
// 4. Median of Two Sorted Arrays
public double findMedianSortedArrays(int[] x, int[] y) {
    if(x.length>y.length){
        return findMedianSortedArrays(y,x);
    }
    int i=0;
    int j=x.length;
    
    while(i<=j){
        int px=(i+j)/2;
        int py=(x.length+y.length+1)/2-px;
        
        int maxLeftX;
        int maxLeftY;
        int minRightX;
        int minRightY;
        
        if(px==0){
            maxLeftX=Integer.MIN_VALUE;
        }else{
            maxLeftX=x[px-1];
        }
        
        if(py==0){
            maxLeftY=Integer.MIN_VALUE;
        }else{
            maxLeftY=y[py-1];
        }
        
        if(px==x.length){
            minRightX=Integer.MAX_VALUE;
        }else{
            minRightX=x[px];
        }
        
        if(py==y.length){
            minRightY=Integer.MAX_VALUE;
        }else{
            minRightY=y[py];
        }
        
        if(maxLeftX <= minRightY && maxLeftY <= minRightX){
            if((x.length+y.length)%2==0){
                double ans=(1.0*Math.max(maxLeftX,maxLeftY)+Math.min(minRightX,minRightY))/2;
               
                return ans;
            }else{
                double ans=Math.max(maxLeftX,maxLeftY);
                return ans;
            }
        }else if(maxLeftX>minRightY){
            j=px-1;
        }else{
            i=px+1;
        }
    }
    return 0.0;
}

// 540. Single Element in a Sorted Array
// https://leetcode.com/problems/single-element-in-a-sorted-array/
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] != nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 2;
            }
        }
        return nums[left];
    }
}