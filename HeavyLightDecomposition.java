import java.util.*;

public class HeavyLightDecomposition {
    static final int MAXN = 100005;
    static List<Integer>[] tree = new ArrayList[MAXN];
    static int[] size = new int[MAXN];
    static int[] depth = new int[MAXN];
    static int[] parent = new int[MAXN];
    static int[] heavy = new int[MAXN];
    static int[] head = new int[MAXN];
    static int[] pos = new int[MAXN];
    static int[] value = new int[MAXN]; // Node values for segment tree
    static int[] segTree;
    static int currentPos = 0;
    static int n;

    public static void main(String[] args) {
        n = 9; // number of nodes

        // Initialize tree
        for (int i = 0; i <= n; i++) tree[i] = new ArrayList<>();

        // Sample Tree Edges
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(2, 5);
        addEdge(3, 6);
        addEdge(6, 7);
        addEdge(6, 8);
        addEdge(6, 9);

        // Sample node values
        for (int i = 1; i <= n; i++) value[i] = i * 10;

        Arrays.fill(heavy, -1);

        dfs(1, 0);
        decompose(1, 1);

        // Build segment tree
        segTree = new int[4 * n];
        buildSegmentTree(1, 0, n - 1);

        // Query example: Max value from node 4 to node 9
        System.out.println("Max from 4 to 9: " + query(4, 9));
    }

    static void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    static int dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int maxSubtree = 0;
        for (int v : tree[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            int subtree = dfs(v, u);
            size[u] += subtree;
            if (subtree > maxSubtree) {
                maxSubtree = subtree;
                heavy[u] = v;
            }
        }
        return size[u];
    }

    static void decompose(int u, int h) {
        head[u] = h;
        pos[u] = currentPos++;

        if (heavy[u] != -1) decompose(heavy[u], h);
        for (int v : tree[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    // Segment Tree Operations
    static void buildSegmentTree(int node, int l, int r) {
        if (l == r) {
            segTree[node] = value[getNodeAtPosition(l)];
        } else {
            int mid = (l + r) / 2;
            buildSegmentTree(2 * node, l, mid);
            buildSegmentTree(2 * node + 1, mid + 1, r);
            segTree[node] = Math.max(segTree[2 * node], segTree[2 * node + 1]);
        }
    }

    static int querySegmentTree(int node, int l, int r, int ql, int qr) {
        if (qr < l || ql > r) return Integer.MIN_VALUE;
        if (ql <= l && r <= qr) return segTree[node];
        int mid = (l + r) / 2;
        return Math.max(
            querySegmentTree(2 * node, l, mid, ql, qr),
            querySegmentTree(2 * node + 1, mid + 1, r, ql, qr)
        );
    }

    static int query(int u, int v) {
        int res = Integer.MIN_VALUE;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            res = Math.max(res, querySegmentTree(1, 0, n - 1, pos[head[u]], pos[u]));
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        return Math.max(res, querySegmentTree(1, 0, n - 1, pos[u], pos[v]));
    }

    static int getNodeAtPosition(int p) {
        for (int i = 1; i <= n; i++) {
            if (pos[i] == p) return i;
        }
        return -1;
    }
}