package Hotel_Prototype.Structures;

import java.util.LinkedList;
import java.util.Queue;

public class BST <E> {
    NodeBST root;

    public NodeBST getRoot() {
        return root;
    }

    public void setRoot(NodeBST root) {
        this.root = root;
    }

    public  BST(){
        this.root = null;
    }

    //Metodos del arbol
    public boolean isEmpty(){
        if(root==null)return true;
        else return false;
    }
    public  void insert(int key, E data){
        NodeBST n = new NodeBST(key);
        n.data = data;

        if(root==null){
            root=n;
        }else {
            NodeBST temp = root;
            while (temp!=null) {
                n.father = temp;
                if(n.key>=temp.key){
                    temp = temp.right;
                }else {
                    temp = temp.left;
                }
            }
            if (n.key<n.father.key){
                n.father.left = n;
            }else {
                n.father.right = n;
            }
        }
    }

    public  void inOrder(NodeBST root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.getData());
            inOrder(root.right);
        }
    }
    public void preOrder( NodeBST root){
        if(root==null)
            return;

        System.out.println("Nodo value => "+ root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void posOrder(NodeBST root){
        if(root==null)
            return;

        posOrder(root.left);
        posOrder(root.right);
        System.out.println("Nodo value => "+ root.data);
    }
    public boolean amplitude( E e){

        Queue<NodeBST> auxiliarQueue = new LinkedList<NodeBST>();
        auxiliarQueue.add(root);

        while (auxiliarQueue.size()!=0){
            NodeBST node = auxiliarQueue.poll();
            System.out.println("Node value => "+node.data);
            if(node.data == e){
                return true;
            }else {
                if(node.left!=null) auxiliarQueue.add(node.left);
                if (node.right!=null)auxiliarQueue.add(node.right);
            }
        }
        return false;
    }
    public int plus(){
        int plus = 0;
        Queue<NodeBST> auxiliarQueue = new LinkedList<NodeBST>();
        auxiliarQueue.add(root);

        while (auxiliarQueue.size()!=0){
            NodeBST node = auxiliarQueue.poll();
            plus += (int) node.data;

            if(node.left!=null) auxiliarQueue.add(node.left);
            if (node.right!=null)auxiliarQueue.add(node.right);

        }
        return plus;
    }
    public int plusOdd(){
        int plus = 0;
        Queue<NodeBST> auxiliarQueue = new LinkedList<NodeBST>();
        auxiliarQueue.add(root);

        while (auxiliarQueue.size()!=0){
            NodeBST node = auxiliarQueue.poll();
            if ( (int) node.data%2 !=0) {
                plus += (int) node.data;
            }
            if(node.left!=null) auxiliarQueue.add(node.left);
            if (node.right!=null)auxiliarQueue.add(node.right);

        }
        return plus;
    }
    private NodeBST search(E name, NodeBST node){
        if(node != null){
            if(node.data == (name)){
                return node;
            } else {
                NodeBST foundNode = search(name, node.left);
                if(foundNode == null) {
                    foundNode = search(name, node.right);
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }
}
