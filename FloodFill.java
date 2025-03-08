import java.util.Arrays;

class FloodFill {
    public static void floodFill(int[][] image, int sr, int sc, int newColor) {
        int rows = image.length, cols = image[0].length;
        int oldColor = image[sr][sc];

        if (oldColor != newColor) {
            dfs(image, sr, sc, oldColor, newColor, rows, cols);
        }
    }

    private static void dfs(int[][] image, int x, int y, int oldColor, int newColor, int rows, int cols) {
        if (x < 0 || y < 0 || x >= rows || y >= cols || image[x][y] != oldColor) {
            return;
        }

        image[x][y] = newColor;

        dfs(image, x + 1, y, oldColor, newColor, rows, cols);
        dfs(image, x - 1, y, oldColor, newColor, rows, cols);
        dfs(image, x, y + 1, oldColor, newColor, rows, cols);
        dfs(image, x, y - 1, oldColor, newColor, rows, cols);
    }

    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 2, 2},
            {0, 0, 2, 2}
        };

        int sr = 1, sc = 1, newColor = 3;
        
        System.out.println("Original Image:");
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }

        floodFill(image, sr, sc, newColor);

        System.out.println("\nFlood Filled Image:");
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }
}
