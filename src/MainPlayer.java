public class MainPlayer implements PlayerInt{

    private char letter;

    public MainPlayer(char x) {
        letter = x;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public Location getMove(char[][][] board) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void reset() {

    }
}
