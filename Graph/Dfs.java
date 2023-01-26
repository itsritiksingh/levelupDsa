// https://leetcode.com/problems/is-graph-bipartite/
// 785. Is Graph Bipartite?
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        for(int v = 0; v < graph.length; v++){
            if(visited[v] == 0){
                boolean isBip = traverseDFS(graph, visited, v, 1);
                if(isBip == false){
                    return false;
                }
            }
        }
        return true;
    }
    class Pair {
        int v;
        int color;
        
        Pair(int v, int color){
            this.v = v;
            this.color = color;
        }
    }
    public boolean traverseDFS(int[][] graph, int[] visited, int v, int color){
        visited[v] = color;
        for(int nbr: graph[v]){
            if(visited[nbr] == 0){
                boolean isBip = traverseDFS(graph, visited, nbr, color * -1);
                if(isBip == false){
                    return false;
                }
            } else {
                int oc = visited[nbr];
                int nc = color * -1;
                
                if(oc != nc){
                    return false;
                }
            }
        }   
        return true;
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

// https://leetcode.com/problems/coloring-a-border/
// 1034. Coloring A Border
class Solution {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int oc = grid[row][col];
        traverse(grid, row, col, oc);3
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == -oc){
                    grid[i][j] = color;
                }
            }
        }   
        return grid;
    }
    public void traverse(int[][] grid, int i, int j, int oc){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != oc){
            return;
        }
        grid[i][j] = -oc;
        traverse(grid, i - 1, j, oc);
        traverse(grid, i + 1, j, oc);
        traverse(grid, i, j - 1, oc);
        traverse(grid, i, j + 1, oc);
        
        if(i > 0 && j > 0 && i < grid.length - 1 && j < grid[0].length - 1 &&
           Math.abs(grid[i - 1][j]) == oc &&
           Math.abs(grid[i + 1][j]) == oc &&
           Math.abs(grid[i][j - 1]) == oc &&
           Math.abs(grid[i][j + 1]) == oc){
            grid[i][j] = oc;
        }
    }
}

// https://practice.geeksforgeeks.org/problems/mother-vertex/1
// mother vertex
class Solution
{
    public int findMotherVertex(int V, ArrayList<ArrayList<Integer>>adj)
    {
        for(int v =0 ;v < V;v++){
            boolean[] visited = new boolean[V];
            int count = dfs(v,adj,visited);
            if(count == V) return v;
        }
        
        return -1;
    }
    public int dfs(int v, ArrayList<ArrayList<Integer>>adj,boolean[] visited){
        
        if(visited[v] == true) return 0;
        
        visited[v] = true;
        
        int count = 0;
        for(int nbr : adj.get(v)){
            count +=dfs(nbr,adj,visited);
        }
        
        return count + 1;
    }
}
// 802. Find Eventual Safe States
// https://leetcode.com/problems/find-eventual-safe-states/
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        ArrayList<Integer> res = new ArrayList<>();
        vis = new int[graph.length];
        
        for(int i = 0; i < graph.length; i++){
            boolean safe = dfs(graph, i);
            if(safe){
                res.add(i);
            }
        }
        
        return res;
    }
    
    public boolean dfs(int[][] graph, int u){
        if(vis[u] == 2){
            return true;
        } else if(vis[u] == 1){
            return false;
        } else {
            vis[u] = 1;
            for(int n: graph[u]){
                boolean safe = dfs(graph, n);
                if(!safe){
                    return false;
                }
            }
            
            vis[u] = 2;
            return true;
        }
    }
    
    int[] vis;
}

// https://leetcode.com/problems/path-with-maximum-gold/description/
// 1219 path with maximum gold
class Solution {
    int ans , gold;
    public int getMaximumGold(int[][] grid) {
        ans = gold = 0;
        boolean [][] vis = new boolean[grid.length][grid[0].length];
        
        int[][] dir = {{0,-1},{0,1},{1,0},{-1,0}};
        
        for(int i = 0;i < grid.length;i++){
            for(int j = 0;j < grid[0].length;j++){
                dfs(grid,vis,i,j,dir);
            }
        }
        
        return ans;
    }
    private void dfs(int[][] grid,boolean[][] vis, int i, int j,int [][]dir){
        if(i < 0 || j <0 || i >= grid.length || j >= grid[0].length) return;
        
        if(vis[i][j] || grid[i][j] == 0) return;
        
        vis[i][j] = true;
        gold += grid[i][j];
        ans = Math.max(ans,gold);
        
        for(int[] d : dir){
            int er = i + d[0];
            int ec = j + d[1];
            
            dfs(grid,vis,er,ec,dir);
        }
        
        vis[i][j] = false;
        gold -= grid[i][j];
    }
}

// https://leetcode.com/problems/number-of-provinces/description/
// 547. Number of Provinces
class Solution {
    public int findCircleNum(int[][] isConnected) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap();
        int n = isConnected[0].length;
        for(int i = 0;i < n;i++){
            graph.put(i,new ArrayList());
        }
        for(int  i =0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(isConnected[i][j] == 1)
                    graph.get(i).add(j);
            }
        }
        
        int count = 0;
        boolean[] vis = new boolean[n];
        for(int i= 0;i < n;i++){
            if(!vis[i]){
                count++;
                dfs(graph,vis,i);
            }
        }
        
        return count;
    }
    
    private void dfs(HashMap<Integer, ArrayList<Integer>> graph, boolean[] vis, int u){
        if(vis[u]) return;
        else vis[u] = true;
        
        for(int v : graph.get(u)){
            if(!vis[v]) dfs(graph,vis,v);
        }
    }
}

// https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/description/
// 2477. Minimum Fuel Cost to Report to the Capital
class Solution {
    long ans = 0; int s;
    public long minimumFuelCost(int[][] roads, int seats) {
        List<List<Integer>> graph = new ArrayList(); s = seats;
        for (int i = 0; i < roads.length + 1; i++) graph.add(new ArrayList());
        for (int[] r: roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        dfs(0, 0, graph);
        return ans;
    }
    private int dfs(int i, int prev, List<List<Integer>> graph) {
        int people = 1;
        for (int x: graph.get(i)) {
            if (x == prev) continue;
            people += dfs(x, i, graph);
        }
        if (i != 0) ans += (people + s - 1) / s;
        return people;
    }
    }
// https://leetcode.com/problems/word-search/
// 79. Word Search
class Solution {
    public boolean exist(char[][] board, String word) {
        // char[] chArr= word.toCharArray();
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board[0].length;j++){
                if(word.charAt(0) == board[i][j]){
                    boolean [][]vis = new boolean[board.length][board[0].length];
                        if(dfs(board,word,i,j,0,vis)){
                        return true;
                        }
                }
            }
        }

        return false;
    }
    private boolean dfs(char[][] board,String word,int i,int j,int idx, boolean[][]vis){
        if(idx == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;

        if(board[i][j] == word.charAt(idx)){ 
            idx++;
        }else return false;

        if(vis[i][j]) return false;
        
        vis[i][j] = true;

        if(dfs(board,word,i-1,j,idx,vis) || dfs(board,word,i+1,j,idx,vis) || dfs(board,word,i,j+1,idx,vis) || dfs(board,word,i,j-1,idx,vis)) return true;

        vis[i][j] = false;
        return false;
    }
}

// https://leetcode.com/problems/all-paths-from-source-to-target/description/
// 797. All Paths From Source to Target
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> ans = new ArrayList();
        List<List<Integer>> ret = new ArrayList();

        dfs(0,graph,ret,ans);
        return ret;
    }
    public void dfs(int src, int[][] graph , List<List<Integer>> ret,List<Integer> ans){
        if(src == graph.length - 1){
            ans.add(src);
            List<Integer> temp = new ArrayList(ans);
            ret.add(temp);
            ans.remove(ans.size() - 1); 
        }
        ans.add(src);
        for(int nbr : graph[src]){
            dfs(nbr,graph,ret,ans);     
        }
        ans.remove(ans.size() - 1); 
    }
}