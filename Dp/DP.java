// minCostClimbingStairs 746

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if(n == 0) return 0;
        int[] dp = new int[n];
//         for(int idx = 1;idx < n;idx++){
//             cost[idx] = Math.min(cost[idx - 1], idx - 2 < 0 ? 0 : cost[idx - 2]) + cost[idx];
//         }
        
//         return Math.min(cost[n -1],cost[n-2]);
            climb_memo(cost,n- 1,dp);
            return Math.min(dp[n-1],dp[n-2]);
    }
    private int climb_memo(int[] cost, int idx,int dp[]) {
        if(idx <0 ) return 0;
        
        if(dp[idx] !=0) return dp[idx];
        int a = climb_memo(cost,idx - 1,dp)+ cost[idx];
        int b = climb_memo(cost,idx - 2,dp)+ cost[idx];

        return dp[idx] = Math.min(a,b);
    }
    
    //https://leetcode.com/problems/house-robber/ 198
        public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        if(n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //Tab
        // nums[2] += nums[0];
        // for(int i = 3; i < n; i++) {
        //     nums[i] += Math.max(nums[i - 3], nums[i -2]);
        // }
        // return Math.max(nums[n - 2], nums[n - 1]);
        //Memo
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        rob_memo(nums,n - 1,dp);
        rob_memo(nums,n - 2,dp);
        return Math.max(dp[n-1],dp[n-2]);
    }
    public int rob_memo(int[] nums,int idx,int[] dp) {
        if(idx < 0) return 0;
        
        if(dp[idx] != -1 )return dp[idx];
        
        int a = rob_memo(nums,idx -2,dp) + nums[idx];
        int b = rob_memo(nums,idx - 3,dp) + nums[idx];
        // System.out.println(idx + " "+ a + " "+b);
        
        return dp[idx] = Math.max(a,b);
    }
    
    354 https://leetcode.com/problems/russian-doll-envelopes/
       //using memo n^2 
//     public int maxEnvelopes(int[][] envelopes) {
//        int maxLen = 0;
//         int dp[] = new int[envelopes.length];
//         Arrays.sort(envelopes, (a, b) -> {
//             return a[0] - b[0];
//         });
//         for(int i = 0;i < envelopes.length;i++){
//             maxLen = Math.max(maxLen,count(envelopes,i,dp));
//         }
//         return maxLen;
//     }
//     public int count(int [][] envelopes,int idx,int dp[]){
//         if(dp[idx] != 0) return dp[idx];
//         int maxLen = 1;
//         for(int j = idx - 1; j >= 0; j--){
//             if(envelopes[j][0] < envelopes[idx][0] && envelopes[j][1] < envelopes[idx][1]){
//              int recAns = count(envelopes,j,dp);
//             maxLen = Math.max(maxLen,recAns + 1);
//             }
//         }
        
//         return dp[idx] =  maxLen;
//     }
  public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a,b) -> a[0] != b[0] ? a[0]-b[0] : b[1] - a[1]);  // custom sorting, first sort the array by width if width not same then sort by height
        
        int[] tails = new int[envelopes.length];  // lis
        int size = 0;
        
        for(int[] enve : envelopes) {
            int i = 0, j = size;
            
            while(i != j) {
                int mid = i + ((j - i) >> 1);  // same as int mid = i + ((j - i) /2;
                
                if(tails[mid] < enve[1])
                    i = mid+1;
                else
                    j = mid;
            }
            tails[i] = enve[1];
            if(i == size)
                size++;
        }
        
        return size;
    }

