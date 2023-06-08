package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wpisz rozmiar planszy (np. 10): ");
        int size = scanner.nextInt();
        int[][] board = createBoard(size);

        System.out.print("Wpisz zasady gry (np. 23/3):");
        String rulesOfTheGame = scanner.next();
        int[] rules = parseRules(rulesOfTheGame);

        System.out.print("Wpisz liczbe iteracji: ");
        int iterations = scanner.nextInt();

        printBoard(board);

        for (int i = 1; i < iterations; i++) {
            board = updateBoard(board, rules);
            printBoard(board);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // metoda tworzaca tablice intow
    private static int[][] createBoard(int size) {
        int[][] board = new int[size][size];
        int center = size / 2;
        for (int i = -1; i <= 1; i++) {
            board[center + i][center] = 1;
        }
        return board;
    }

    // metoda ktora zwraca tablice intow
    private static int[] parseRules(String rulesString) {
        return Arrays.stream(rulesString.split("/"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    // metoda ktora iteruje po tablicy i zwraca tablice intow zywych sasiadow
    private static int[][] updateBoard(int[][] board, int[] rules) {
        int size = board.length;
        int[][] nextBoard = new int[size][size];
        for (int i = 0; i < size * size; i++) {
            int row = i / size, col = i % size;
            int numbers = getNumbers(board, row, col);
            if (board[row][col] == 1 && (numbers == 2 || numbers == 3) || board[row][col] == 0 && numbers == rules[1]) {
                nextBoard[row][col] = 1;
            }
        }
        return nextBoard;
    }

    // metoda zwracajaca liczbe zywych sasiadow
    private static int getNumbers(int[][] board, int i, int j) {
        int numbers = 0;
        int size = board.length;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                numbers += board[(i + x + size) % size][(j + y + size) % size];
            }
        }
        numbers -= board[i][j];
        return numbers;
    }

    private static void printBoard(int[][] board) {
        System.out.println();
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}