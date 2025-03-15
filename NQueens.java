import java.util.*;

class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize board with empty spaces
        for (char[] row : board) Arrays.fill(row, '.');

        // Start backtracking from the first row
        backtrack(results, board, 0, n);
        return results;
    }

    private static void backtrack(List<List<String>> results, char[][] board, int row, int n) {
        if (row == n) { // Base case: All queens placed
            results.add(construct(board)); // Save solution
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) { // Check safety
                board[row][col] = 'Q'; // Place queen
                backtrack(results, board, row + 1, n); // Recur to next row
                board[row][col] = '.'; // Backtrack (remove queen)
            }
        }
    }

    private static boolean isSafe(char[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;

        // Check left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        // Check right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q') return false;

        return true;
    }

    private static List<String> construct(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board)
            solution.add(new String(row));
        return solution;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> solutions = solveNQueens(n);

        System.out.println("Total Solutions: " + solutions.size());
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}