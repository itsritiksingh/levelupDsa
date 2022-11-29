// https://leetcode.com/problems/max-consecutive-ones-iii/
// 1004. Max Consecutive Ones III
class Solution {
    public int longestOnes(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int res = 0;
        int countz = 0;
        while(i < nums.length){
            if(nums[i] == 0){
                countz++;
            }
            while(countz > k){
                if(nums[j] == 0){
                    countz--;
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
            i++;
        }
        return res;
    }
}
// https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1
class Solution
{
    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        Collections.sort(a);
        int ans = Integer.MAX_VALUE;
        for(int e = m - 1; e < a.size(); e++){
            int s = e - m + 1;
            int diff = a.get(e) - a.get(s);
            ans = Math.min(ans, diff);
        } 
        return ans;
    }
}
// https://leetcode.com/problems/minimum-window-substring/description/
// 76 minimum window substring
public String minWindow(String s, String t) {
    int m=s.length(),n=t.length();
    String ans="";
    if(n>m || n==0 || m==0){
        return "";
    }
    
    //for frequency of chars in string t
    HashMap<Character,Integer> hm1=new HashMap<>();
    for(int i=0;i<n;i++){
        hm1.put(t.charAt(i),hm1.getOrDefault(t.charAt(i),0)+1);
    }
    
    //for freq of chars in string s
    HashMap<Character,Integer> hm2=new HashMap<>();
    int start=0,end=0;
    int matchCount=0;
    int minLen=Integer.MAX_VALUE,minStart=0;
    
    while(end<m){
        
        //acquire characters
        char ch=s.charAt(end);
        if(hm1.containsKey(ch)){
            if(hm2.containsKey(ch)){
                if(hm2.get(ch)<hm1.get(ch)){
                    matchCount++;
                }
                hm2.put(ch,hm2.get(ch)+1);
            }
            else{
                matchCount++;
                hm2.put(ch,1);
            }
        }
        end++;
        
        //release chracters till matchCount=n and update length after each char
        while(matchCount==n){
            if((end-start)<minLen){
                minLen=end-start;
                minStart=start;
            }
            char chS=s.charAt(start);
            if(hm1.containsKey(chS)){
                if(hm2.get(chS)>hm1.get(chS)){
                    hm2.put(chS,hm2.get(chS)-1);
                }
                else{
                    hm2.put(chS,hm2.get(chS)-1);
                    matchCount--;
                }
            }
            start++;
        }   
    }
    if(minLen==Integer.MAX_VALUE){
        return "";
    }
    return s.substring(minStart,minStart+minLen);
}

//220 https://leetcode.com/problems/contains-duplicate-iii/
public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    TreeSet<Long> tset = new TreeSet();
    for(int i=0;i < nums.length;i++){
        Long justSmaller = tset.floor((long)nums[i]);
        Long justLarger = tset.ceiling((long)nums[i]);
        
        if(justSmaller != null && nums[i] - justSmaller <=t){
            return true;
        }
        
        if(justLarger != null&& justLarger - nums[i]<=t) return true;
        
        tset.add((long)nums[i]);
        
        if(tset.size()>k){
            tset.remove((long)nums[i-k]);
        }
    }
return false;
}