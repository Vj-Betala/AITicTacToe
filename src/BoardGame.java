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
        listData = new char[4][4][4];
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
    public int xWinCondition() {
        //
        for (int x = 0; x < listData.length; x++)
            for (int y = 0; y < listData.length; y++)
                for (int z = 0; z < listData.length; z++)
                    if (listData[x][y][z] == '-') {}
        return WIN;
        //find if row is complete x return  find if row in complete o
    }

    @Override
    public String toString() {
        return "" + Arrays.deepToString(listData);
    }
}