import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Player List:" + "\n"
                    + "Human - 0" + "\n"
                    + "Random AI - 1" + "\n"
                    + "StraightLine - 2" + "\n"
                    + "Select Player 1:");
            x = keyboard.nextInt();
        } while (x > 3); // change while statement after adding more AIs
        do {
            System.out.println("Player List:" + "\n"
                    + "Human - 0" + "\n"
                    + "Random AI - 1" + "\n"
                    + "StraightLine - 2" + "\n"
                    + "Select Player 2:");
            y = keyboard.nextInt();
        } while (y > 2); // change while statement after adding more AIs
        int games, waitNextMove, waitWinningMove;
        if (x != 0 && y != 0) {
            System.out.println("How many games should be played?");
            games = keyboard.nextInt();
            System.out.println("How many milliseconds between each move? (250, 500, 1000)");
            waitNextMove = keyboard.nextInt();
            System.out.println("How many milliseconds between each game? (2000, 3000)");
            waitWinningMove = keyboard.nextInt();
        }
        else
            games = waitNextMove = waitWinningMove = 0;

        new TicTacFrame("TicTac", x, y, games, waitNextMove, waitWinningMove);
    }
}
