// https://leetcode.com/problems/next-greater-element-i/description/
// 496. Next Greater Element I
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack();
        int temp = 0;
        
         for (int i = nums2.length - 1; i >= 0; i--) {
            temp = nums2[i];
            while (st.size() > 0 && nums2[i] >= st.peek()) {
                st.pop();
            }
            if (st.size() > 0) {
                nums2[i] = st.peek();

            } else {
                nums2[i] = -1;
            }

            for (int j = 0; j < nums1.length; j++) {
                if (nums1[j] == temp) {
                    nums1[j] = nums2[i];
                }
            }
            st.push(temp);
        }
        return nums1;
    }
}
// https://leetcode.com/problems/next-greater-element-ii/description/
// 503. Next Greater Element II
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack();
        boolean flag = true;
        for(int i = nums.length -1;i>=0;i--)
        {
            int temp = nums[i];
            flag = true;
            while(st.size()>0 && nums[i] >= st.peek())
            {
                st.pop();
            }
            if(st.size()>0)
            {
                nums[i] = st.peek();
            }
            else {
                for(int j = 0;j<i;j++)
                {
                    if(nums[j] > nums[i])
                    {
                        nums[i] = nums[j];
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    nums[i] = -1;
                }
            }
            st.push(temp); 
        }
        return nums;
    }
}
// https://leetcode.com/problems/next-greater-node-in-linked-list/description/
// 1019. Next Greater Node In Linked List
class Solution {
    int len;
    public int[] nextLargerNodes(ListNode head) {
        len = 0;
        ListNode nHead = reverseList(head);
        int res[] = new int[len];
        // System.out.println(len);
        res[--len] = 0;
        Stack<Integer> st = new Stack();
        ListNode curr = nHead.next;
        st.push(nHead.val);
        while(len >= 0 && curr != null){
            while(st.size() > 0 && st.peek() <= curr.val) st.pop();

            if(st.size() > 0){
                res[--len] = st.peek();
            }else {
                res[--len] = 0;
            }

            st.push(curr.val);
            curr = curr.next;
        }
        return res;
    }
    private ListNode reverseList(ListNode head){
        ListNode prev = null, curr = head;
        while(curr != null){
            len++;
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }

        return prev;
    }
}
// https://leetcode.com/problems/longest-valid-parentheses/description/
// 32. Longest Valid Parentheses
public class Solution {

    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

// https://leetcode.com/problems/simplify-path/description/
// 71. Simplify Path
class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack();
        String[] strarr = path.split("/");
        for(String s: strarr){
            //s.equals(" ") not working
            if(s.trim().isEmpty()) continue;
            else if(s.equals(".")) continue;
            else if(s.equals("..")) {if(st.size() > 0) st.pop();}
            else st.push(s);
        }
        StringBuilder sol = new StringBuilder();
        while(st.size() > 0){
            sol.insert(0,st.pop());
            sol.insert(0,"/");
        }

        if(sol.length() == 0) sol.append('/');
        return sol.toString();
    }
}

// 1673. Find the Most Competitive Subsequence
// https://leetcode.com/problems/find-the-most-competitive-subsequence/
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[k];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[i] < nums[stack.peek()] && nums.length - i + stack.size() > k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(i);
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = nums[stack.pop()];
        }
        return result;
    }
}

// 155. Min Stack
// https://leetcode.com/problems/min-stack/


// https://leetcode.com/problems/asteroid-collision/
// 735. Asteroid Collision
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack();
        for(int d: asteroids){
            if(d > 0) st.push(d);
            else {
                // d < 0
                if(st.empty() || st.peek() < 0) st.push(d);
                else {
                    int temp = st.pop();
                    while(temp > 0 && (temp < Math.abs(d) && !st.empty())){
                        temp = st.pop();
                    }

                    if(temp < 0){
                        st.push(temp); st.push(d);
                    } 
                    //temp > 0 and d == temp
                    else if(temp == Math.abs(d)) continue;
                    // temp > 0 and temp < abs(d)
                    else if(temp < Math.abs(d) && st.empty()) st.push(d);
                    // temp > 0 and temp > abs(d)
                    else st.push(temp);
                }
            }
        }

        int[] ans = new int[st.size()];
        int idx = ans.length-1;
        while(idx >= 0){
            ans[idx--] = st.pop();
        }
        return ans;
    }
}
// 239. Sliding Window Maximum
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] nge = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        
        st.push(nums.length - 1);
        nge[nums.length - 1] = nums.length;
        
        for(int i = nums.length - 2; i >= 0; i--){
            int val = nums[i];
            while(st.size() > 0 && val >= nums[st.peek()]){
                st.pop();
            }
            
            if(st.size() == 0){
                nge[i] = nums.length;
            } else {
                nge[i] = st.peek();
            }
            
            st.push(i);
        }
        
        
        int[] res = new int[nums.length - k + 1];
        int j = 0;
        for(int i = 0; i < res.length; i++){
            if(j < i){
                j = i;
            }
            
            while(nge[j] <= i + k - 1){
                j = nge[j];
            }
            
            res[i] = nums[j];
        }
     
        return res;
    }
}