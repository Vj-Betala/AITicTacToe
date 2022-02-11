import java.util.Arrays;

public class BoardGame {

    private char[][][] listData;
    public static final int LOSE = 0;
    public static final int TIE = 1;
    public static final int WIN = 2;

    public BoardGame() {
        listData = new char[4][4][4];
        for (int x = 0; x < listData.length; x++)
            for (int y = 0; y < listData.length; y++)
                for (int z = 0; z < listData.length; z++)
                    listData[x][y][z] = '-';
    }

    public void clear() {
        for (int x = 0; x < listData.length; x++)
            for (int y = 0; y < listData.length; y++)
                for (int z = 0; z < listData.length; z++)
                    listData[x][y][z] = '-';
    }

    public boolean addChar(char c, Location l) {
        if(listData[l.getSheet()][l.getRow()][l.getCol()] == '-') {
            listData[l.getSheet()][l.getRow()][l.getCol()] = c;
            return true;
        }
        return false;
    }

    public Character removerChar(Location l) {
        Character c = listData[l.getSheet()][l.getRow()][l.getCol()];
        listData[l.getSheet()][l.getRow()][l.getCol()] = '-';
        return c;
    }

    public char[][][] getListData() {
        return listData;
    }

    public boolean isFull() {
        for (int x = 0; x < listData.length; x++)
            for (int y = 0; y < listData.length; y++)
                for (int z = 0; z < listData.length; z++)
                    if (listData[x][y][z] == '-')
                        return false;
        return true;
    }

    public int[][] winningMoves() {
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



    public int xWinCondition(int[][] moves) {
        if (moves == null)
            return TIE;
        if (listData[moves[0][0]][moves[0][1]][moves[0][2]] == 'x')
            return WIN;
        return LOSE;
//        //checking for x win
//        for (int x = 0; x < listData.length; x++)
//            for (int y = 0; y < listData.length; y++) {
//                if ((listData[x][y][0] == 'x') && (listData[x][y][0] == listData[x][y][1]) && (listData[x][y][1] == listData[x][y][2]) && (listData[x][y][2] == listData[x][y][3]))
//                    return WIN;
//                if ((listData[x][0][y] == 'x') && (listData[x][0][y] == listData[x][1][y]) && (listData[x][1][y] == listData[x][2][y]) && (listData[x][2][y] == listData[x][3][y]))
//                    return WIN;
//                if ((listData[0][x][y] == 'x') && (listData[0][x][y] == listData[1][x][y]) && (listData[1][x][y] == listData[2][x][y]) && (listData[2][x][y] == listData[3][x][y]))
//                    return WIN;
//            } //vertical (16), horizontal (16) on plane, multi-plane straight (16)
//        for (int x = 0; x < listData.length; x++) {
//            if ((listData[x][0][0] == 'x') && (listData[x][0][0] == listData[x][1][1]) && (listData[x][1][1] == listData[x][2][2]) && (listData[x][2][2] == listData[x][3][3]))
//                return WIN;
//            if ((listData[x][3][0] == 'x') && (listData[x][3][0] == listData[x][2][1]) && (listData[x][2][1] == listData[x][1][2]) && (listData[x][1][2] == listData[x][0][3]))
//                return WIN;
//            if ((listData[0][x][0] == 'x') && (listData[0][x][0] == listData[1][x][1]) && (listData[1][x][1] == listData[2][x][2]) && (listData[2][x][2] == listData[3][x][3]))
//                return WIN;
//            if ((listData[3][x][0] == 'x') && (listData[3][x][0] == listData[2][x][1]) && (listData[2][x][1] == listData[1][x][2]) && (listData[1][x][2] == listData[0][x][3]))
//                return WIN;
//            if ((listData[0][0][x] == 'x') && (listData[0][0][x] == listData[1][1][x]) && (listData[1][1][x] == listData[2][2][x]) && (listData[2][2][x] == listData[3][3][x]))
//                return WIN;
//            if ((listData[3][0][x] == 'x') && (listData[3][0][x] == listData[2][1][x]) && (listData[2][1][x] == listData[1][2][x]) && (listData[1][2][x] == listData[0][3][x]))
//                return WIN;
//        } //diagonal (8) on plane, multi-plane diagonal (16),
//        if ((listData[0][0][0] == 'x') && (listData[0][0][0] == listData[1][1][1]) && (listData[1][1][1] == listData[2][2][2]) && (listData[2][2][2] == listData[3][3][3]))
//            return WIN;
//        if ((listData[0][0][3] == 'x') && (listData[0][0][3] == listData[1][1][2]) && (listData[1][1][2] == listData[2][2][1]) && (listData[2][2][1] == listData[3][3][0]))
//            return WIN;
//        if ((listData[0][3][0] == 'x') && (listData[0][3][0] == listData[1][2][1]) && (listData[1][2][1] == listData[2][1][2]) && (listData[2][1][2] == listData[3][0][3]))
//            return WIN;
//        if ((listData[3][0][0] == 'x') && (listData[3][0][0] == listData[2][1][1]) && (listData[2][1][1] == listData[1][2][2]) && (listData[1][2][2] == listData[0][3][3]))
//            return WIN;
//        // multi-multi-plane diagonal (4)
//
//        //checking for o win
//        for (int x = 0; x < listData.length; x++)
//            for (int y = 0; y < listData.length; y++) {
//                if ((listData[x][y][0] == 'o') && (listData[x][y][0] == listData[x][y][1]) && (listData[x][y][1] == listData[x][y][2]) && (listData[x][y][2] == listData[x][y][3]))
//                    return LOSE;
//                if ((listData[x][0][y] == 'o') && (listData[x][0][y] == listData[x][1][y]) && (listData[x][1][y] == listData[x][2][y]) && (listData[x][2][y] == listData[x][3][y]))
//                    return LOSE;
//                if ((listData[0][x][y] == 'o') && (listData[0][x][y] == listData[1][x][y]) && (listData[1][x][y] == listData[2][x][y]) && (listData[2][x][y] == listData[3][x][y]))
//                    return LOSE;
//            } //vertical (16), horizontal (16) on plane, multi-plane straight (16)
//        for (int x = 0; x < listData.length; x++) {
//            if ((listData[x][0][0] == 'o') && (listData[x][0][0] == listData[x][1][1]) && (listData[x][1][1] == listData[x][2][2]) && (listData[x][2][2] == listData[x][3][3]))
//                return LOSE;
//            if ((listData[x][3][0] == 'o') && (listData[x][3][0] == listData[x][2][1]) && (listData[x][2][1] == listData[x][1][2]) && (listData[x][1][2] == listData[x][0][3]))
//                return LOSE;
//            if ((listData[0][x][0] == 'o') && (listData[0][x][0] == listData[1][x][1]) && (listData[1][x][1] == listData[2][x][2]) && (listData[2][x][2] == listData[3][x][3]))
//                return LOSE;
//            if ((listData[3][x][0] == 'o') && (listData[3][x][0] == listData[2][x][1]) && (listData[2][x][1] == listData[1][x][2]) && (listData[1][x][2] == listData[0][x][3]))
//                return LOSE;
//            if ((listData[0][0][x] == 'o') && (listData[0][0][x] == listData[1][1][x]) && (listData[1][1][x] == listData[2][2][x]) && (listData[2][2][x] == listData[3][3][x]))
//                return LOSE;
//            if ((listData[3][0][x] == 'o') && (listData[3][0][x] == listData[2][1][x]) && (listData[2][1][x] == listData[1][2][x]) && (listData[1][2][x] == listData[0][3][x]))
//                return LOSE;
//        } //diagonal (8) on plane, multi-plane diagonal (16),
//        if ((listData[0][0][0] == 'o') && (listData[0][0][0] == listData[1][1][1]) && (listData[1][1][1] == listData[2][2][2]) && (listData[2][2][2] == listData[3][3][3]))
//            return LOSE;
//        if ((listData[0][0][3] == 'o') && (listData[0][0][3] == listData[1][1][2]) && (listData[1][1][2] == listData[2][2][1]) && (listData[2][2][1] == listData[3][3][0]))
//            return LOSE;
//        if ((listData[0][3][0] == 'o') && (listData[0][3][0] == listData[1][2][1]) && (listData[1][2][1] == listData[2][1][2]) && (listData[2][1][2] == listData[3][0][3]))
//            return LOSE;
//        if ((listData[3][0][0] == 'o') && (listData[3][0][0] == listData[2][1][1]) && (listData[2][1][1] == listData[1][2][2]) && (listData[1][2][2] == listData[0][3][3]))
//            return LOSE;
//        // multi-multi-plane diagonal (4)
//        return TIE;
    }

    @Override
    public String toString() {
        return "" + Arrays.deepToString(listData);
    }
}