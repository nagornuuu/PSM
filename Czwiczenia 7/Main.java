import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        double[][] board = new double[41][41];

        // Fill top of table with 200
        Arrays.fill(board[0], 200);

        // Fill bottom of table with 150
        Arrays.fill(board[board.length - 1], 150);

        // Fill middle of table with 100 and 50
        for (int i = 1; i < board.length - 1; i++) {
            board[i][0] = 100;
            board[i][board[i].length - 1] = 50;
        }

        // Perform calculations on table
        for (int k = 0; k < 1000; k++) {
            for (int i = 1; i < board.length - 1; i++) {
                for (int j = 1; j < board[i].length - 1; j++) {
                    board[i][j] = (board[i - 1][j] + board[i + 1][j] + board[i][j - 1] + board[i][j + 1]) / 4;
                }
            }
        }

        // Print table
        for (double[] row : board) {
            for (double value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }

        // Save table to CSV file
        try (PrintWriter printWriter = new PrintWriter("result.csv")) {
            for (double[] row : board) {
                String rowString = Arrays.stream(row)
                        .mapToObj(Double::toString)
                        .collect(Collectors.joining(", "));
                printWriter.println(rowString);
            }
            System.out.println("CSV file saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving CSV file: " + e.getMessage());
        }
    }
}