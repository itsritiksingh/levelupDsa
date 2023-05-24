// 56. Merge Intervals
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a , b) -> {
            return a[0] - b[0];
        });
        
        ArrayList<int[]> list = new ArrayList<>();
        for(int[] interval: intervals){
            if(list.size() == 0){
                list.add(interval);
            } else {
                int[] last = list.get(list.size() - 1);
                if(interval[0] > last[1]){
                    list.add(interval);
                } else {
                    last[1] = Math.max(last[1], interval[1]);
                }
            }
        }
        
        return list.toArray(new int[list.size()][2]);
    }
}
// 452. Minimum Number of Arrows to Burst Balloons
class Solution {
    public int findMinArrowShots(int[][] points) {
        //due to constraint we cannot use this method
        // Arrays.sort(points,(a,b)-> {
        //     return a[0] - b[0];
        // });
        Arrays.sort(points, (a, b) -> {
            if(a[0] > b[0]){
                return 1;
            } else if(a[0] == b[0]){
                return 0;
            } else {
                return -1;
            }
        });
        
        ArrayList<int[]> res = new ArrayList();
        for(int i = 0;i < points.length;i++){
            if(i == 0) res.add(points[i]);
            else {
                int[] last = res.get(res.size() - 1);
                if(last[1] < points[i][0]){
                    res.add(points[i]);
                } else {
                    last[0] = Math.max(last[0],points[i][0]);
                    last[1] = Math.min(last[1],points[i][1]);
                }
            }
        }
        return res.size();
    }
}

// 57. Insert Interval
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0;
        while(i < intervals.length && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        int[] interval = newInterval;
        while(i < intervals.length && intervals[i][0] <= interval[1]){
            interval[0] = Math.min(intervals[i][0], interval[0]);
            interval[1] = Math.max(intervals[i][1], interval[1]);
            i++;
        }
        
        res.add(interval);
        while(i < intervals.length){
            res.add(intervals[i]);
            i++;
        }
        
        return res.toArray(new int[res.size()][2]);
    }
}

// 435. Non-overlapping Intervals
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->(a[1]-b[1]));

        int start=intervals[0][0];
        int end=intervals[0][1];

        int count=0;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<end){
                count++;
            }
            else{
                start=intervals[i][0];
                end=intervals[i][1];
            }
        }
        return count;

    }
}

class MyClass {
    public static void main(String []args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] intervals = new int[n];
        for(int i = 0;i < n;i++){
            intervals[i] = scn.nextInt();
        }
        
        int curr = intervals[0];
        int ans = intervals[0];

        for(int i = 1;i < intervals.length;i++){
            int d = intervals[i];
            if(d > intervals[i-1]) {
                curr += d;
            } else {
                curr = d;
            }

            ans = Math.max(ans,curr);
        }

        System.out.println(ans);

    }
}

class MyClass {
    int gstep;
    public static void main(String []args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[] intervals = new int[n];
        for(int i = 0;i < m;i++){
            intervals[i] = scn.nextInt();
        }
        
        HashSet<Integer> hset = new HashSet();
        gstep = Integer.MAX_VALUE;
        findSol(hset,intervals,n,0,m-1,1)
        System.out.println(gstep);
    }
    private void finSol(HashSet<Integer> hset,int[] intervals,int n,int start,int end,int step){
        if(hset.size() == n) gstep = Math.min(gstep,step);
        if(start > end) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        boolean found = false;
        if(hset.contains(intervals[start])) found = true;
        else hset.add(intervals[start]);
        finSol(hset,intervals,start+1,end,step+1);
        if(!found) hset.remove(intervals[start]);

        found = false;
        if(hset.contains(intervals[end])) found = true;
        else hset.add(intervals[end]);
        finSol(hset,intervals,start,end-1,step+1);
        if(!found) hset.remove(intervals[end]);
    }
}

// https://leetcode.com/problems/intervals-between-identical-elements/
// 2121. Intervals Between Identical Elements
class Solution {
    public long[] getDistances(int[] arr) {
        long[] result = new long[arr.length];

        /* Find indices of element element */
        Map<Integer, ArrayList<Integer>> occurrences = new HashMap<>();
        for (int i = 0; i < arr.length; ++i) {
            occurrences.putIfAbsent(arr[i], new ArrayList<>());
            occurrences.get(arr[i]).add(i);
        }

        /* Loop through the occurrences of each number in input array */
        for (ArrayList<Integer> indices : occurrences.values()) {
            int size = indices.size();

            /*
            To calculate the sum of elements on the left and right side of current occurrence position,
            maintain a sum of elements occurred before the current occurrence in prefixSum
            To get the sum of indices on the right of current occurrence, subtract current index and the prefixSum 
            from the sum
            */
            long sum = 0L;

            for (int index : indices) {
                sum += index;
            }

            long prefixSum = 0L;
            for (long i = 0L; i < size; i++) {
                int index =  indices.get((int) i);
                
                result[index] = index * i - prefixSum + 
                    (sum - prefixSum - index) - (size - i - 1) * index;
                prefixSum += index;
            }
        }
        return result;
    }
}