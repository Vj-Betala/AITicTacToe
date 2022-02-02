import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TicTacPanel extends JPanel implements MouseListener {
    private Random rand;
    private BufferedImage buffer;
    private AI_Build ai1;
    private AI_Build ai2;
    private BoardGame board;
    boolean x, y;
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

    public TicTacPanel(boolean x, boolean y) {
        super();
        setSize(800,600);
        buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        addMouseListener(this);
        if (!x)
            ai1 = new AI_Build(0);
        if (!y)
            ai2 = new AI_Build(1);
        board = new BoardGame();
        rand = new Random();
        this.x = x; this.y = y; turn = true;
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
        //if (board.isFull()) {
            bg.setFont(g.getFont().deriveFont(30.00f));
            if (board.xWinCondition() == board.TIE)
                bg.drawString("It was a Tie.",getWidth()/2 - 80,40);
            else if (board.xWinCondition() == board.WIN)
                bg.drawString("X won the game.",getWidth()/2 - 100,40);
            else
                bg.drawString("O won the game.",getWidth()/2 - 100,40);
        //}
        g.drawImage(buffer,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!board.isFull()) {
            int i = e.getX();
            int j = e.getY();
            if (x && y) {
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
            } else if (x) {
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
            } else if (y) {
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
            } else {
                if (turn) {
                    Location move = getAi1().getMove(getBoard().getListData());
                    getBoard().getListData()[move.getSheet()][move.getRow()][move.getCol()] = 'x';
                    turn = false;
                } else {
                    Location move = getAi2().getMove(getBoard().getListData());
                    getBoard().getListData()[move.getSheet()][move.getRow()][move.getCol()] = 'o';
                    turn = true;
                }
            }
        }
        else {
            if (board.xWinCondition() == board.WIN)
                ;
            else if (board.xWinCondition() == board.LOSE)
                ;
            else
                ;

        }
        repaint();
    }

    //public boolean

    @Override
    public void mousePressed(MouseEvent e) {

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

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
