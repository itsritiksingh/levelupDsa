import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args){
        
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        String pi = "314159265358979323846264338327";
        while(t-- > 0){
            String n = scn.nextLine();
 
            int idx = 0;
            while(idx < n.length()){
                if(n.charAt(idx) == pi.charAt(idx)){
                    idx++;
                }
                else break;
            }
            System.out.println(idx);
        }
    }
}