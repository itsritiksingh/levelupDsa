import java.io.*;
import java.util.*;

public class Main {

  public static class Node {

    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {

    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  //using stack
  public static void levelOrder(Node node) {
    Stack<Node> st = new Stack();
    Stack<Node> tpst = new Stack();
    st.push(node);

    while (st.size() > 0) {
      Node temp = st.pop();

      if (temp == null) continue;

      System.out.print(temp.data + " ");

      tpst.push(temp.left);
      tpst.push(temp.right);

      if (st.size() == 0) {
        while (tpst.size() > 0) {
          st.push(tpst.pop());
        }
        System.out.println();
      }
    }
  }
 
  //107 https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
  //LOLW using null value
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if (root == null) return new ArrayList();
    Queue<TreeNode> ans = new LinkedList();
    Stack<TreeNode> st = new Stack();
    ans.add(root);
    ans.add(null);

    while (ans.size() > 0) {
      TreeNode temp = ans.remove();

      if (temp != null) {
        st.push(temp);

        if (temp.right != null) {
          ans.add(temp.right);
        }
        if (temp.left != null) {
          ans.add(temp.left);
        }
      } else {
        st.push(temp);
        if (ans.size() > 0) ans.add(temp);
      }
    }

    List<List<Integer>> ret = new ArrayList();
    List<Integer> tempAns = new ArrayList();
    while (st.size() > 0) {
      TreeNode node = st.pop();
      // System.out.println(node);
      if (node != null) {
        tempAns.add(node.val);
      } else {
        if (tempAns.size() > 0) ret.add(tempAns);
        tempAns = new ArrayList();
      }
    }
    ret.add(tempAns);
    return ret;
  }

  //using LOLW with size var of queue
  ArrayList<Integer> leftView(Node root) {
    if (root == null) return new ArrayList<>();
    ArrayDeque<Node> qu = new ArrayDeque<>();
    ArrayList<Integer> ans = new ArrayList<>();
    qu.add(root);
    ans.add(root.data);

    while (qu.size() > 0) {
      int len = qu.size();
      boolean flag = false;
      while (len-- > 0) {
        Node temp = qu.remove();

        if (temp.left != null) qu.add(temp.left);
        if (temp.right != null) qu.add(temp.right);
      }
      if (qu.peek() != null) ans.add(qu.peek().data);
    }
    return ans;
  }

  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) return new ArrayList<>();
    ArrayDeque<TreeNode> qu = new ArrayDeque<>();
    ArrayList<Integer> ans = new ArrayList<>();
    qu.add(root);
    ans.add(root.val);

    while (qu.size() > 0) {
      int len = qu.size();
      boolean flag = false;
      while (len-- > 0) {
        TreeNode temp = qu.remove();

        if (temp.right != null) qu.add(temp.right);
        if (temp.left != null) qu.add(temp.left);
      }
      if (qu.peek() != null) ans.add(qu.peek().val);
    }

    return ans;
  }

  //top view
  class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    static class Pair{
        Node node;
        int horiz;
    }
    static ArrayList<Integer> topView(Node root)
    {
        ArrayList<Integer> retVal = new ArrayList<>();
        HashMap<Integer,Node> map = new HashMap<>();
        
        int lv = 0, rv = 0;
        
        Queue<Pair> queue = new LinkedList<>();
        Pair p = new Pair();
        p.node = root;
        p.horiz = 0;
        
        queue.add(p);
        
        while(queue.size() > 0){
            Pair temp = queue.remove();
            
            if(temp.horiz < lv) {
                lv = temp.horiz;
            }
            
            if(temp.horiz > rv){
                rv = temp.horiz;
            }
            
            if(map.containsKey(temp.horiz) == false){
                map.put(temp.horiz, temp.node);
            }
            
            if(temp.node.left != null){
                Pair lp = new Pair();
                lp.node = temp.node.left;
                lp.horiz = temp.horiz - 1;
                
                queue.add(lp);
            }
            
            if(temp.node.right != null){
                Pair rp = new Pair();
                rp.node = temp.node.right;
                rp.horiz = temp.horiz + 1;
                
                queue.add(rp);
            }
        }
        
        for(int i = lv; i <= rv;i++){
            retVal.add(map.get(i).data);
        }
        
        return retVal;
    }
}

  //94 inorder traversal using morris algo(no recursion/stack)
  // https://leetcode.com/problems/binary-tree-inorder-traversal/

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> retVal = new ArrayList();
    TreeNode curr = root;

    while (curr != null) {
      if (curr.left == null) {
        retVal.add(curr.val);
        curr = curr.right;
      } else {
        TreeNode iop = curr.left;
        while (iop.right != null && iop.right != curr) {
          iop = iop.right;
        }

        if (iop.right == null) {
          iop.right = curr;
          curr = curr.left;
        } else {
          iop.right = null;
          retVal.add(curr.val);
          curr = curr.right;
        }
      }
    }

    return retVal;
  }

  //144 https://leetcode.com/problems/binary-tree-preorder-traversal/
  // morris traversal algo
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> retVal = new ArrayList();
    TreeNode curr = root;

    while (curr != null) {
      if (curr.left == null) {
        retVal.add(curr.val);
        curr = curr.right;
      } else {
        TreeNode iop = curr.left;
        while (iop.right != null && iop.right != curr) {
          iop = iop.right;
        }
        if (iop.right == null) {
          iop.right = curr;
          retVal.add(curr.val);
          curr = curr.left;
        } else {
          iop.right = null;
          curr = curr.right;
        }
      }
    }

    return retVal;
  }

  //99 https://leetcode.com/problems/recover-binary-search-tree/
  public void recoverTree(TreeNode root) {
    TreeNode curr = root, prev = null, n1 = null, n2 = null;
    while (curr != null) {
      if (curr.left == null) {
        if (prev != null) {
          if (curr.val < prev.val) {
            if (n1 == null) n1 = prev;
            n2 = curr;
          }
        }
        prev = curr;
        curr = curr.right;
      } else {
        TreeNode iop = curr.left;
        while (iop.right != null && iop.right != curr) {
          iop = iop.right;
        }

        if (iop.right == null) {
          iop.right = curr;
          curr = curr.left;
        } else {
          if (prev != null) {
            if (curr.val < prev.val) {
              if (n1 == null) n1 = prev;
              n2 = curr;
            }
          }
          prev = curr;
          iop.right = null;
          curr = curr.right;
        }
      }
    }
    int temp = n1.val;
    n1.val = n2.val;
    n2.val = temp;
  }

  // https://nados.io/question/find-and-nodetorootpath-in-binary-tree
  public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
    if (node == null) return new ArrayList();

    ArrayList al;

    if (node.data == data) {
      al = new ArrayList();
      al.add(node.data);
      return al;
    }

    al = nodeToRootPath(node.left, data);

    if (al.size() != 0) {
      al.add(node.data);
      return al;
    }

    al = nodeToRootPath(node.right, data);
    if (al.size() != 0) {
      al.add(node.data);
      return al;
    }

    return al;
  }

  //  https://nados.io/question/print-nodes-k-distance-away
  public static void printKNodesFar(Node node, int data, int k) {
    ArrayList<Node> path = nodeToRootPath(node, data);
    printKLevelsDown(path.get(0), k);
    for (int i = 1; i < path.size(); i++) {
      Node prev = path.get(i - 1);
      Node curr = path.get(i);

      if (i < k) {
        if (prev == curr.left) {
          printKLevelsDown(curr.right, k - i - 1);
        } else {
          printKLevelsDown(curr.left, k - i - 1);
        }
      } else if (i == k) {
        System.out.println(curr.data);
      } else {
        break;
      }
    }
  }
  //dependency to previous ques. https://nados.io/question/print-k-levels-down
  public static void printKLevelsDown(Node node, int k) {
    if (node == null || k < 0) {
      return;
    }

    if (k == 0) {
      System.out.println(node.data);
      return;
    }

    printKLevelsDown(node.left, k - 1);
    printKLevelsDown(node.right, k - 1);
  }

  //450. Delete Node in a BST
  // https://leetcode.com/problems/delete-node-in-a-bst/

  class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(root.val > key){
            root.left = deleteNode(root.left,key);
        }else if(root.val < key) root.right = deleteNode(root.right,key);
        else {
            // root.val == key 
            // one or no child 
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            }
            //you can also replace it with inorder predessor
            root.val = findIOS(root.right);
            root.right = deleteNode(root.right,root.val);
        }
        
        
        return root;
    }
    public int findIOS(TreeNode node){
        int min = node.val;
        while(node != null) {
            min = node.val;
            node = node.left;

        }
        return min;
    }
}

//lca in binary tree
//https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1/
class Solution
{
    boolean f1 = false;
    boolean f2 = false;
	Node lca(Node root, int n1,int n2)
	{
		Node lca = helper( root, n1,  n2);
		if(f1 && f2) return lca;
		else return null;
	}
	Node helper(Node node, int n1 , int n2){
	    if(node == null) return null;
	    
	    Node left = helper(node.left,n1,n2);
	    Node right = helper(node.right,n1,n2);
	    
	    if(node.data == n1){
	        f1 = true;
	        return node;
	    }else if(node.data == n2){
	        f2 = true;
	        return node;
	    }else if(left != null && right != null){
	        return node;
	    }else if(left != null){
	        return left;
	    }else if(right != null){
	        return right;
	    }else return null;
	}
}

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);
    levelOrder(root);
  }
}
