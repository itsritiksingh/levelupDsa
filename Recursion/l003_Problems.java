import java.util.*;

public class l003_Problems {
    // https://leetcode.com/problems/integer-to-roman/description/
    // 12 Integer to roman

    // 17
    private int letterCombinations(String digits, int idx, String[] codes, List<String> ans, String psf) {
        if (idx == digits.length()) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        int val = digits.charAt(idx) - '0';
        String code = codes[val];
        for (int i = 0; i < code.length(); i++) {
            count += letterCombinations(digits, idx + 1, codes, ans, psf + code.charAt(i));
        }

        return count;
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();

        String[] codes = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> ans = new ArrayList<>();
        letterCombinations(digits, 0, codes, ans, "");

        return ans;
    }

    // 518
    // https://leetcode.com/problems/coin-change-2/
    private int change(int tar, int[] coins, int idx) {
        if (tar == 0)
            return 1;
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0)
                count += change(tar - coins[i], coins, i);
        }
        return count;
    }

    public int change(int amount, int[] coins) {
        return change(amount, coins, 0);
    }

    // 322
    private int coinChange_(int[] coins, int tar) {
        if (tar == 0)
            return 0;

        int minCoin = (int) 1e9;
        for (int coin : coins) {
            if (tar - coin >= 0)
                minCoin = Math.min(minCoin, coinChange_(coins, tar - coin) + 1);
        }

        return minCoin;
    }

    public int coinChange(int[] coins, int tar) {
        int ans = coinChange_(coins, tar);
        return ans != (int) 1e9 ? ans : -1;
    }

    // 46
    public int permute(int[] nums, int ElementUsed, List<List<Integer>> ans, List<Integer> smallAns) {
        if (ElementUsed == nums.length) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > -11) {
                int val = nums[i];
                nums[i] = -11; // used
                smallAns.add(val);

                count += permute(nums, ElementUsed + 1, ans, smallAns);

                nums[i] = val; // unused
                smallAns.remove(smallAns.size() - 1);
            }
        }
        return count;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        permute(nums, 0, ans, smallAns);
        return ans;
    }

    // 39
    private int combinationSum(int[] arr, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                smallAns.add(arr[i]);
                count += combinationSum(arr, tar - arr[i], i, ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
            }
        }
        return count;
    }

    public List<List<Integer>> combinationSum(int[] arr, int tar) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum(arr, tar, 0, ans, smallAns);
        return ans;
    }

    // 40
    private int combinationSum2(int[] arr, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return 1;
        }

        int count = 0, prev = -1;
        for (int i = idx; i < arr.length; i++) {
            if (prev != arr[i] && tar - arr[i] >= 0) {
                smallAns.add(arr[i]);
                count += combinationSum2(arr, tar - arr[i], i + 1, ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
                prev = arr[i];
            }
        }
        return count;
    }

    public List<List<Integer>> combinationSum2(int[] arr, int tar) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum2(arr, tar, 0, ans, smallAns);
        return ans;
    }

}

// 1458. Max Dot Product of Two Subsequences
// https://leetcode.com/problems/max-dot-product-of-two-subsequences/

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