
/*
@author : Eddy Kubwimana

- Lab Activity 3

*/

// creation of  Task class

class Task{

    // instance variables

    int taskId;
    String description;
    static enum Status {pending, completed};
    int priority;
    Status status;
    


    // constructor 

    public Task(int id, String description, int priority){

        this.taskId = id;
        this.description = description;
        this.priority =priority;
        this.status = Status.pending;

    }

    public String toString(){

        return " Task Id : "+ this.taskId+ "  Description  : "+ this.description +"status :"+ this.status + " priority : "+this.priority;
    }

}

// I will implement the class using linked list 

class Node{

     // instance variable for Node object

     Task data;
     Node next;
     Node prev;
     

     // Node object constructor
     public Node(Task data){
        
        this.data= data;
     }

     public String toString(){
        
        return this.data.toString();
     }


     
}


class Stack{

    // instance variable
    Node tail;

    // constructor  for the stack object

    public void push(Task task){
        // push method for adding the task at the top of the stack

        Node node = new Node(task);

        if(this.tail == null){

            tail =node;



        }
        else{
            
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }


    }

    public boolean pop(){
        // pop method for removing element for the last recently added task
        // return true if the task is removed, return false if it removed from empty stack

        if (this.tail == null){

            return false;


        }

        else{

            tail.prev.next = null;
            tail = tail.prev;
            return true;


        }


    }

    Task top(){

        // method to return the top task from the stack w/ removing it
        
        return this.tail.data;
    }


    void displayStack(){

         //to string method to print all task in the stack

         Node curr_node = this.tail;
         int counter = 0;
         while(curr_node!=null){

            System.out.println(" Recent Task " + counter + " : " + curr_node.data.toString());
            counter++;
            curr_node = curr_node.prev;

         }




    }
   



}

class Queue{

    Node head;
    Node tail;


    public void enqueue(Task task){

        // Adding to the queue the new normal tasks

        Node node = new Node(task);

        if(this.tail == null){

            this.head = node;
            this.tail = this.head;


        }

        else{

            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;


        }

    }


    public boolean dequeue(){

        if(this.head== null){
            return false;
        }

        else{
        
            this.head = this.head.next;
            return true;

        }

    }




     void displayQueue(){

         //to string method to print all task in the queue

         Node curr_node = this.head;
         int counter = 0;
         while(curr_node!=null){

            System.out.println(" Recent Task " + counter + " : " + curr_node.data.toString());
            counter++;
            curr_node = curr_node.next;

         }




    }



    

}
