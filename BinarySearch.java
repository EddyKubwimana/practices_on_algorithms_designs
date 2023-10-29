class Student{
    int id;
    String name;
    int age;
    double grade;
    int year;

    public Student(int id, String name, int age, double grade, int year){

        this.id = id;
        this.name = name;
        this.age = age;
        this.grade =grade;
        this.year = year;

    }

    public String toString(){

        return " Id : "+ this.id + " Name : "+ this.name + " class : "+ this.year;
    

    }



}


class BubbleSort<type extends Student>{

    type[]array;

    public BubbleSort(type[]list){

        this.array = list;


        

    }

    public type[] sort(){

        boolean handler = true;

        while(handler == true){

            handler = false;

            for (int i =0; i< this.array.length-1; i++){

                if (this.array[i].id>this.array[i+1].id ){

                    handler = true;

                    type holder = this.array[i];
                    array[i] = this.array[i+1];
                    array[i+1] = holder;

                }



            }
        }

        return this.array;



    }


}



public class BinarySearch<type extends Student>{

    type[]students;


    BinarySearch(type[]students){

        this.students = students;

    }

    void search(int StudentId){

      Student[]array = new BubbleSort<Student>(this.students).sort();

      int low = 0;
      int upper = array.length-1;

    
        
     while (low <= upper) {
            int mid = low+ (upper-low) / 2; 

            if (array[mid].id == StudentId) {
                System.out.println(array[mid]);
                return;
            } else if (array[mid].id < StudentId) {
                low = mid + 1; 
            } else {
                upper = mid - 1; 
            }
        }
        
        System.out.println("no student with id; "+ StudentId);
        return;

    }

     





    public static void main(String[]args){


       Student s1 =new Student(83092026,"Eddy", 10, 5, 2026);
       Student s2 =new Student(83092024,"Daniel", 15, 5, 2024);
       Student s3 =new Student(83092023,"Olivier", 9, 5, 2023);
       Student s4 =new Student(83092021,"Philbert", 26, 5, 2021);

       Student[] students = {s1,s2,s3,s4};

       Student[]sorted = new BubbleSort<Student>(students).sort();

      BinarySearch<Student> binary = new BinarySearch<Student>(students);
       
      binary.search(83092026);







    }
}
