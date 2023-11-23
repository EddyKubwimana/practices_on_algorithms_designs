

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

        if(tree.data>value){

            root.left = recurseInsert(tree.left, value);

        }

        else{

            root.right = recurseInsert(tree.right, value);

        }

        return root;



    }

    public void insert(int value){

        root = recurseInsert(root, value);

    }




    public static void main(String[]args){



    }






    
}
