
/*
@author : Eddy Kubwimana

- Lab Activity 3

*/

// creation of  Task class
import java.util.Scanner;
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

        return " Task Id : " + this.taskId + "  Description  : " + this.description + "status :" + this.status
                + " priority : " + this.priority;
    }

}

// I will implement the class using linked list

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

class Stack {

    // instance variable
    Node tail;
    int size;

    // constructor for the stack object

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

            System.out.println(" Recent Task " + counter + " : " + curr_node.data.toString());
            counter++;
            curr_node = curr_node.prev;

        }

    }

}

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

            System.out.println(" Recent Task " + counter + " : " + curr_node.data.toString());
            counter++;
            curr_node = curr_node.next;

        }

    }

}

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

    public void createTask() {

        System.out.println("Create the task you want to add to your processor");

        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);

        System.out.println("==============Enter the Id of the Task===================");
        int id = input1.nextInt();
        System.out.println("=============Enter the description of the task===========");
        String description = input2.nextLine();
        System.out.println("=============Enter the priority of the task===========");
        int priority = input3.nextInt();
        input1.close();
        input2.close();
        input3.close();
        this.addTask(new Task(id, description, priority));

    }

    public void process() {
        // this the method to process the task in the processor

        if (this.highpriority != null) {



            Task beingProccessed = this.highpriority.top();
            this.highpriority.pop();

            Scanner decision = new Scanner(System.in);

            System.out.println("======OPTIONS===========");
            System.out.println("Do you want to add : " + beingProccessed + "  " + " in processed bin");
            System.out.println("         1. Yes             ");
            System.out.println("         2. No              ");
            System.out.println("     Enter your choice :       ");

            int choice = decision.nextInt();
            decision.close();

            if (choice == 1) {

                beingProccessed.status = Task.Status.completed;

                this.processed.enqueue(beingProccessed);

            } else {

                System.out.println("Thanks, the task will not added tho the processed bin");
            }

        } else if (this.normalPriority != null) {

            Task beingProccessed = this.normalPriority.head.data;
            this.normalPriority.dequeue();

            Scanner decision = new Scanner(System.in);

            System.out.println("======OPTIONS===========");
            System.out.println("Do you want to add : " + beingProccessed + "  " + " in processed bin");
            System.out.println("         1. Yes             ");
            System.out.println("         2. No              ");
            System.out.println("     Enter your choice :       ");

            int choice = decision.nextInt();
            decision.close();

            if (choice == 1) {

                beingProccessed.status = Task.Status.completed;

                this.processed.enqueue(beingProccessed);

            } else {

                System.out.println("Thanks, the task will not added tho the processed bin");
            }

        }

    }

    public void options(){

        boolean repeat = true;
        Scanner options = new Scanner(System.in);
        do{
          

            System.out.println("=============OPTIONS===============");
            System.out.println("           1. ADD TASK             ");
            System.out.println("           2. VIEW QUEUE           ");
            System.out.println("           3. VIEW STACK            ");
            System.out.println("           4. PROCESS A TASK        ");
            System.out.println("          5. VIEW ADDED TO PROCCESSED  ");
            System.out.println("           6. QUIT                 ");
            System.out.println("=============Enter the option===============");
            
           

            int choice = options.nextInt();


            if( choice == 1){

                this.createTask();

            }
            else if (choice ==2){

                if (this.normalPriority!=null){
                        this.normalPriority.displayQueue();

                }

                else{


                    System.out.println("The queue is empty, there is no normal priority here");


                }


                  

            }

            else if (choice ==3){

                if (this.highpriority !=null){

                    this.highpriority.displayStack();

                }
                else{

                    System.out.println("The queue is empty, there is no normal priority here");

                    
                }
                
            

            }

            else if (choice ==4){

                this.process();



            }

            else if (choice == 5){

                this.processed.displayQueue();
            }

            else if( choice ==6){

                repeat = false;

            }

            else{
                System.out.println("====The option is not recognized, try again=====");
            }

        }

        while ( repeat == true);

        options.close();






    }

    public void prioritize() {

        // This method use bubble sort to prioritize high priority task during
        // processing them

        Node priority = this.highpriority.tail;

        boolean handler = true;
        while (handler == true) {

            handler = false;

            for (int i = 0; i < this.highpriority.size; i++) {

                if (priority.prev != null & priority.prev.data.taskId > priority.data.taskId) {

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


public class LabActivity3{
    

    public static void main(String[]arr){


        Processor processor1 = new Processor();

        //processor1.options();

        Stack trial = new Stack();

        Task t1 = new Task(100, "w", 10);
        trial.push(null);


    }
}