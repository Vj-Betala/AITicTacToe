public class secondAIVR0173 extends AI_Build2{

    public secondAIVR0173(int x){
        super(x);
        OSCOREWEIGHT = 2;

        MYSCOREWIEGHT = 11;
    }

    @Override
    public Location getMove(char[][][] board) {
        return getMoveHelper(board);
    }

    private Location getMoveHelper(char[][][] board){

        MaxHeapLocation<Location> score = new MaxHeapLocation<>();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++) {
                    if(board[i][j][k] == '-') {
                        int[] scores = LocationScore.getScore(board, new Location(k,j,i), getLetter());
                        int[] oScores = LocationScore.getScore(board, new Location(k,j,i), getoLetter());
                        score.add(new HeapNode<>(new Location(k, j, i), ((scores[3] * MYSCOREWIEGHT*10 + oScores[3] * OSCOREWEIGHT * 10) + (scores[2] * MYSCOREWIEGHT + oScores[2]*OSCOREWEIGHT) + (scores[1]))));

                    }
                }
            }
        }

        return score.remove().getData();

    }



}
