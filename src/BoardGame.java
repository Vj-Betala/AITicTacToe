public class BoardGame {

    private char[][][] listData;

    public BoardGame() {
        listData = new char[4][4][4];
    }

    public void clear() {
        listData = new char[4][4][4];
    }

    public boolean addChar(char c, Location l) {
        if(listData[l.getSheet()][l.getRow()][l.getCol()] == 0) {
            listData[l.getSheet()][l.getRow()][l.getCol()] = c;
            return true;
        }
        return false;
    }

    public Character removerChar(Location l) {
        Character c = listData[l.getSheet()][l.getRow()][l.getCol()];
        listData[l.getSheet()][l.getRow()][l.getCol()] = 0;
        return c;
    }

    public char[][][] getListData() {
        return listData;
    }
}
