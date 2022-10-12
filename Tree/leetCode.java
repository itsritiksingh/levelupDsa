// 113. //https://leetcode.com/problems/path-sum-ii/
// return all root-to-leaf paths where the sum of the node values in the path equals targetSum

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return findPath(root,targetSum);
    }
    
    public List<List<Integer>> findPath(TreeNode root,int tar){
        if(root == null) return new ArrayList();
        
        List<List<Integer>> ans = new ArrayList();
        if(tar - root.val == 0 && root.left == null && root.right == null){
            List<Integer> li  = new ArrayList();
            li.add(root.val);
            ans.add(li);
            return ans;
        }
        
        List<List<Integer>> temp = pathSum(root.left,tar - root.val);
        if(temp != null && temp.size() > 0){
            for(List<Integer> d : temp) d.add(0,root.val);
            ans = temp;
        }
        
        temp = pathSum(root.right,tar - root.val);
        if(temp != null && temp.size() > 0){
             for(List<Integer> d : temp) d.add(0,root.val);
             if(ans == null) ans = temp;
             else {
                 for(List<Integer> d : temp) ans.add(d);
             }
        }
        
        return ans;
    }
}

// 129. Sum Root to Leaf Numbers
//  https://leetcode.com/problems/sum-root-to-leaf-numbers/

class Solution {
    public int sumNumbers(TreeNode root) {
        return findSum(root,0);
    }
    
    public int findSum(TreeNode root,int curr){
        if(root == null) return 0;
         
        if(root.left == null && root.right == null) return curr * 10 + root.val;
        
        curr = curr * 10 + root.val;
        return findSum(root.left,curr) + findSum(root.right,curr) ;
    }
}

// 297. Serialize and Deserialize Binary Tree
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }
    
    void helper(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append(".");
            sb.append(" ");
            return;
        }
        
        sb.append(node.val);
        sb.append(" ");
        
        helper(node.left, sb);
        helper(node.right, sb);
    }
    
    class Pair {
        TreeNode node;
        int state = 0;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        
        String[] darray = data.split(" ");
        
        Stack<Pair> st = new Stack<>();
        Pair rootp = new Pair();
        rootp.node = new TreeNode(Integer.parseInt(darray[0]));
        st.push(rootp);
        
        int idx = 0;
        while(st.size() > 0){
            Pair pair = st.peek();
            
            if(pair.state == 0){
                idx++;
                pair.state++;
                
                if(darray[idx].equals(".") == false){
                    Pair leftp = new Pair();
                    leftp.node = new TreeNode(Integer.parseInt(darray[idx]));
                    pair.node.left = leftp.node;
                    
                    st.push(leftp);
                }
            } else if(pair.state == 1){
                idx++;
                pair.state++;
                
                if(darray[idx].equals(".") == false){
                    Pair rightp = new Pair();
                    rightp.node = new TreeNode(Integer.parseInt(darray[idx]));
                    pair.node.right = rightp.node;
                    
                    st.push(rightp);
                }                
            } else {
                st.pop();
            }
        }
        
        return rootp.node;
    }
}
//687. Longest Univalue Path
// https://leetcode.com/problems/longest-univalue-path/

class Solution {
    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }
    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
//230. Kth Smallest Element in a BST
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// approach 1:
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // if(root == null) return 0;
        int lc = count(root.left);
        if(lc + 1 == k) return root.val;
        else if(lc >= k) return kthSmallest(root.left,k);
        else return kthSmallest(root.right,k - lc - 1);
    
    }
    public int count(TreeNode root){
    
        if(root == null) return 0;
        
        int lc = count(root.left),rc = count(root.right);
        
        return lc + rc + 1;
    
    }
}
//approch 2 using morris:
class Solution {
    // Return the Kth smallest element in the given BST
    public int KthSmallestElement(Node root, int K) {
        // Write your code here
        Node curr = root;
        int count = 0;
        
        while(curr != null){
            if(curr.left == null){
                count++;
                if(count == K){
                    return curr.data;
                }
                
                curr = curr.right;
            } else {
                Node iop = curr.left;
                while(iop.right != null && iop.right != curr){
                    iop = iop.right;
                }
                
                if(iop.right == null){
                    iop.right = curr; // making the thread
                    curr = curr.left;
                } else {
                    iop.right = null;
                    
                    count++;
                    if(count == K){
                        return curr.data;
                    }
                    curr = curr.right;
                }
            }
        }
        
        return -1;
    }

//1028. Recover a Tree From Preorder Traversal
// https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
class Solution {
    public TreeNode recoverFromPreorder(String traversal) {
        return helper(traversal, 0);
    }
    int i = 0;
    public TreeNode helper(String str,int depth){
        
            int d = 0;
            while(i + d < str.length() && str.charAt(i+d) == '-'){
                d++;
            }
            
            if(d != depth) return null;
            
            int nd = 0;
            while(i + d + nd < str.length() && str.charAt(i + d + nd) != '-'){
                nd++;
            }
            
            int val = Integer.parseInt(str.substring(i+d,i+d+nd));
            i = i + d + nd;
            
            TreeNode node = new TreeNode(val);
            node.left = helper(str,depth + 1);
            node.right = helper(str,depth + 1);
            
            return node;
    }
}
//103. Binary Tree Zigzag Level Order Traversal
//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode node) {
        if(node == null) return new ArrayList();
        List<List<Integer>> ans = new ArrayList();
        List<Integer> temp = new ArrayList();
      Stack<TreeNode> stack = new Stack<>();
      stack.add(node);

      Stack<TreeNode> cstack = new Stack<>();
      int level = 0;

      while(stack.size() > 0){
        node = stack.pop();
        temp.add(node.val);

        if(level % 2 == 0){
            if(node.left != null) cstack.push(node.left);
            if(node.right != null) cstack.push(node.right);
        } else {
          if(node.right != null) cstack.push(node.right);
          if(node.left != null) cstack.push(node.left);
        }

        if(stack.size() == 0){
          stack = cstack;
          cstack = new Stack<>();
          level++;
          ans.add(temp);
          temp = new ArrayList();   
        }
      }
        return ans;
    }
}

// 662. Maximum Width of Binary Tree
// https://leetcode.com/problems/maximum-width-of-binary-tree/

class Solution {
    class Pair {
        long min;
        long max;
    }
    
    long maxW = 0;
    public int widthOfBinaryTree(TreeNode root) {
        HashMap<Integer, Pair> map = new HashMap<>();
        maxW = 0;
        helper(root, 1, 1, map);
        return (int)maxW;
    }
    
    public void helper(TreeNode node, int level, long idx, HashMap<Integer, Pair> map){
        if(node == null){
            return;
        }
        
        helper(node.left, level + 1, 2 * idx, map);
        helper(node.right, level + 1, 2 * idx + 1, map);
        
        Pair p = null;
        
        if(map.containsKey(level)){
            p = map.get(level);
            p.max = idx;
        } else {
            p = new Pair();
            p.min = idx;
            p.max = idx;
            map.put(level, p);
        }
        
        long width = p.max - p.min + 1;
        if(width > maxW){
            maxW = width;
        }
    }
}

// https://binarysearch.com/problems/Longest-Even-Sum-Path
class Solution {
    class Pair {
        int op = 0;
        int ep = 0;
    }

    int mep = 0;

    public int solve(Tree root) {
        mep = 0;
        helper(root);
        return mep;
    }

    public Pair helper(Tree node){
        if(node == null){
            return new Pair();
        }

        Pair lp = helper(node.left);
        Pair rp = helper(node.right);

        int f1 = Math.max(lp.ep, rp.ep);
        int f2 = Math.max(lp.op, rp.op);

        Pair mp = new Pair();
        if(node.val % 2 == 0){
            mp.ep = f1 + 1;
            mp.op = f2 == 0? 0: f2 + 1;

            int f3 = lp.ep + rp.ep;
            int f4 = lp.op + rp.op;
            int f7 = Math.max(f3, f4);
            mep = Math.max(mep, f7 + 1);
        } else {
            mp.ep = f2 == 0? 0: f2 + 1;
            mp.op = f1 + 1;

            int f5 = lp.ep + rp.op;
            int f6 = lp.op + rp.ep;
            int f8 = Math.max(f5, f6);
            mep = Math.max(mep, f8 == 0? 0: f8 + 1);
        }

        return mp;
    }
}