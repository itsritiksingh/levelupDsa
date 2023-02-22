// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
// In one move, you can increment n - 1 elements of the array by 1.
// 453. Minimum Moves to Equal Array Elements
class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int num: nums){
            min = Math.min(min, num);
        }
        int res = 0;
        for(int num: nums){
            res += num - min;
        }
        return res;
    }
}
// https://practice.geeksforgeeks.org/problems/digit-multiplier3000/1
// Given a positive integer N, find the smallest number S such that the product of all the digits of S is equal to the number N. If there's no such number, Print "-1".
// for N = 100 ans = 455
class Solution {
    static String getSmallest(Long N) {
        StringBuilder sb = new StringBuilder();
        if(N < 10){
            return N + "";
        }
        for(int div = 9; div > 1; div--){
            while(N % div == 0){
                N = N / div;
                sb.append(div);
            }
        }
        if(N >= 10){
            return "-1";
        }
        return sb.reverse().toString();
    }
};

// https://leetcode.com/problems/permutation-sequence/description/
// 60. Permutation Sequence
class Solution {
    public String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> numbers = new ArrayList();
        for(int i = 1;i < n;i++){
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);
        StringBuilder ans = new StringBuilder();
        k = k- 1;
        while(true){
            ans.append(numbers.get(k/ fact));
            //to remove it takes O(n) total TC would be O(n*n)
            numbers.remove(k/fact);
            if(numbers.size() == 0){
                break;
            }

            k = k % fact;
            fact = fact / numbers.size();
        }

        return ans.toString();
    }
}