import java.util.*;

class TopologicalSortBFS {
    private int vertices;
    private List<List<Integer>> adjList;
    private int[] indegree;

    public TopologicalSortBFS(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        indegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        indegree[v]++;
    }

    public List<Integer> topologicalSort() {
        Queue1<Integer> queue = new LinkedList<Integer>(); // FIX: Explicit Type Argument
        List<Integer> topOrder = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topOrder.add(node);

            for (int neighbor : adjList.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return topOrder;
    }

    public static void main(String[] args) {
        TopologicalSortBFS graph = new TopologicalSortBFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        List<Integer> result = graph.topologicalSort();
        System.out.println("Topological Sorting (BFS): " + result);
    }
}