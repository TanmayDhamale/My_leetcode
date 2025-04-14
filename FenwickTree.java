class FenwickTree {
    private int[] tree;
    private int n;

    // Constructor
    public FenwickTree(int size) {
        n = size;
        tree = new int[n + 1]; // 1-based indexing
    }

    // Add value to index (0-based index)
    public void update(int index, int value) {
        index += 1; // Convert to 1-based
        while (index <= n) {
            tree[index] += value;
            index += index & -index; // Move to parent
        }
    }

    // Get prefix sum from 0 to index
    public int query(int index) {
        index += 1; // Convert to 1-based
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index; // Move to ancestor
        }
        return sum;
    }

    // Get sum in range [left, right]
    public int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    // Build BIT from an array
    public void build(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            update(i, arr[i]);
        }
    }

    // Display internal tree (for debug)
    public void printTree() {
        for (int i = 1; i <= n; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    // Main method to test
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);

        System.out.println("Prefix sum till index 3: " + ft.query(3)); // 1+2+3+4 = 10
        System.out.println("Range sum [1, 3]: " + ft.rangeQuery(1, 3)); // 2+3+4 = 9

        ft.update(2, 2); // Add 2 at index 2 (0-based)
        System.out.println("After update: Range sum [1, 3]: " + ft.rangeQuery(1, 3)); // 2+5+4 = 11
    }
}