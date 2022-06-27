// 114. Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

class Solution {
        TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        
        flatten(root.right);
        flatten(root.left);
        
        root.left = null;
        root.right = prev;
        prev = root;
    }
}

// https://leetcode.com/problems/convert-bst-to-greater-tree/
// 538. Convert BST to Greater Tree
class Solution {
    int totalSum;
    public TreeNode convertBST(TreeNode root) {
        totalSum = 0;
        helper(root);
        return root;
    }
    public void helper(TreeNode root){
        if(root == null) return;
        
        helper(root.right);
        
        root.val += totalSum;
        totalSum = root.val;
        helper(root.left);
    }
}