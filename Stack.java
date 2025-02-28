class Stack {
    private int arr[];
    private int top;
    private int capacity;

    // Constructor to initialize stack
    public Stack(int size) {
        arr = new int[size]; // Create stack array
        capacity = size;
        top = -1;
    }

    // Push: Add element to stack
    public void push(int item) {
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push " + item);
            return;
        }
        arr[++top] = item;
        System.out.println("Pushed: " + item);
    }

    // Pop: Remove and return top element
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! No element to pop.");
            return -1;
        }
        return arr[top--];
    }

    // Peek: Get top element without removing it
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return arr[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Print stack elements
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5); // Stack of size 5

        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.printStack(); // Print stack

        System.out.println("Top Element (Peek): " + stack.peek());

        System.out.println("Popped Element: " + stack.pop());
        stack.printStack();

        System.out.println("Popped Element: " + stack.pop());
        System.out.println("Popped Element: " + stack.pop());

        stack.pop(); // Trying to pop from empty stack
    }
}