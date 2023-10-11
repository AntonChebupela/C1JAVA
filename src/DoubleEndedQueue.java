import java.util.NoSuchElementException;

public class DoubleEndedQueue<T> {
    private Node<T> front; // Голова очереди
    private Node<T> rear;  // Хвост очереди
    private int size;

    // Внутренний класс для представления элемента очереди
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }

    // Конструктор для создания пустой очереди
    public DoubleEndedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Добавить элемент в начало очереди
    public void addFront(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    public void addRear(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }
    // Удалить элемент с начала очереди
    public T removeFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        T data = front.data;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            if (front != null) {
                front.prev = null;
            }
        }
        size--;
        return data;
    }

    // Удалить элемент с конца очереди
    public T removeRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        T data = rear.data;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            rear = rear.prev;
            if (rear != null) {
                rear.next = null;
            }
        }
        size--;
        return data;
    }


    // Добавить элемент в произвольное место очереди по индексу
    public void addAtIndex(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        if (index == 0) {
            addFront(item);
        } else if (index == size) {
            addRear(item);
        } else {
            Node<T> newNode = new Node<>(item);
            Node<T> current = front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    // Найти индекс первого вхождения элемента в очереди (с начала)
    public int find(T item) {
        Node<T> current = front;
        int index = 0;
        while (current != null) {
            if (current.data.equals(item)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Элемент не найден
    }

    // Обновить элемент в очереди по индексу
    public void updateAtIndex(int index, T newItem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = newItem;
    }

    // Получить первый элемент очереди
    public T getFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        return front.data;
    }

    // Получить последний элемент очереди
    public T getRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        return rear.data;
    }

    // Очистить очередь
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    // Проверить, содержит ли очередь указанный элемент
    public boolean contains(T item) {
        return find(item) != -1;
    }


    // Проверить, пуста ли очередь
    public boolean isEmpty() {
        return size == 0;
    }

    // Получить размер очереди
    public int size() {
        return size;
    }

    // Вывести содержимое очереди
    public void printQueue() {
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Получить элемент по индексу
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void removeAtIndex(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            throw new NoSuchElementException("Индекс выходит за пределы очереди.");
        }

        if (index == 0) {
            removeFront();
        } else if (index == size() - 1) {
            removeRear();
        } else {
            Node<T> current = front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current.next = null;
            current.prev = null;
            size--;
        }
    }

}
