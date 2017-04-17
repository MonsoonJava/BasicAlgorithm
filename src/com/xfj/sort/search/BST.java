package com.xfj.sort.search;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by asus on 2017/4/17.
 */
public class BST<Key extends Comparable,Value> {

    private Node root;

    private int count;

    public BST(){
        root = null;
        count = 0;
    }
    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0 ;
    }

    public void insert(Key key,Value value){
        root = insert(root,key,value);
    }

    private Node  insert(Node node,Key key,Value value){

        //递归返回条件
        if(node == null){
            node = new Node(key,value);
            count++;
            return node;
        }
        if(node.k.compareTo(key) == 0){
            node.v = value;
            return node;
        }else if(node.k.compareTo(key) > 0){
            node.left  = insert(node.left,key,value);
        }else {
            node.right = insert(node.right,key,value);
        }
        return node;
    }

    public boolean contain(Key key){
         return  contain(root,key);
    }


    private boolean contain(Node node,Key key){
        //递归退出条件
        if(node == null) return false;

        if(node.k.compareTo(key) == 0) return true;
        else if(node.k.compareTo(key) > 0)
            return contain(node.right,key);
        else
            return contain(node.left,key);
    }

    public Node minimum(){
        if(root != null)
            return minimum(root);
        return null;
    }

    //寻找最小值节点
    private Node minimum(Node node){
        assert (null != node);
        if( node.left == null) return node;
        return minimum(node.left);
    }

    public void removeMin(){
        if(root == null) return;
        root = removeMin(root);
    }

    //一个二叉树删除最小节点同时返回root
    public Node removeMin(Node node){
        assert (node != null);
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
       }
        node.left = removeMin(node.left);
        return node;
    }

    //寻找最大值节点
    public Node maximum(){
        if(root != null){
            return maximum(root);
        }
        return null;
    }

    private Node maximum(Node node){
        assert (node != null);
        if(node.right == null) return node;
        return maximum(node.right);
    }

    public void removeMax(){
        if(root == null) return;
        root = removeMax(root);
    }

    //一个二叉树删除最大节点同时返回root
    public Node removeMax(Node node){
        assert (node != null);
        if(node.right == null){
            Node leftNode = node.left;
            //删除引用，以便垃圾回收
            node.left = null;
            count--;
            return leftNode;
        }
        node.right = removeMin(node.right);
        return node;
    }

    //删除一个key对应的节点
    public void remove(Key key){
        if( null == key && root == null) return;
        root = remove(root,key);
    }

    public Node remove(Node node,Key key){
        assert (node != null);
        if(key.compareTo(node.k) > 0){
            //node的右节点为删除那个节点后的子二叉树
            node.right = remove(node.right,key);
            return node;
        }else if(key.compareTo(node.k) < 0){
            node.left = remove(node.left,key);
            return node;
        }else{
            // node.k == key时处理
            if(node.left == null){
                //直接将右节点代替要删除的节点
                count--;
                Node rightNode = node.right;
                //释放索引，用于垃圾回收
                node.right = null;
                return rightNode;
            }
            if(node.right == null){
                //直接将左节点代替要删除的节点
                count--;
                Node leftNode = node.left;
                //释放索引，用于垃圾回收
                node.left = null;
                return leftNode;
            }
            //node的左右节点都不为null时，需要对节点变化删除
            Node success = minimum(node.right);//找到替代删除节点的节点
            Node rightRoot = removeMin(node.right);//删除最小节点后的右子树的根
            count--; //将节点数量减一
            success.right = rightRoot;
            success.left = node.left;
            return success;
        }
    }


    public Value search(Key key){
        return  search(root,key);
    }

    private Value search(Node node,Key key){
        //递归退出条件
        if(node == null) return null;

        if(node.k.compareTo(key) == 0) return node.v;
        else if(node.k.compareTo(key) > 0)
            return search(node.right,key);
        else
            return search(node.left,key);
    }

    //深度优先的先序遍历
    public void perOrder(Node node){
        if(null != node){
            System.out.println(node.k);
            //先左后右
            perOrder(node.left);
            perOrder(node.right);
        }
    }

    //深度优先的中序遍历
    public void inOrder(Node node){
        if(null != node){
            inOrder(node.left);
            System.out.println(node.k);
            inOrder(node.right);
        }
    }

    //深度优先的后序遍历
    public void laOrder(Node node){
        if(null != node){
            laOrder(node.left);
            laOrder(node.right);
            System.out.println(node.k);
        }
    }


    public void level(){
        levelOrder(root);
    }

    //广度优先遍历
    public void levelOrder(Node node){
        Queue<Node> queue = new ArrayBlockingQueue(100);
        queue.add(node);//队列填满时不阻塞，而是抛出异常
        while (!queue.isEmpty()){// (!queue.isEmpty()){
            Node ele = queue.poll();
            System.out.println(ele.k + "  " + ele.v);
            if(ele.left != null)
                queue.add(ele.left);
            if(ele.right != null){
                queue.add(ele.right);
            }
        }
    }


    private class Node{
        private Key k;
        private Value v;
        private Node left;
        private Node right;

        Node(Key k,Value v){
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }
    }

    public static void main(String[] args){
        BST<Integer,String> bst = new BST<>();
        bst.insert(3,"c");
        bst.insert(1,"a");
        bst.insert(2,"b");
        bst.insert(5,"e");
        bst.insert(4,"d");
        System.out.println(bst.size());
        System.out.println(bst.contain(6));
        System.out.println(bst.contain(3));
        System.out.println(bst.search(6));
        System.out.println(bst.search(1));
        bst.level();
        System.out.println("------------in order---------------");
        bst.inOrder(bst.root);
        System.out.println("=================max & min==================");
        System.out.println("max " +bst.maximum());
        System.out.println("min " +bst.minimum());
/*      System.out.println("------------- remove max & min -----------------");
        bst.removeMin();
        bst.removeMax();
        bst.inOrder(bst.root);*/
        System.out.println("------------- remove random key -----------------");
        bst.remove(5);
        bst.inOrder(bst.root);
    }

}
