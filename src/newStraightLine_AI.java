public class newStraightLine_AI extends AI_Build{

    public newStraightLine_AI(int x, int y){
        super(x);
        OSCOREWEIGHT = y;
    }

    @Override
    public Location getMove(char[][][] board) {
        return getMoveHelper(board);
    }

    private Location getMoveHelper(char[][][] board){

            MyNoStarvePriorityQueue<Location> score = new MyNoStarvePriorityQueue<>();


            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    for(int k = 0; k < 4; k++) {
                        if(board[i][j][k] == '-') {
                            int[] scores = getScore(board, new Location(k,j,i), getLetter());
                            int[] oScores = getScore(board, new Location(k,j,i), getoLetter());
                            score.add(new PriorityNode<>(new Location(k, j, i), ((scores[3] * 100 + oScores[3] * OSCOREWEIGHT * 10) + (scores[2] * 10 + oScores[2]*OSCOREWEIGHT) + (scores[1]))));

                        }
                    }
                }
            }



            return score.remove().getData();

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
                    break;
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
            count = 0;
            for (int i = 0; i < 4; i++) {
                if (board[column][i][sheet] == player1) {
                    count++;
                } else if (board[column][i][sheet] == player2) {
                    count = 0;
                    break;
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
            count = 0;
            for (int i = 0; i < 4; i++) {
                if (board[column][row][i] == player1) {
                    count++;
                } else if (board[column][row][i] == player2) {
                    count = 0;
                    break;
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
            count = 0;
            if (column == row) {
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
            count = 0;
            if (column + row == 3) {
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
            count = 0;
            if (column == sheet) {
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
            count = 0;
            if (column + sheet == 3) {
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
            count = 0;
            if (row + sheet == 3) {
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
            count = 0;
            if ((sheet == 0 && row == 0 && column == 0) || (sheet == 1 && row == 1 && column == 1) || (sheet == 2 && row == 2 && column == 2) || (sheet == 3 && row == 3 && column == 3)) {
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
            count = 0;
            if ((sheet == 0 && row == 0 && column == 3) || (sheet == 1 && row == 1 && column == 2) || (sheet == 2 && row == 2 && column == 1) || (sheet == 3 && row == 3 && column == 0)) {
                if (board[3][0][0] == player1)
                    count++;
                if (board[2][1][1] == player1)
                    count++;
                if (board[1][2][2] == player1)
                    count++;
                if (board[0][3][3] == player1)
                    count++;
                if (board[3][0][0] == player2 || board[1][2][2] == player2 || board[2][1][1] == player2 || board[0][3][3] == player2)
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
            count = 0;
            if ((sheet == 0 && row == 3 && column == 0) || (sheet == 1 && row == 2 && column == 1) || (sheet == 2 && row == 1 && column == 2) || (sheet == 3 && row == 0 && column == 3)) {
                if (board[0][3][0] == player1)
                    count++;
                if (board[1][2][1] == player1)
                    count++;
                if (board[2][1][2] == player1)
                    count++;
                if (board[3][0][3] == player1)
                    count++;
                if (board[3][0][3] == player2 || board[1][2][1] == player2 || board[2][1][2] == player2 || board[0][3][0] == player2)
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
            count = 0;
            if ((sheet == 3 && row == 0 && column == 0) || (sheet == 2 && row == 1 && column == 1) || (sheet == 1 && row == 2 && column == 2) || (sheet == 0 && row == 3 && column == 3)) {
                if (board[0][0][3] == player1)
                    count++;
                if (board[1][1][2] == player1)
                    count++;
                if (board[2][2][1] == player1)
                    count++;
                if (board[3][3][0] == player1)
                    count++;
                if (board[0][0][3] == player2 || board[2][2][1] == player2 || board[1][1][2] == player2 || board[3][3][0] == player2)
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
