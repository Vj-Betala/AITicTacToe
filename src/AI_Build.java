public class AI_Build extends MainPlayer {
    private Location move;
    private char letter;
    public boolean emptyBoard;

    public AI_Build(int x) {
        if (x == 0)
            setLetter('x');
        else
            setLetter('o');

        emptyBoard = true;
    }

    public void isEmptyBoard(char[][][] board){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    if(board[i][j][k] == '-')
                        emptyBoard = false;
                }
            }
        }
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
