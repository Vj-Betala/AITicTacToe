public class GameRun {

    private AI_Build0175 ai1;
    private BoardGame board;
    private boolean isPlayerXTurn = true;

    public GameRun() {
        //ai1 = new AI_Build();
        board = new BoardGame();
    }

    public void setPlayers(boolean player1, boolean player2) {

    }

//    public int winCondition() {
        //find if row is complete x return  find if row in complete o
  //  }

    public void requestTurn(){
        if(isPlayerXTurn){
            Location temp = ai1.getMove(board.getListData());
            board.addChar('x', temp);
        }

        System.out.println(board);
    }

    public AI_Build0175 getAi1() {
        return ai1;
    }

    public void setAi1(AI_Build0175 ai1) {
        this.ai1 = ai1;
    }

    public BoardGame getBoard() {
        return board;
    }

    public void setBoard(BoardGame board) {
        this.board = board;
    }

    public boolean isPlayerXTurn() {
        return isPlayerXTurn;
    }

    public void setPlayerXTurn(boolean playerXTurn) {
        isPlayerXTurn = playerXTurn;
    }
}

// TODO: 1/27/22 Check if X or O is AI, X always goes before O, reference both in there