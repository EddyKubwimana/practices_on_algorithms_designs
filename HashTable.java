
//=========================Entry class =======================
/**
 * The Entry class represents a key-value pair used in data structures such as maps.
 * Each Entry object encapsulates an integer key and an integer value associated with that key.
 */
 class Entry {



    /**
     * The key of the Entry, an integer value.
     */
    int key;

    /**
     * The value associated with the key, an integer value.
     */
    int value;

    /**
     * Constructs an Entry object with the specified key and value.
     *
     * @param key   The integer key for the Entry.
     * @param value The integer value associated with the key.
     */
    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns a string representation of the Entry.
     *
     * @return A string in the format "{key : value}" representing the Entry.
     */
    public String toString() {
        return "{" + this.key + " : " + this.value + "}";
    }
}



//=========================Hash function class =======================



/**
 * The Hash class provides methods for performing hash functions.
 * It includes methods for primary and secondary hashing.
 */
class Hash {

    /**
     * Computes the primary hash value for a given key and capacity.
     *
     * @param key      The integer key for which the hash value is computed.
     * @param capacity The capacity of the hash table.
     * @return The primary hash value for the given key and capacity.
     */
    int primaryHash(int key, int capacity) {
        return key % capacity;
    }

    /**
     * Computes the secondary hash value for a given key.
     * The secondary hash is used in scenarios such as open addressing in hash tables.
     *
     * @param key The integer key for which the secondary hash value is computed.
     * @return The secondary hash value for the given key.
     */
    int secondaryHash(int key) {

        // Using the formula 11 - (key % 11) for secondary hashing
        return (11 - (key % 11));
    }
}



//===================HashTable==================
public class HashTable{


    // This are instance variable the hasttable class
    private Entry[]table;
    private int capacity;
    private int length;


    // constructor
    // takes int capacity
    public HashTable(int capacity){

        this.capacity = capacity;
        this.table = new Entry[this.capacity];

    }


    public int getCapacity(){
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



        in



        

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



    


