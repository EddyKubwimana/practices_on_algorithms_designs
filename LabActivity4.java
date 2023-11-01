
/*
@author : Eddy Kubwimana

- Lab Activity 3

*/


import java.util.InputMismatchException;
import java.util.Scanner;


// =====================================================================================================================================
                                                       //This is the task class
// =====================================================================================================================================

class Task {

    // instance variables

    int taskId;
    String description;

    static enum Status {
        pending, completed
    };

    int priority;
    Status status;

    // constructor

    public Task(int id, String description, int priority) {

        this.taskId = id;
        this.description = description;
        this.priority = priority;
        this.status = Status.pending;

    }

    public String toString() {

        return " Task Id : " + this.taskId + "  Description  : " + this.description + "   status : " + this.status
                + " priority : " + this.priority;
    }

}

// I will implement the class using linked list


// =====================================================================================================================================
                                                       //This is the node class
// =====================================================================================================================================

class Node {

    // instance variable for Node object

    Task data;
    Node next;
    Node prev;

    // Node object constructor
    public Node(Task data) {

        this.data = data;
    }

    public String toString() {

        return this.data.toString();
    }

}



// =====================================================================================================================================
                                                       //This is the Stack class
// =====================================================================================================================================

class Stack {

    // instance variable
    Node tail;
    int size;

    public void push(Task task) {
        // push method for adding the task at the top of the stack

        Node node = new Node(task);

        this.size++;

        if (this.tail == null) {

            tail = node;

        } else {

            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }

    }

    public boolean pop() {
        // pop method for removing element for the last recently added task
        // return true if the task is removed, return false if it removed from empty
        // stack

        if (this.tail == null) {

            return false;

        }

        else {

            tail.prev.next = null;
            tail = tail.prev;
            this.size--;
            return true;

        }

    }

    Task top() {

        // method to return the top task from the stack w/ removing it

        return this.tail.data;
    }

    void displayStack() {

        // to string method to print all task in the stack

        if (this.tail == null) {

            System.out.println("The stack is empty, there is no high priority here");
            return;
        }

        Node curr_node = this.tail;
        int counter = 0;
        while (curr_node != null) {

            System.out.println(" Recent Task " + counter+1 + " : " + curr_node.data.toString());
            counter++;
            curr_node = curr_node.prev;

        }

    }

}



// =====================================================================================================================================
                                                       //This is the queue node
// =====================================================================================================================================
class Queue {

    Node head;
    Node tail;
    int size;

    public void enqueue(Task task) {

        // Adding to the queue the new normal tasks

        Node node = new Node(task);
        this.size++;

        if (this.tail == null) {

            this.head = node;
            this.tail = this.head;

        }

        else {

            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;

        }

    }

    public boolean dequeue() {
        
        // The method remove the oldest element in the queue
        // return true if it is remove
        // return false otherwise

        if (this.head == null) {
            return false;
        }

        else {

            this.head = this.head.next;
            this.size--;
            return true;

        }

    }

    void displayQueue() {

        // to string method to print all task in the queue

        if (this.head == null) {

            System.out.println("There is no normal task here, the queue is empty");
            return;

        }

        Node curr_node = this.head;

        int counter = 0;
        while (curr_node != null) {

            System.out.println(" oldest Task " + counter+1 + " : " + curr_node.data.toString());
            counter++;
            curr_node = curr_node.next;

        }

    }

}



// =====================================================================================================================================
                                //This is the processor for handling the processing of task
// =====================================================================================================================================

class Processor {

    Stack highpriority = new Stack();
    Queue normalPriority = new Queue();
    Queue processed = new Queue();

    public void addTask(Task data) {

        // This method add a given task either to high priority or normal prority list

        if (data.priority > 5) {

            highpriority.push(data);
            this.prioritize();

        }

        else {

            normalPriority.enqueue(data);

        }

    }


    public Task process() {

        // this the method to process the task in the processor

        if (this.highpriority.tail != null) {


                Task beingProccessed = this.highpriority.top();

                if (this.highpriority.tail.prev !=null){

                     this.highpriority.pop();
                }
                else{

                    this.highpriority.tail =null;

                }
               

                return beingProccessed;

        } 


        else if (this.normalPriority.head != null) {

            Task beingProccessed = this.normalPriority.head.data;

            if (this.normalPriority.head.next !=null){
                    this.normalPriority.dequeue();

            }else{

                this.normalPriority.head = null;


            }
        

            return beingProccessed;
        } else{

            System.out.println("There is nothing to process !");

            return null;


        }

    }




public void options() {

    // This is essential for the working of the whole program
    // managed all options of the programs and interact with the different object to perform different tasks.



    boolean repeat = true;


    Scanner keyboard = new Scanner(System.in);



   

    do {


         try{

    
    
                System.out.println("=============OPTIONS===============");
                System.out.println("1. ADD TASK");
                System.out.println("2. VIEW QUEUE");
                System.out.println("3. VIEW STACK");
                System.out.println("4. PROCESS A TASK");
                System.out.println("5. VIEW ADDED TO PROCESSED");
                System.out.println("6. QUIT");
                System.out.println("=============Enter the option===============");

               
                if (keyboard.hasNextInt()) {

                    
                  

                    
                    int choice = keyboard.nextInt();

                    if (choice == 1) {


                        System.out.println("Create the task you want to add to your processor");
                        System.out.println("==============Enter the Id of the Task===================");
                        int id = keyboard.nextInt();
                        keyboard.nextLine(); 
                        System.out.println("=============Enter the description of the task===========");
                        String description = keyboard.nextLine();
                        System.out.println("=============Enter the priority of the task===========");
                        int priority = keyboard.nextInt();
                        keyboard.nextLine();
                        Task created = new Task(id, description, priority);
                        this.addTask(created);

                    
                    } else if (choice == 2) {
                        if (this.normalPriority != null) {
                            this.normalPriority.displayQueue();
                        } else {
                            System.out.println("The queue is empty; there is no normal priority here");
                        }
                    } else if (choice == 3) {
                        if (this.highpriority != null) {
                            this.highpriority.displayStack();
                        } else {
                            System.out.println("The queue is empty; there is no normal priority here");
                        }
                    } else if (choice == 4) {

                        Task beingProccessed = this.process();

                        if (beingProccessed!= null){

                            System.out.println("======OPTIONS===========");
                            System.out.println("Do you want to add : " + beingProccessed + "  " + " in processed bin");
                            System.out.println("=======1. Yes ==========");
                            System.out.println("=======2. No ==========");
                            System.out.println("====Enter your choice :=====");
                            
                            int newchoice = keyboard.nextInt();
                            keyboard.nextLine();

                            if (newchoice == 1  ) {

                                beingProccessed.status = Task.Status.completed;

                                this.processed.enqueue(beingProccessed);

                            } else {

                                System.out.println("Thanks, the task will not added tho the processed bin");
                            }




                        }else{

                            System.out.println("There is nothing to process");

                        }
                        
                    } else if (choice == 5) {
                        this.processed.displayQueue();
                    } else if (choice == 6) {
                        repeat = false;
                    } else {
                        System.out.println("The option is not recognized, try again.");
                    }

                
               
                    
                } else {
                    System.out.println("Invalid input. Please enter a valid option");
                    keyboard.nextLine();
                }
            
            }

              catch (InputMismatchException e) {
                    System.out.println("====Invalid input. Please enter a valid information. Try again ====");
                    // Consume the invalid input
                    keyboard.nextLine();
        }
            
        } while (repeat);

    keyboard.close();}


    public void prioritize() {

        // This method use bubble sort to prioritize high priority task during processing

        Node priority = this.highpriority.tail;

        boolean handler = true;
        while (handler == true) {

            handler = false;

            for (int i = 0; i < this.highpriority.size; i++) {

                if (priority.prev != null ) {

                    if(priority.prev.data.priority > priority.data.priority){


                        Task holder = priority.data;
                        priority.data = priority.prev.data;
                        priority.prev.data = holder;
                        handler = true;
                        priority = priority.prev;
                    }

                    

                }

            }

        }

    }

}



// =====================================================================================================================================
                                                       //This is the main class
// =====================================================================================================================================

public class LabActivity4{
    

    public static void main(String[]arr){

        // instatiantion of the processor object
        Processor processor1 = new Processor();
        
        // calling the options method on the processor instance
        
        processor1.options();


    }
}