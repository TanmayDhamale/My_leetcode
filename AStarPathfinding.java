import java.util.*;

class Node implements Comparable<Node> {
    public int x, y;
    public int g; // Cost from start to current node
    public int h; // Heuristic cost to goal
    public Node parent;

    public Node(int x, int y, Node parent, int g, int h) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    public int f() {
        return g + h;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f(), other.f());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node n = (Node) o;
            return this.x == n.x && this.y == n.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class AStarPathfinding {
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}}; // 4 directions: down, up, right, left

    public static List<Node> aStar(int[][] grid, int[] start, int[] end) {
        int rows = grid.length, cols = grid[0].length;
        PriorityQueue<Node> open = new PriorityQueue<>();
        Set<Node> closed = new HashSet<>();

        Node startNode = new Node(start[0], start[1], null, 0, heuristic(start, end));
        open.add(startNode);

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current.x == end[0] && current.y == end[1]) {
                return reconstructPath(current);
            }

            closed.add(current);

            for (int[] dir : directions) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || grid[nx][ny] == 1)
                    continue; // Out of bounds or blocked

                Node neighbor = new Node(nx, ny, current, current.g + 1, heuristic(new int[]{nx, ny}, end));

                if (closed.contains(neighbor)) continue;

                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                }
            }
        }

        return new ArrayList<>(); // No path found
    }

    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static int heuristic(int[] a, int[] b) {
        // Manhattan distance
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}
        };

        int[] start = {0, 0};
        int[] end = {4, 4};

        List<Node> path = aStar(grid, start, end);

        System.out.println("Path from start to end:");
        for (Node n : path) {
            System.out.print("[" + n.x + "," + n.y + "] ");
        }
    }
}