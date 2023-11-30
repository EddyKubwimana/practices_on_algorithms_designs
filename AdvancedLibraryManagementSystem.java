import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;



// ================================Book class ========================================

class Book {
    String title;
    String author;
    String ISBN;
    boolean isCheckedOut;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isCheckedOut = false;
    }
}




// ================================Borrower class ========================================

class Borrower{

    String name;
    String id;
    
    public Borrower(String name, String id){

        this.id = id;
        this.name = name;

    }


    public String toString(){

        return " Person : "+ this.name + " ID :"+this.id;

    }

   
    


}



// ================================catalogue class  ========================================

class Catalogue {
    private Map<String, Book> catalogue;

    public Catalogue() {
        this.catalogue = new HashMap<>();
    }

    public void addBook(Book book) {
        catalogue.put(book.ISBN, book);
    }

    public Book getBook(String ISBN) {
        return catalogue.get(ISBN);
    }

    public void displayCatalogue() {
        System.out.println("Catalogue:");
        for (Book book : catalogue.values()) {
            System.out.println(book.title + " by " + book.author + " (ISBN: " + book.ISBN + ")");
        }
    }
}


// ================================ Book availabity class ========================================
class BookAvailability {
    private TreeNode root;

    private static class TreeNode {
        Book book;
        TreeNode left, right;

        public TreeNode(Book book) {
            this.book = book;
            this.left = this.right = null;
        }
    }

    public void insertBook(Book book) {
        root = insert(root, book);
    }

    private TreeNode insert(TreeNode node, Book book) {
        if (node == null) {
            return new TreeNode(book);
        }

        int compareResult = book.ISBN.compareTo(node.book.ISBN);

        if (compareResult < 0) {
            node.left = insert(node.left, book);
        } else if (compareResult > 0) {
            node.right = insert(node.right, book);
        }

        return node;
    }

    public void updateBookAvailability(Book book, boolean isCheckedOut) {
        root = updateBookAvailability(root, book, isCheckedOut);
    }

    private TreeNode updateBookAvailability(TreeNode node, Book book, boolean isCheckedOut) {
        if (node == null) {
            return null;
        }

        int compareResult = book.ISBN.compareTo(node.book.ISBN);

        if (compareResult < 0) {
            node.left = updateBookAvailability(node.left, book, isCheckedOut);
        } else if (compareResult > 0) {
            node.right = updateBookAvailability(node.right, book, isCheckedOut);
        } else {
            node.book.isCheckedOut = isCheckedOut;
        }

        return node;
    }

    public boolean isBookAvailable(Book book) {
        return isBookAvailable(root, book);
    }

    private boolean isBookAvailable(TreeNode node, Book book) {
        if (node == null) {
            return false;
        }

        int compareResult = book.ISBN.compareTo(node.book.ISBN);

        if (compareResult < 0) {
            return isBookAvailable(node.left, book);
        } else if (compareResult > 0) {
            return isBookAvailable(node.right, book);
        } else {
            return !node.book.isCheckedOut;
        }
    }
}



// ================================library Management System class  ========================================

class LibraryManagementSystem {
    private Catalogue catalogue;
    private BookAvailability bookAvailability;
    private Queue<Book> checkoutQueue;

    public LibraryManagementSystem() {
        this.catalogue = new Catalogue();
        this.bookAvailability = new BookAvailability();
        this.checkoutQueue = new LinkedList<>();
    }

    public void addBook(String title, String author, String ISBN) {
        Book newBook = new Book(title, author, ISBN);
        catalogue.addBook(newBook);
        bookAvailability.insertBook(newBook);
        System.out.println("Book added successfully.");
    }

    public void checkOutBook(String ISBN) {
        Book book = catalogue.getBook(ISBN);

        if (book == null) {
            System.out.println("Book not found in the catalogue.");
        } else {
            if (bookAvailability.isBookAvailable(book)) {
                checkoutQueue.add(book);
                bookAvailability.updateBookAvailability(book, true);
                System.out.println("Book checked out successfully.");
            } else {
                System.out.println("Book is already checked out. Added to the waiting list.");
            }
        }
    }

    public void returnBook(String ISBN) {
        Book book = catalogue.getBook(ISBN);

        if (book == null) {
            System.out.println("Book not found in the catalogue.");
        } else {
            if (bookAvailability.isBookAvailable(book)) {
                System.out.println("Book is not checked out. Cannot return.");
            } else {
                bookAvailability.updateBookAvailability(book, false);
                System.out.println("Book returned successfully.");
                if (!checkoutQueue.isEmpty()) {
                    Book nextBook = checkoutQueue.poll();
                    bookAvailability.updateBookAvailability(nextBook, true);
                    System.out.println("Next book in the waiting list checked out: " + nextBook.title);
                }
            }
        }
    }

    public void searchBook(String ISBN) {
        Book book = catalogue.getBook(ISBN);

        if (book == null) {
            System.out.println("Book not found in the catalogue.");
        } else {
            System.out.println("Book Found: " + book.title + " by " + book.author + " (ISBN: " + book.ISBN + ")");
            System.out.println("Availability: " + (bookAvailability.isBookAvailable(book) ? "Available" : "Checked Out"));
        }
    }

    public void displayCatalogue() {
        catalogue.displayCatalogue();
    }
}

public class AdvancedLibraryManagementSystem {
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Adding books to the library
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "9780141182636");
        library.addBook("To Kill a Mockingbird", "Harper Lee", "9780061120084");
        library.addBook("1984", "George Orwell", "9780451524935");

        // Checking out and returning books
        library.checkOutBook("9780141182636");
        library.checkOutBook("9780061120084");
        library.returnBook("9780141182636");
        library.returnBook("9780061120084");

        // Searching for a book
        library.searchBook("9780451524935");

        // Displaying the catalogue
        library.displayCatalogue();
    }
}
