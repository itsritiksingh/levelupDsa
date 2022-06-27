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
                // here with add current weight of all node traveeled so far
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
