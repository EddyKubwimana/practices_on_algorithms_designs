class Sub{

    int data;
    Sub left;
    Sub right;

   public  Sub(int data){
    
        this.data = data;

    }

    public String toString(){

        return "data :" +this.data;

    }


}


public class Tree{

    Sub root;


    public Tree(int rootdata){

        this.root = new  Sub(rootdata);

    }

    // This method add a value to the tree
    // return nothing
    public void add(int value){

        Sub curr = this.root;

        while(curr!=null){

            if (curr.data>=value){

                if (curr.left== null){

                    curr.left =new Sub(value);
                    return;

                }
                else{

                    curr = curr.left;


                }
    

            }
            else{

          

                if (curr.right== null){

                    curr.right =new Sub(value);
                    return;

                }
                else{
                    curr =  curr.right;
                }

            }

           

        }

        




    }

    // this method identify if a certain element exist in the tree
    // return true if the element exist, false if it does not.
    public boolean find(int value){

        Sub curr = this.root;

        while(curr!=null){

            if (curr.data==value){

                return true;

            }

            else{

                if(curr.data>value){

                    curr = curr.left;

                }

                else{

                    curr = curr.right;

                }

            }

            
                
            

        }

        return false;





    }

   // this method takes a value
   // return the its subtree
    public Sub rootvalue(int value){

        boolean lock = this.find(value);

        if(lock == true){

            Sub curr = this.root;

            while (curr!= null){

               if (curr.data==value){

                break;


               }
               else{

                if(curr.data>value){

                    curr = curr.left;

                }

                else{

                    curr = curr.right;

                }


               }

            }

            return curr;

            
       }

       else{

        return null;

       }

     
}

// find the predecessor of an element 
// return sub tree of that predeccessor

public Sub findPredecessor(int value){

    Sub curr = this.rootvalue(value);


    if(curr.right!=null){

    

            curr = curr.right;

            while(curr.left!= null){

                curr = curr.left;

            }

            return curr;

    
    }

    else{

        return null;

    }


        
}

// this method takes a value 
// if the value exist in the tree, it is going to delete the value
// return true if the element was deleted
//return false it it was not found and deleted

public boolean delete(int value){

    Sub subroot = this.rootvalue(value);
    Sub pred = this.findPredecessor(value);
    if (subroot == null){

        return false;
    }

    else{


        if (pred == null){

            subroot = null;
            return true;

        }

        else{

            int temp = pred.data;
            subroot.data = temp;

             
            return true;
            

        }

       
    
    }

}









               



    public static void main(String[]arr){

        Tree tree = new Tree(22);
        tree.add(20);
        tree.add(10);
        tree.add(30);
        tree.add(15);
        tree.add(5);
        tree.add(25);
        tree.add(35);


       Sub node = tree.findPredecessor(20);

       System.out.println(tree.right.right.data);




    }



}


