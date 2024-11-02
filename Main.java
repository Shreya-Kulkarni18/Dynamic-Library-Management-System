import java.util.*;
public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        // Initial user setup
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a Book");
            System.out.println("2. Show Available Books by Genre");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. View My Borrowed Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:  // Add a Book
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    library.addBook(title, author, genre);
                    break;

                case 2:  // Show Available Books by Genre
                    System.out.print("Enter genre: ");
                    genre = scanner.nextLine();
                    library.showAvailableBooks(genre);
                    break;

                case 3:  // Borrow a Book
                    System.out.print("Enter genre: ");
                    genre = scanner.nextLine();
                    library.showAvailableBooks(genre);
                    System.out.print("Enter book title to borrow: ");
                    title = scanner.nextLine();
                    library.borrowBook(userName, genre, title);
                    break;

                case 4:  // Return a Book
                    System.out.print("Enter book title to return: ");
                    title = scanner.nextLine();
                    library.returnBook(userName, title);
                    break;

                case 5:  // View My Borrowed Books
                    library.showUserBorrowedBooks(userName);
                    break;

                case 6:  // Exit
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
