import java.util.*;

public class LibraryManager {

    private DoubleEndedQueue<Book> bookQueue;
    private List<Book> borrowedBooks = new ArrayList<>();
    private List<Book> availableBooks = new ArrayList<>();

    public LibraryManager(DoubleEndedQueue<Book> bookQueue) {
        this.bookQueue = bookQueue;
    }

    public LibraryManager() {
        bookQueue = new DoubleEndedQueue<>();
    }

    public void manageLibrary(DoubleEndedQueue<Object> deque) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Добро пожаловать в библиотеку!");

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить 5 книг по умолчанию");
            System.out.println("2. Создать книгу");
            System.out.println("3. Взять книгу");
            System.out.println("4. Вернуть книгу");
            System.out.println("5. Вывести список доступных книг");
            System.out.println("6. Вывести список взятых книг");
            System.out.println("7. Выйти из библиотеки");
            System.out.print("Введите номер действия (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем перевод строки

            switch (choice) {
                case 1:
                    addDefaultBooks();
                    break;
                case 2:
                    createBook(scanner);
                    break;
                case 3:
                    borrowBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    displayAvailableBooks();
                    break;
                case 6:
                    displayBorrowedBooks();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный выбор действия.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Выход из библиотеки.");
    }

    private void createBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();

        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();

        System.out.print("Введите дату написания книги: ");
        String date = scanner.nextLine();

        System.out.print("Введите жанр книги: ");
        String genre = scanner.nextLine();

        Book book = new Book(title, author, date, genre);
        availableBooks.add(book);

        System.out.println("Книга успешно создана.");
    }

    private void addDefaultBooks() {
        List<Book> booksToAdd = new ArrayList<>();
        booksToAdd.add(new Book("Мастер и Маргарита", "Михаил Булгаков", "1967", "Фантастика"));
        booksToAdd.add(new Book("Преступление и наказание", "Фёдор Достоевский", "1866", "Роман"));
        booksToAdd.add(new Book("1984", "Джордж Оруэлл", "1949", "Фантастика"));
        booksToAdd.add(new Book("Анна Каренина", "Лев Толстой", "1877", "Роман"));
        booksToAdd.add(new Book("Убийство в Восточном экспрессе", "Агата Кристи", "1934", "Детектив"));

        availableBooks.addAll(booksToAdd);
        System.out.println("5 книг успешно добавлены в библиотеку.");
    }

    private void borrowBook(Scanner scanner) {
        if (availableBooks.isEmpty()) {
            System.out.println("В библиотеке нет доступных книг.");
            return;
        }

        System.out.println("Список доступных книг:");
        displayAvailableBooks();

        System.out.print("Введите номер книги, которую хотите взять: ");
        int bookIndex = scanner.nextInt();

        if (bookIndex >= 0 && bookIndex < availableBooks.size()) {
            Book bookToBorrow = availableBooks.remove(bookIndex);
            borrowedBooks.add(bookToBorrow);
            System.out.println("Вы взяли книгу: " + bookToBorrow.getTitle());
        } else {
            System.out.println("Некорректный индекс книги.");
        }
    }

    private void returnBook(Scanner scanner) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("У вас нет взятых книг.");
            return;
        }

        System.out.println("Список ваших взятых книг:");
        displayBorrowedBooks();

        System.out.print("Введите номер книги, которую хотите вернуть: ");
        int bookIndex = scanner.nextInt();

        if (bookIndex >= 0 && bookIndex < borrowedBooks.size()) {
            Book bookToReturn = borrowedBooks.remove(bookIndex);
            availableBooks.add(bookToReturn);
            System.out.println("Вы вернули книгу: " + bookToReturn.getTitle());
        } else {
            System.out.println("Некорректный индекс книги.");
        }
    }

    private void displayAvailableBooks() {
        if (availableBooks.isEmpty()) {
            System.out.println("Библиотека пуста.");
        } else {
            int index = 0;
            for (Book book : availableBooks) {
                System.out.println(index + ". " + book.toString());
                index++;
            }
        }
    }

    private void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("У вас нет взятых книг.");
        } else {
            int index = 0;
            for (Book book : borrowedBooks) {
                System.out.println(index + ". " + book.toString());
                index++;
            }
        }
    }
}