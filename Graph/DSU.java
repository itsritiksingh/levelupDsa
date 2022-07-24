// https://leetcode.com/problems/redundant-connection/
// cycle detection using dsu
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        
        parent = new int[edges.length + 1];
        rank = new int[edges.length + 1];
        
        for(int i = 1;i <= edges.length ;i++){
            parent[i] = i;
            rank[i] = 0;
        }
        
        for(int i = 0;i < edges.length;i++){
            int src = edges[i][0];
            int nbr = edges[i][1];
            
            int srcLead = find(src);
            int nbrLead = find(nbr);
            
            if(srcLead == nbrLead){
                return new int[]{src,nbr};
            }else {
                union(srcLead,nbrLead);
            }
        }
        return new int[2];
    }
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/sentence_similarity_official/ojquestion
// Sentence Similarity
public class Main {

  static HashMap<String, String> parent;
  static HashMap<String, Integer> rank;
  
  public static boolean areSentencesSimilarTwo(String[] Sentence1, String[] Sentence2, String[][] pairs) {
    parent = new HashMap<>();
    rank = new HashMap<>();
    
    for(int i = 0; i < pairs.length; i++){
        if(!parent.containsKey(pairs[i][0])){
            parent.put(pairs[i][0], pairs[i][0]);
            rank.put(pairs[i][0], 0);
        }
        
        if(!parent.containsKey(pairs[i][1])){
            parent.put(pairs[i][1], pairs[i][1]);
            rank.put(pairs[i][1], 0);
        }
    }
    
    for(int i = 0; i < pairs.length; i++){
        String p0Lead = find(pairs[i][0]);
        String p1Lead = find(pairs[i][1]);
        
        if(p0Lead.equals(p1Lead) == false){
            union(p0Lead, p1Lead);
        }
    }
    if(Sentence1.length != Sentence2.length){
        return false;
    }
    
    for(int i = 0; i < Sentence1.length; i++){
        String w1 = Sentence1[i];
        String w2 = Sentence2[i];
        
        if(w1.equals(w2)){
            continue;
        } else if(parent.containsKey(w1) == false  || parent.containsKey(w2) == false){
            return false;
        }
        
        String w1l = find(w1);
        String w2l = find(w2);
        if(w1l.equals(w2l) == false){
            return false;
        }
    }
    return true;
  }
  public static String find(String x){
      
      if(parent.get(x).equals(x)){
          return x;
      } else {
          String pox = parent.get(x);
          String setLead = find(pox);
          parent.put(x, setLead);
          return setLead;
      }
  }
  public static void union(String xl, String yl){
      if(rank.get(xl) < rank.get(yl)){
          parent.put(xl, yl);
      } else if(rank.get(yl) < rank.get(xl)){
          parent.put(yl, xl);
      } else {
          parent.put(xl, yl);
          rank.put(yl, rank.get(yl) + 1);
      }
  }
}

// https://leetcode.com/problems/satisfiability-of-equality-equations/
// 990. Satisfiability of Equality Equations
// alternate approach https://raw.githubusercontent.com/sumeet-malik/level2and3/main/June27/Practice/equations.txt
class Solution {
    HashMap<Character,Character> parent = new HashMap();
    HashMap<Character,Integer> rank = new HashMap();
    public boolean equationsPossible(String[] equations) {
        for(int i = 0; i < equations.length;i++){
            
            char f1 = equations[i].charAt(0);
            char f2 = equations[i].charAt(3);

            parent.putIfAbsent(f1,f1);
            parent.putIfAbsent(f2,f2);
            
            rank.putIfAbsent(f1,0);
            rank.putIfAbsent(f2,0);
        }
        
        for(int i = 0;i < equations.length;i++){
            if(equations[i].charAt(1) == '!') continue;
            
            char f1 = equations[i].charAt(0);
            char f2 = equations[i].charAt(3);
            
            char f1Lead = find(f1);
            char f2Lead = find(f2);
            
            if(f1Lead != f2Lead){
                union(f1Lead,f2Lead);
            }
        }
        
        for(int i = 0;i < equations.length;i++){
            if(equations[i].charAt(1) == '=') continue;
            
            char f1 = equations[i].charAt(0);
            char f2 = equations[i].charAt(3);
            
            char f1Lead = find(f1);
            char f2Lead = find(f2);
            
            if(f1Lead == f2Lead){
                return false;
            }
        }
        return true;
    }
    
    public char find(char f1){
        if(parent.get(f1) == f1) return f1;
        else {
            parent.put(f1,find(parent.get(f1)));
            return parent.get(f1);
        }
    }
    
    public void union(char f1,char f2){
        if(rank.get(f1) < rank.get(f2)){
            parent.put(f1,f2);
        }else if(rank.get(f1) > rank.get(f2)) {
            parent.put(f2,f1);
        }else {
            rank.put(f1,rank.get(f1) + 1);
            parent.put(f1,f2);
        }
    }
}
// https://leetcode.com/problems/evaluate-division/
// 399. Evaluate Division