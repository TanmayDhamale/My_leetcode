class SegmentTree {
    private int[] tree, lazy;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];  // Allocate enough space
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    // Build the segment tree
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Propagate pending updates
    private void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node]; // Apply the update
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    // Range update: add value to all elements in [l, r]
    public void updateRange(int l, int r, int val) {
        updateRange(0, 0, n - 1, l, r, val);
    }

    private void updateRange(int node, int start, int end, int l, int r, int val) {
        propagate(node, start, end);
        if (start > r || end < l) return; // No overlap
        if (start >= l && end <= r) { // Total overlap
            lazy[node] += val;
            propagate(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        updateRange(2 * node + 1, start, mid, l, r, val);
        updateRange(2 * node + 2, mid + 1, end, l, r, val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range query: sum of elements in [l, r]
    public int queryRange(int l, int r) {
        return queryRange(0, 0, n - 1, l, r);
    }

    private int queryRange(int node, int start, int end, int l, int r) {
        propagate(node, start, end);
        if (start > r || end < l) return 0; // No overlap
        if (start >= l && end <= r) return tree[node]; // Total overlap

        int mid = (start + end) / 2;
        int leftSum = queryRange(2 * node + 1, start, mid, l, r);
        int rightSum = queryRange(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    // Print current tree (for debugging)
    public void printTree() {
        for (int i = 0; i < 4 * n; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    // Test
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segTree = new SegmentTree(arr);

        System.out.println("Initial sum of [1, 3]: " + segTree.queryRange(1, 3)); // 3+5+7 = 15

        segTree.updateRange(1, 3, 3); // Add 3 to range [1,3]
        System.out.println("After update: sum of [1, 3]: " + segTree.queryRange(1, 3)); // (6+8+10) = 24

        System.out.println("Sum of [0, 5]: " + segTree.queryRange(0, 5)); // 1 + 6 + 8 + 10 + 9 + 11 = 45
    }
}