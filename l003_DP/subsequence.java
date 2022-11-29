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