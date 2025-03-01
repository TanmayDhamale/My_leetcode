class Queue {
    private int arr[];
    private int front, rear, capacity, size;

    // Constructor to initialize queue
    public Queue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Enqueue: Add element to the queue
    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue Overflow! Cannot enqueue " + item);
            return;
        }
        rear = (rear + 1) % capacity; // Circular increment
        arr[rear] = item;
        size++;
        System.out.println("Enqueued: " + item);
    }

    // Dequeue: Remove and return front element
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! No element to dequeue.");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity; // Circular increment
        size--;
        return item;
    }

    // Peek: Get the front element without removing it
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[front];
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Print queue elements
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Queue: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue queue = new Queue(5); // Queue of size 5

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.printQueue(); // Print queue

        System.out.println("Front Element (Peek): " + queue.peek());

        System.out.println("Dequeued Element: " + queue.dequeue());
        queue.printQueue();

        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60); // This will fill the queue

        queue.printQueue();

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue(); // Trying to dequeue from empty queue
    }
}