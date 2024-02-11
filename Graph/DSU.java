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

// https://leetcode.com/problems/number-of-provinces/submissions/959805570/

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


// https://leetcode.com/problems/largest-component-size-by-common-factor/description/
// Largest Component Size by Common Factor
class DSU {
    ArrayList<Integer> parent = new ArrayList();
    public DSU (int n) {
        for (int i = 0; i < n; i++)
            parent.add(i);
    }
    int findParent(int n) {
        if(parent.get(n) == n)
            return n;
        parent.set(n, findParent(parent.get(n)));
        return parent.get(n);
    }
    void union(int u, int v) {
        int uParent = findParent(u);
        int vParent = findParent(v);
        
        if(uParent != vParent)
            parent.set(uParent, parent.get(vParent));
    }
}
class Solution {
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        int x = nums[0];
        for (int num : nums)
            x = Math.max(x, num);
        DSU dsu = new DSU(x + 1);
        
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    dsu.union(num, i);
                    dsu.union(num, num/i);
                }
            }
        }
        Map<Integer, Integer> map = new HashMap();
        int ans = 1;
        for (int num : nums) {
            int par = dsu.findParent(num);
            map.put(par, map.getOrDefault(par, 0) + 1);
            ans = Math.max(ans, map.get(par));
        }
        return ans;
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

// 765. Couples Holding Hands
// https://leetcode.com/problems/couples-holding-hands/
class Solution {
    public int minSwapsCouples(int[] row) {
        parent = new int[row.length];
        rank = new int[row.length];
            
        for(int i = 0;i < row.length; i+=2){
            parent[i] = parent[i + 1] = i;
            rank[i] = rank[i + 1] = 1;
        }
        
        int count = 0;
        for(int i = 0;i < row.length - 1;i+=2){
            int il = find(row[i]);
            int jl = find(row[i + 1]);
            
            if(il != jl){
                union(il,jl);
                count++;
            }
        }
        
        return count;
    }
    int []parent;
    int[] rank;
    public int find(int i){
        if(parent[i] == i) return i;
        else {
            parent[i] = find(parent[i]);
            return parent[i];
        }
    }
    public void union(int i,int j){
        if(rank[i] < rank[j]){
            parent[j] = i;
        }else if(rank[i] > rank[j]){
            parent[i] = j;
        }else{
            rank[i]++;
            parent[i] = j;
        }
    }
}

// 803. Bricks Falling When Hit

class Solution {
    int m;
    int n;
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        m = grid.length;
        n = grid[0].length;
        
        parent = new int[m * n + 1];
        rank = new int[m * n + 1];
        size = new int[m * n + 1];
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;
            size[i] = 1;
            rank[i] = 0;
        }
        
        for(int[] hit: hits){
            int x = hit[0];
            int y = hit[1];
            
            if(grid[x][y] == 1){    
                grid[x][y] = 2;
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    handleUnionOfAllNbrs(grid, i, j);
                }
            }
        }
        
        int[] res = new int[hits.length];
        
        for(int i = hits.length - 1; i >= 0; i--){
            int x = hits[i][0];
            int y = hits[i][1];
            
            if(grid[x][y] == 2){
                int bricksIn0 = size[find(0)];
                grid[x][y] = 1;
                handleUnionOfAllNbrs(grid, x, y);
                int newBricksIn0 = size[find(0)];
                
                if(newBricksIn0 > bricksIn0){
                  res[i] =  newBricksIn0 - bricksIn0 - 1; 
                } 
            }
        }
        
        return res;
    }
    
    int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    void handleUnionOfAllNbrs(int[][] grid, int i, int j){
        int bno = i * n + j + 1;
        
        for(int[] dir: dirs){
            int ni = i + dir[0];
            int nj = j + dir[1];
            
            if(ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 1){
                int nbno = ni * n + nj + 1;
                union(bno, nbno);
            }
        }
        
        if(i == 0){
            union(0, bno);
        }
    }
       
    int[] parent;
    int[] rank;
    int[] size;
    void union(int X, int Y){
        int x = find(X);
        int y = find(Y);
        if(x == y){
            return;
        }
        
        if(rank[x] < rank[y]){
            parent[x] = y;
            size[y] += size[x];
        } else if(rank[y] < rank[x]){
            parent[y] = x;
            size[x] += size[y];
        } else {
            parent[y] = x;
            size[x] += size[y];
            rank[x]++;
        }
    }
    
    int find(int x){
        if(parent[x] == x){
            return parent[x];
        } else {
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}

// 721. Accounts Merge
// https://leetcode.com/problems/accounts-merge/description/
class Solution {
    class UnionFind {
        int[] parent;
        int[] weight;
        
        public UnionFind(int num) {
            parent = new int[num];
            weight = new int[num];
            
            for(int i =  0; i < num; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }
        
        public void union(int a, int  b) {
            int rootA = root(a);
            int rootB = root(b);
            
            if (rootA == rootB) {
                return;
            }
            
            if (weight[rootA] > weight[rootB]) {
                parent[rootB] = rootA;
                weight[rootA] += weight[rootB];
            } else {
                parent[rootA] = rootB;
                weight[rootB] += weight[rootA];
            }
        }
        
        public int root(int a) {
            if (parent[a] == a) {
                return a;
            }
            
            parent[a] = root(parent[a]);
            return parent[a];
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int size = accounts.size();

        UnionFind uf = new UnionFind(size);

        // prepare a hash with unique email address as key and index in accouts as value
        HashMap<String, Integer> emailToId = new  HashMap<>();
        for(int i = 0; i < size; i++) {
            List<String> details = accounts.get(i);
            for(int j = 1; j < details.size(); j++) {
                String email = details.get(j);
                
				// if we have already seen this email before, merge the account  "i" with previous account
				// else add it to hash
                if (emailToId.containsKey(email)) {
                    uf.union(i, emailToId.get(email));
                } else  {
                    emailToId.put(email, i);
                }
            }
        }
        
        // prepare a hash with index in accounts as key and list of unique email address for that account as value
        HashMap<Integer, List<String>> idToEmails = new HashMap<>();
        for(String key : emailToId.keySet()) {
            int root = uf.root(emailToId.get(key));
            
            if (!idToEmails.containsKey(root)) {
                idToEmails.put(root, new ArrayList<String>());
            }
            
            idToEmails.get(root).add(key);
        }
        
        // collect the emails from idToEmails, sort it and add account name at index 0 to get the final list to add to final return List
        List<List<String>> mergedDetails =  new ArrayList<>();
        for(Integer id : idToEmails.keySet()) {
            List<String> emails =  idToEmails.get(id);
            Collections.sort(emails);
            //add name
            emails.add(0, accounts.get(id).get(0));
            
            mergedDetails.add(emails);
        }
        
        return  mergedDetails;
    }
}

// https://leetcode.com/problems/count-servers-that-communicate/description/
// notable mentions : 1267. Count Servers that Communicate

//https://leetcode.com/problems/making-a-large-island/description/
// 827. Making A Large Island
//medium version : https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/description/  

class Solution {
    int n;
    public int largestIsland(int[][] grid) {
        n = grid.length;
        //see union find from above class
        UnionFind uf = new UnionFind(n*n);
        int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int max = 0;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(i - 1>= 0 && grid[i][j] == 1 && grid[i-1][j] == 1){
                    uf.union(findIndex(i-1,j), findIndex(i,j));
                }

                if(j - 1>= 0 && grid[i][j] == 1 && grid[i][j-1] == 1){
                    uf.union(findIndex(i,j-1), findIndex(i,j));
                }
                if(grid[i][j] == 1)
                max = Math.max(max ,uf.weight[uf.root(findIndex(i ,j))]);
            }
        }

        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
               if(grid[i][j] == 0) {
                   HashSet<Integer> set = new HashSet();
                   int sum = 0;
                   for(int[] d: dirs){
                       int er = i + d[0];
                       int ec = j + d[1];
                       if(er >= 0 && er < n && ec >= 0 && ec < n && grid[er][ec] == 1){
                           int parent = uf.root(findIndex(er ,ec));
                           if(!set.contains(parent)){
                               sum += uf.weight[parent];
                               set.add(parent);
                           }
                       }
                   }
                   max = Math.max(max,sum+1);
               }
            }
        }

        return max;

    }
    private int findIndex(int i ,int j){
        return i*n+j;
    }
}

// https://leetcode.com/problems/redundant-connection-ii/description/
// 685. Redundant Connection II

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
    //check double parent
    int ans1 = -1;
    int ans2 = -1;
    int [] inDegree = new int[edges.length+1];
    Arrays.fill(inDegree,-1);
    
    for( int i = 0 ; i < edges.length; i++ ){
        int var1 = edges[i][0];
        int var2 = edges[i][1];
        if( inDegree[var2] == -1 ) inDegree[var2] = i;
        else {
            ans1 = inDegree[var2];
            ans2 = i;    //store the double parent
        }
    }
    
    // their will be only three categories:
    // two parent
    // cyclic graph
    // two parent + cyclic
    // ex: https://youtu.be/d0tqBMRZ6UQ?t=1022

    //(u,v)  u is parent  u points to v
    UnionFind uf = new UnionFind(edges.length*2);
    for(int [] edge: edges){
        if(ans2!=-1 && edge == edges[ans2]) continue;
        int u = edge[0];
        int v = edge[1];
        int paru = uf.find(u);
        int parv = uf.find(v);
        //in case of cyclic graph ans1 will be -1
        if(paru == parv)return ans1==-1 ? edge: edges[ans1];
        uf.union(u,v);
    }
    return edges[ans2];
}
}

// https://leetcode.com/problems/rank-transform-of-a-matrix/description/
// 1632. Rank Transform of a Matrix

// from solution
// https://leetcode.com/problems/rank-transform-of-a-matrix/solutions/1703698/java-clean-and-detailed-solution-with-handwritten-explanation/
class Solution {
	public class Pair implements Comparable<Pair>{
		int ele;
		int r;
		int c;
		
		public Pair(int ele, int r, int c) {
			this.ele = ele;
			this.r = r;
			this.c = c;
		}
		
		public int compareTo(Pair o) {
			return this.ele - o.ele;
		}
	}
    
	int[] rows;
	int[] cols;
	int[] par;
	int[] rank;
	
    public int[][] matrixRankTransform(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        rows = new int[n];
        cols = new int[m];
        par = new int[n * m];
        rank = new int[n * m];
        Pair[] arr = new Pair[n * m];
        int count = 0;
        
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		arr[count] = new Pair(matrix[i][j], i, j);
        		count++;
        	}
        }
        Arrays.sort(arr);
        List<Pair> ls = new ArrayList<>();
        int last = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++) {
        	if(arr[i].ele != last) {
        		process(ls, matrix);
        		last = arr[i].ele;
        		ls = new ArrayList<>();
        	}
        	ls.add(arr[i]);
        }
        
        process(ls, matrix);
        return matrix;
    }
    
    public int findp(int x, int[] par) {
    	if(par[x] < 0)
    		return x;
    	int temp = findp(par[x], par);
    	par[x] = temp;
    	return temp;
    }
    
    public void process(List<Pair> ls, int[][] matrix) {
    	int n = matrix.length;
    	int m = matrix[0].length;
    	int[] par = new int[n + m];
    	for(int i = 0; i < par.length; i++)
            par[i] = -1;
    	
    	for(Pair p : ls) {
    		int i = p.r;
    		int j = p.c;
    		
    		int p1 = findp(i, par);
    		int p2 = findp(n + j, par);
    		
    		if(p1 != p2) {
    			int maxrank = 
					Math.min(par[p1],Math.min(par[p2], -Math.max(rows[i], cols[j]) - 1));
    			par[p2] = maxrank;
    			par[p1] = p2;
    		}
    	}
    	
    	for(Pair p : ls) {
        int i = p.r;
    		int j = p.c;
            
    		int parp = findp(p.r, par);
    		matrix[i][j] = -par[parp];
            
            rows[i] = matrix[i][j];
            cols[j] = matrix[i][j];
    	}
    }
}