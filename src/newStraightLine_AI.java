import java.lang.reflect.Array;
import java.util.ArrayList;

public class newStraightLine_AI extends AI_Build{

    public newStraightLine_AI(int x){
        super(x);
    }

    @Override
    public Location getMove(char[][][] board) {
        return getMoveHelper(board);
    }

    private Location getMoveHelper(char[][][] board){
            ArrayList<Location> zeros = new ArrayList<>();
            ArrayList<Location> ones = new ArrayList<>();
            ArrayList<Location> doubles = new ArrayList<>();
            ArrayList<Location> triples = new ArrayList<>();
            ArrayList<Location> quadruples = new ArrayList<>();

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    for(int k = 0; k < 4; k++) {
                        if(board[i][j][k] == '-') {
                            int[] scores = getScore(board, new Location(k,j,i), getLetter());
                            if(scores[0] > 0) {
                                zeros.add(new Location(k,j,i));
                            }
                            else if(scores[1] > 0) {
                                ones.add(new Location(k,j,i));
                            }
                            else if(scores[2] > 0) {
                                doubles.add(new Location(k,j,i));
                            }
                            else if(scores[3] > 0) {
                                triples.add(new Location(k,j,i));
                            }else if(scores[4] > 0) {
                                quadruples.add(new Location(k,j,i));
                            }
                        }
                    }
                }
            }

            if(quadruples.size() > 0){
                return quadruples.get((int) (Math.random()*quadruples.size()));
            }if(triples.size() > 0){
                return triples.get((int) (Math.random()*triples.size()));
            }if(doubles.size() > 0){
                return doubles.get((int) (Math.random()*doubles.size()));
            }if(ones.size() > 0){
                return ones.get((int) (Math.random()*ones.size()));
            }if(zeros.size() > 0){
                return zeros.get((int) (Math.random()*zeros.size()));
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
    public int[] getScore(char[][][] board, Location loc, char x){
        char player1, player2;
        if(x == 'x'){
            player1 = 'x';
            player2 = 'o';
        } else {
            player1 = 'o';
            player2 = 'x';
        }

        int zero = 0, ones = 0, doubles = 0, tripes = 0, quads = 0;
        int column = loc.getCol(), row = loc.getRow(), sheet = loc.getSheet(), count = 1;

        {
            for (int i = 0; i < 4; i++) {
                if (board[column][row][i] == player1) {
                    count++;
                } else if (board[column][row][i] == player2) {
                    count = 0;
                }

            }
            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            for (int i = 0; i < 4; i++) {
                if (board[column][i][sheet] == player1) {
                    count++;
                } else if (board[column][i][sheet] == player2) {
                    count = 0;
                }
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            for (int i = 0; i < 4; i++) {
                if (board[i][row][sheet] == player1) {
                    count++;
                } else if (board[i][row][sheet] == player2) {
                    count = 0;
                }
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (column == row) {
                count = 1;
                if (board[0][0][sheet] == player1)
                    count++;
                if (board[1][1][sheet] == player1)
                    count++;
                if (board[2][2][sheet] == player1)
                    count++;
                if (board[3][3][sheet] == player1)
                    count++;
                if (board[0][0][sheet] == player2 || board[1][1][sheet] == player2 || board[2][2][sheet] == player2 || board[3][3][sheet] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (column + row == 3) {
                count = 1;
                if (board[0][3][sheet] == player1)
                    count++;
                if (board[1][2][sheet] == player1)
                    count++;
                if (board[2][1][sheet] == player1)
                    count++;
                if (board[3][0][sheet] == player1)
                    count++;
                if (board[0][3][sheet] == player2 || board[1][2][sheet] == player2 || board[2][1][sheet] == player2 || board[3][0][sheet] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (column == sheet) {
                count = 1;
                if (board[0][row][0] == player1)
                    count++;
                if (board[1][row][1] == player1)
                    count++;
                if (board[2][row][2] == player1)
                    count++;
                if (board[3][row][3] == player1)
                    count++;
                if (board[0][row][0] == player2 || board[1][row][1] == player2 || board[2][row][2] == player2 || board[3][row][3] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (column + sheet == 3) {
                count = 1;
                if (board[0][row][3] == player1)
                    count++;
                if (board[1][row][2] == player1)
                    count++;
                if (board[2][row][1] == player1)
                    count++;
                if (board[3][row][0] == player1)
                    count++;
                if (board[0][row][3] == player2 || board[1][row][2] == player2 || board[2][row][1] == player2 || board[3][row][0] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (row == sheet) {
                count = 1;
                if (board[column][0][0] == player1)
                    count++;
                if (board[column][1][1] == player1)
                    count++;
                if (board[column][2][2] == player1)
                    count++;
                if (board[column][3][3] == player1)
                    count++;
                if (board[column][3][3] == player2 || board[column][2][2] == player2 || board[column][1][1] == player2 || board[column][0][0] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (row + sheet == 3) {
                count = 1;
                if (board[column][0][3] == player1)
                    count++;
                if (board[column][1][2] == player1)
                    count++;
                if (board[column][2][1] == player1)
                    count++;
                if (board[column][3][0] == player1)
                    count++;
                if (board[column][3][0] == player2 || board[column][2][1] == player2 || board[column][1][2] == player2 || board[column][0][3] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (sheet + row + column == row * 3) {
                count = 1;
                if (board[0][0][0] == player1)
                    count++;
                if (board[1][1][1] == player1)
                    count++;
                if (board[2][2][2] == player1)
                    count++;
                if (board[3][3][3] == player1)
                    count++;
                if (board[3][3][3] == player2 || board[1][1][1] == player2 || board[2][2][2] == player2 || board[0][0][0] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }

        {
            count = 1;
            if (sheet + row + column == row * 3) {
                count = 1;
                if (board[0][0][0] == player1)
                    count++;
                if (board[1][1][1] == player1)
                    count++;
                if (board[2][2][2] == player1)
                    count++;
                if (board[3][3][3] == player1)
                    count++;
                if (board[3][3][3] == player2 || board[1][1][1] == player2 || board[2][2][2] == player2 || board[0][0][0] == player2)
                    count = 0;
            }

            switch (count) {
                case 0:
                    zero++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    doubles++;
                    break;
                case 3:
                    tripes++;
                    break;
                case 4:
                    quads++;
                    break;
            }
        }



        return new int[] {zero, ones, doubles, tripes, quads};

    }
}
