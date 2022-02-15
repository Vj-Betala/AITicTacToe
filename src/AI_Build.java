public class AI_Build extends MainPlayer {
    private Location move;
    private char letter, oLetter;
    public boolean emptyBoard;
    public int OSCOREWEIGHT;

    public AI_Build(int x) {
        if (x == 0) {
            setLetter('x');
            setoLetter('o');
        }

        else {
            setLetter('o');
            setoLetter('x');
        }

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

    public char getoLetter() {
        return oLetter;
    }

    public void setoLetter(char oLetter) {
        this.oLetter = oLetter;
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

    public void setOSCOREWEIGHT(int y) {
        OSCOREWEIGHT = y;
    }
}
