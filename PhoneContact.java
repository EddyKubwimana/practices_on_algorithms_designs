class Contact {
    String name;
    String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

class Node {
    Contact contact;
    Node left, right;

    public Node(Contact contact) {
        this.contact = contact;
        this.left = this.right = null;
    }
}

public class PhoneContact{
    private Node root;

    public PhoneContact() {
        this.root = null;
    }

    public void insert(Contact contact) {
        root = insertRec(root, contact);
    }

    private Node insertRec(Node root, Contact contact) {
        if (root == null) {
            root = new Node(contact);
            return root;
        }

        if (contact.name.compareToIgnoreCase(root.contact.name) < 0) {
            root.left = insertRec(root.left, contact);
        } else if (contact.name.compareToIgnoreCase(root.contact.name) > 0) {
            root.right = insertRec(root.right, contact);
        }

        return root;
    }

    public Contact search(String contactName) {
        return searchRec(root, contactName);
    }

    private Contact searchRec(Node root, String contactName) {
        if (root == null || root.contact.name.equalsIgnoreCase(contactName)) {
            return (root != null) ? root.contact : null;
        }

        if (contactName.compareToIgnoreCase(root.contact.name) < 0) {
            return searchRec(root.left, contactName);
        }

        return searchRec(root.right, contactName);
    }

    public void delete(String contactName) {
        root = deleteRec(root, contactName);
    }

    private Node deleteRec(Node root, String contactName) {
        if (root == null) {
            return root;
        }

        if (contactName.compareToIgnoreCase(root.contact.name) < 0) {
            root.left = deleteRec(root.left, contactName);
        } else if (contactName.compareToIgnoreCase(root.contact.name) > 0) {
            root.right = deleteRec(root.right, contactName);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.contact = minValue(root.right);

            root.right = deleteRec(root.right, root.contact.name);
        }

        return root;
    }

    private Contact minValue(Node root) {
        Contact minValue = root.contact;
        while (root.left != null) {
            minValue = root.left.contact;
            root = root.left;
        }
        return minValue;
    }

    public void print() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.contact.name + ": " + root.contact.phoneNumber);
            inorder(root.right);
        }
    }


    public static void main(String[] args) {
       PhoneContact directory = new PhoneContact();

        // Insert contacts
        directory.insert(new Contact("Alice", "123-456-7890"));
        directory.insert(new Contact("Bob", "987-654-3210"));
        directory.insert(new Contact("Charlie", "111-222-3333"));

        // Search for a contact
        Contact searchResult = directory.search("Bob");
        if (searchResult != null) {
            System.out.println("Found: " + searchResult.name + " - " + searchResult.phoneNumber);
        } else {
            System.out.println("Contact not found.");
        }

        // Delete a contact
        directory.delete("Alice");

        // Print the telephone directory
        System.out.println("\nTelephone Directory:");
        directory.print();
    }
}

