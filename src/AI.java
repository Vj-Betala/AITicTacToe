public class AI implements PlayerInt {
    char letter;
    String name;
    int[][][] locations;

    public int[][][] getLocations() {
        return locations;
    }

    public void setLocations(int[][][] locations) {
        this.locations = locations;
    }

    public AI(char o, String name) {
        letter = o;
        this.name = name;
        int[][][] locations = new int[4][4][4];
        for (int x = 0; x < locations.length; x++) {
            for (int y = 0; y < locations.length; y++) {
                for (int z = 0; z < locations.length; z++) {
                    //locations[x][y][z] =
                }
            }
        }
    }

    //for (int x = 0; ) {}

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public Location getMove(char[][][] board) {
        return new Location(0,0,0);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void reset() {
        new AI(letter, name);
    }
}
