// 11. Container With Most Water
// https://leetcode.com/problems/container-with-most-water/
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length -1;
        int max = -1;
        while(l < r){
            max = Math.max(max, (r - l) * Math.min(height[l], height[r]));    
            if(height[r] > height[l]){
                l++;
            }else {
                r--;
            }
        }
        return max;
    }
}
// 977. Squares of a Sorted Array
// https://leetcode.com/problems/squares-of-a-sorted-array/
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right =nums.length - 1 , k = nums.length - 1;
        int output[] = new int[nums.length];
        
        while(k>= 0){
            if(Math.abs(nums[left]) > Math.abs(nums[right]) ){
                output[k] = nums[left] * nums[left];
                k--;
                left++;
            }else{
                output[k] = nums[right] * nums[right];
                k--;
                right--;
            }
        }
        return output;
    }
}
// 556. Next Greater Element III
// https://leetcode.com/problems/next-greater-element-iii/
class Solution {
    public int nextGreaterElement(int n) {
        char[] arr = (n + "").toCharArray();
        
        int i = arr.length - 1;
        while(i > 0){
            if(arr[i - 1] >= arr[i]){
                i--;
            } else {
                break;
            }
        }
        if(i == 0){
            return -1;
        }
        int idx1 = i - 1;
        int j = arr.length - 1;
        while(j > idx1){
            if(arr[j] > arr[idx1]){
                break;
            }
            j--;
        }
        swap(arr,idx1,j);
        int left = i, right = arr.length - 1;
        while(left < right){
            swap(arr,left,right);
            left++;
            right--;
        }
        String res = new String(arr);
        long val = Long.parseLong(res);
        return (val > Integer.MAX_VALUE ? -1 : (int)val);
    }
    void swap(char[] arr,int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
// https://leetcode.com/problems/long-pressed-name/
// 925. Long Pressed Name
class Solution {
    public boolean isLongPressedName(String n, String t) {
        int i = 0;
        int j = 0;
        while(i < t.length()){
            if(j < n.length() && t.charAt(i) == n.charAt(j)){
                i++;
                j++;
            } else if(i > 0 && t.charAt(i) == t.charAt(i - 1)){
                i++;
            } else {
                return false;
            }
        }
        return j == n.length();
    }
}
// https://leetcode.com/problems/reverse-vowels-of-a-string/
// 345. Reverse Vowels of a String
class Solution {
    public String reverseVowels(String str) {
        int l = 0,r = str.length() - 1;
        StringBuilder s = new StringBuilder(str);
        while(l < r){
            if(isVowel(s.charAt(l)) && isVowel(s.charAt(r))){
                char lch = s.charAt(l);
                char rch = s.charAt(r);
                s.setCharAt(r,lch) ;
                s.setCharAt(l,rch) ;
                
                l++;
                r--;
            }else if(!isVowel(s.charAt(l))) l++;
             else if(!isVowel(s.charAt(r)))  r--; 
        }
        return s.toString();
    }
    public boolean isVowel(char ch){
        //alternately you can also use :check.indexOf(sb.charAt(li)) == -1
        // String check = "aeiouAEIOU";
        if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch=='U') return true;
        else return false;
    }
}
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
// 462. Minimum Moves to Equal Array Elements II
// let arr = [10,2,6,9,1,3,4] -> sortedarr = [1,2,3,4,6,9,10]
// median = 4
// we will deduct each element from median suppose for first and last element: 4 - 1 + 10 - 4 = 10 - 1
// this is what we have done below
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        
        int li = 0, ri = nums.length -1 , res = 0;
        while(li < ri){
            res += nums[ri] - nums[li];
            li++;
            ri--;
        }
        
        return res;
    }
}
// https://leetcode.com/problems/product-of-array-except-self/
// 238. Product of Array Except Self
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        for(int i = 0;i < nums.length; i++){
            if(i == 0){
                left[i] = 1;
            }else {
                left[i] = left[i - 1] * nums[i - 1];
            }
        }
        
        int right = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            right = right * nums[i + 1];
            left[i] = left[i] * right;
        }
        
        return left;
    }
}
// https://leetcode.com/problems/maximum-swap/
// 670. Maximum Swap
class Solution {
    public int maximumSwap(int num) {
        char[] arr = (num + "").toCharArray();
        
        int[] right = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--){
            if(i == arr.length - 1){
                right[i] = -1;
            } else if(i == arr.length - 2){
                right[i] = arr.length - 1;
            } else {
                if(arr[i + 1] > arr[right[i + 1]]){
                    right[i] = i + 1;
                } else {
                    right[i] = right[i + 1]; 
                }
            }
        }
        
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] < arr[right[i]]){
                char temp = arr[i];
                arr[i] = arr[right[i]];
                arr[right[i]] = temp;
                break;
            }
        }
        
        return Integer.parseInt(new String(arr));
    }
}
// https://leetcode.com/problems/maximize-distance-to-closest-person/
// 849. Maximize Distance to Closest Person
class Solution {
    public int maxDistToClosest(int[] seats) {
        int j = -1;
        
        int res = 0;
        for(int i = 0; i < seats.length; i++){
            if(seats[i] == 1){
                int ans = 0;
                
                if(j < 0){
                    // case1 : [0,0,0,0,1]
                    ans = i;
                } else {
                    //case 2: [1,0,0,0,1,0,1]
                    ans = (i - j) / 2;
                }
                res = Math.max(res, ans);
                j = i;
            }
        }
        // case3: [1,0,0,0]
        int ans = seats.length - 1 - j;
        res = Math.max(res, ans);
        
        return res;
    }
}
// https://leetcode.com/problems/boats-to-save-people/
// 881. Boats to Save People
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int l = 0;
        int r = people.length - 1;
        while(l < r){
            int val = people[l] + people[r];
            if(val <= limit){
                ans++;
                l++;
                r--;
            } else {
                ans++;
                r--;
            }
        }
        if(l == r){
            ans += 1;
        }
        return ans;
    }
}
// https://practice.geeksforgeeks.org/problems/key-pair5616/1
// Key Pair
class Solution {
    boolean hasArrayTwoCandidates(int arr[], int n, int x) {       
        Arrays.sort(arr);
        int l = 0;
        int r = arr.length - 1;
        while(l < r){
            if(arr[l] + arr[r] < x){
                l++;
            } else if(arr[l] + arr[r] > x){
                r--;
            } else {
                return true;
            }
        }
        return false;
    }
}
// https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
// Find Pair Given Difference
class Solution
{
    public boolean findPair(int arr[], int size, int n){
        Arrays.sort(arr);
        int j = 0;
        int i = 1;
        while(i < arr.length){
            if(arr[i] - arr[j] > n){
                j++;
                if(j == i){
                    i++;
                }
            } else if(arr[i] - arr[j] < n){
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
//best meeting point
// 1. A group of two or more people wants to meet and minimize the total travel distance.
// 2. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/best-meeting-point/ojquestion
    public static int minTotalDistance(int[][] grid) {
        ArrayList<Integer> xcord = new ArrayList<>();
        ArrayList<Integer> ycord = new ArrayList<>();
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1) {
                    xcord.add(r);
                }
            }
        }
        for(int c = 0; c < grid[0].length; c++) {
            for(int r = 0; r < grid.length; r++) {
                if(grid[r][c] == 1) {
                    ycord.add(c);
                }
            }
        }
        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);
        int dist = 0;
        for(int i = 0; i < xcord.size(); i++) {
            dist += Math.abs(xcord.get(i) - x) + Math.abs(ycord.get(i) - y);
        }
        return dist;
    }
