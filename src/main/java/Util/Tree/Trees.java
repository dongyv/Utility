/**
 * Copyright (c) <a href="http://git.oschina.net/eason_zhangfei">zhangfei_eason</a>.
 */
package com.dql.cla.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author http://git.oschina.net/xiachenhang
 * @date 2018-02-23
 */
public class Trees {
	class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //重建二叉树（用前序和中序）
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reBuildBinaryTree(pre,0,pre.length-1, in, 0, in.length-1);
    }
    //递归构建二叉树（用前序和中序）
    private TreeNode reBuildBinaryTree(int [] pre,int pStart,int pEnd, int [] in, int iStart,int iEnd){
        //树成立的条件
        if(pStart > pEnd || iStart > iEnd)
            return null;
        //初始化树的根节点
        TreeNode rootNode = new TreeNode(pre[pStart]);
        //分割树的前序、中序遍历序列
        for(int i = 0; i < in.length; i++){
            if(in[i] == pre[pStart]){
                rootNode.left = reBuildBinaryTree( pre, pStart+1, pStart+i-iStart, in, iStart, i-1);
                rootNode.right = reBuildBinaryTree( pre, pStart+i-iStart+1,pEnd, in, i+1,iEnd);
                break;
            }
        }
        return rootNode;
    }
    public void preOrderRecursively(TreeNode root){
        if(root != null){
            System.out.print(root.val + " ");
            preOrderRecursively(root.left);
            preOrderRecursively(root.right);
        }
    }
    public void preOrder(TreeNode root){
        //使用栈保存已经遍历过的节点（所有的点在进栈的时候打印）
        Stack<TreeNode> stack = new Stack();
        if (root == null)
            System.out.println("树为空");
        else {
            while(root != null || !stack.empty()){
                while(root != null){
                    System.out.print(root.val + " ");
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                root = root.right;
            }
        }
    }
    public void inOrderRecursively(TreeNode root){
        if(root != null){
            inOrderRecursively(root.left);
            System.out.print(root.val + " ");
            inOrderRecursively(root.right);
        }
    }
    public void inOrder(TreeNode root){
        //使用栈保存已经遍历过的节点（所有的点在出栈的时候打印）
        if (root == null)
            System.out.println("树为空");
        else {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while(root != null || !stack.empty()){
                while (root != null){
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }

        }
    }
    public void postOrderRecursively(TreeNode root){
        if(root != null){
            postOrderRecursively(root.left);
            postOrderRecursively(root.right);
            System.out.print(root.val + " ");
        }
    }
    public void postOrder(TreeNode root){
        //使用栈保存已经遍历过的节点（只有第二次访问的时候才能出栈，并且打印）
        if (root == null){
            System.out.println("树为空");
        }else {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            HashMap<TreeNode,Boolean> tag = new HashMap<TreeNode,Boolean>();
            while(root != null || !stack.empty()){
                while(root != null){
                    stack.push(root);
                    tag.put(root,false); //false表示第一次入栈，并访问
                    root = root.left;
                }
                if (!stack.empty() && !tag.get(stack.peek())){
                    //栈顶元素第一次入栈，转向右节点,并置为第二次访问（不出栈）
                    root = stack.peek();
                    tag.put(root,true);//true表示第二次访问
                    root = root.right;
                }else {
                    //需要加上stack不为空（循环中是出栈操作）
                    while(!stack.empty() && tag.get(stack.peek())){
                        //所有的访问两次的元素出栈
                        root = stack.pop();
                        System.out.print(root.val + " ");
                    }
                    root = null;//root置为null,向右支树走
                }
            }
        }
    }
    //层次遍历
    public static void levelOrder(TreeNode root){

        if(root == null)
            System.out.println("树为空");
        else {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            while(!queue.isEmpty()){
                root = queue.poll();
                System.out.print(root.val + " ");
                if (root.left != null)
                    queue.add(root.left);
                if(root.right != null)
                    queue.add(root.right);

            }
        }
    }
    //求二叉树的深度
    public int hightTree(TreeNode root){
        if(root == null)
            return 0;
        int left = hightTree(root.left);
        int right = hightTree(root.right);
        return (left > right ? left : right) + 1;
    }
    //求二叉树的个数
    public int countTree(TreeNode root){
        if(root == null)
            return 0;
        return countTree(root.left) + countTree(root.right) + 1;
    }
    public static void main(String[] args) {
        Trees a = new Trees();
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode root = a.reConstructBinaryTree(pre,in);

        System.out.println("前序递归和非递归");
        a.preOrderRecursively(root);
        System.out.println("");
        a.preOrder(root);
        System.out.println("");

        System.out.println("中序递归和非递归");
        a.inOrderRecursively(root);
        System.out.println("");
        a.inOrder(root);
        System.out.println("");

        System.out.println("后序递归和非递归");
        a.postOrderRecursively(root);
        System.out.println("");
        a.postOrder(root);

        System.out.println("");
        System.out.println("层次遍历");
        a.levelOrder(root);

        System.out.println("");
        System.out.println("二叉树的深度");
        System.out.println(a.hightTree(root));
        
        System.out.println("二叉树的节点个数");
        System.out.println(a.countTree(root));
    }
}
