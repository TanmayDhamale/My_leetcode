import java.util.*;

class TarjanSCC {
    private int time = 0; // Discovery time tracker
    private final List<List<Integer>> graph;
    private final int[] discovery, low;
    private final boolean[] inStack;
    private final Stack<Integer> stack; // ✅ Fixed Stack declaration
    private final List<List<Integer>> sccs; // List of SCCs

    public TarjanSCC(int vertices) {
        graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) graph.add(new ArrayList<>());
        discovery = new int[vertices];
        low = new int[vertices];
        inStack = new boolean[vertices];
        stack = new Stack<>(); // ✅ Fixed: Use correct generic type
        sccs = new ArrayList<>();
        Arrays.fill(discovery, -1); // Mark as unvisited
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }

    private void dfs(int u) {
        discovery[u] = low[u] = ++time;
        stack.push(u);
        inStack[u] = true;

        for (int v : graph.get(u)) {
            if (discovery[v] == -1) { // If v is not visited
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) { // If v is in stack, update low-link
                low[u] = Math.min(low[u], discovery[v]);
            }
        }

        // If u is the root of an SCC, pop all nodes in SCC
        if (low[u] == discovery[u]) {
            List<Integer> scc = new ArrayList<>();
            int node;
            do {
                node = stack.pop();
                inStack[node] = false;
                scc.add(node);
            } while (node != u);
            sccs.add(scc);
        }
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < graph.size(); i++) {
            if (discovery[i] == -1) dfs(i);
        }
        return sccs;
    }

    public static void main(String[] args) {
        TarjanSCC tarjan = new TarjanSCC(5);
        tarjan.addEdge(1, 0);
        tarjan.addEdge(0, 2);
        tarjan.addEdge(2, 1);
        tarjan.addEdge(0, 3);
        tarjan.addEdge(3, 4);

        List<List<Integer>> sccs = tarjan.findSCCs();
        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }
    }
}