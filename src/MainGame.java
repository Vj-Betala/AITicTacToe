public class MainGame {
    public static void main(String[] args) {
        GameRun game = new GameRun();


        for (int i = 0; i < 15; i++) {
            game.requestTurn();
        }
    }
}
