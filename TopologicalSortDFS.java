import java.util.*;

class TopologicalSortDFS {
    private int vertices;
    private List<List<Integer>> adjList;
    private boolean[] visited;
    private Stack<Integer> stack;

    public TopologicalSortDFS(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    private void dfs(int node) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
        stack.push(node); // Push after visiting all neighbors (postorder)
    }

    public List<Integer> topologicalSort() {
        visited = new boolean[vertices];
        stack = new Stack<>();

        // Perform DFS on all unvisited nodes
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        // Pop elements from stack to get topological order
        List<Integer> topOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topOrder.add(stack.pop());
        }

        return topOrder;
    }

    public static void main(String[] args) {
        TopologicalSortDFS graph = new TopologicalSortDFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        List<Integer> result = graph.topologicalSort();
        System.out.println("Topological Sorting (DFS): " + result);
    }
}