
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Eddy Kubwimana
 * Email: eddy.kubwimana@ashesi.edu.gh
 *
 * Lab 5: Telephone Directory Management System
 * Features:
 * 1. BST Implementation: The core of the system utilizes a Binary Search Tree to efficiently store and manage
 *    contact information.
 * 2. Contact Class: The Contact class represents an individual's contact information, including name, phone
 *    number, address, email, and website.
 * 3. TelephoneDirectory Class: The TelephoneDirectory class provides a set of functionalities, including
 *    insertion, search, deletion, printing, and more, to manage the contacts in the telephone directory.
 


//=============================Contact class =====================================================

/**
 * The Contact class represents a person's contact information, including their name, phone number,
 * address, email, and website.
 */

class Contact {
    /** The name of the contact. */
    String name;

    /** The phone number of the contact. */
    String phoneNumber;

    /** The address of the contact. */
    String address;

    /** The email address of the contact. */
    String email;

    /** The website URL of the contact. */
    String website;

    /**
     * Constructs a new Contact object with the specified information.
     *
     * @param name        The name of the contact.
     * @param phoneNumber The phone number of the contact.
     * @param address     The address of the contact.
     * @param email       The email address of the contact.
     * @param website     The website URL of the contact.
     */
    public Contact(String name, String phoneNumber, String address, String email, String website) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.website = website;
    }

    /**
     * Returns a string representation of the contact's name and phone number.
     *
     * @return A string containing the name and phone number of the contact.
     */
    public String toString() {
        return "Name: " + this.name + ", Phone: " + this.phoneNumber + " Address : "+ this.address;
    }
}


//=============================Tree Node class  =====================================================


/**
 * The TreeNode class represents a node in a binary tree structure, specifically designed
 * for a telephone directory application. Each node contains a Contact object, as well as
 * references to its left and right child nodes.
 */
class TreeNode {

    /** The Contact object associated with this tree node. */
    Contact contact;

    /** Reference to the left child node. */
    TreeNode left;

    /** Reference to the right child node. */
    TreeNode right;

    /**
     * Constructs a new TreeNode with the specified Contact object.
     *
     * @param contact The Contact object associated with this tree node.
     */
    public TreeNode(Contact contact) {
        this.contact = contact;
        this.left = this.right = null;
    }

    public String toString(){

        return this.contact.toString();
    }
}



//============================= Telephone directory class  =====================================================


/**
 * The TelephoneDirectory class represents a telephone directory implemented using a Binary Search Tree (BST).
 * It provides functionalities to insert, search, delete, print, get all contacts, search by criteria, save
 * to a file, and load from a file.
 */
public class TelephoneDirectory {

    /** The root node of the BST representing the telephone directory. */
    private TreeNode root;

    /**
     * Constructs a new TelephoneDirectory with an initially null root.
     */
    public TelephoneDirectory() {
        this.root = null;
    }

    /**
     * use a recursive method to find a where the root is null by comparing the new contact with existing ones
     * @param root
     * @param contact
     * @return
     */


    private TreeNode insertRecursive(TreeNode root, Contact contact) {
        if (root == null) {
            return new TreeNode(contact);
        }

        if (contact.name.compareTo(root.contact.name) < 0) {

            root.left = insertRecursive(root.left, contact);

        } else if (contact.name.compareTo(root.contact.name) > 0) {

            root.right = insertRecursive(root.right, contact);
        }

        return root;
    }



    /**
     * Inserts a new contact into the telephone directory.
     *
     * @param contact The contact to be inserted.
     */
    public void insert(Contact contact) {
        root = insertRecursive(root, contact);
    }

    /**
     *  search recursive for a given contact
     * @param root
     * @param contactName
     * @return
     */
    private TreeNode searchRecursive(TreeNode root, String contactName) {
        if (root == null || root.contact.name.equals(contactName)) {
            return root;
        }

        if (contactName.compareTo(root.contact.name) < 0) {
            return searchRecursive(root.left, contactName);
        }else{

        return searchRecursive(root.right, contactName);
    }
    }


    /**
     * Searches for a contact in the telephone directory and returns their phone number if found.
     *
     * @param contactName The name of the contact to search for.
     * @return The phone number of the contact if found, or "Contact not found" otherwise.
     */
    public String search(String contactName) {
        TreeNode result = searchRecursive(root, contactName);
        return (result != null) ? result.contact.phoneNumber : "Contact not found";
    }

    
    /**
     * Deletes a contact from the telephone directory.
     *
     * @param contactName The name of the contact to be deleted.
     */
    public void delete(String contactName) {
        root = deleteRecursive(root, contactName);
    }

    /**
     * delete a given contact
     * @param root
     * @param contactName
     * @return root
     */

    private TreeNode deleteRecursive(TreeNode root, String contactName) {
        if (root == null) {
            return root;
        }

        if (contactName.compareTo(root.contact.name) < 0) {
            root.left = deleteRecursive(root.left, contactName);
        } else if (contactName.compareTo(root.contact.name) > 0) {
            root.right = deleteRecursive(root.right, contactName);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.contact.name = minValue(root.right);

            root.right = deleteRecursive(root.right, root.contact.name);
        }

        return root;
    }

    private String minValue(TreeNode root) {
        String minValue = root.contact.name;
        while (root.left != null) {
            minValue = root.left.contact.name;
            root = root.left;
        }
        return minValue;
    }

    /**
     * Prints the telephone directory in ascending order.
     */
    public void print() {
        printInOrder(root);
    }

    private void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.contact.name + ": " + root.contact.phoneNumber);
            printInOrder(root.right);
        }
    }

    /**
     * Gets a list of all contacts in the telephone directory.
     *
     * @return The list of all contacts.
     */
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        getAllContacts(root, contacts);
        return contacts;
    }

    /**
     *  get all contacts from the the tree;
     * @param root
     * @param contacts
     */
    private void getAllContacts(TreeNode root, List<Contact> contacts) {
        if (root != null) {
            getAllContacts(root.left, contacts);
            contacts.add(root.contact);
            getAllContacts(root.right, contacts);
        }
    }

    /**
     * Searches the telephone directory by multiple criteria, such as contact name, address, phone number,
     * email, or website.
     *
     * @param criteria The criteria to search for in the contacts.
     * @return The list of contacts matching the search criteria.
     */
    public List<Contact> searchByCriteria(String criteria) {
        List<Contact> result = new ArrayList<>();
        searchByCriteria(root, criteria, result);
        return result;
    }

    private void searchByCriteria(TreeNode root, String criteria, List<Contact> result) {
        if (root != null) {
            if (root.contact.name.toLowerCase().contains(criteria.toLowerCase()) ||
                    root.contact.address.toLowerCase().contains(criteria.toLowerCase()) ||
                    root.contact.phoneNumber.contains(criteria) ||
                    root.contact.email.toLowerCase().contains(criteria.toLowerCase()) ||
                    root.contact.website.toLowerCase().contains(criteria.toLowerCase())) {
                result.add(root.contact);
            }

            searchByCriteria(root.left, criteria, result);
            searchByCriteria(root.right, criteria, result);
        }
    }





    public static void main(String[] args) {
        TelephoneDirectory directory = new TelephoneDirectory();

       

        
    directory.insert(new Contact("Alice Smith", "234-567-8901", "456 Oak St", "alice.smith@example.com", "www.alicesmith.com"));
    directory.insert(new Contact("Bob Johnson", "345-678-9012", "789 Pine St", "bob.johnson@example.com", "www.bobjohnson.com"));

    directory.insert(new Contact("Jane Smith", "987-654-3210", "456 Oak St", "jane.smith@example.com", "www.janesmith.com"));
    directory.insert(  new Contact("Samuel Johnson", "555-123-4567", "789 Maple St", "samuel.johnson@example.com", "www.samueljohnson.com"));
    directory.insert( new Contact("Emily Davis", "222-333-4444", "567 Pine St", "emily.davis@example.com", "www.emilydavis.com"));
    directory.insert( new Contact("Michael White", "555-555-5555", "789 Cedar St", "michael.white@example.com", "www.michaelwhite.com"));
    directory.insert( new Contact("Sarah Brown", "111-222-3333", "345 Birch St", "sarah.brown@example.com", "www.sarahbrown.com"));
    directory.insert( new Contact("Daniel Miller", "777-888-9999", "678 Elm St", "daniel.miller@example.com", "www.danielmiller.com"));
    directory.insert( new Contact("Olivia Wilson", "444-666-8888", "901 Oak St", "olivia.wilson@example.com", "www.oliviawilson.com"));
    directory.insert( new Contact("James Taylor", "999-888-7777", "234 Maple St", "james.taylor@example.com", "www.jamestaylor.com"));
    directory.insert( new Contact("Sophia Anderson", "444-333-2222", "567 Pine St", "sophia.anderson@example.com", "www.sophiaanderson.com"));
    directory.insert( new Contact("William Clark", "555-444-3333", "789 Cedar St", "william.clark@example.com", "www.williamclark.com"));
    directory.insert( new Contact("Ava Moore", "777-666-5555", "123 Birch St", "ava.moore@example.com", "www.avamoore.com"));
    directory.insert( new Contact("Ethan Baker", "111-999-8888", "456 Elm St", "ethan.baker@example.com", "www.ethanbaker.com"));
    directory.delete("Alice Smith");

    // Print the telephone directory
    System.out.println("Telephone Directory 1:");
    directory.print();

    System.out.println("============================");

    System.out.println("Telephone Directory 2:");

    directory.delete("Ethan Baker");
    directory.delete("Ava Moore");

    directory.print();


}}
    