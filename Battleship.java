//I worked on the homework assignment alone, using only course materials.
import java.util.Scanner;
public class Battleship {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Battleship!\n");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        char[][] p1Board = new char[5][5];
        char[][] p2Board = new char[5][5];
        char[][] p1BoardTH = new char[5][5];
        char[][] p2BoardTH = new char[5][5];
        blankGen(p1Board);
        blankGen(p2Board);
        blankGen(p1BoardTH);
        blankGen(p2BoardTH);
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter ship " + (i + 1) + " location: ");
            int p1r = input.nextInt();
            int p1c = input.nextInt();
            if (!coordCheck(p1r, p1c)) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                i--;
            } else {
                if (p1Board[p1r][p1c] != '-') {
                    System.out.println("You already have a ship there. Choose different coordinates.");
                    i--;
                } else {
                    p1Board[p1r][p1c] = '@';
                }
            }
        }
        printBattleShip(p1Board);
        blankLines();
        // blank lines
        System.out.println("\nPLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter ship " + (i + 1) + " location: ");
            int p2r = input.nextInt();
            int p2c = input.nextInt();
            if (!coordCheck(p2r, p2c)) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                i--;
            } else {
                if (p2Board[p2r][p2c] != '-') {
                    System.out.println("You already have a ship there. Choose different coordinates.");
                    i--;
                } else {
                    p2Board[p2r][p2c] = '@';
                }
            }
        }
        printBattleShip(p2Board);
        blankLines();
        boolean winner = false;
        while (!winner) {
            boolean invalid = false;
            boolean turn1 = true;
            boolean turn2 = false;
            while (turn1) {
                System.out.println("Player 1, enter hit row/column: ");
                int p1rHit = input.nextInt();
                int p1cHit = input.nextInt();
                if (!(coordCheck(p1rHit, p1cHit))) { //means coordCheck is invalid
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                } else {
                    if (p1BoardTH[p1rHit][p1cHit] == 'O') {
                        invalid = true;
                        while (invalid) {
                            System.out.println("You already fired on this spot. Choose different coordinates.");
                            if (coordCheck(p1rHit, p1cHit)) {
                                invalid = false;
                            }
                        }
                    } else if (p1BoardTH[p1rHit][p1cHit] == 'X') {
                        invalid = true;
                        System.out.println("You already fired on this spot. Choose different coordinates.");
                        if (coordCheck(p1rHit, p1cHit)) {
                            invalid = false;
                        }
                    } else if (p2Board[p1rHit][p1cHit] != '@') {
                        System.out.println("PLAYER 1 MISSED!");
                        p1BoardTH[p1rHit][p1cHit] = 'O';
                        p2Board[p1rHit][p1cHit] = 'O';
                        printBattleShip(p1BoardTH);
                        System.out.println("\n");
                        turn1 = false;
                        turn2 = true;
                    } else if (p2Board[p1rHit][p1cHit] == '@') {
                        System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                        p1BoardTH[p1rHit][p1cHit] = 'X';
                        p2Board[p1rHit][p1cHit] = 'X';
                        printBattleShip(p1BoardTH);
                        System.out.println("\n");
                        if (!boardCheck(p2Board)) {
                            winnerPrint(1, p1Board, p2Board);
                            winner = true;
                            break;
                        }
                        turn1 = false;
                        turn2 = true;
                    } else {
                        System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                        winner = true;
                    }
                }
            }
            while (turn2) {
                System.out.println("Player 2, enter hit row/column: ");
                int p2rHit = input.nextInt();
                int p2cHit = input.nextInt();
                if (!coordCheck(p2rHit, p2cHit)) { //means coordCheck is invalid
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                } else {
                    if (p2BoardTH[p2rHit][p2cHit] == 'O') {
                        invalid = true;
                        while (invalid) {
                            System.out.println("You already fired on this spot. Choose different coordinates.");
                            if (coordCheck(p2rHit, p2cHit)) {
                                invalid = false;
                            }
                        }
                    } else if (p2BoardTH[p2rHit][p2cHit] == 'X') {
                        invalid = true;
                        System.out.println("You already fired on this spot. Choose different coordinates.");
                        if ((coordCheck(p2rHit, p2cHit))) {
                            invalid = false;
                        }
                    } else if (p1Board[p2rHit][p2cHit] != '@') {
                        System.out.println("PLAYER 2 MISSED!");
                        p2BoardTH[p2rHit][p2cHit] = 'O';
                        p1Board[p2rHit][p2cHit] = 'O';
                        printBattleShip(p2BoardTH);
                        System.out.println("\n");
                        turn2 = false;
                        turn1 = true;
                    } else if (p1Board[p2rHit][p2cHit] == '@') {
                        System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                        p2BoardTH[p2rHit][p2cHit] = 'X';
                        p1Board[p2rHit][p2cHit] = 'X';
                        printBattleShip(p2BoardTH);
                        System.out.println("\n");
                        if (!boardCheck(p1Board)) {
                            winnerPrint(2, p1Board, p2Board);
                            winner = true;
                        }
                        turn2 = false;
                        turn1 = true;
                    }
                }
            }

        }
    }

    public static void winnerPrint(int winnerNum, char[][] p1Board, char[][] p2Board) {
        System.out.println("PLAYER " + winnerNum + " WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
        System.out.println("Final boards: \n");
        printBattleShip(p1Board);
        System.out.println("\n");
        printBattleShip(p2Board);
    }

    public static void blankLines() {
        int count = 1;
        do {
            System.out.println("\n");
            count++;
        } while (count <= 100);
    }

    // valid coords
    public static boolean coordCheck(int r, int c) {
        boolean valid = false;
        if (((r > 4) || (r < 0)) || ((c > 4) || (c < 0))) {
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
    // dub check
    public static boolean boardCheck(char[][] board) {
        boolean result = false;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == '@') {
                    result = true;
                }
            }
        }
        return result;
    }
    // very cool and huge big brain moment
    public static char[][] blankGen(char[][] inputB) {
        for (int col = 0; col < inputB[0].length; col++) {
            for (int row = 0; row < inputB.length; row++) {
                inputB[row][col] = '-';
            }
        }
        return inputB;
    }
    // Use this method to print game boards to console
    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }
}