import java.util.NoSuchElementException;
import java.util.Scanner;

public class QueueManager {
    private final DoubleEndedQueue<Object> deque;
    private final Scanner scanner;

    public QueueManager() {
        deque = new DoubleEndedQueue<>();
        scanner = new Scanner(System.in);
    }
    public DoubleEndedQueue<Object> getDeque() {
        return deque;
    }

    public void start() {
        System.out.println("Добро пожаловать в программу управления очередью.");
        boolean running = true;

        while (running) {
            System.out.println("Выберите действие:");


            System.out.println("1. Добавить элемент в очередь (в конец)");
            System.out.println("2. Добавить элемент в начало очереди");
            System.out.println("3. Удаление из начала очереди");
            System.out.println("4. Удаление с конца очереди");
            System.out.println("5. Узнать размер очереди");
            System.out.println("6. Получить элемент по индексу");
            System.out.println("7. Обновить элемент по индексу");
            System.out.println("8. Найти индекс элемента");
            System.out.println("9. Получить первый элемент очереди");
            System.out.println("10. Получить последний элемент очереди");
            System.out.println("11. Вывести содержимое очереди");
            System.out.println("12. Очистить список");
            System.out.println("13. Выйти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите элементы для добавления в конец очереди (или 'Закончить' для завершения ввода): ");
                    while (true) {
                        if (scanner.hasNextInt()) {
                            int itemToAddRear = scanner.nextInt();
                            deque.addFront(itemToAddRear);
                        } else {
                            String input = scanner.next();
                            if (input.equalsIgnoreCase("Закончить")) {
                                break;
                            } else {
                                deque.addRear(input);
                            }
                        }
                    } break;

                case 2:
                    System.out.print("Введите элемент для добавления в начало очереди (или 'Закончить' для завершения ввода): ");
                    while (true) {
                        if (scanner.hasNextInt()) {
                            int itemToAddFront = scanner.nextInt();
                            deque.addFront(itemToAddFront);
                        } else {
                            String input = scanner.next();
                            if (input.equalsIgnoreCase("Закончить")) {
                                break;
                            } else {
                                deque.addFront(input);
                            }
                        }
                    }
                    break;
                case 3:

                    System.out.print("Введите элемент для удаления из начала очереди (или 'Закончить' для завершения удаления): ");
                    while (true) {
                        String input = scanner.next();
                        if (input.equalsIgnoreCase("Закончить")) {
                            break;
                        } else {
                            try {
                                deque.removeFront();
                                System.out.println("Удален элемент из начала: " + input);
                            } catch (NoSuchElementException e) {
                                System.out.println("Такого элемента нет");
                            }
                        }
                    }
                    break;

                case 4:

                    System.out.print("Введите элемент для удаления с конца очереди (или 'Закончить' для завершения удаления): ");
                    while (true) {
                        String input = scanner.next();
                        if (input.equalsIgnoreCase("Закончить")) {
                            break;
                        } else {
                            try {
                                deque.removeRear();
                                System.out.println("Удален элемент с конца: " + input);
                            } catch (NoSuchElementException e) {
                                System.out.println("Такого элемента нет");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Размер очереди: " + deque.size());
                    break;
                case 6:
                    System.out.print("Введите индекс элемента для получения: ");
                    int indexToGet = scanner.nextInt();
                    if (!deque.isEmpty()) {
                        try {
                            Object item = deque.get(indexToGet);
                            System.out.println("Элемент по индексу " + indexToGet + ": " + item);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Индекс выходит за пределы очереди.");
                        }
                    } else {
                        System.out.println("Очередь пуста.");
                    }
                    break;
                case 7:
                    System.out.print("Введите индекс элемента для обновления: ");
                    int indexToUpdate = scanner.nextInt();
                    System.out.print("Введите новое значение: ");
                    if (scanner.hasNextInt()) {
                        int newItem = scanner.nextInt();
                        try {
                            deque.updateAtIndex(indexToUpdate, newItem);
                            System.out.println("Элемент по индексу " + indexToUpdate + " обновлен.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Индекс выходит за пределы очереди.");
                        }
                    } else {
                        System.out.println("Новое значение должно быть целым числом.");
                    }
                    break;
                case 8:
                    System.out.print("Введите элемент для поиска индекса: ");
                    if (scanner.hasNextInt()) {
                        int itemToFind = scanner.nextInt();
                        int foundIndex = deque.find(itemToFind);
                        if (foundIndex != -1) {
                            System.out.println("Индекс элемента " + itemToFind + ": " + foundIndex);
                        } else {
                            System.out.println("Элемент не найден в очереди.");
                        }
                    } else {
                        System.out.println("Поиск поддерживается только для целых чисел.");
                    }
                    break;
                case 9:
                    Object frontItem = deque.getFront();
                    if (frontItem != null) {
                        System.out.println("Первый элемент очереди: " + frontItem);
                    } else {
                        System.out.println("Очередь пуста.");
                    }
                    break;
                case 10:
                    Object rearItem = deque.getRear();
                    if (rearItem != null) {
                        System.out.println("Последний элемент очереди: " + rearItem);
                    } else {
                        System.out.println("Очередь пуста.");
                    }
                    break;
                case 11:
                    System.out.println("Содержимое очереди:");
                    for (Object item : deque) {
                        System.out.print(item + " ");
                    }
                    System.out.println();
                    break;
                case 12:
                    deque.clear();
                    System.out.println("Очередь очищена.");
                    break;
                case 13:
                    running = false;
                    break;
                default:
                    System.out.println("Неверная команда. Пожалуйста, выберите существующее действие.");
            }
        }
        scanner.close();
    }
}