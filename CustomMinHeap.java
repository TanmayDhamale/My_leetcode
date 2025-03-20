class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }

    public void insert(int value) {
        if (size == capacity) return; // Heap is full
        heap[size] = value;
        int current = size;

        while (current > 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public int extractMin() {
        if (size == 0) return Integer.MAX_VALUE;
        int min = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        return min;
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < size && heap[left] < heap[smallest]) smallest = left;
        if (right < size && heap[right] < heap[smallest]) smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

public class CustomMinHeap {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(10);
        minHeap.insert(40);
        minHeap.insert(20);
        minHeap.insert(5);

        System.out.print("Heap Elements: ");
        minHeap.printHeap();

        System.out.println("Min Element Removed: " + minHeap.extractMin());

        System.out.print("Heap After Deletion: ");
        minHeap.printHeap();
    }
}