// https://pepcoding.com/resources/online-java-foundation/graphs/shortest-path-in-weights-official/ojquestion
// Dijkstra algo using Shortest Path In Weights
public class Main {
   public static void main(String[] args) throws Exception {
      int src = Integer.parseInt(br.readLine());

      PriorityQueue<Pair> queue = new PriorityQueue<>();
      queue.add(new Pair(src, src + "", 0));
      boolean[] visited = new boolean[vtces];
      while(queue.size() > 0){
         Pair rem = queue.remove();

         if(visited[rem.v] == true){
            continue;
         }
         visited[rem.v] = true;
         System.out.println(rem.v + " via " + rem.psf + " @ " + rem.wsf);
         
         for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == false) {
                // here with add current weight of all node travelled so far
               queue.add(new Pair(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
            }
         }
      }
   }

   static class Pair implements Comparable<Pair> {
      int v;
      String psf;
      int wsf;

      Pair(int v, String psf, int wsf){
         this.v = v;
         this.psf = psf;
         this.wsf = wsf;
      }

      public int compareTo(Pair o){
         return this.wsf - o.wsf;
      }
   }
}

// https://pepcoding.com/resources/online-java-foundation/graphs/minimum-wire-to-connect-all-pcs-official/ojquestion
// prim's algo 
public class Main {
   public static void main(String[] args) throws Exception {
      int src = 0;
      PriorityQueue<Pair> queue = new PriorityQueue<>();
      queue.add(new Pair(src, -1, 0));
      Integer[] visited = new Integer[vtces];
      while(queue.size() > 0){
         Pair rem = queue.remove();

         if(visited[rem.v] != null){
            continue;
         }
         visited[rem.v] = rem.p;
         if(rem.p != -1){
            System.out.println("[" + rem.v + "-" + rem.p + "@" + rem.wt + "]");
         }
         
         for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == null) {
                //here we don't add weight of parent
               queue.add(new Pair(e.nbr, rem.v, e.wt));
            }
         }
      }
   }
   static class Pair implements Comparable<Pair> {
      Integer v;
      Integer p;
      int wt;

      Pair(Integer v, Integer p, int wt){
         this.v = v;
         this.p = p;
         this.wt = wt;
      }

      public int compareTo(Pair o){
         return this.wt - o.wt;
      }
   }
}

//Function to find number of strongly connected components in the graph using kosaraju

class Solution
{
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        boolean[] vis1 = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for(int v = 0; v < V; v++){
            if(!vis1[v]){
                dfs1(v, adj, vis1, st);
            }
        }
        
        // transpose
        ArrayList<ArrayList<Integer>> tsp = new ArrayList<>();
        for(int v = 0; v < V; v++){
            tsp.add(new ArrayList<>());
        }
        
        for(int v = 0; v < V; v++){
            for(int nbr: adj.get(v)){
                tsp.get(nbr).add(v);
            }
        }
        
        // dfs2
        int count = 0;
        boolean[] vis2 = new boolean[V];
        while(st.size() > 0){
            int v = st.pop();
            if(!vis2[v]){
                dfs2(v, tsp, vis2);
                count++;
            }
        }
        
        return count;
    }
    
    public void dfs2(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited){
        visited[v] = true;
        for(int nbr: adj.get(v)){
            if(!visited[nbr]){
                dfs2(nbr, adj, visited);
            }
        }
    }
    
    public void dfs1(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> st){
        
        visited[v] = true;
        for(int nbr: adj.get(v)){
            if(!visited[nbr]){
                dfs1(nbr, adj, visited, st);
            }
        }
        
        st.push(v);
    }
}
//topological sort
// https://www.pepcoding.com/resources/online-java-foundation/graphs/compilation-order-official/ojquestion
public class Main {
   static class Edge {
      int src;
      int nbr;

      Edge(int src, int nbr) {
         this.src = src;
         this.nbr = nbr;
      }
   }

   public static void main(String[] args) throws Exception {
      boolean[] visited = new boolean[vtces];
      Stack<Integer> st = new Stack<>();
      for(int v = 0; v < vtces; v++){
         if(visited[v] == false){
            topological(graph, v, visited, st);
         }
      }

      while(st.size() > 0){
         System.out.println(st.pop());
      }
   }

   public static void topological(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> st) {
      visited[src] = true;
      for (Edge e : graph[src]) {
         if (!visited[e.nbr]) {
            topological(graph, e.nbr, visited, st);
         }
      }
      st.push(src);
   }
}
//topological sort using kahn's algo
class Solution
{
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        int[] tsort = new int[V];   
        int[] inDegree = new int[V];
        for(int v = 0; v < V; v++){
            for(int n: adj.get(v)){
                inDegree[n]++;
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int v = 0; v < V; v++){
            if(inDegree[v] == 0){
                queue.add(v);
            }
        }
        int idx = 0;
        while(queue.size() > 0){
            int v = queue.remove();
            tsort[idx++] = v;
            for(int n: adj.get(v)){
                inDegree[n]--;
                if(inDegree[n] == 0){
                    queue.add(n);
                }
            }
        }
        
        return tsort;
    }
}

// Minimum spanning tree using kruskals
public static void kruskals(ArrayList<Edge>[] graph){
   PriorityQueue<Edge> pq = new PriorityQueue<>();
   for(int v = 0; v < graph.length; v++){
      for(Edge e: graph[v]){
         pq.add(e);
      }
   }
   parent = new int[graph.length];
   rank = new int[graph.length];
   for(int i = 0; i < graph.length; i++){
      parent[i] = i;
      rank[i] = 0;
   }
   while(pq.size() > 0){
      Edge e = pq.remove();
      
      int srcLead = find(e.src);
      int nbrLead = find(e.nbr);
      
      if(srcLead != nbrLead){
        union(srcLead, nbrLead);
      }
   }
}
static int[] parent;
static int[] rank;
public static int find(int x){
   if(parent[x] == x){
      return x;
   } else {
      parent[x] = find(parent[x]);
      return parent[x];
   }
}
public static void union(int s1l, int s2l){
   if(rank[s1l] < rank[s2l]){
      parent[s1l] = s2l;
   } else if(rank[s2l] < rank[s1l]){
      parent[s2l] = s1l;
   } else {
      parent[s1l] = s2l;
      rank[s2l]++;
   }
}
// travel eular path
// 332. Reconstruct Itinerary
// https://leetcode.com/problems/reconstruct-itinerary/
class Solution {
    HashMap<String, PriorityQueue<String>> adj;
    
    public List<String> findItinerary(List<List<String>> tickets) {
        adj = new HashMap<>();
        
        for(List<String> ticket: tickets){
            adj.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            adj.get(ticket.get(0)).add(ticket.get(1));
        }
        
        
        
        ArrayList<String> res = new ArrayList<>();
        dfs("JFK", res);
        return res;
    }
    
    public void dfs(String src, ArrayList<String> res){
        if(adj.containsKey(src) == false || adj.get(src).size() == 0){
            res.add(0, src);
            return;
        } 
        
        while(adj.get(src).size() > 0){
            String nbr = adj.get(src).remove();
            dfs(nbr, res);
        }
        res.add(0, src);
    }
}

//https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
class Solution
{
    public int[] articulationPoints(int V, ArrayList<ArrayList<Integer>> adj)
    {
        HashSet<Integer> aps = new HashSet<>();
        boolean[] vis = new boolean[V];
        int[] dis = new int[V];
        int[] low = new int[V];
        
        for(int i = 0; i < V; i++){
            if(!vis[i]){
                dfs(adj, aps, vis, dis, low, i, -1);
            }
        }
        
        
        int[] res = new int[aps.size()];
        int idx = 0;
        for(int i: aps){
            res[idx] = i;
            idx++;
        }
        
        if(aps.size() == 0){
            res = new int[] {-1};
        }
        
        Arrays.sort(res);
        
        return res;
    }
    
    int time = 0;
    public void dfs(ArrayList<ArrayList<Integer>> adj, HashSet<Integer> aps, boolean[] vis, int[] dis, int[] low, int u, int p){
        vis[u] = true;
        dis[u] = low[u] = ++time;
        
        int c = 0;
        for(int v: adj.get(u)){
            if(v == p){
                continue;
            } else if(vis[v]){
                low[u] = Math.min(low[u], dis[v]);
            } else {
                c++;
                dfs(adj, aps, vis, dis, low, v, u);
                low[u] = Math.min(low[u], low[v]);
                
                if(p != -1 && low[v] >= dis[u]){
                    aps.add(u);
                }
            }
        }
        
        if(p == -1 && c > 1){
            aps.add(u);
        }
    }
}
// more question on articulationPoints
//https://practice.geeksforgeeks.org/problems/bridge-edge-in-graph/1
// https://practice.geeksforgeeks.org/problems/doctor-strange2206/1

//The Bellman–Ford : shortest paths from a single source vertex to all of the other vertices,  edges may contain -ve weight
// https://practice.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

class Solution
{
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> adj, int S)
    {
        // Write your code here
        int[] res = new int[V];
        Arrays.fill(res, 100000000);
        res[S] = 0;
        
        for(int i = 1; i <= V - 1; i++){
            for(ArrayList<Integer> edge: adj){
                int u = edge.get(0);
                int v = edge.get(1);
                int p = edge.get(2);
                if(res[u] == 100000000){
                    continue;
                } else {
                    res[v] = Math.min(res[v], res[u] + p);
                }
            }
        }
        
        return res;
    }
}
//Floyd warshall Algo to find:   The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed Graph. 
class Solution
{
    public void shortest_distance(int[][] matrix)
    {
        // Code here 
        for(int k = 0; k < matrix.length; k++){
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix.length; j++){
                    if(matrix[i][k] == -1){
                        continue;
                    } else if(matrix[k][j] == -1){
                        continue;
                    } else if(matrix[i][j] == -1){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    } else {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }
    }
}
// https://practice.geeksforgeeks.org/problems/find-the-maximum-flow2126/1
// find maximum flow in a network
class Solution 
{ 
    int findMaxFlow(int N, int M, ArrayList<ArrayList<Integer>> Edges) 
    { 
        int mflow = 0;
        
        int[][] graph = new int[N + 1][N + 1];
        int[][] rgraph = new int[N + 1][N + 1];
        for(ArrayList<Integer> edge: Edges){
            graph[edge.get(0)][edge.get(1)] += edge.get(2);
            graph[edge.get(1)][edge.get(0)] += edge.get(2);
            rgraph[edge.get(0)][edge.get(1)] += edge.get(2);
            rgraph[edge.get(1)][edge.get(0)] += edge.get(2);
        }
        
        int[] p = new int[N + 1]; 
        while(traverse(rgraph, p, 1, N) == true){
            int flow = Integer.MAX_VALUE;
            
            for(int i = N; i != 1; i = p[i]){
                flow = Math.min(flow, rgraph[p[i]][i]);
            }
            
            for(int i = N; i != 1; i = p[i]){
                rgraph[p[i]][i] -= flow;
                rgraph[i][p[i]] += flow;
            }
            
            mflow += flow;
            Arrays.fill(p, -1);
        }
        
        return mflow;
    }
    
    boolean traverse(int[][] rgraph, int[] p, int s, int d){
        boolean[] vis = new boolean[rgraph.length];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        
        while(queue.size() > 0){
            int rem = queue.remove();
            
            if(vis[rem] == true){
                continue;
            }
            vis[rem] = true;
            
            if(rem == d){
                return true;
            }
            
            for(int i = 1; i < rgraph[rem].length; i++){
                if(rgraph[rem][i] > 0 && !vis[i]){
                    p[i] = rem;
                    queue.add(i);
                }
            }
        }
        return false;
    }
} 
