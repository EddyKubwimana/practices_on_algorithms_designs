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


               



    public static void main(String[]arr){

        Tree tree = new Tree(22);
        tree.add(20);
        tree.add(10);
        tree.add(30);
        tree.add(15);
        tree.add(5);
        tree.add(25);
        tree.add(35);

        System.out.println(tree.root.right.left.data);
        System.out.println(tree.find(100));

        Sub a = tree.rootvalue(25);

        System.out.println(a.data);

    }



}


