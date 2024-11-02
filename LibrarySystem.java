import java.util.*;

public class LibrarySystem {
    private Map<String, LinkedList<Book>> genreMap;
    private Set<Book> availableBooks;
    private Map<String, List<Book>> userBorrowedBooks;

    public LibrarySystem() {
        genreMap = new HashMap<>();
        availableBooks = new HashSet<>();
        userBorrowedBooks = new HashMap<>();
    }

    public void addBook(String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        genreMap.computeIfAbsent(genre, k -> new LinkedList<>()).add(book);
        availableBooks.add(book);
        System.out.println("Book added: " + title);
    }

    public void showAvailableBooks(String genre) {
        List<Book> genreBooks = genreMap.getOrDefault(genre, new LinkedList<>());
        System.out.println("\nAvailable books in " + genre + ":");
        boolean found = false;
        for (Book book : genreBooks) {
            if (book.isAvailable()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
                found = true;
            }
        }
        if (!found) System.out.println("No books available in this genre.");
    }

    public void borrowBook(String userName, String genre, String title) {
        List<Book> genreBooks = genreMap.getOrDefault(genre, new LinkedList<>());
        for (Book book : genreBooks) {
            if (book.isAvailable() && book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailable(false);
                availableBooks.remove(book);
                userBorrowedBooks.computeIfAbsent(userName, k -> new ArrayList<>()).add(book);
                System.out.println("Borrowed " + title + " by " + userName);
                return;
            }
        }
        System.out.println("Book not available for borrowing.");
    }

    public void returnBook(String userName, String title) {
        List<Book> borrowedBooks = userBorrowedBooks.getOrDefault(userName, new ArrayList<>());
        for (Book book : borrowedBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailable(true);
                availableBooks.add(book);
                borrowedBooks.remove(book);
                System.out.println("Returned " + title + " by " + userName);
                return;
            }
        }
        System.out.println("Book not found in borrowed list.");
    }

    public void showUserBorrowedBooks(String userName) {
        List<Book> borrowedBooks = userBorrowedBooks.getOrDefault(userName, new ArrayList<>());
        if (borrowedBooks.isEmpty()) {
            System.out.println(userName + " has no borrowed books.");
        } else {
            System.out.println(userName + "'s borrowed books:");
            for (Book book : borrowedBooks) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}
