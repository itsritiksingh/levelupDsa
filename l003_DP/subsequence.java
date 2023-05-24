// https://leetcode.com/problems/arithmetic-slices-ii-subsequence/description/
// 446. Arithmetic Slices II - Subsequence
class Solution {
    public int numberOfArithmeticSlices(int[] arr) {
        int ans = 0;
        HashMap<Integer,Integer> maps[] = new HashMap[arr.length];
        for(int i = 0; i < arr.length ;i++){
            maps[i] = new HashMap();
        }

        for(int i = 1; i < maps.length; i++){
            for(int j = 0; j < i;j++){
                long cd = (long)arr[i] - (long)arr[j];
                if(cd <= Integer.MIN_VALUE || cd >= Integer.MAX_VALUE)
                    continue;
                
                int apsEndingAtJ = maps[j].getOrDefault((int)cd,0);
                int apsEndingAtI = maps[i].getOrDefault((int)cd,0);

                ans += apsEndingAtJ;
                maps[i].put((int)cd,apsEndingAtI+apsEndingAtJ+1);
            }
        }

        return ans;
    }
}

// 1312. Minimum Insertion Steps to Make a String Palindrome
// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/

class Solution {
    public int minInsertions(String s) {
        int [][] dp = new int[s.length()][s.length()];
        return s.length() - longestPalindromicSubsequence(0,s.length()-1,dp,s);
    }

    private int longestPalindromicSubsequence(int i,int j,int dp[][],String s){
        if(i >= j) return i == j? 1 : 0; 

        if(dp[i][j] != 0 ) return dp[i][j];

        int a = longestPalindromicSubsequence(i+1,j-1,dp,s);
        int b = longestPalindromicSubsequence(i+1,j,dp,s);
        int c = longestPalindromicSubsequence(i,j-1,dp,s);

        if(s.charAt(i) == s.charAt(j)){
            return dp[i][j] = a + 2;
        } else {
            return dp[i][j] = Math.max(b,c);
        }
    }
}

// https://leetcode.com/problems/max-dot-product-of-two-subsequences/
// 1458. Max Dot Product of Two Subsequences
class Solution {
    int maxAns;
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int dp[][] = new int[nums1.length+1][nums2.length+1];
        maxAns = 0;
        return longestSubsequence(nums1.length,nums2.length,nums1,nums2,dp);
    }
    
    private int longestSubsequence(int n,int m,int[] nums1,int[] nums2,int[][] dp){
        if(n == 0 || m == 0) return -1001;
        if(dp[n][m] != 0) return dp[n][m];
        
        int a = longestSubsequence(n-1,m-1,nums1,nums2,dp);
        int b = longestSubsequence(n-1,m,nums1,nums2,dp);
        int c = longestSubsequence(n,m-1,nums1,nums2,dp);

        dp[n][m] = nums1[n-1] * nums2[m-1];
        dp[n][m] += Math.max(a,0);
        dp[n][m] = Math.max(b,dp[n][m]);
        dp[n][m] = Math.max(c,dp[n][m]);

        return dp[n][m];
    }
}