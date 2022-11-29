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

// 547. Number of Provinces
// https://leetcode.com/problems/number-of-provinces/submissions/749761926/


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
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parent = new HashMap<>();
        mult = new HashMap<>();
        for(List<String> eqn: equations){
            addToDSU(eqn.get(0));
            addToDSU(eqn.get(1));
        }
        int i = 0;
        for(List<String> eqn: equations){
            String l0 = find(eqn.get(0));
            String l1 = find(eqn.get(1));
        
            Double m0 = mult.get(eqn.get(0));
            Double m1 = mult.get(eqn.get(1));
            
            mult.put(l0, m1 * values[i] / m0);
            parent.put(l0, l1);
            
            i++;
        }
        double[] res = new double[queries.size()];
        i = 0;
        
        for(List<String> query: queries){
            if(parent.containsKey(query.get(0)) == false || parent.containsKey(query.get(1)) == false) {
                res[i] = -1;
                i++;
                continue;
            }
            
            String l0 = find(query.get(0));
            String l1 = find(query.get(1));
            
            if(l0.equals(l1) == false){
                res[i] = -1;
                i++;
                continue;
            } 
            
            Double m0 = mult.get(query.get(0));
            Double m1 = mult.get(query.get(1));
            res[i] = m0 / m1;
        
            i++;
        }
        return res;
    }

    HashMap<String, String> parent;
    HashMap<String, Double> mult;
    
    void addToDSU(String x){
        if(parent.containsKey(x) == false){
            parent.put(x, x);
            mult.put(x, 1.0);
        }
    }
    String find(String x){
        if(parent.get(x).equals(x)){
            return parent.get(x);
        } else {
            String cp = parent.get(x);
            String np = find(parent.get(x));
            
            mult.put(x, mult.get(cp) * mult.get(x));
            parent.put(x, np);
            
            return np;
        }
    }
}

// https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
// Job Sequencing Problem
class Solution
{
    int[] parent;
    int[] JobScheduling(Job arr[], int n)
    {
        Arrays.sort(arr, (a, b) -> {
            return b.profit - a.profit;
        });
        
        parent = new int[101];
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;
        }
        
        int count = 0;
        int profit = 0;
        
        for(Job j: arr){
            int empty = find(j.deadline);
            if(empty > 0){
                count++;
                profit += j.profit;
                parent[empty] = find(empty - 1);
            }
        }
        return new int[] {count, profit};
    }
    int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

}
// https://leetcode.com/problems/smallest-string-with-swaps/
// 1202. Smallest String With Swaps
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        parent = new int[s.length()];
        rank = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            parent[i] = i;
            rank[i] = 0;
        }
        for(List<Integer> pair: pairs){
            int i = pair.get(0);
            int j = pair.get(1);
            
            int il = find(i);
            int jl = find(j);
            if(il != jl){
                union(il, jl);
            }
        }
        PriorityQueue<Character>[] pqs = new PriorityQueue[s.length()];
        for(int i = 0; i < pqs.length; i++){
            pqs[i] = new PriorityQueue<>();
        }
        
        for(int i = 0; i < s.length(); i++){
            int il = find(i);
            char ch = s.charAt(i);
            pqs[il].add(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            int il = find(i);
            char ch = pqs[il].remove();
            sb.append(ch);
        }
        
        return sb.toString();
    }
    int[] parent;
    int[] rank;
    
    void union(int x, int y){
        if(rank[x] < rank[y]){
            parent[x] = y;
        } else if(rank[y] < rank[x]){
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }
    int find(int x){
        if(parent[x] == x){
            return x;
        } else {
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}

// https://leetcode.com/problems/cracking-the-safe/
// 753. Cracking the Safe
class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(0);
        }
        
        HashSet<String> vis = new HashSet<>();
        vis.add(sb.toString());
        
        int lim = (int)Math.pow(k, n);
        dfs(sb, vis, lim, n, k);
        
        return sb.toString();
    }
    
    public boolean dfs(StringBuilder sb, HashSet<String> vis, int lim, int n, int k){
        if(vis.size() == lim){
            return true;
        }
        
        String lnm1 = sb.substring(sb.length() - (n - 1));
        for(int i = 0; i < k; i++){
            String npwd = lnm1 + i;
            if(!vis.contains(npwd)){
                vis.add(npwd);
                sb.append(i);
                boolean flag = dfs(sb, vis, lim, n, k);
                if(flag){
                    return true;
                }
                
                vis.remove(npwd);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        return false;
    }
}
// https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
// 1579. Remove Max Number of Edges to Keep Graph Fully Traversable
class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] p = new int[n + 1];
        int[] r = new int[n + 1];
        for(int i = 1; i < p.length; i++){
            p[i] = i;
            r[i] = 0;
        }
        int res = 0;
        int e1 = 0;
        int e2 = 0;
        for(int[] edge: edges){
            if(edge[0] == 3){
                boolean flag = union(p, r, edge[1], edge[2]);
                if(flag){
                    e1++;
                    e2++;
                } else {
                    res++;
                }
            }
        }
        int[] p1 = p.clone();
        int[] r1 = r.clone();
        for(int[] edge: edges){
            if(edge[0] == 1){
                boolean flag = union(p1, r1, edge[1], edge[2]);
                if(flag){
                    e1++;
                } else {
                    res++;
                }
            }
        }
        int[] p2 = p.clone();
        int[] r2 = r.clone();
        for(int[] edge: edges){
            if(edge[0] == 2){
                boolean flag = union(p2, r2, edge[1], edge[2]);
                if(flag){
                    e2++;
                } else {
                    res++;
                }
            }
        }
        if(e1 == n - 1 && e2 == n - 1){
            return res;
        } else {
            return -1;
        }
    }
    int find(int[] p, int x){
        if(p[x] == x){
            return x;
        } else {
            p[x] = find(p, p[x]);
            return p[x];
        }
    }
    boolean union(int[] p, int[] r, int X, int Y){
        int x = find(p, X);
        int y = find(p, Y);
        if(x == y){
            return false;
        }   
        if(r[x] < r[y]){
            p[x] = y;
        } else if(r[y] < r[x]){
            p[y] = x;
        } else {
            p[y] = x;
            r[x]++;
        }
        return true;
    }
}

// 1627. Graph Connectivity With Threshold
// https://leetcode.com/problems/graph-connectivity-with-threshold/
class Solution {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        p = new int[n + 1];
        r = new int[n + 1];
        for(int i = 0; i < p.length; i++){
            p[i] = i;
            r[i] = 0;
        }
        // time complexity nloglogn
        for(int div = threshold + 1; div <= n; div++){
            for(int m = 1; div * m <= n; m++){
                union(div, div * m);
            }
        }
        ArrayList<Boolean> res = new ArrayList<>();
        
        for(int[] query: queries){
            int n1 = query[0];
            int n2 = query[1];
            boolean flag = (find(n1) == find(n2));
            res.add(flag);
        }
        
        return res;
    }
    
    int[] p;
    int[] r;
    
    int find(int x){
        if(p[x] == x){
            return x;
        } else {
            p[x] = find(p[x]);
            return p[x];
        }
    }
    
    void union(int X, int Y){
        int x = find(X);
        int y = find(Y);
        
        if(x == y){
            return;
        }
        
        if(r[x] < r[y]){
            p[x] = y;
        } else if(r[y] < r[x]){
            p[y] = x;
        } else {
            p[y] = x;
            r[x]++;
        }
    }
}

// 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
// https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
class Solution {
    static class UnionFind {
        int[] p;
        int[] r;
        int comps = 0;
        
        UnionFind(int n){
            p = new int[n];
            r = new int[n];
            comps = n;
            for(int i = 0; i < p.length; i++){
                p[i] = i;
                r[i] = 0;
            }
        }
        
        int find(int x){
            if(p[x] == x){
                return x;
            } else {
                p[x] = find(p[x]);
                return p[x];
            }
        }
        
        boolean union(int X, int Y){
            int x = find(X);
            int y = find(Y);
            if(x == y){
                return false;
            }
            
            if(r[x] < r[y]){
                p[x] = y;
            } else if(r[y] < r[x]){
                p[y] = x;
            } else {
                p[y] = x;
                r[x]++;
            }
            comps--;
            
            return true;
        }
        
        boolean isConnected(){
            return comps == 1;
        }
    }
    
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int wt;
        
        Edge(int u, int v, int wt){
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
        
        public int compareTo(Edge o){
            return this.wt - o.wt;
        }
    }
    
    public int buildMST(int n, int[][] edges, int[] edgeToSkip, int[] edgeToPick){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for(int[] edge: edges){
            if(edge == edgeToSkip){
                continue;
            } else if(edge == edgeToPick){
                continue;
            }
            
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            pq.add(new Edge(u, v, wt));
        }
        
        UnionFind uf = new UnionFind(n);
        int cost = 0;
        
        if(edgeToPick != null){
            uf.union(edgeToPick[0], edgeToPick[1]);
            cost += edgeToPick[2];
        }
        
        while(pq.size() > 0){
            Edge rem = pq.remove();
            if(uf.union(rem.u, rem.v) == true){
                cost += rem.wt;
            }
        }
        
        if(uf.isConnected() == true){
            return cost;
        } else {
            return Integer.MAX_VALUE;
        }
    }
    
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int mstCost = buildMST(n, edges, null, null);
        
        ArrayList<Integer> critical = new ArrayList<>();
        ArrayList<Integer> pcritical = new ArrayList<>();
        
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            
            int mstCostWithoutEdge = buildMST(n, edges, edge, null);
            if(mstCostWithoutEdge > mstCost){
                critical.add(i);
            } else {
                int mstCostWithEdge = buildMST(n, edges, null, edge);
                if(mstCostWithEdge > mstCost){
                    // redundant
                } else {
                    pcritical.add(i);
                }
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        res.add(critical);
        res.add(pcritical);
        return res;
    }
}