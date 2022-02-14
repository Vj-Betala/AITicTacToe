public class newStraightLine_AI extends AI_Build{

    public newStraightLine_AI(char x){
        super(x);
    }

    @Override
    public Location getMove(char[][][] board) {
        return super.getMove(board);
    }

    private Location getMoveHelper(char[][][] board, Location loc){
        int win = xWinCondition(checkWInner(board), board);

        if(win != BoardGame.TIE){
            return loc;
        }

        else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        if(board[i][j][k] == '-'){
                            board[i][j][k] = getLetter();
                            loc = new Location(k,j,i);

                        }
                    }
                }
            }
        }


        return null;
    }

    public int xWinCondition(int[][] moves,char[][][] listData) {
        if (moves == null)
            return BoardGame.TIE;
        if (listData[moves[0][0]][moves[0][1]][moves[0][2]] == 'x')
            return BoardGame.WIN;
        return BoardGame.LOSE;
    }

    public int[][] checkWInner(char[][][] listData) {
        //checking for x win
        for (int x = 0; x < listData.length; x++)
            for (int y = 0; y < listData.length; y++) {
                if ((listData[x][y][0] == 'x') && (listData[x][y][0] == listData[x][y][1]) && (listData[x][y][1] == listData[x][y][2]) && (listData[x][y][2] == listData[x][y][3]))
                    return new int[][]{{x, y, 0},{x,y,1},{x,y,2},{x,y,3}};
                if ((listData[x][0][y] == 'x') && (listData[x][0][y] == listData[x][1][y]) && (listData[x][1][y] == listData[x][2][y]) && (listData[x][2][y] == listData[x][3][y]))
                    return new int[][]{{x, 0, y},{x,1,y},{x,2,y},{x,3,y}};
                if ((listData[0][x][y] == 'x') && (listData[0][x][y] == listData[1][x][y]) && (listData[1][x][y] == listData[2][x][y]) && (listData[2][x][y] == listData[3][x][y]))
                    return new int[][]{{0, x, y},{1,x,y},{2,x,y},{3,x,y}};
            } //vertical (16), horizontal (16) on plane, multi-plane straight (16)
        for (int x = 0; x < listData.length; x++) {
            if ((listData[x][0][0] == 'x') && (listData[x][0][0] == listData[x][1][1]) && (listData[x][1][1] == listData[x][2][2]) && (listData[x][2][2] == listData[x][3][3]))
                return new int[][]{{x, 0, 0},{x,1,1},{x,2,2},{x,3,3}};
            if ((listData[x][3][0] == 'x') && (listData[x][3][0] == listData[x][2][1]) && (listData[x][2][1] == listData[x][1][2]) && (listData[x][1][2] == listData[x][0][3]))
                return new int[][]{{x, 3, 0},{x,2,1},{x,1,2},{x,0,3}};
            if ((listData[0][x][0] == 'x') && (listData[0][x][0] == listData[1][x][1]) && (listData[1][x][1] == listData[2][x][2]) && (listData[2][x][2] == listData[3][x][3]))
                return new int[][]{{0, x, 0},{1,x,1},{2,x,2},{3,x,3}};
            if ((listData[3][x][0] == 'x') && (listData[3][x][0] == listData[2][x][1]) && (listData[2][x][1] == listData[1][x][2]) && (listData[1][x][2] == listData[0][x][3]))
                return new int[][]{{3, x, 0},{2,x,1},{1,x,2},{0,x,3}};
            if ((listData[0][0][x] == 'x') && (listData[0][0][x] == listData[1][1][x]) && (listData[1][1][x] == listData[2][2][x]) && (listData[2][2][x] == listData[3][3][x]))
                return new int[][]{{0, 0, x},{1,1,x},{2,2,x},{3,3,x}};
            if ((listData[3][0][x] == 'x') && (listData[3][0][x] == listData[2][1][x]) && (listData[2][1][x] == listData[1][2][x]) && (listData[1][2][x] == listData[0][3][x]))
                return new int[][]{{3, 0, x},{2,1,x},{1,2,x},{0,3,x}};
        } //diagonal (8) on plane, multi-plane diagonal (16)
        if ((listData[0][0][0] == 'x') && (listData[0][0][0] == listData[1][1][1]) && (listData[1][1][1] == listData[2][2][2]) && (listData[2][2][2] == listData[3][3][3]))
            return new int[][]{{0, 0, 0},{1,1,1},{2,2,2},{3,3,3}};
        if ((listData[0][0][3] == 'x') && (listData[0][0][3] == listData[1][1][2]) && (listData[1][1][2] == listData[2][2][1]) && (listData[2][2][1] == listData[3][3][0]))
            return new int[][]{{0, 0, 3},{1,1,2},{2,2,1},{3,3,0}};
        if ((listData[0][3][0] == 'x') && (listData[0][3][0] == listData[1][2][1]) && (listData[1][2][1] == listData[2][1][2]) && (listData[2][1][2] == listData[3][0][3]))
            return new int[][]{{0, 3, 0},{1,2,1},{2,1,2},{3,0,3}};
        if ((listData[3][0][0] == 'x') && (listData[3][0][0] == listData[2][1][1]) && (listData[2][1][1] == listData[1][2][2]) && (listData[1][2][2] == listData[0][3][3]))
            return new int[][]{{3, 0, 0},{2,1,1},{1,2,2},{0,3,3}};
        // multi-multi-plane diagonal (4)

        //checking for o win
        for (int x = 0; x < listData.length; x++)
            for (int y = 0; y < listData.length; y++) {
                if ((listData[x][y][0] == 'o') && (listData[x][y][0] == listData[x][y][1]) && (listData[x][y][1] == listData[x][y][2]) && (listData[x][y][2] == listData[x][y][3]))
                    return new int[][]{{x, y, 0},{x,y,1},{x,y,2},{x,y,3}};
                if ((listData[x][0][y] == 'o') && (listData[x][0][y] == listData[x][1][y]) && (listData[x][1][y] == listData[x][2][y]) && (listData[x][2][y] == listData[x][3][y]))
                    return new int[][]{{x, 0, y},{x,1,y},{x,2,y},{x,3,y}};
                if ((listData[0][x][y] == 'o') && (listData[0][x][y] == listData[1][x][y]) && (listData[1][x][y] == listData[2][x][y]) && (listData[2][x][y] == listData[3][x][y]))
                    return new int[][]{{0, x, y},{1,x,y},{2,x,y},{3,x,y}};
            } //vertical (16), horizontal (16) on plane, multi-plane straight (16)
        for (int x = 0; x < listData.length; x++) {
            if ((listData[x][0][0] == 'o') && (listData[x][0][0] == listData[x][1][1]) && (listData[x][1][1] == listData[x][2][2]) && (listData[x][2][2] == listData[x][3][3]))
                return new int[][]{{x, 0, 0},{x,1,1},{x,2,2},{x,3,3}};
            if ((listData[x][3][0] == 'o') && (listData[x][3][0] == listData[x][2][1]) && (listData[x][2][1] == listData[x][1][2]) && (listData[x][1][2] == listData[x][0][3]))
                return new int[][]{{x, 3, 0},{x,2,1},{x,1,2},{x,0,3}};
            if ((listData[0][x][0] == 'o') && (listData[0][x][0] == listData[1][x][1]) && (listData[1][x][1] == listData[2][x][2]) && (listData[2][x][2] == listData[3][x][3]))
                return new int[][]{{0, x, 0},{1,x,1},{2,x,2},{3,x,3}};
            if ((listData[3][x][0] == 'o') && (listData[3][x][0] == listData[2][x][1]) && (listData[2][x][1] == listData[1][x][2]) && (listData[1][x][2] == listData[0][x][3]))
                return new int[][]{{3, x, 0},{2,x,1},{1,x,2},{0,x,3}};
            if ((listData[0][0][x] == 'o') && (listData[0][0][x] == listData[1][1][x]) && (listData[1][1][x] == listData[2][2][x]) && (listData[2][2][x] == listData[3][3][x]))
                return new int[][]{{0, 0, x},{1,1,x},{2,2,x},{3,3,x}};
            if ((listData[3][0][x] == 'o') && (listData[3][0][x] == listData[2][1][x]) && (listData[2][1][x] == listData[1][2][x]) && (listData[1][2][x] == listData[0][3][x]))
                return new int[][]{{3, 0, x},{2,1,x},{1,2,x},{0,3,x}};
        } //diagonal (8) on plane, multi-plane diagonal (16),
        if ((listData[0][0][0] == 'o') && (listData[0][0][0] == listData[1][1][1]) && (listData[1][1][1] == listData[2][2][2]) && (listData[2][2][2] == listData[3][3][3]))
            return new int[][]{{0, 0, 0},{1,1,1},{2,2,2},{3,3,3}};
        if ((listData[0][0][3] == 'o') && (listData[0][0][3] == listData[1][1][2]) && (listData[1][1][2] == listData[2][2][1]) && (listData[2][2][1] == listData[3][3][0]))
            return new int[][]{{0, 0, 3},{1,1,2},{2,2,1},{3,3,0}};
        if ((listData[0][3][0] == 'o') && (listData[0][3][0] == listData[1][2][1]) && (listData[1][2][1] == listData[2][1][2]) && (listData[2][1][2] == listData[3][0][3]))
            return new int[][]{{0, 3, 0},{1,2,1},{2,1,2},{3,0,3}};
        if ((listData[3][0][0] == 'o') && (listData[3][0][0] == listData[2][1][1]) && (listData[2][1][1] == listData[1][2][2]) && (listData[1][2][2] == listData[0][3][3]))
            return new int[][]{{3, 0, 0},{2,1,1},{1,2,2},{0,3,3}};
        // multi-multi-plane diagonal (4)


        return null;
    }
}
