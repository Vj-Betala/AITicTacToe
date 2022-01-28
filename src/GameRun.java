import com.sun.corba.se.impl.oa.poa.AOMEntry;

public class GameRun {

    private MainPlayer playerx, playero;
    private BoardGame board;
    private boolean isPlayerXTurn = true;


    // TODO: 1/28/22 Add setplayers to constructor

    public GameRun() {
        board = new BoardGame();
    }

    public void setPlayers(int player1, int player2) {
        if (player1 == 0){
            this.playerx = new HumanPlayer('x');
        } else {
            this.playerx = new AI_Build('x');
        }

        if (player2 == 0) {
            this.playero = new HumanPlayer('o');
        } else {
            this.playero = new AI_Build('o');
        }
    }

    public void requestTurn(){
        if(isPlayerXTurn){
            Location temp = playerx.getMove(board.getListData());
            board.addChar('x', temp);
        }

        System.out.println(board);
    }

    public BoardGame getBoard() {
        return board;
    }
}

// TODO: 1/27/22 Check if X or O is AI, X always goes before O, reference both in there