package com.zwh.tree;

public class Tree {
    public int data;
    public Tree father, leftSon, rightSon;
    public boolean isLeftSon = true;
    public static Tree root;

    public boolean hasleft() {
        return leftSon != null;
    }

    public boolean hasRight() {
        return rightSon != null;
    }

    public Tree(boolean isLeftSon) {
        super();
        this.isLeftSon = isLeftSon;
    }


    public Tree() {
        super();
    }
    public void insert(Integer data,Tree father){
//如果father为null创建根节点，root.data = data;
        if(father == null){
            root = new Tree();
            root.data = data;
            return;
        }
        //在father节点插入的时候要判断当前树的根节点是否存在，否则返回
        if(root == null){
            return;
        }
        //插入的data和双亲节点的data比较大小
        int compare = data.compareTo(father.data);
        //data相等的话，接返回，插入失败
        if(compare==0)return;
        //如果插入的data大于双亲节点的data，则在右子树种查找
        if(compare>0){
            //没有右孩子，则将data作为father的右孩子节点
            if(!father.hasRight()){
                father.rightSon = new Tree(false);
                father.rightSon.data = data;
                father.rightSon.father = father;
            }else insert(data,father.rightSon);//有右孩子，就将father指向father.rightSon
        }else{
            //左孩子(同理)
            if(!father.hasleft()){
                father.leftSon = new Tree(false);
                father.leftSon.data = data;
                father.leftSon.father = father;
            }else insert(data,father.leftSon);
        }
    }
    //这里直接将root作为插入点，不断的寻找合适的插入点
    public void insert(Integer data){
        if(root == null){
            root = new Tree();
            root.data = data;
            return;
        }
        if(data.compareTo(root.data)==0){
            return;
        }
        insert(data,root);
    }
    //从root节点开始遍历
    public static void list(){
        if(root==null)return;
        list(root);
    }
    //从指定节点开始遍历(前序遍历)
    public static void list(Tree tree){
        if(tree==null)return;
        System.out.println(tree.data);
        if(tree.hasleft())list(tree.leftSon);
        if(tree.hasRight())list(tree.rightSon);
    }
}