package Hotel_Prototype.Structures;

public class NodeBST<E>{
    public NodeBST father;
    public NodeBST right;
    public NodeBST left;
    public int key;
    public E data;

    public NodeBST(NodeBST father, NodeBST right, NodeBST left, int key, E data) {
        this.father = father;
        this.right = right;
        this.left = left;
        this.key = key;
        this.data = data;
    }

    public NodeBST(int key) {
        this.father = null;
        this.right = null;
        this.left = null;
        this.key = key;
        this.data = null;
    }

    public NodeBST getFather() {
        return father;
    }

    public void setFather(NodeBST father) {
        this.father = father;
    }

    public NodeBST getRight() {
        return right;
    }

    public void setRight(NodeBST right) {
        this.right = right;
    }

    public NodeBST getLeft() {
        return left;
    }

    public void setLeft(NodeBST left) {
        this.left = left;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}