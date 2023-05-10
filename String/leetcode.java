// https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
// 28. Find the Index of the First Occurrence in a String
// refer this image: https://media.geeksforgeeks.org/wp-content/cdn-uploads/20200520123449/KMP-Algorithm-Page-1.png
class Solution {
    public int strStr(String txt, String pat) {
        int[] lps = getLPS(pat);
        for(int d: lps){
            System.out.print(d+" ");
        }
        
        int i = 0;
        int j = 0;
        while(i < txt.length()){
            if(txt.charAt(i) == pat.charAt(j)){
                i++;
                j++;
                
                if(j == pat.length()){
                    return i - j;
                }
            } else if (j == 0){
                i++;
            } else {
                j = lps[j - 1];
            }
        }
        return -1;
    }
    
    int[] getLPS(String pat){
        int[] lps = new int[pat.length()];
        
        int len = 0;
        int i = 1;
        while(i < pat.length()){
            if(pat.charAt(i) == pat.charAt(len)){
                lps[i] = len + 1;
                len++;
                i++;
            } else if(len == 0){
                lps[i] = 0;
                len = 0;
                i++;
            } else {
                len = lps[len - 1];
            }
        }
        return lps; 
    }
}