public class AI_Build extends MainPlayer {
    private Location move;
    private char letter;

    public AI_Build(int x) {
        if (x == 0)
            setLetter('x');
        else
            setLetter('o');
    }

    @Override
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public Location getMove(char[][][] board) {

        int x, y, z;
        do {
            x = (int) (Math.random()*4);
            y = (int) (Math.random()*4);
            z = (int) (Math.random()*4);
        } while (board[z][y][x] != '-');

        move = new Location(x,y,z);
        return move;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void reset() {

    }
}
