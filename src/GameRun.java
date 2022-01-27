public class GameRun {

    private AI_Build ai1;
    private BoardGame board;
    private boolean isPlayerXTurn = true;

    public GameRun() {
        ai1 = new AI_Build();
        board = new BoardGame();
    }

    public void setPlayers(boolean player1, boolean player2) {

    }

    public void requestTurn(){
        if(isPlayerXTurn){
            Location temp = ai1.getMove(board.getListData());
            board.addChar('x', temp);
        }

        System.out.println(board);
    }
}

// TODO: 1/27/22 Check if X or O is AI, X always goes before O, reference both in there