import java.util.Arrays;

public class l001_leetcode {
    // 62
    public int uniquePaths(int n, int m) {
        int ER = n - 1, EC = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // 63
    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length, m = grid[0].length, ER = n - 1, EC = m - 1;
        if (grid[0][0] == 1 || grid[ER][EC] == 1)
            return 0;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // 396
    //As we rotate the number clockwise, the value is increased by sum - the length * preLastNum
    public int maxRotateFunction(int[] nums) {
        int sum = 0, n = nums.length, maxSum = 0, sumSF = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sumSF += i * nums[i];
        }

        maxSum = sumSF;
        for (int i = 0; i < n; i++) {
            sumSF = sumSF - sum + n * nums[i];
            maxSum = Math.max(maxSum, sumSF);
        }

        return maxSum;
    }

    // 64
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

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1/

    public static int goldmine_memo(int sr, int sc, int er, int ec, int[][] arr, int[][] dp, int[][] dir) {
        if (sc == ec) {
            return dp[sr][sc] = arr[sr][sc];
        }

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int maxCoin = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0], c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                maxCoin = Math.max(maxCoin, goldmine_memo(r, c, er, ec, arr, dp, dir) + arr[sr][sc]);
            }
        }

        return dp[sr][sc] = maxCoin;
    }

    public static int maxGold(int n, int m, int[][] arr) {
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int maxCoin = 0;
        for (int r = 0; r < arr.length; r++) {
            maxCoin = Math.max(maxCoin, goldmine_memo(r, 0, n - 1, m - 1, arr, dp, dir));
        }

        return maxCoin;
    }

    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/

}
// https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
// 1235. Maximum Profit in Job Scheduling
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        PriorityQueue<Job> pq = new PriorityQueue<>((e1, e2) -> e1.end - e2.end);
        TreeMap<Integer,Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for(int i = 0; i < startTime.length; i++){
            Job curr = new Job(startTime[i], endTime[i], profit[i]);
            pq.add(curr);
        }
        while(pq.size() != 0){
            Job curr = pq.poll();
            int start = curr.start;
            int end = curr.end;
            dp.put(end, Math.max(dp.floorEntry(start).getValue() + curr.profit, dp.floorEntry(end).getValue()));
        }
        return dp.lastEntry().getValue();
    }
}

class Job{
    int start;
    int end; 
    int profit;
    public Job(int start, int end, int profit){
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
}