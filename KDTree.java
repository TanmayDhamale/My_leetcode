class KDTree {
    static class Node {
        int[] point; // 2D or kD point
        Node left, right;

        Node(int[] point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }
    }

    static final int K = 2; // Change this to make it k-dimensional
    Node root;

    public KDTree() {
        root = null;
    }

    public Node insert(Node root, int[] point, int depth) {
        if (root == null)
            return new Node(point);

        int cd = depth % K; // Current dimension

        if (point[cd] < root.point[cd])
            root.left = insert(root.left, point, depth + 1);
        else
            root.right = insert(root.right, point, depth + 1);

        return root;
    }

    public void insert(int[] point) {
        root = insert(root, point, 0);
    }

    public boolean search(Node root, int[] point, int depth) {
        if (root == null)
            return false;

        if (arePointsSame(root.point, point))
            return true;

        int cd = depth % K;
        if (point[cd] < root.point[cd])
            return search(root.left, point, depth + 1);
        else
            return search(root.right, point, depth + 1);
    }

    public boolean search(int[] point) {
        return search(root, point, 0);
    }

    private boolean arePointsSame(int[] p1, int[] p2) {
        for (int i = 0; i < K; i++) {
            if (p1[i] != p2[i])
                return false;
        }
        return true;
    }

    public void preorder(Node root) {
        if (root != null) {
            System.out.println("(" + root.point[0] + ", " + root.point[1] + ")");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Driver
    public static void main(String[] args) {
        KDTree tree = new KDTree();
        int[][] points = {
            {3, 6}, {17, 15}, {13, 15}, {6, 12},
            {9, 1}, {2, 7}, {10, 19}
        };

        for (int[] point : points)
            tree.insert(point);

        System.out.println("Preorder traversal:");
        tree.preorder(tree.root);

        int[] query1 = {10, 19};
        int[] query2 = {12, 19};

        System.out.println("Search " + arrayToString(query1) + ": " + tree.search(query1));
        System.out.println("Search " + arrayToString(query2) + ": " + tree.search(query2));
    }

    private static String arrayToString(int[] point) {
        return "(" + point[0] + ", " + point[1] + ")";
    }
}