
/**
 *Lab : 5
 * Author: Eddy Kubwimana
 * Email: eddy.kubwimana@ashesi.edu.gh
 *
 * Introduction:
 *
 * The HashTable class is part of the "DataStructuresLab developed Eddy . This lab is aimed at
 * exploring and implementing  set of data structures implementations in Java. The HashTable class, specifically,
 * offers a simple hash table using open addressing techniques, allowing for efficient insertion, search, and deletion
 * of key-value pairs. The class employs a custom Entry class for representing individual entries in the hash table.
 *
 * Project Details:
 *
 * - Project Name: DataStructures lab
 * - Author: Eddy Kubwimana
 * - Email: eddy.kubwimana@ashesi.edu.gh
 * - GitHub: https://github.com/EddyKubwimana/practices_on_algorithms_designs
 */



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

    public Entry(int Id, int Grade) {
        this.key = Id;
        this.value = Grade;
    }

    /**
     * Returns a string representation of the Entry.
     *
     * @return A string in the format "{key : value}" representing the Entry.
     */

    public String toString() {
        return "{" + " Student ID : "+ this.key + " , Student Grade : " + this.value + "}";
    }


   /**
     * Gets the unique identifier (ID) associated with the student.
     *
     * @return The student's ID.
     */

    public int getId() {
        return this.key;
    }

    /**
     * Gets the grade associated with the student.
     *
     * @return The student's grade.
     */

    public int getGrade() {
        return this.value;
    }

    /**
     * Sets the unique identifier (ID) for the student.
     *
     * @param ID The new ID to be assigned to the student.
     */
    public void setId(int ID) {
        this.key = ID;
    }

    /**
     * Sets the grade for the student.
     *
     * @param Grade The new grade to be assigned to the student.
     */
    public void setGrade(int Grade) {
        this.value = Grade;
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



/**
 * The HashTable class represents a simple hash table implementation using open addressing.
 * It allows for inserting, searching, and deleting key-value pairs.
 */
public class HashTable {

    /**
     * An array to store entries (key-value pairs) in the hash table.
     */
    private Entry[] table;

    /**
     * The capacity of the hash table, representing the maximum number of entries it can hold.
     */
    private int capacity;

    /**
     * The current number of entries in the hash table.
     */
    private int length;

    /**
     * Constructs a HashTable with the specified capacity.
     *
     * @param capacity The maximum number of entries the hash table can hold.
     */
    public HashTable(int capacity) {
        this.capacity = findNextPrime(capacity);
        this.table = new Entry[this.capacity];
    }

    /**
     * Gets the capacity of the hash table.
     *
     * @return The maximum number of entries the hash table can hold.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Inserts a key-value pair into the hash table.
     * If the table is full, a message indicating that the table is full will be printed.
     *
     * @param key   The key of the entry to be inserted.
     * @param value The value associated with the key.
     */
    public void insert(int key, int value) {
        if (this.length == capacity) {
            this.resize();
            System.out.println("=====The the hastable has been resized ====");
            this.insert(key, value);
        } else {
            int primaryIndex = new Hash().primaryHash(key, this.capacity);
            int secondaryIndex = new Hash().secondaryHash(key);

            int i = 0;

            while (i < this.capacity) {
                int index = (primaryIndex + (i * secondaryIndex)) % this.capacity;

                if (this.table[index] == null) {
                    this.table[index] = new Entry(key, value);
                    this.length++;
                    return;
                } else if (this.table[index] != null && this.table[index].key == key) {
                    this.table[index].value = value;
                    return;
                } else {
                    i++;
                }
            }
        }
    }

    /**
     * Searches for an entry with the specified key in the hash table.
     *
     * @param key The key to search for in the hash table.
     * @return The Entry object associated with the key, or null if not found.
     */
    public Entry search(int key) {
        int primaryIndex = new Hash().primaryHash(key, this.capacity);
        int secondaryIndex = new Hash().secondaryHash(key);

        int i = 0;

        while (i < this.capacity) {
            int index = (primaryIndex + (i * secondaryIndex)) % this.capacity;

            if (this.table[index] != null && this.table[index].key == key) {
                return this.table[index];
            } else if (this.table[index] == null) {
                return null;
            } else {
                i++;
            }
        }

        return null;
    }

    /**
     * Deletes an entry with the specified key from the hash table.
     * If the entry is successfully deleted, the method returns true; otherwise, it returns false.
     *
     * @param key The key of the entry to be deleted.
     * @return True if the entry is successfully deleted, false otherwise.
     */
    public boolean delete(int key) {
        int primaryIndex = new Hash().primaryHash(key, this.capacity);
        int secondaryIndex = new Hash().secondaryHash(key);

        int i = 0;

        while (i < this.capacity) {
            int index = (primaryIndex + (i * secondaryIndex)) % this.capacity;

            if (this.table[index].key == key) {
                this.capacity--;
                this.table[index] = null;
                return true;
            }

            i++;
        }

        return false;
    }


    /**
     * Resizes the hash table to the next prime number greater than or equal to twice the current capacity.
     * Rehashes existing entries into the new table to maintain data integrity.
     */
    private void resize() {
        int newCapacity = findNextPrime(this.capacity * 3)+1; // Double the capacity and find the next prime
        Entry[] newTable = new Entry[newCapacity];

        // Rehash existing entries into the new table
        for (Entry entry : table) {
            if (entry != null) {
                int key = entry.key;
                int value = entry.value;

                int primaryIndex = new Hash().primaryHash(key, newCapacity);
                int secondaryIndex = new Hash().secondaryHash(key);

                int i = 0;
                while (true) {
                    int index = (primaryIndex + (i * secondaryIndex)) % newCapacity;

                    if (newTable[index] == null) {
                        newTable[index] = new Entry(key, value);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }

        this.capacity = newCapacity;
        this.table = newTable;
    }

    // Other methods...

    /**
     * Finds the next prime number greater than or equal to the given number.
     *
     * @param n The starting point to find the next prime number.
     * @return The next prime number greater than or equal to n.
     */
    private int findNextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    /**
     * Checks if a given number is prime.
     *
     * @param num The number to check for primality.
     * @return True if the number is prime, false otherwise.
     */
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[]args){

        HashTable table = new HashTable(1);
        table.insert(83092025,80);
        table.insert(93077,10);
        table.insert(22,90);
        table.insert(3418,323);
        table.insert(23,67);
        table.insert(1200,690);
        table.insert(67,100);
        table.insert(100,1);
        table.insert(8309205,80);
        table.insert(9307,10);
        table.insert(225,90);
        table.insert(341870,323);
        table.insert(23111,67);
        table.insert(120022,690);
        table.insert(6789,100);
        table.insert(10001,1);

        System.out.println(table.search(83092025));
        System.out.println(table.search(93077));
        System.out.println(table.search(22));
        System.out.println(table.search(3418));
        System.out.println(table.search(23));
        System.out.println(table.search(1200));
        System.out.println(table.search(67));
        table.delete(67);
        System.out.println(table.search(67));
        System.out.println(table.capacity);



    }
    }



    


