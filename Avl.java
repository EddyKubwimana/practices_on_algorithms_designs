

class TreeNode{

    int data;

    TreeNode left,right;

    /**
     * 
     * @param data
     */
    public TreeNode(int data){
        this.data = data;

        this.left=this.right= null;
    }

    /**
     * @param null
     * @return  toString method
     */
    public String toString(){

        return " Data : "+ this.data;
    }


}



public class Avl {

   public TreeNode root;


    public Avl(){

        this.root = null;
    }

    private TreeNode recurseInsert(TreeNode tree, int value){

        if (tree == null ){

            return new TreeNode(value);
        }

        if(tree.data>=value){

            tree.left = recurseInsert(tree.left, value);

        }

        else if (tree.data< value){

            tree.right = recurseInsert(tree.right, value);

        }

        return tree;



    }

    public void insert(int value){

        root = recurseInsert(root, value);

    }


    private void inorderTraversal(TreeNode root){

        if (root != null){

            inorderTraversal(root.left);
            System.out.println(root.data);
            inorderTraversal(root.right);

        }

    }


    private void preorderTraversal(TreeNode root){

        if (root != null){
            System.out.println(root.data);
            preorderTraversal(root.left);
            preorderTraversal(root.right);

        }
    }


    private void postorderTraversal(TreeNode root){

        if(root != null){

            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.println(root.data);

        }

    }


    public void  inorder(){

        inorderTraversal(root);
    }

    public void postorder(){

        postorderTraversal(root);
    }

    public void preorder(){

        preorderTraversal(root);

    }



     




    public static void main(String[]args){


        Avl tree = new Avl();

        tree.insert(100);
        tree.insert(70);
        tree.insert(85);
        tree.insert(50);
        tree.insert(150);
        tree.insert(175);
        tree.insert(130);

        tree.preorder();
       



    }






    
}
