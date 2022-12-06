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