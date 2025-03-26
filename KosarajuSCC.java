import java.util.*;

// Class to represent a directed graph using adjacency list representation
class Digraph {
    private final int V; // Number of vertices
    private List<Integer>[] adj; // Adjacency list for the graph

    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        this.V = V;
        adj = (List<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    // Returns the reverse (transpose) of the digraph
    public Digraph reverse() {
        Digraph reversed = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                reversed.addEdge(w, v);
            }
        }
        return reversed;
    }
}

public class KosarajuSCC {
    private boolean[] marked; // marked[v] = has vertex v been visited?
    private int[] id;         // id[v] = id of the SCC containing vertex v
    private int count;        // Number of strongly connected components
    private Mystack<Integer> stack; // Stack to store vertices in order of finishing time

    public KosarajuSCC(Digraph G) {
        // Step 1: Compute finishing times using DFS on the original graph
        marked = new boolean[G.V()];
        stack = new Mystack<>(); // Use java.util.Stack with generics
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }

        // Step 2: Compute SCCs using DFS on the reversed graph
        Digraph reversed = G.reverse();
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;

        // Process vertices in order of decreasing finishing time
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!marked[v]) {
                dfsReversed(reversed, v);
                count++;
            }
        }
    }

    // DFS on the original graph to compute finishing times
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        stack.push(v); // Push vertex to stack after exploring all its neighbors
    }

    // DFS on the reversed graph to identify SCCs
    private void dfsReversed(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfsReversed(G, w);
            }
        }
    }

    // Returns the number of strongly connected components
    public int count() {
        return count;
    }

    // Are vertices v and w in the same SCC?
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    // Returns the SCC id of vertex v
    public int id(int v) {
        return id[v];
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example graph with 5 vertices
        Digraph G = new Digraph(5);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 0);
        G.addEdge(1, 3);
        G.addEdge(3, 4);

        KosarajuSCC scc = new KosarajuSCC(G);
        System.out.println("Number of strongly connected components: " + scc.count());

        // Print the SCCs
        for (int i = 0; i < scc.count(); i++) {
            System.out.print("SCC " + i + ": ");
            for (int v = 0; v < G.V(); v++) {
                if (scc.id(v) == i) {
                    System.out.print(v + " ");
                }
            }
            System.out.println();
        }
    }
}