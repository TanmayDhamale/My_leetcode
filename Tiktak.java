import java.util.Scanner;

public class Tiktak {
    static char[][] board = new char[3][3];
    static char player1 = 'X';
    static char player2 = 'O';
    static char currentPlayer = player2; // Start with player2 so player1 goes first
    static int row, column;
    static boolean gameEnded = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tic Tac Toe");
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
        
        initializeBoard();

        int moves = 0; // Count moves for draw condition

        while (!gameEnded && moves < 9) {
            displayBoard();
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            System.out.println("Current Player: " + currentPlayer);

            System.out.print("Enter row and column (0-2): ");
            row = sc.nextInt();
            column = sc.nextInt();

            if (isValidMove(row, column)) {
                board[row][column] = currentPlayer;
                moves++;

                if (checkWin()) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (moves == 9) {
                    displayBoard();
                    System.out.println("It's a draw!");
                }
            } else {
                System.out.println("Invalid move. Try again.");
                currentPlayer = (currentPlayer == player1) ? player2 : player1; // Let the same player try again
            }
        }

        sc.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    static void displayBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer &&
                 board[i][1] == currentPlayer &&
                 board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer &&
                 board[1][i] == currentPlayer &&
                 board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }
}