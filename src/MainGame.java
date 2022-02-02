import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int a, b;
        boolean x, y;
        do {
            System.out.println("Is X a human? 0 for false, 1 for true");
            a = keyboard.nextInt();
        } while (a != 1 && a != 0);
        x = a == 1;
        do {
            System.out.println("Is O a human? 0 for false, 1 for true");
            b = keyboard.nextInt();
        } while (b != 1 && b != 0);
        y = b == 1;

        new TicTacFrame("TicTac", x, y);
    }
}
