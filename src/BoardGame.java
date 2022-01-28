import java.util.Arrays;

public class BoardGame {

    private char[][][] listData;

    public BoardGame() {
        listData = new char[4][4][4];
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

    @Override
    public String toString() {
        return "" + Arrays.deepToString(listData);
    }
}
