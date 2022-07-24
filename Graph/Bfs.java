// https://leetcode.com/problems/bus-routes/
// 815. Bus Routes
class Solution {
    class Pair {
        int bus;
        int csf;
        
        Pair(int bus, int csf){
            this.bus = bus;
            this.csf = csf;
        }
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target){
            return 0;
        }
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int bus = 0; bus < routes.length; bus++){
            for(int stop: routes[bus]){
                if(map.containsKey(stop) == false){
                    map.put(stop, new ArrayList<>());
                }
                map.get(stop).add(bus);
            }
        }
        
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        for(int bsrc: map.get(source)){
            queue.add(new Pair(bsrc, 1));
        }
        
        boolean[] visited = new boolean[routes.length];
        while(queue.size() > 0){
            // n*
            Pair rem = queue.remove();
            
            if(visited[rem.bus] == true){
                continue;
            }
            visited[rem.bus] = true;
            
            for(int rstop: routes[rem.bus]){
                if(rstop == target){
                    return rem.csf;
                }
            }
            
            for(int rstop: routes[rem.bus]){
                for(int bus: map.get(rstop)){
                    if(visited[bus] == false){
                        queue.add(new Pair(bus, rem.csf + 1));
                    }
                }
            }
        }
        return -1;
    }
}

// https://leetcode.com/problems/01-matrix/
// 542. 01 Matrix
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
    public int[][] updateMatrix(int[][] mat) {
        int[][] visited = new int[mat.length][mat[0].length];
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == 0){
                    queue.add(new Pair(i, j, 0));
                }
                visited[i][j] = -1;
            }
        }
        while(queue.size() > 0){
            Pair rem = queue.remove();
            
            if(visited[rem.i][rem.j] != -1){
                continue;
            }
            visited[rem.i][rem.j] = rem.level;
            
            addChild(mat, visited, queue, rem.i - 1, rem.j, rem.level + 1);
            addChild(mat, visited, queue, rem.i + 1, rem.j, rem.level + 1);
            addChild(mat, visited, queue, rem.i, rem.j - 1, rem.level + 1);
            addChild(mat, visited, queue, rem.i, rem.j + 1, rem.level + 1);
        }
        
        return visited;
    }
    
    public void addChild(int[][] mat, int[][] visited, ArrayDeque<Pair> queue, int i, int j, int level){
        if(i < 0 || j < 0 || i >= mat.length || j >= mat[0].length || visited[i][j] != -1){
            return;
        }
        
        queue.add(new Pair(i, j, level));
    }
}

// https://leetcode.com/problems/coloring-a-border/
// 1034. Coloring A Border
class Solution {
    class Pair {
        int i;
        int j;
        boolean border;
        
        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        
        Pair p = new Pair(row, col);
        p.border = isBorder(grid, row, col);
        queue.add(p);
        
        ArrayList<Pair> res = new ArrayList<>();
        int orgc = grid[row][col];
        
        while(queue.size() > 0){
            Pair rem = queue.remove();
            
            if(visited[rem.i][rem.j]){
                continue;
            }
            visited[rem.i][rem.j] = true;
            
            if(rem.border){
                res.add(rem);
            }
            
            addN(grid, visited, queue, orgc, rem.i - 1, rem.j);
            addN(grid, visited, queue, orgc, rem.i + 1, rem.j);
            addN(grid, visited, queue, orgc, rem.i, rem.j - 1);
            addN(grid, visited, queue, orgc, rem.i, rem.j + 1);
        }
        
        for(Pair pair: res){
            grid[pair.i][pair.j] = color;
        }
        return grid;
    }
    public void addN(int[][] grid, boolean[][] visited, ArrayDeque<Pair> queue, int orgc, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] == true || grid[i][j] != orgc){
            return;
        }
        
        Pair p = new Pair(i, j);
        p.border = isBorder(grid, i, j);
        queue.add(p);
    }    
    public boolean isBorder(int[][] grid, int i, int j){
        if(i == 0){
            return true;
        } else if(i == grid.length - 1){
            return true;
        } else if(j == 0){
            return true;
        } else if(j == grid[0].length - 1){
            return true;
        } else {
            int color = grid[i][j];
            if(grid[i - 1][j] != color){
                return true;
            } else if(grid[i + 1][j] != color){
                return true;
            } else if(grid[i][j - 1] != color){
                return true;
            } else if(grid[i][j + 1] != color){
                return true;
            } else {
                return false;
            }
        }
    }
}

// 924. Minimize Malware Spread
// https://leetcode.com/problems/minimize-malware-spread/

class Solution {
    public int minMalwareSpread(int[][] graph, int[] infected) {
        HashSet<Integer> setInfected = new HashSet<>();
        Arrays.sort(infected);
        int count = 0;
        for(int val: infected){
            setInfected.add(val);
        }
        int ans = infected[0]; 
        for(int curr=0 ;curr<infected.length; curr++){
            boolean[] visited = new boolean[graph.length];
            int temp = dfs(graph, visited, infected[curr], infected[curr], setInfected);
            if(temp>count){
                count = temp;
                ans = infected[curr];
            }
        }
        return ans;
    }
    static int dfs(int[][] graph, boolean[] visited, int initialInfected, int curr, HashSet<Integer> setInfected){
        if(visited[curr]){
            return 0;
        }
        if(curr!=initialInfected && setInfected.contains(curr)){
            return Integer.MIN_VALUE;
        }
        visited[curr] = true;
        int count=0;
        for(int nbr=0; nbr<graph[curr].length; nbr++){
            if(graph[curr][nbr]==1 && visited[nbr]==false){
                int val = dfs(graph, visited, initialInfected, nbr, setInfected);
                if(val==Integer.MIN_VALUE){ return val;}
                count+= val;
            }   
        }
        return count+1;
    }
}