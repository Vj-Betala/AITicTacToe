import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Player List:" + "\n"
                    + "0: Human" + "\n"
                    + "1: Random AI" + "\n"
                    + "2: Vaarij's Shitty AI (jkjk)" + "\n"
                    + "3: Blocking AI" + "\n"
                    + "4: Fascinating Blocking AI (in dev)" + "\n"
                    + "Coming Soon..." + "\n"
                    + "Select Player 1:");
            x = keyboard.nextInt();
        } while (x > 4 || x < 0); // change while statement after adding more AIs
        do {
            System.out.println("Player List:" + "\n"
                    + "0: Human" + "\n"
                    + "1: Random AI" + "\n"
                    + "2: Vaarij's Shitty AI (jkjk)" + "\n"
                    + "3: Blocking AI" + "\n"
                    + "4: Fascinating Blocking AI (in dev)" + "\n"
                    + "Coming Soon..." + "\n"
                    + "Select Player 2:");
            y = keyboard.nextInt();
        } while (y > 4 || y < 0); // change while statement after adding more AIs
        int games, waitNextMove, waitWinningMove, frame;
        if (x != 0 && y != 0) {
            System.out.println("How many games should be played?");
            games = keyboard.nextInt();
            System.out.println("How many milliseconds between each move? (250, 500, 1000)");
            waitNextMove = keyboard.nextInt();
            System.out.println("How many milliseconds between each game? (2000, 3000)");
            waitWinningMove = keyboard.nextInt();
            System.out.println("Do you want a display? 0 = no; 1 = true.");
            frame = keyboard.nextInt();
        }
        else {
            games = waitNextMove = waitWinningMove = 0;
            frame = 1;
        }
        new TicTacFrame("TicTac", x, y, games, waitNextMove, waitWinningMove, frame);
    }
}
