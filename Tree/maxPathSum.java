class Solution {

  // it returns maximum root to node, but also calculates maximum node to node
  // 124  https://leetcode.com/problems/binary-tree-maximum-path-sum/ //MAX SUM N to N

  int max = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    max = Integer.MIN_VALUE;
    maxPathSum_RTN(root);
    return max;
  }

  public int maxPathSum_RTN(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int left = maxPathSum_RTN(node.left);
    int right = maxPathSum_RTN(node.right);

    int left_dash = Math.max(0, left);
    int right_dash = Math.max(0, right);

    int sumFromLeftToRight = left_dash + node.val + right_dash;
    if (sumFromLeftToRight > max) {
      max = sumFromLeftToRight;
    }

    return Math.max(left_dash, right_dash) + node.val;
  }
}

// max sum root to leaf
// https://practice.geeksforgeeks.org/problems/maximum-sum-leaf-to-root-path/1/
//#1
class Solution
{
    public static int maxPathSum(Node root)
    {
        if(root == null) return 0;
        int ls = maxPathSum(root.left) + root.data,rs = maxPathSum(root.right) + root.data;
        
        return Math.max(ls,rs);
    }
}
// #2 Travel and peek strategy
// ---------------------------------------------------------
class Solution
{
    static int max = Integer.MIN_VALUE;
    public static int maxPathSum(Node root)
    {
        max = Integer.MIN_VALUE;
        travel(root, 0);
        return max;
    }
    
    public static void travel(Node node, int sum){
        if(node == null){
            return;
        }
        
        if(node.left == null && node.right == null){
            sum = sum + node.data;
            if(sum > max){
                max = sum;
            }
            return;
        }
        
        travel(node.left, sum + node.data);
        travel(node.right, sum + node.data);
    }
}


//max sum leaf to leaf
// https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
class Solution
{
    int max; //  maximum l 2 l
    int maxPathSum(Node root)
    { 
        max = Integer.MIN_VALUE;
        helper(root);
        return max;
    } 
    
    // it returns maximum root to leaf
    int helper(Node node){
      if(node.left != null && node.right != null){
          int left = helper(node.left);
          int right = helper(node.right);

          max = Math.max(max, left + node.data + right);

          return Math.max(left, right) + node.data;
      } else if(node.left != null){
          int left = helper(node.left);
          return node.data + left;
      } else if(node.right != null){
          int right = helper(node.right);
          return node.data + right;
      } else {
          return node.data;
      }
    }
}
// gfg consider single child root as leaf too
// L2L_GFG
class Solution
{
    int max; //  maximum l 2 l
    int maxPathSum(Node root)
    { 
        max = Integer.MIN_VALUE;


        if(root.left != null && root.right != null){
            helper(root);
            return max;
        } else {
            // because this question on GFG considers root with one child as  a leaf
            int val = helper(root);
            return Math.max(val, max);
        }
    } 
    
    // it returns maximum root to leaf
    int helper(Node node){
      if(node.left != null && node.right != null){
          int left = helper(node.left);
          int right = helper(node.right);

          max = Math.max(max, left + node.data + right);

          return Math.max(left, right) + node.data;
      } else if(node.left != null){
          int left = helper(node.left);
          return node.data + left;
      } else if(node.right != null){
          int right = helper(node.right);
          return node.data + right;
      } else {
          return node.data;
      }
    }
}