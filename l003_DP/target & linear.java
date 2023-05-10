// https://leetcode.com/problems/minimum-path-sum/description/
// 64. Minimum Path Sum
// python 3 sol. https://leetcode.com/problems/minimum-path-sum/submissions/854882705/
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length, ER = n - 1, EC = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = grid[er][ec];
                    continue;
                }

                int min = (int) 1e9;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m) {
                        min = Math.min(min, dp[r][c]);
                    }
                }
                dp[er][ec] = min + grid[er][ec];
            }
        }

        return dp[ER][EC];
    }
}
// https://leetcode.com/problems/triangle/description/
// 120. Triangle
// python3 https://leetcode.com/problems/triangle/submissions/855531346/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] arr = new int[triangle.get(size - 1).size()][size];
        add(triangle,size - 1,arr);
        return arr[0][0];
    }
    public void add(List<List<Integer>> triangle,int idx,int[][] arr){
        if(idx< 0 ) return;
        if(idx == triangle.size() - 1){
            for(int i= 0;i< triangle.get(idx).size();i++){
                arr[idx][i] = triangle.get(idx).get(i);
            }
        }else {
            for(int i= 0;i< triangle.get(idx).size();i++){
                int temp = triangle.get(idx).get(i);
                arr[idx][i] = arr[idx + 1][i] > arr[idx + 1][i+1] ? 
                    arr[idx + 1][i+1] + temp : arr[idx + 1][i] + temp;
            }
        }
        
        add(triangle,idx-1,arr);
    }
}
// https://leetcode.com/problems/dungeon-game/submissions/855570798/
// 174. Dungeon Game
// code need to redefine + python3 missing

// https://leetcode.com/problems/maximal-square/description/
// 221. Maximal Square
// python3 missing
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
// https://leetcode.com/problems/perfect-squares/description/
// 279. Perfect Squares
// for n = 12 => 0 1 2 3 1 2 3 4 2 1 2 3 3 
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2;i <= n;i++){
            int min = Integer.MAX_VALUE;

            for(int j = 1; j * j <=i;j++){
                int rem = i - j * j;
                if(dp[rem] < min){
                    min = dp[rem];
                }
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }
}
// https://leetcode.com/problems/ones-and-zeroes/description/
// 474. Ones and Zeroes
// https://leetcode.com/problems/ones-and-zeroes/submissions/856483290/ for recursive solution
class Solution {
    int[][] dp;
    public int findMaxForm(String[] strs, int m, int n) {
        dp= new int[m+1][n+1];
        
        for(String s:strs){
            int[] count = count(s);
            for(int zero=m;zero>=count[0];zero--){ 
                for(int one=n; one>=count[1]; one--){
                    dp[zero][one] = Math.max(dp[zero-count[0]][one-count[1]] +1 , dp[zero][one]);
                                                //if we select                     //if we don't select it
                }
            }
        }
        return dp[m][n];
    }
    
    int[] count(String s){
        int[] count=new int[2];
        for(char c: s.toCharArray()){
            count[c-'0']++;
        }
        return count;
    }
}
// https://leetcode.com/problems/2-keys-keyboard/
// 650. 2 Keys Keyboard
class Solution {
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }
}
// https://leetcode.com/problems/minimum-falling-path-sum/
// 931. Minimum Falling Path Sum
class Solution {
    public int minFallingPathSum(int[][] matrix) {
       int dp[][] = new int[matrix.length][matrix.length];
       int dir[][] = new int[][]{{1,-1},{1,0},{1,1}};
       int max = (int)1e9;
       for(int i = 0;i < matrix.length; i++){
           max = Math.min(find(0,i,dir,matrix,dp),max);
       } 
       return max;
    }
    private int find(int i,int j,int [][]dir,int[][] matrix,int[][] dp){
        if(i >= matrix.length) return 0;
        if(dp[i][j] != 0) return dp[i][j];
        int max = (int)1e9;
        for(int[] d: dir){
            int c = j + d[1];
            if(c >= 0 && c < matrix.length)
                max = Math.min(find(i+1,c,dir,matrix,dp),max);
        }

        return dp[i][j] = max + matrix[i][j];
    }
}

// https://leetcode.com/problems/word-break/description/
// 139. Word Break
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }
    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}
// https://leetcode.com/problems/word-break-ii/description/
// 140. Word Break II

class Solution {
    List<String> ret;
    public List<String> wordBreak(String s, List<String> wordDict) {
        ret = new ArrayList();
        HashSet<String> set = new HashSet(wordDict);
        findSentence(s,"",set);
        return ret;
    }
    private void findSentence(String s,String ans, HashSet<String> set){
        if(s.length() == 0) {
            ret.add(ans.trim());
            return;
        }
        for(int i = 0;i < s.length();i++){
            String left = s.substring(0,i+1);
            if(set.contains(left)){
                String right = s.substring(i+1,s.length());
                findSentence(right,ans + left+ " ",set);
            }
        }

        return;
    }
}

// https://leetcode.com/problems/domino-and-tromino-tiling/description/
// 790. Domino and Tromino Tiling
class Solution {
    // 1,1,2,5,11,24
    private static final int MOD = 1000000007;
    public int numTilings(int N) {
        if (N == 1) return 1;
        if (N == 2) return 2;
        long[] dp = new long[N + 1];
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % MOD;
        }
        return (int) dp[N];
    }
}