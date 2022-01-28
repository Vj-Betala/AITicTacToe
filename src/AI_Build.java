public class AI_Build extends MainPlayer{
    private Location move;

    public AI_Build(char x){
        super(x);
    }

    @Override
    public Location getMove(char[][][] board) {

        int x = 0, y = 0, z = 0;
        do{
            x = (int) (Math.random()*4);
            y = (int) (Math.random()*4);
            z = (int) (Math.random()*4);
        } while (board[z][y][x] != '-');

        move = new Location(x,y,z);
        return move;
    }


    @Override
    public void reset() {

    }
}
