public class StraightLine_AI extends AI_Build {

    public StraightLine_AI(int x) {
        super(x);
    }

    public static final int DEPTHREACH = 5;

    @Override
    public Location getMove(char[][][] board) {

        // TODO: 2/9/22 Do not mutate global board

        Location loc = new Location(0,0,0);


        if(emptyBoard){
            int x, y, z;
                x = (int) (Math.random()*4);
                y = (int) (Math.random()*4);
                z = (int) (Math.random()*4);

            return new Location(x,y,z);
        }

        int bestScore = -1000;

        for(int i = 0; i < 4; i++){
            for(int j =0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    if(board[i][j][k] == '-'){
                        board[i][j][k] = getLetter();
                        int score = getmoveChecker(board, DEPTHREACH, false);
                        board[i][j][k] = '-';
//                        System.out.println(board[i][j][k]);
                        if(score > bestScore){
                            bestScore = score;
                            loc = new Location(k,j,i);
                        }
                    }
                }
            }
        }

        return loc;


    }

    private Location getMoveHelperStraight(char[][][] board,int maxDepth, Location loc) {

        // TODO: 2/8/22 Add Recursive board grading

        int win = xWinCondition(checkWInner(board), board);
        loc = new Location(0, 0, 0);

        if (win != BoardGame.TIE || maxDepth == 0) {
            if ((getLetter() == 'x' && win == BoardGame.WIN) || maxDepth == 0) {
                return loc;

            }
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (board[i][j][k] == '-') {
                            board[i][j][k] = getLetter();
                            loc = new Location(k,j,i);
                            if(xWinCondition(checkWInner(board), board) == BoardGame.WIN){
                                board[i][j][k] = '-';
                                return loc;
                            } else {
                                return getMoveHelperStraight(board, maxDepth -1, loc);
                            }
                        }
                    }
                }
            }
        }
                return null;
    }


    private int getmoveChecker(char[][][] board, int maxDepth, boolean currentPlayer){
        int win = xWinCondition(checkWInner(board), board);

        if (win != BoardGame.TIE ){
            if (getLetter() == 'x' && BoardGame.WIN == win){
                return 1  - (DEPTHREACH - maxDepth);
            }else if(getLetter() == 'x' && BoardGame.LOSE == win){
                return -1  - (DEPTHREACH - maxDepth);
            } else if(getLetter() == 'o' && BoardGame.WIN == win) {
                return -1  - (DEPTHREACH - maxDepth);
            } else if(getLetter() == 'o' && BoardGame.LOSE == win){
                return 1  - (DEPTHREACH - maxDepth);
            }
        }
        else if(maxDepth == 0){
            return 0;
        }

        int bestScore = 0;

        if(win == BoardGame.TIE) {
            if (currentPlayer) {
                bestScore = -1000;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            if (board[i][j][k] == '-') {
                                board[i][j][k] = getLetter();
                                int score = getmoveChecker(board, maxDepth - 1, false);
                                board[i][j][k] = '-';
//                                System.out.println(board[i][j][k]);
                                bestScore = Math.max(score, bestScore);
                            }
                        }
                    }
                }
            } else {
                bestScore = 1000;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            if (board[i][j][k] == '-') {
                                int score = getmoveChecker(board, maxDepth - 1, true);
                                board[i][j][k] = '-';
                                bestScore = Math.min(bestScore, score);
                            }
                        }
                    }
                }

            }
        }
        return bestScore;
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

    // TODO: 2/8/22 Start With a StraightLine and Blocking until at least 10 moves are placed, then run board grading 
    
    //Alpha Beta Pruning, Depth finding
}
