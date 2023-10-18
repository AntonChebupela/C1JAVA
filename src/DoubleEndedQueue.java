import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleEndedQueue<T> implements Iterable<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }

    public DoubleEndedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

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


    private class DoubleEndedIterator implements Iterator<T> {
        private Node<T> current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleEndedIterator();
    }

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
        return -1;
    }


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


    public T getFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        return front.data;
    }


    public T getRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        return rear.data;
    }


    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }


    public boolean contains(T item) {
        return find(item) != -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


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
