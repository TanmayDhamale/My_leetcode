class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') { // Empty cell found
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place the number

                            if (solve(board)) return true; // Recur to solve further
                            
                            board[row][col] = '.'; // Backtrack if invalid
                        }
                    }
                    return false; // No valid number found, trigger backtracking
                }
            }
        }
        return true; // Solved successfully
    }

    private static boolean isValid(char[][] board, int row, int col, char num) {
        // Check row & column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        // Check 3x3 subgrid
        int subGridRow = (row / 3) * 3, subGridCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[subGridRow + i][subGridCol + j] == num) return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println("Original Sudoku:");
        printBoard(board);
        solveSudoku(board);
        System.out.println("\nSolved Sudoku:");
        printBoard(board);
    }
}