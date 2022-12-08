// https://leetcode.com/problems/sort-characters-by-frequency/
// 451. Sort Characters By Frequency
class Solution {
    // class Pair
    public String frequencySort(String s) {
        char[] charArr = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap();
        
        for(char ch : charArr) map.put(ch, map.getOrDefault(ch,0) + 1);
        PriorityQueue<Pair<Character,Integer>> pq = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        
        for(char ch : map.keySet()) pq.add(new Pair(ch,map.get(ch)));
        
        StringBuilder ans = new StringBuilder();
        
        while(pq.size() > 0){
            Pair temp = pq.remove();
            for(int i = 0;i < (int)temp.getValue();i++){
                ans.append(temp.getKey());
            }
        }
        
        return ans.toString();
        
    }
}