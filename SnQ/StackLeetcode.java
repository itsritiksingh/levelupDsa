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