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
