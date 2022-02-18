import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TicTacPanel extends JPanel implements MouseListener, KeyListener ,Runnable {
    private Random rand;
    private BufferedImage buffer;
    private AI_Build ai1;
    private AI_Build ai2;
    private BoardGame board;
    int x, y, games, waitNextMove, waitWinningMove, xwins, ywins;
    boolean turn;

    public AI_Build getAi1() {
        return ai1;
    }

    public AI_Build getAi2() {
        return ai2;
    }

    public BoardGame getBoard() {
        return board;
    }

    public TicTacPanel(int x, int y, int games, int waitNextMove, int waitWinningMove) {
        super();
        setSize(800,600);
        buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        addMouseListener(this);
        addKeyListener(this);
        if (x == 1)
            ai1 = new AI_Build(0);
        else if (x == 2)
            ai1 = new AI_Blocking(0);
        else if (x == 3)
            ai1 = new secondAIVR0173(0);
        if (y == 1)
            ai2 = new AI_Build(1);
        else if (y == 2)
            ai2 = new AI_Blocking(1);
        else if (y == 3)
            ai2 = new secondAIVR0173(1);
        board = new BoardGame();
        rand = new Random();
        this.x = x; this.y = y; turn = true; this.games = games; this.waitNextMove = waitNextMove; this.waitWinningMove = waitWinningMove;
    }

    public void paint(Graphics g) {
        Graphics bg = buffer.getGraphics();
        bg.setColor(Color.BLACK);
        bg.fillRect(0,0,getWidth(),getHeight());
        bg.setColor(Color.WHITE);
        bg.setFont(g.getFont().deriveFont(50.00f));
        for (int x = 0; x < getBoard().getListData().length; x++) {
            for (int y = 0; y < getBoard().getListData()[0].length; y++) {
                for (int z = 0; z < getBoard().getListData()[0][0].length; z++) {
                    bg.drawRect(40 + 40*x + 180*z,400 + 40*y - 120*z,40,40);
                    if (getBoard().getListData()[x][y][z] != '-')
                        bg.drawString("" + getBoard().getListData()[x][y][z],40 + 40*x + 180*z + 8,400 + 40*y - 120*z + 35);
                }
            }
        }
        int[][] moves = board.winningMoves();
        int condition = board.xWinCondition(moves);
        bg.setColor(Color.GREEN);
        if (moves != null)
            for (int x = 0; x < moves.length; x++) {
                if (condition == BoardGame.WIN)
                    bg.drawString("x", 40 + 40 * moves[x][0] + 180 * moves[x][2] + 8, 400 + 40 * moves[x][1] - 120 * moves[x][2] + 35);
                else
                    bg.drawString("o", 40 + 40 * moves[x][0] + 180 * moves[x][2] + 8, 400 + 40 * moves[x][1] - 120 * moves[x][2] + 35);
            }
        bg.setFont(g.getFont().deriveFont(30.00f));
        if (board.isFull() && condition == BoardGame.TIE)
            bg.drawString("It was a Tie.", getWidth() / 2 - 80, 40);
        else if (condition == BoardGame.LOSE)
            bg.drawString("O won the game.",getWidth()/2 - 100,40);
        else if (condition == BoardGame.WIN)
            bg.drawString("X won the game.",getWidth()/2 - 100,40);
        g.drawImage(buffer,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((x == 0 || y == 0) && !board.isFull() && board.xWinCondition(board.winningMoves()) == BoardGame.TIE) {
            System.out.println(board.xWinCondition(board.winningMoves()));
            int i = e.getX();
            int j = e.getY();
            //both human
            if (x == 0 && y == 0) {
                if (turn) {
                    for (int a = 0; a < getBoard().getListData().length; a++) {
                        for (int b = 0; b < getBoard().getListData()[0].length; b++) {
                            for (int c = 0; c < getBoard().getListData()[0][0].length; c++) {
                                Rectangle r = new Rectangle(40 + 40 * a + 180 * c, 400 + 40 * b - 120 * c, 40, 40);
                                if (r.contains(i, j) && getBoard().getListData()[a][b][c] == '-') {
                                    getBoard().getListData()[a][b][c] = 'x';
                                    turn = false;
                                }
                            }
                        }
                    }
                } else {
                    for (int a = 0; a < getBoard().getListData().length; a++) {
                        for (int b = 0; b < getBoard().getListData()[0].length; b++) {
                            for (int c = 0; c < getBoard().getListData()[0][0].length; c++) {
                                Rectangle r = new Rectangle(40 + 40 * a + 180 * c, 400 + 40 * b - 120 * c, 40, 40);
                                if (r.contains(i, j) && getBoard().getListData()[a][b][c] == '-') {
                                    getBoard().getListData()[a][b][c] = 'o';
                                    turn = true;
                                }
                            }
                        }
                    }
                }
            }
            //x human o ai
            else if (x == 0) {
                if (turn) {
                    for (int a = 0; a < getBoard().getListData().length; a++) {
                        for (int b = 0; b < getBoard().getListData()[0].length; b++) {
                            for (int c = 0; c < getBoard().getListData()[0][0].length; c++) {
                                Rectangle r = new Rectangle(40 + 40 * a + 180 * c, 400 + 40 * b - 120 * c, 40, 40);
                                if (r.contains(i, j) && getBoard().getListData()[a][b][c] == '-') {
                                    getBoard().getListData()[a][b][c] = 'x';
                                    turn = false;
                                }
                            }
                        }
                    }
                } else {
                    Location move = getAi2().getMove(getBoard().getListData());
                    getBoard().getListData()[move.getSheet()][move.getRow()][move.getCol()] = 'o';
                    turn = true;
                }
            }
            //x ai o human
            else if (y == 0) {
                if (!turn) {
                    for (int a = 0; a < getBoard().getListData().length; a++) {
                        for (int b = 0; b < getBoard().getListData()[0].length; b++) {
                            for (int c = 0; c < getBoard().getListData()[0][0].length; c++) {
                                Rectangle r = new Rectangle(40 + 40 * a + 180 * c, 400 + 40 * b - 120 * c, 40, 40);
                                if (r.contains(i, j) && getBoard().getListData()[a][b][c] == '-') {
                                    getBoard().getListData()[a][b][c] = 'o';
                                    turn = true;
                                }
                            }
                        }
                    }
                } else {
                    Location move = getAi1().getMove(getBoard().getListData());
                    getBoard().getListData()[move.getSheet()][move.getRow()][move.getCol()] = 'x';
                    turn = false;
                }
            }
        }
        repaint();
    }

    @Override
    public void run() {
        if(x!=0 && y!=0){
            for(int i = 0; i < games; i++){
                while(!board.isFull() && board.xWinCondition(board.winningMoves()) == BoardGame.TIE){
                    if (turn) {
                        Location move = getAi1().getMove(getBoard().getListData());
                        getBoard().getListData()[move.getSheet()][move.getRow()][move.getCol()] = 'x';
                        turn = false;
                    } else {
                        Location move = getAi2().getMove(getBoard().getListData());
                        getBoard().getListData()[move.getSheet()][move.getRow()][move.getCol()] = 'o';
                        turn = true;
                    }
                    try{
                        repaint();
                        Thread.sleep(waitNextMove);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if (board.xWinCondition(board.winningMoves()) == BoardGame.WIN)
                    xwins++;
                else if (board.xWinCondition(board.winningMoves()) == BoardGame.LOSE)
                    ywins++;
                board = new BoardGame();
                try{
                    Thread.sleep(waitWinningMove);
                    repaint();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("X won " + xwins + " and O won " + ywins + ". There were " + (games-xwins-ywins) + " ties.");
        }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char dir = e.getKeyChar();
        if (dir == 'r' && (x != 1 || y != 1 ) && (board.isFull() || (board.xWinCondition(board.winningMoves()) != BoardGame.TIE))) {
            board = new BoardGame();
            turn = true;
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println(board.toString());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
