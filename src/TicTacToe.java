package ticTacToe;

import java.util.Scanner;


public class TicTacToe {
    public static void main(String[] arg) {
        System.out.println("Enter the dimension of the tic-tac-toe game board: ");
        int boardDimension = new Scanner(System.in).nextInt();
        while (boardDimension < 0 || boardDimension > 20) {
            System.out.println("Enter the correct dimension of the tic-tac-toe game board: ");
            boardDimension = new Scanner(System.in).nextInt();
        }
        char currentPlayer = 'X';
        char[][] board = new char[boardDimension][boardDimension];
        boolean gameRunning = true;

        while (gameRunning) {
            TicTacToe.printBoard(board);
            boolean correctMovement = move(currentPlayer, board, boardDimension);
            if (correctMovement) {
                boolean winRows = checkRows(board, currentPlayer);
                boolean winColumns = checkColumns(board, currentPlayer);
                boolean winSlant1 = checkSlant1(board, currentPlayer);
                boolean winSlant2 = checkSlant2(board, currentPlayer);
                if (winRows || winColumns || winSlant1 || winSlant2) {
                    System.out.println(
                            "Congratulations! Player " + currentPlayer + " won!");
                    gameRunning = false;
                } else {
                    gameRunning = checkBlanks(board);
                    currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                }
            }
        }
    }

    public static boolean move(char currentPlayer, char[][] board, int boardDimension) {
        System.out.println("Player with the symbol:" + currentPlayer);
        System.out.println(" Enter the row index: ");
        int row = new Scanner(System.in).nextInt();
        while (row > boardDimension || row < 0) {
            System.out.println("Enter the correct row index: ");
            row = new Scanner(System.in).nextInt();}
        System.out.println("Enter the column index: ");
        int column = new Scanner(System.in).nextInt();;
        while (column > boardDimension || column < 0) {
            System.out.println("Enter the correct column index: ");
            column = new Scanner(System.in).nextInt();}
        if (board[row][column] == 0) {
            board[row][column] = currentPlayer;
            return true;
        }
        else {
            System.out.println("This field is already taken, please choose another!");
            return false;
        }
    }

    public static void printBoard(char[][] board) {
        //number of columns
        System.out.print(" ");
        for (int i = 0; i < board.length; i++) {
            System.out.print("\t" + i );
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean checkRows(char[][] board, char currentPlayer) {
        for (int row = 0; row < board.length; row++) {
            boolean isWin = true;
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] != currentPlayer) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {return true;}
        }
        return false;
    }

    public static boolean checkColumns(char[][] board, char currentPlayer) {
        for (int row = 0; row < board.length; row++) {
            boolean isWin = true;
            for (int column = 0; column < board[row].length; column++) {
                if (board[column][row] != currentPlayer) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {return true;}
        }
        return false;
    }

    public static boolean checkSlant1(char[][] board, char currentPlayer) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][row] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSlant2(char[][] board, char currentPlayer) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][board.length - (row +1)] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkBlanks(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            boolean isBlank = false;
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] == 0) {
                    isBlank = true;
                    break;
                }
            }
            if (isBlank) {
                return true;
            }
        }
        System.out.println("Game over! No way to move!");
        return false;
    }
}
