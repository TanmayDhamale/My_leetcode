import java.util.PriorityQueue;

public class MinHeapExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min Heap

        // Insert elements
        minHeap.add(10);
        minHeap.add(40);
        minHeap.add(20);
        minHeap.add(5);

        System.out.println("Min Heap Elements: " + minHeap);

        // Extract min element (root)
        System.out.println("Min Element Removed: " + minHeap.poll());

        // Peek (Get min element without removing)
        System.out.println("Min Element: " + minHeap.peek());

        System.out.println("Min Heap after deletion: " + minHeap);
    }
}