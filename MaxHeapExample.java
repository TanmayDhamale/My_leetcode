import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeapExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap

        // Insert elements
        maxHeap.add(10);
        maxHeap.add(40);
        maxHeap.add(20);
        maxHeap.add(5);

        System.out.println("Max Heap Elements: " + maxHeap);

        // Extract max element (root)
        System.out.println("Max Element Removed: " + maxHeap.poll());

        // Peek (Get max element without removing)
        System.out.println("Max Element: " + maxHeap.peek());

        System.out.println("Max Heap after deletion: " + maxHeap);
    }
}