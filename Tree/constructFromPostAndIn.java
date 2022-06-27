/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 //105 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 // Construct Binary Tree from Preorder and Inorder Traversal
 class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer,Integer> hm = new HashMap();
        for(int i= 0;i< inorder.length;i++){
            hm.put(inorder[i],i);
        }
        
        return construct(0,preorder.length -1 , 0, inorder.length - 1,preorder,hm);
    }
    public TreeNode construct(int pre_lo,int pre_hi,int in_lo,int in_hi,int[] preorder,HashMap<Integer,Integer> hm){
        if(pre_lo > pre_hi || in_lo > pre_hi) return null;
        
        TreeNode node = new TreeNode();
        int idx = hm.get(preorder[pre_lo]);
        node.val = preorder[pre_lo];
        node.left = construct(pre_lo + 1,pre_lo + idx - in_lo,in_lo, in_lo + idx - 1,preorder,hm);
        node.right = construct(pre_lo + idx - in_lo+1,pre_hi,idx + 1, in_hi,preorder,hm);
        
        return node;
    }

     //106 https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     // Construct Binary Tree from postorder and Inorder Traversal
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode retVal = buildTreeRec(inorder, postorder, 0, postorder.length - 1, 0, inorder.length - 1);
        return retVal;
    }
    
    public TreeNode buildTreeRec(int[] inorder, int[] postorder, int postlo, int posthi, int inlo, int inhi){
        if(postlo > posthi || inlo > inhi){
            return null;
        }
        
        TreeNode node = new TreeNode();
        
        int idx = inlo;
        while(idx <= inhi){
            if(inorder[idx] == postorder[posthi]){
                break;
            }
            idx++;
        }
        
        int rhs = inhi - idx;
        
        node.val = postorder[posthi];
        node.left = buildTreeRec(inorder, postorder, postlo, posthi - rhs - 1, inlo, idx - 1);
        node.right = buildTreeRec(inorder, postorder, posthi - rhs, posthi - 1, idx + 1, inhi);
        
        return node;
    }

    public TreeNode buildTreeFromPrePost(int[] preorder, int[] postorder) {
        HashMap<Integer,Integer> hm = new HashMap();
        for(int i= 0;i< postorder.length;i++){
            hm.put(postorder[i],i);
        }
        
        return construct(0,preorder.length -1 , 0, postorder.length - 1,preorder,hm);
    }
    public TreeNode construct(int pre_lo,int pre_hi,int in_lo,int in_hi,int[] preorder,HashMap<Integer,Integer> hm){
        if(pre_lo > pre_hi || in_lo > pre_hi) return null;
        
        TreeNode node = new TreeNode();
        int idx = hm.get(preorder[pre_lo + 1]);
        node.val = preorder[pre_lo];
        node.left = construct(pre_lo + 1,pre_lo + idx ,in_lo, in_lo + idx,preorder,hm);
        node.right = construct(pre_lo + idx + 1,pre_hi,in_lo + idx + 1, in_hi,preorder,hm);
        
        return node;
    }
}

// 1008. Construct Binary Search Tree from Preorder Traversal
// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
class Solution {
    static int idx;
    public TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return construct(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public TreeNode construct(int[] preorder,int min,int max){
        if(idx >= preorder.length) return null;
        
        if(preorder[idx] > max || preorder[idx] < min ) return null;
        
        TreeNode root = new TreeNode();
        root.val = preorder[idx];
        
        idx++;
        
        root.left = construct(preorder,min, root.val);
        root.right = construct(preorder,root.val, max);
                
        return root;
    }
}

//889. Construct Binary Tree from Preorder and Postorder Traversal
//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<postorder.length; i++) {
            map.put(postorder[i], i);
        }
        
        TreeNode root = construct(preorder, postorder, map, 0, preorder.length - 1, 0, postorder.length - 1);
        return root;
    }
    
    public TreeNode construct(int[] pre, int[] post, HashMap<Integer, Integer> map, int prelo, int prehi, int postlo, int posthi) {
        if(prelo > prehi || postlo > posthi) {
            return null;
        }
        
        TreeNode node = new TreeNode(pre[prelo]);
        
        if(prelo + 1 <= prehi) {
            int index = map.get(pre[prelo + 1]);
            int lhs = index - postlo + 1;
            
            node.left = construct(pre, post, map, prelo + 1, prelo + lhs, postlo, postlo + lhs - 1);
            node.right = construct(pre, post, map, prelo + lhs + 1, prehi, postlo + lhs, posthi - 1);
        }
        
        return node;
    }
}

// Construct Binary Search Tree from PostOrder Traversal
class Solution {
    int index = 0;
    public TreeNode bstFromPostorder(int[] postorder) {
        index = postorder.length - 1;
        return construct(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode construct(int[] post, int min, int max){
        if(index == post.length){
            return null;
        } else if(post[index] > min && post[index] < max){
            TreeNode node = new TreeNode();
            
            node.val = post[index];
            index--;
            
            node.right = construct(post, node.val, max);
            node.left = construct(post, min, node.val);
            
            return node;
        } else {
            return null;
        }
    }
    
}