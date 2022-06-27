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