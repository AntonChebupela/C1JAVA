import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        QueueManager queueManager = new QueueManager();
        TaskManager taskManager = new TaskManager(new DoubleEndedQueue<>());

        while (running) {
            System.out.println("Добро пожаловать в программу!");
            System.out.println("Выберите действие:");
            System.out.println("1. Управление очередью");
            System.out.println("2. Управление задачами");
            System.out.println("3. Выйти из программы");
            System.out.print("Введите номер действия (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    queueManager.start();
                    break;
                case 2:
                    taskManager.manageTasks(scanner);
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
