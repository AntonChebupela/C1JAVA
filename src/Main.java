import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QueueManager queueManager = new QueueManager();
        LibraryManager libraryManager = new LibraryManager();

        System.out.println("Добро пожаловать в программу!");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Управление очередью");
            System.out.println("2. TaskLibrary (управление библиотекой книг)");
            System.out.println("3. Выйти из программы");
            System.out.print("Введите номер действия (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    queueManager.start();
                    break;
                case 2:
                    libraryManager.manageLibrary(queueManager.getDeque());
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный выбор действия.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Программа завершена.");
    }
}