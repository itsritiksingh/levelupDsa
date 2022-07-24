// https://leetcode.com/problems/rotting-oranges/
// 994. Rotting Oranges
class Solution {
    class Pair {
        int x;
        int y;
        int t;
        
        Pair(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    
    public int orangesRotting(int[][] grid) {
      ArrayDeque<Pair> queue = new ArrayDeque<>();
      int fresh = 0;
      for(int x = 0; x < grid.length; x++){
          for(int y = 0; y < grid[0].length; y++){
              if(grid[x][y] == 2){
                  queue.add(new Pair(x, y, 0));
              } else if(grid[x][y] == 1){
                  fresh++;
              }
          }
      }
    
      boolean[][] visited = new boolean[grid.length][grid[0].length];
      int time = 0;
      while(queue.size() > 0){
          // remove
          Pair rem = queue.remove();
          
          // mark*
          if(visited[rem.x][rem.y] == true){
              continue;
          }
          visited[rem.x][rem.y] = true;
          
          // work
          if(rem.t > time){
              time = rem.t;
          }
          if(grid[rem.x][rem.y] == 1){
              fresh--;
          }
          
          // add neighbors
          addNeighbors(rem.x + 1, rem.y, visited, grid, queue, rem.t + 1);
          addNeighbors(rem.x - 1, rem.y, visited, grid, queue, rem.t + 1);
          addNeighbors(rem.x, rem.y + 1, visited, grid, queue, rem.t + 1);
          addNeighbors(rem.x, rem.y - 1, visited, grid, queue, rem.t + 1);   
      }
        
      if(fresh == 0){
          return time;
      } else {
          return -1;
      }
    }
    
    public void addNeighbors(int x, int y, boolean[][] visited, int[][] grid, ArrayDeque<Pair> queue, int t){
        if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length){
            return;
        } else if(visited[x][y]){
            return;
        } else if(grid[x][y] == 0){
            return;
        }   
        queue.add(new Pair(x, y, t));
    }   
}

// https://leetcode.com/problems/number-of-enclaves/
// 1020. Number of Enclaves
class Solution {
    boolean flag = true;
    int len = 0;
    
    public int numEnclaves(int[][] grid) {
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(visited[i][j] == false && grid[i][j] == 1){
                    flag = true;
                    len = 0;
                    traverse(grid, visited, i, j);
                    if(flag == true){
                        count += len;
                    }
                }
            }
        }
        
        return count;
    }
    
    public void traverse(int[][] grid, boolean[][] visited, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length){
            flag = false;
            return;
        } else if(grid[i][j] == 0){
            return;
        } else if(visited[i][j] == true){
            return;
        }
        
        len++;
        visited[i][j] = true;
        traverse(grid, visited, i + 1, j);
        traverse(grid, visited, i - 1, j);
        traverse(grid, visited, i, j + 1);
        traverse(grid, visited, i, j - 1);
    }   
}

// https://leetcode.com/problems/is-graph-bipartite/
// 785. Is Graph Bipartite?
class Solution {
    class Pair {
        int v;
        int color; // 0 is uncolored, 1 is c1, -1 is c2
        
        Pair(int v, int color){
            this.v = v;
            this.color = color;
        }
    }
    
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        for(int v = 0; v < graph.length; v++){
            if(visited[v] == 0){
                boolean isBip = traverse(graph, v, visited);
                if(isBip == false){
                    return false;
                }
            }
        }
        
        return true;
    }   
    public boolean traverse(int[][] graph, int v, int[] visited){
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(v, 1));
        
        while(queue.size() > 0){
            Pair rem = queue.remove();
            
            if(visited[rem.v] != 0){
              if(visited[rem.v] != rem.color){
                  return false;
              }  
            } 
            
            visited[rem.v] = rem.color;
            
            for(int n: graph[rem.v]){
                if(visited[n] == 0){
                    queue.add(new Pair(n, rem.color * -1));
                }
            }
        }
        return true;
    }
}

// https://leetcode.com/problems/swim-in-rising-water/
// 778. Swim in Rising Water using prims
class Solution {
    class Pair implements Comparable<Pair> {
        int i;
        int j;
        int tesf;
        
        Pair(int i, int j, int tesf){
            this.i = i;
            this.j = j;
            this.tesf = tesf;
        }
        public int compareTo(Pair o){
            return this.tesf - o.tesf;
        }
    }
    
    public int swimInWater(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        pq.add(new Pair(0, 0, grid[0][0]));
        while(pq.size() > 0){
            Pair rem = pq.remove();
            if(visited[rem.i][rem.j] == true){
                continue;
            }
            visited[rem.i][rem.j] = true;
            if(rem.i == grid.length - 1 && rem.j == grid[0].length - 1){
                return rem.tesf;
            }
            addN(rem.i - 1, rem.j, rem.tesf, grid, visited, pq);
            addN(rem.i + 1, rem.j, rem.tesf, grid, visited, pq);
            addN(rem.i, rem.j - 1, rem.tesf, grid, visited, pq);
            addN(rem.i, rem.j + 1, rem.tesf, grid, visited, pq);
        }
        return -1;
    }
    public void addN(int i, int j, int ot, int[][] grid, boolean[][] visited, PriorityQueue<Pair> pq){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] == true){
            return;
        }
        pq.add(new Pair(i, j, Math.max(ot, grid[i][j])));
    }   
}

// https://leetcode.com/problems/as-far-from-land-as-possible/
// 1162. As Far from Land as Possible
class Solution {
    class Pair {
        int i , j,sr,sc,val;
        Pair(int i,int j,int sr,int sc,int val){
            this.i = i;
            this.j = j;
            this.sr = sr;
            this.sc = sc;
            this.val = val;
        }
    }
    int max = -1;
    public int maxDistance(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        ArrayDeque<Pair> qu = new ArrayDeque<>();
        
        for(int i= 0;i< grid.length;i++){
            for(int j = 0 ;j < grid[0].length;j++){
                if(grid[i][j] == 1) qu.add(new Pair(i,j,i,j,1));
            }
        }
        
        while(qu.size() > 0){
            Pair rem = qu.remove();
            
            if(visited[rem.i][rem.j] == true) continue;
            visited[rem.i][rem.j]  = true;
            
            if(rem.val == 0) max = Math.max(max,Math.abs(rem.i - rem.sr) + Math.abs(rem.j - rem.sc));
            
            addN(rem.i - 1,rem.j,rem.sr,rem.sc,grid,qu);
            addN(rem.i + 1,rem.j,rem.sr,rem.sc,grid,qu);
            addN(rem.i,rem.j + 1,rem.sr,rem.sc,grid,qu);
            addN(rem.i,rem.j - 1,rem.sr,rem.sc,grid,qu);
        }
        
        return max;
    }
    public void addN(int i,int j,int sr,int sc,int[][] grid,ArrayDeque<Pair> qu){
        if(i < 0|| j < 0 || i >= grid.length || j >= grid[i].length)
            return;
        
        qu.add(new Pair(i,j,sr,sc,grid[i][j]));
    }
}

// https://leetcode.com/problems/shortest-bridge/
// 934. Shortest Bridge
class Solution {
    class Pair {
        int i;
        int j;
        int level;
        
        Pair(int i, int j, int level){
            this.i = i;
            this.j = j;
            this.level = level;
        }
    }

    public int shortestBridge(int[][] grid) {
        
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        
        boolean found = false;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    dfs(grid, queue, i, j);
                    found = true;
                    break;
                }
            }
            
            if(found){
                break;
            }
        }
        while(queue.size() > 0){
            Pair rem = queue.remove();
            
            if(grid[rem.i][rem.j] == -2){
                continue;
            }
            
            if(grid[rem.i][rem.j] == 1){
                return rem.level - 1;
            }
            
            grid[rem.i][rem.j] = -2;
            
            addN(grid, queue, rem.i - 1, rem.j, rem.level + 1);
            addN(grid, queue, rem.i + 1, rem.j, rem.level + 1);
            addN(grid, queue, rem.i, rem.j - 1, rem.level + 1);
            addN(grid, queue, rem.i, rem.j + 1, rem.level + 1);
        }
        
        return -1;
    }
    public void addN(int[][] grid, ArrayDeque<Pair> queue, int i, int j, int level){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length){
            return;
        } else if(grid[i][j] == -1){
            return;
        } else if(grid[i][j] == -2){
            return;
        }   
        queue.add(new Pair(i, j, level));
    }
    public void dfs(int[][] grid, ArrayDeque<Pair> queue, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || grid[i][j] == -1){
            return;
        }
        
        grid[i][j] = -1;
        queue.add(new Pair(i, j, 0));
        dfs(grid, queue, i - 1, j);
        dfs(grid, queue, i + 1, j);
        dfs(grid, queue, i, j - 1);
        dfs(grid, queue, i, j + 1);
    }
}

// https://leetcode.com/problems/clone-graph/
// 133. Clone Graph
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        
        HashMap<Integer, Node> visited = new HashMap<>();
        return helper(node, visited);
    }
    
    public Node helper(Node node, HashMap<Integer, Node> visited){
        Node clone = new Node(node.val);
        visited.put(node.val, clone);
        
        for(Node nbr: node.neighbors){
            Node nbrClone = null;
            
            if(visited.containsKey(nbr.val) == false){
                nbrClone = helper(nbr, visited);
            } else {
                nbrClone = visited.get(nbr.val);
            }
            
            clone.neighbors.add(nbrClone);
        }
        return clone;
    }
}

// https://practice.geeksforgeeks.org/problems/alien-dictionary/1/
// Alien Dictionary using topology sort
class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        ArrayList<Integer>[] graph = (ArrayList<Integer>[])new ArrayList[K];
        for(int i = 0; i < K; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < dict.length - 1; i++){
            String word1 = dict[i];
            String word2 = dict[i + 1];
            
            for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);
                
                if(ch1 != ch2){
                    graph[ch1 - 'a'].add(ch2 - 'a');
                    break;
                }
            }
        }
        boolean[] vis = new boolean[K];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < K; i++){
            if(!vis[i]){
                tsort(graph, vis, st, i);
            }
        }
     
        String ans = "";
        while(st.size() > 0){
            ans += (char)(st.pop() + 'a');
        }
        return ans;
    }
    
    public void tsort(ArrayList<Integer>[] graph, boolean[] vis, Stack<Integer> st, int v){
        vis[v] = true;
        for(int n: graph[v]){
            if(!vis[n]){
                tsort(graph, vis, st, n);
            }
        }
        st.push(v);
    }
}

// https://leetcode.com/problems/course-schedule-ii/
// 210. Course Schedule II

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] req: prerequisites){
            graph[req[0]].add(req[1]);
        }
        
        int[] in = new int[numCourses];
        for(int v = 0; v < graph.length;v++){
            for(int n: graph[v]){
                in[n]++;
            }
        }
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int v = 0; v < graph.length; v++){
            if(in[v] == 0) queue.add(v);
        }
        
        int[] ans = new int[graph.length];
        int idx = ans.length - 1;
        while(queue.size() > 0){
            int rem = queue.remove();
            ans[idx] = rem;
            idx--;
            
            for(int n: graph[rem]){
                in[n]--;
                
                if(in[n] == 0) queue.add(n);
            }
        }
        
        if(idx == -1){
            return ans;
        } else {
            return new int[] {};
        }
     }
}

//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/number-of-island-2-official/ojquestion
// Krushals algo
public class Main {

  public static List<Integer> numIslands2(int m, int n, int[][] positions) {
    int[][] grid = new int[m][n];
    parent = new int[m * n];
    rank = new int[m * n];
    
    for(int i = 0; i < m * n; i++){
        parent[i] = i;
        rank[i] = 0;
    }
    
    ArrayList<Integer> res = new ArrayList<>();
    count = 0;
    
    for(int[] pos: positions){
        int x = pos[0];
        int y = pos[1];
        grid[x][y] = 1;
        count++;
        handleNewCell(x, y, x - 1, y, m, n, grid);
        handleNewCell(x, y, x + 1, y, m, n, grid);
        handleNewCell(x, y, x, y - 1, m, n, grid);
        handleNewCell(x, y, x, y + 1, m, n, grid);
        
        res.add(count);
    }
    return res;
  }
  
  static void handleNewCell(int x, int y, int xx, int yy, int m, int n, int[][] grid){
      if(xx < 0 || yy < 0 || xx >= m || yy >= m || grid[xx][yy] == 0){
          return;
      }
      
      int xyCell = x * n + y;
      int xxyyCell = xx * n + yy;
      
      int xylead = find(xyCell);
      int xxyylead = find(xxyyCell);
      
      if(xylead != xxyylead){
          count--;
          union(xylead, xxyylead);
      }
  }
  
  static int count;
  static int[] parent;
  static int[] rank;
  
  
  static int find(int i){
      if(parent[i] == i){
          return i;
      } else {
          parent[i] = find(parent[i]);
          return parent[i];
      }
  }
  
  static void union(int i, int j){
      if(rank[i] < rank[j]){
          parent[i] = j;
      } else if(rank[j] < rank[i]){
          parent[j] = i;
      } else {
          parent[i] = j;
          rank[j]++;
      }
  }
}

// 959. Regions Cut By Slashes using DSU
// https://leetcode.com/problems/regions-cut-by-slashes 
class Solution {
    public int regionsBySlashes(String[] grid) {
        parent = new int[4 * grid.length * grid.length];
        rank = new int[4 * grid.length * grid.length];
        
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;
            rank[i] = 0;
        }
        
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length(); j++){
                char ch = grid[i].charAt(j);
                
                int bno = i * grid.length + j;  
                
                if(ch != '/'){
                    unionHelper(4 * bno + 0, 4 * bno + 1);
                    unionHelper(4 * bno + 2, 4 * bno + 3);
                }
                
                if(ch != '\\'){
                    unionHelper(4 * bno + 0, 4 * bno + 3);
                    unionHelper(4 * bno + 1, 4 * bno + 2);
                }
                
                if(i > 0){
                    int obno = (i - 1) * grid.length + j;
                    unionHelper(4 * bno + 0, 4 * obno + 2);
                }
                
                if (j > 0){
                    int obno = i * grid.length + (j - 1);
                    unionHelper(4 * bno + 3, 4 * obno + 1);
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i < parent.length; i++){
            if(parent[i] == i){
                count++;
            }
        }
        return count;
    }
    
    int[] parent;
    int[] rank;
    
    int find(int x){
        if(parent[x] == x){
            return x;
        } else {
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }
    
    void union(int xl, int yl){
        if(rank[xl] < rank[yl]){
            parent[xl] = yl;
        } else if(rank[yl] < rank[xl]){
            parent[yl] = xl;
        } else {
            parent[xl] = yl;
            rank[yl]++;
        }
    }
    
    void unionHelper(int x, int y){
        int xl = find(x);
        int yl = find(y);
        
        if(xl != yl){
            union(xl, yl);
        }
    }
}