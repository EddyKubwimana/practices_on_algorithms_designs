
//=========================Entry=======================
class Entry{
    int key;
    int value;

    public Entry(int key, int value){

        this.key = key;
        this.value = value;

    }

    public String toString(){

        return "{" +this.key+ " : "+ this.value +"}";

    }
}


//=========================Hash=======================

class Hash{

    int primaryHash(int key, int capacity){

        return key%capacity;


    }

    int secondaryHash(int key){

        return(11-(key%11));

    }

}

//===================HashTable==================
public class HashTable{

    private Entry[]table;
    private int capacity;
    private int length;


    public HashTable(int capacity){

        this.capacity = capacity;
        this.table = new Entry[this.capacity];

    }


    public getCapacity(){
        return this.capacity;
    }

    public void insert(int key, int value){

        if(this.length == capacity){
            System.out.println("=====The table is full ====");
        }else{

           int primaryIndex = new Hash().primaryHash(key,this.capacity);
           int secondaryIndex = new Hash().secondaryHash(key);


           int i = 0;

           while (i<this.capacity){
              
            int index = (primaryIndex+(i*secondaryIndex))%this.capacity;

            if (this.table[index]==null){
                this.table[index] = new Entry(key,value);
                this.length++;
                return ;

            }
            else if ( this.table[index]!=null && this.table[index].key == key){

                this.table[index].value = value;
                return;

                
            }

            else{

                i++;

            }


            }

           }

        }


        Entry retrieve(int key){

            int primaryIndex = new Hash().primaryHash(key,this.capacity);
            int secondaryIndex = new Hash().secondaryHash(key);

            int i= 0;

            while(i<this.capacity){

                int index = (primaryIndex+(i*secondaryIndex))%this.capacity;

                if( this.table[index] != null && this.table[index].key ==key){


                    return this.table[index];

                }
                else if(this.table[index]==null){

                    return null;

                }

                else{

                    i++;
                }

            

            }

            return null;}



        

    public static void main(String[]args){

        HashTable table = new HashTable(7);
        table.insert(83092025,80);
        table.insert(93077,10);
        table.insert(22,90);
        table.insert(3418,323);
        table.insert(23,67);
        table.insert(1200,690);
        table.insert(67,100);
        table.insert(100,1);

        System.out.println(table.retrieve(83092025));
        System.out.println(table.retrieve(93077));
        System.out.println(table.retrieve(22));
        System.out.println(table.retrieve(3418));
        System.out.println(table.retrieve(23));
        System.out.println(table.retrieve(1200));
        System.out.println(table.retrieve(67));
    }
    }



    


