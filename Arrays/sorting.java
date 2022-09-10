// 1329 // https://leetcode.com/problems/sort-the-matrix-diagonally/
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int gap = i - j;
                if(map.containsKey(gap) == false){
                    map.put(gap, new PriorityQueue<>());
                }
                map.get(gap).add(mat[i][j]);
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int gap = i - j;
                mat[i][j] = map.get(gap).remove();
            }
        }
        return mat;
    }
}

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for(int gap = -(m - 1); gap <= (n - 1); gap++){
            ArrayList<Integer> diag = new ArrayList<>();
            int i = gap < 0? -gap: 0;
            int j = gap < 0? 0: gap;
            while(i < m && j < n){
                diag.add(mat[i][j]);
                i++;
                j++;
            }
            Collections.sort(diag);
            i = gap < 0? -gap: 0;
            j = gap < 0? 0: gap;
            int idx = 0;
            while(i < m && j < n){
                mat[i][j] = diag.get(idx);
                idx++;
                i++;
                j++;
            }
        }
        return mat;
    }
}

// Minimum Platforms
// https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
static int findPlatform(int arr[], int dep[], int n)
    {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int count = 0;
        int max = 0;
        int i = 0;
        int j = 0;
        while(i < arr.length && j < dep.length){
            if(arr[i] <= dep[j]){
                i++;
                count++;
            } else {
                j++;
                count--;
            }
            max = Math.max(count, max);
        }
        return max;
    }

// 1094. Car Pooling
// https://leetcode.com/problems/car-pooling/
// Solution 1
class Solution {
    class Pair implements Comparable<Pair> {
        int time;
        int delta;
        Pair(int time, int delta){
            this.time = time;
            this.delta = delta;
        }
        public int compareTo(Pair o){
            if(this.time != o.time){
                return this.time - o.time;
            } else {
                if(this.delta < 0){
                    return -1;
                } else if(o.delta < 0){
                    return +1;
                } else {
                    return 0;
                }
            }
        }
    }
    public boolean carPooling(int[][] trips, int capacity) {
        int[] map = new int[1001];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int[] trip: trips){
            pq.add(new Pair(trip[1], +trip[0]));
            pq.add(new Pair(trip[2], -trip[0]));
        }
        int pic = 0;
        while(pq.size() > 0){
            Pair rem = pq.remove();
            pic += rem.delta;
            
            if(pic > capacity){
                return false;
            } else if(pic < 0){
                pic = 0;
            }
        }
        return true;
    }
}

//Solution 2
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> stops = new ArrayList<>();
        for(int[] trip: trips){
            int people = trip[0];
            int from = trip[1];
            int to = trip[2];
            if(map.containsKey(from) == false){
                map.put(from, +people);
                stops.add(from);
            } else {
                map.put(from, map.get(from) + people);
            }
            if(map.containsKey(to) == false){
                map.put(to, -people);
                stops.add(to);
            } else {
                map.put(to, map.get(to) - people);
            }
        }
        Collections.sort(stops);
        int pic = 0;
        for(int stop: stops){
            int delta = map.get(stop);
            pic += delta;
            
            if(pic > capacity){
                return false;
            }
        }
        return true;
    }
}