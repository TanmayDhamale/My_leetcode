class PersistentSegmentTree {
    static class Node {
        int sum;
        Node left, right;

        Node(int sum, Node left, Node right) {
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }

    private int size;
    private List<Node> versions;

    public PersistentSegmentTree(int[] arr) {
        size = arr.length;
        versions = new ArrayList<>();
        Node root = build(arr, 0, size - 1);
        versions.add(root); // Version 0
    }

    private Node build(int[] arr, int l, int r) {
        if (l == r) {
            return new Node(arr[l], null, null);
        }
        int mid = (l + r) / 2;
        Node left = build(arr, l, mid);
        Node right = build(arr, mid + 1, r);
        return new Node(left.sum + right.sum, left, right);
    }

    // Create a new version after updating index i to val
    public void update(int version, int i, int val) {
        Node newRoot = update(versions.get(version), 0, size - 1, i, val);
        versions.add(newRoot);
    }

    private Node update(Node node, int l, int r, int i, int val) {
        if (l == r) {
            return new Node(val, null, null); // New node with updated value
        }
        int mid = (l + r) / 2;
        if (i <= mid) {
            Node left = update(node.left, l, mid, i, val);
            return new Node(left.sum + node.right.sum, left, node.right);
        } else {
            Node right = update(node.right, mid + 1, r, i, val);
            return new Node(node.left.sum + right.sum, node.left, right);
        }
    }

    // Query sum in range [ql, qr] on specific version
    public int query(int version, int ql, int qr) {
        return query(versions.get(version), 0, size - 1, ql, qr);
    }

    private int query(Node node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0; // No overlap
        if (ql <= l && r <= qr) return node.sum; // Complete overlap

        int mid = (l + r) / 2;
        return query(node.left, l, mid, ql, qr) + query(node.right, mid + 1, r, ql, qr);
    }

    // Get total number of versions
    public int getVersionCount() {
        return versions.size();
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        PersistentSegmentTree pst = new PersistentSegmentTree(arr);

        System.out.println("Version 0: Sum of [1,3] = " + pst.query(0, 1, 3)); // 2+3+4 = 9

        pst.update(0, 2, 10); // Update index 2 to 10 → new version (version 1)
        System.out.println("Version 1: Sum of [1,3] = " + pst.query(1, 1, 3)); // 2+10+4 = 16
        System.out.println("Version 0 still gives: Sum of [1,3] = " + pst.query(0, 1, 3)); // 9

        pst.update(1, 4, 0); // Update index 4 to 0 → new version (version 2)
        System.out.println("Version 2: Sum of [3,4] = " + pst.query(2, 3, 4)); // 4+0 = 4
    }
}