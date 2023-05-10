/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while(t-- > 0){
		    int n = scn.nextInt();
		    HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		    while(n-- > 0){
		        int temp = scn.nextInt();
		        hm.put(temp,hm.getOrDefault(temp,0)+1);
		    }
		    
		    for(int key : hm.keySet()){
		        if(hm.get(key) %2 != 0) {
		            System.out.println(key);
		            break;
		        } 
		    }
		}
	}
}
