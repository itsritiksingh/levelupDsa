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

// https://leetcode.com/problems/subarrays-with-k-different-integers/description/
// 992. Subarrays with K Different Integers

class Solution {
    public int subArray(List<Integer> nums, int k) {
        int n = nums.size();
        Map<Integer, Integer> map = new HashMap<>(); // create a map to store the count of each number in the current subarray
        int count = 0, start = 0, end = 0; // initialize variables for count of valid subarrays, start and end index of subarray
        while (end < n) { // loop through the array from the start to end index
            map.put(nums.get(end), map.getOrDefault(nums.get(end), 0) + 1); // add the current element to the map and increment its count
            while (map.size() > k) { // if the number of distinct elements in the map is greater than k, move the start index and remove elements from the map
                map.put(nums.get(start), map.get(nums.get(start)) - 1); // decrement the count of the element at the start index
                if (map.get(nums.get(start)) == 0) { // if the count of the element at the start index becomes 0, remove it from the map
                    map.remove(nums.get(start));
                }
                start++; // increment the start index to move the window
            }
            count += end - start + 1; // add the count of valid subarrays for the current subarray to the total count
            end++; // increment the end index to move the window
        }
        return count; // return the total count of valid subarrays
    }
    
    public int subarraysWithKDistinct(int[] nums, int k) {
        List<Integer> list = new ArrayList<>(); // convert the input array to a list
        for (int num : nums) {
            list.add(num);
        }
        // return the count of subarrays with exactly k distinct elements minus the count of subarrays with less than k distinct elements
        return subArray(list, k) - subArray(list, k - 1);
    }
}

// 1156. Swap For Longest Repeated Character Substring
// https://leetcode.com/problems/swap-for-longest-repeated-character-substring/description/
class Solution {
    public int maxRepOpt1(String text) {
        int firstPointer = 0;
        int secondPointer = 0;
        int n = text.length();
        int maxVal = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<text.length();i++){
            char letter = text.charAt(i);
            map.put(letter, map.getOrDefault(letter, 0) + 1);    
        }
        
        while(firstPointer < n){
            char letter = text.charAt(secondPointer);
            int lastUnique = firstPointer;
            int countSoFar = 0;
            while(secondPointer < n && text.charAt(secondPointer) == letter){
                secondPointer++;
                countSoFar++;
            }
            
            lastUnique = secondPointer;
            if(map.get(letter) - countSoFar > 0){
                secondPointer++;
            }
            while(secondPointer < n && text.charAt(secondPointer) == letter){
                secondPointer++;
            }

            int res = Math.min(secondPointer-firstPointer, map.get(letter));
            maxVal = Math.max(res, maxVal);
            firstPointer = secondPointer = lastUnique;
        }
        return maxVal;
    }
}

// 1044. Longest Duplicate Substring
// https://leetcode.com/problems/longest-duplicate-substring/description/
// https://youtu.be/FQ8hcOOzQMU
class Solution {
    public String longestDupSubstring(String S) {
        String ans = "";
        
        int left = 1;
        int right = S.length()-1;
        
        while(left <= right){
            int m = left + (right - left)/2;
            
            String dup = getDup(m,S);
            
            if(dup != null){
                ans = dup;
                left = m+1;
            }else{
                right = m-1;
            }
        }
        
        return ans;
    }
    
    private String getDup(int size, String s){
        long H = hash(s.substring(0,size));
		
        HashSet<Long> set = new HashSet();
        set.add(H);

        long pow = 1;
        for(int i=1;i<size;i++)
            pow = (pow * 31);
        
		int n = s.length();
        for(int i=size;i < n;i++){
            H = nextHash(pow, H, s.charAt(i-size), s.charAt(i));
            if(!set.add(H)){
                //assuming that their are no collisions
                return s.substring(i-size+1, i+1);
            }
        }
        
        return null;
    }
    
    private long hash(String s){
        long h = 0;
        long a = 1;
        
		int n = s.length();
        for(int k=n;k>=1;k--){
            char ch = s.charAt(k-1);
            h += (ch - 'a' + 1) * a;
            a = (a*31);
        }
        
        return h;
    }
    
    private long nextHash(long pow,long hash,char left,char right){
        return (hash - (left - 'a' + 1) * pow) * 31 + (right - 'a' + 1);
    }
}