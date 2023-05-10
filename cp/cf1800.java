import java.util.Scanner;
//is it a cat?
public class Main
{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int t = Integer.parseInt(scn.nextLine());
        char test[][] = new char[][]{{'m','M'},{'e','E'},{'o','O'},{'w','W'}};
        while(t-- > 0){
            int len = Integer.parseInt(scn.nextLine());
            String n = scn.nextLine();
            int idx = 0,curr = 0;
            boolean flag = false;
            for(int i = 0;i < len;i++){
                if(idx == test.length) {
                    flag = true;
                    break;
                }
                

                if(idx != test.length && n.charAt(i) == test[idx][0] || n.charAt(i) == test[idx][1]){curr++; continue;}
                else if(idx + 1 != test.length && curr > 0 &&( n.charAt(i) == test[idx+1][0] || n.charAt(i) == test[idx+1][1])){
                    idx++;
                    curr++;
                } else {
                    flag = true;
                    break;
                    
                }
            }
            
            if(flag || idx < test.length -1) System.out.println("NO");
            else System.out.println("YES");
        }
	}
}

// Count the Number of Pairs

public class Main
{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int t = Integer.parseInt(scn.nextLine());
        while(t-- > 0){
            String[] ip = scn.nextLine().trim().split(" ");
            int len = Integer.parseInt(ip[0]);
            int k = Integer.parseInt(ip[1]);
            String str = scn.nextLine();
            int[] ch = new int[52];
            int ans=  0;
            for(int i = 0;i < len;i++){
                if(str.charAt(i) >= 97){
                    ch[str.charAt(i) - 'A' - 6]++;
                } else ch[str.charAt(i) - 'A']++;
            }
            for(int i = 0;i < 52;i++){
                if(i < 26){
                    int upperCount = Math.min(ch[i+26],ch[i]);
                    ch[i+26] -= upperCount;
                    ans += upperCount;
                    if(ch[i] > 1) {
                        int diff = Math.min(k,(ch[i] - upperCount)/2);
                        ans += diff;
                        k -= diff; 
                    }
                } else {
                    int diff = Math.min(k,ch[i]/2);
                    ans += diff;
                    k -= diff; 
                }
            }
            
           System.out.println(ans);
        }
	}
}

// C1. Powering the Hero (easy version)
public class Main{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int t = Integer.parseInt(scn.nextLine());
        while(t-- > 0){
            int ans= 0;
            PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
            int len = Integer.parseInt(scn.nextLine());
            for(int i = 0;i < len;i++){
                int e = scn.nextInt();
                if(e > 0){
                    pq.add(e);
                }else ans += pq.poll();
            }
            System.out.println(ans);
        }
    }
}

// D. Remove Two Letters
public class Main{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int t = Integer.parseInt(scn.nextLine());
        while(t-- > 0){
            int ans= 0;
            HashSet<String> set = new HashSet();
            int len = Integer.parseInt(scn.nextLine());
            String str = scn.nextLine();
            HashSet<String> set = new HashSet();
            for(int i = 0;i < len-1;i++){
                String temp = str.substring(0,i) + str.substring(i+2);
                System.out.println(temp);
                set.add(temp);
            }
            System.out.println(temp.size());
        }
    }
}
// E1. Unforgivable Curse (easy version)

public class Main{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int t = Integer.parseInt(scn.nextLine());
        while(t-- > 0){
            int ans= 0;
            HashSet<String> set = new HashSet();
            int len = Integer.parseInt(scn.nextLine());
            String str = scn.nextLine();
            HashSet<String> set = new HashSet();
            for(int i = 0;i < len-1;i++){
                String temp = str.substring(0,i) + str.substring(i+2);
                System.out.println(temp);
                set.add(temp);
            }
            System.out.println(temp.size());
        }
    }
}