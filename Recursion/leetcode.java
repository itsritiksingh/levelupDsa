// https://leetcode.com/problems/different-ways-to-add-parentheses/description/
// 241. Different Ways to Add Parentheses

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '-' ||
                input.charAt(i) == '*' ||
                input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 :   part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }
}

//based on observation
// https://leetcode.com/problems/elimination-game/description/
// 390. Elimination Game

class Solution {
    public int lastRemaining(int n) {
        boolean left = true;
        int head = 1,step = 1, remain = n;
        while(remain > 1){
            //we will always shift head to left and if remain is odd
            // for eg : 6
            //itr0: 1 , 2 ,3 ,4, 5, 6
            // itr1: 2,4,6 here head will change to 4
            if(left || (remain & 1) == 1){
                head += step;
            }

            step *= 2;
            remain /= 2;
            left = !left;
        }

        return head;
    }
}

class Solution {
    int bobPoint = 0;
    int[] maxbob = new int[12];
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int[] bob = new int[12];
        calculate(aliceArrows, bob, 11, numArrows, 0);  //Start with max point that is 11
        return maxbob;
    }
    public void calculate(int[] alice, int[] bob, int index, int remainArr, int point) {
        if(index < 0 || remainArr <= 0) {
            //consider this test case for below if condition
            // aliceArrows = [3,2,28,1,7,1,16,7,3,13,3,5]
            // o/p [4,3,0,2,8,2,17,8,4,14,4,6]
            // eo/p [21,3,0,2,8,2,17,8,4,14,4,6]
            if(remainArr > 0)
                bob[0] += remainArr;
            if(point > bobPoint) { // Update the max points and result output
                bobPoint = point;
                maxbob = bob.clone();
            }
            return;
        }
        //part 1: assign 1 more arrow than alice
        if(remainArr >= alice[index]+1) {
            bob[index] = alice[index] + 1;
            calculate(alice, bob, index-1, remainArr-(alice[index]+1), point + index);
            bob[index] = 0;
        }
        //part 2: assign no arrow and move to next point
        calculate(alice, bob, index-1, remainArr, point);
        bob[index] = 0;
    }
}

// https://leetcode.com/problems/extra-characters-in-a-string/description/
// 2707. Extra Characters in a String
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        // Create set for dictionary for O(1) search in dictionary.
        for (String word : dictionary) set.add(word); 
        
        // Memoization array, with Integer.MAX_VALUE as default value.
        int[] dp = new int[s.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // start with index 0.
        return split(s, 0, dp, set);
    }
    
    private int split(String s, int index, int[] dp, Set<String> dictionary) {
        // if index reached at the end of the string there no extra character remaining.
        if (index >= s.length()) return 0;
        
        // if dictionary contains word for subtring from index to length.
        // There is no extra character.
        if (dictionary.contains(s.substring(index, s.length()))) return 0;
        
        // if dp[index] is not default value, 
        // we have already calculated result for this index.
        if (dp[index] != Integer.MAX_VALUE) return dp[index];
        
        // Default value can be all the characters of subtring.
        int min = s.length() - index;
        // check for all possible substring from current index
        for (int i = index + 1; i <= s.length(); i++) {
            // if this substring is in dictionary there are zero characters.
            // else all characters of this substring are remaining.
            int count = dictionary.contains(s.substring(index, i)) ? 0 : i - index;
            // check for second half after split.
            count += split(s, i, dp, dictionary);
            // updated the minimum value.
            min = Math.min(min, count);
        }
        
        // update dp[index] with current min.
        dp[index] = min;
        return min;
    }
}