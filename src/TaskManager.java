import java.util.Scanner;

public class TaskManager {
    private final DoubleEndedQueue<Task> tasks;
    private boolean defaultTasksAdded;

    public TaskManager(DoubleEndedQueue<Task> tasks) {
        this.tasks = tasks;
        this.defaultTasksAdded = false;
    }

    public void addPriorityTask(Task task) {
        task.setPriority(true);
        tasks.addFront(task);
        System.out.println("Приоритетная задача добавлена: " + task.getTitle());
    }

    public void addRegularTask(Task task) {
        tasks.addRear(task);
        System.out.println("Обычная задача добавлена: " + task.getTitle());
    }

    public void performPriorityTask() {
        Task task;
        boolean found = false;

        while (!tasks.isEmpty()) {
            task = tasks.removeFront();

            if (task.isPriority()) {
                System.out.println("Выполнена приоритетная задача: " + task.getTitle());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Нет приоритетных задач для выполнения.");
        }
    }

    public void performRegularTask() {
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для выполнения.");
            return;
        }

        Task task;
        while (true) {
            task = tasks.removeFront();
            if (task == null) {
                System.out.println("Нет обычных задач для выполнения.");
                break;
            } else if (!task.isPriority()) {
                System.out.println("Выполнена обычная задача: " + task.getTitle());
                break;
            }
        }
    }

    public void displayTasks() {
        System.out.println("Список задач:");

        System.out.println("Приоритетные задачи:");
        for (Task task : tasks) {
            if (task.isPriority()) {
                System.out.println(task);
            }
        }

        System.out.println("\nОбычные задачи:");
        for (Task task : tasks) {
            if (!task.isPriority()) {
                System.out.println(task);
            }
        }
    }

    public void manageTasks(Scanner scanner) {
        boolean taskManagement = true;
        while (taskManagement) {
            System.out.println("\nУправление задачами:");
            System.out.println("1. Добавить задачи по умолчанию");
            System.out.println("2. Создать приоритетную задачу");
            System.out.println("3. Создать обычную задачу");
            System.out.println("4. Выполнить приоритетную задачу");
            System.out.println("5. Выполнить обычную задачу");
            System.out.println("6. Вывести список задач");
            System.out.println("7. Вернуться в главное меню");

            int taskChoice = scanner.nextInt();
            scanner.nextLine();

            switch (taskChoice) {
                case 1:
                    addDefaultTasks();
                    break;
                case 2:
                    System.out.print("Введите название приоритетной задачи: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите описание приоритетной задачи: ");
                    String description = scanner.nextLine();
                    Task priorityTask = new Task(title, description);
                    addPriorityTask(priorityTask);
                    break;
                case 3:
                    System.out.print("Введите название обычной задачи: ");
                    String regTitle = scanner.nextLine();
                    System.out.print("Введите описание обычной задачи: ");
                    String regDescription = scanner.nextLine();
                    Task regularTask = new Task(regTitle, regDescription);
                    addRegularTask(regularTask);
                    break;
                case 4:
                    performPriorityTask();
                    break;
                case 5:
                    performRegularTask();
                    break;
                case 6:
                    displayTasks();
                    break;
                case 7:
                    taskManagement = false;
                    break;
                default:
                    System.out.println("Неверная команда. Пожалуйста, выберите существующее действие.");
            }
        }
    }

    private void addDefaultTasks() {
        if (defaultTasksAdded) {
            System.out.println("Элементы по умолчанию уже добавлены.");
        } else {
            addRegularTask(new Task("Подготовить презентацию для встречи с клиентом", "Подготовить материалы и слайды для презентации."));
            addRegularTask(new Task("Заказать офисные принадележности", "Проверить запасы офисных принадележностей и сделать заказ."));
            addRegularTask(new Task("Подготовить отчет о финансовых показателях", "Собрать и проанализировать финансовые данные для отчета."));
            addRegularTask(new Task("Провести собрание сотрудников", "Организовать собрание для обсуждения текущих задач и планов."));
            addRegularTask(new Task("Составить план мероприятия на выходные", "Подготовить расписание мероприятия на выходные дни."));
            addRegularTask(new Task("Ответить на клиентские запросы по электронной почте", "Проверить и ответить на важные запросы от клиентов."));

            addPriorityTask(new Task("Срочно разработать стратегию маркетинга", "Проработать стратегию для ближайших месяцев."));
            addPriorityTask(new Task("Организовать важное совещание с инвесторами", "Подготовить и провести совещание с ключевыми инвесторами."));
            addPriorityTask(new Task("Закупить необходимое оборудование для проекта", "Найти и закупить необходимое оборудование для текущего проекта."));

            defaultTasksAdded = true;
            System.out.println("Элементы по умолчанию успешно добавлены.");
        }
    }
}
