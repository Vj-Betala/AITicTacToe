import javax.swing.*;
import java.awt.*;

public class TicTacFrame extends JFrame {
    public TicTacFrame(String title, int x, int y, int games, int waitNextMove, int waitWinningMove)
    {
        //creates frame with title
        super(title);
        //tells program to stop running after x is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets the frame to not be resizable by the user
        setResizable(false);
        //creates the frame
        pack();
        //creates the panel
        TicTacPanel p = new TicTacPanel(x, y, games, waitNextMove, waitWinningMove);
        //get insets
        Insets insets = getInsets();
        //calculating window size
        int width = p.getWidth() + insets.left + insets.right;
        int height = p.getHeight() + insets.top + insets.bottom;
        //set the preferred size
        setPreferredSize(new Dimension(width, height));
        //turn off layout options
        setLayout(null);
        //adds the panel to the frame
        add(p);
        //adjusts to be the size we set with preferred size
        pack();
        //show the screen
        setVisible(true);

        if(x!=0 || y!=0){
            Thread t = new Thread(p);
            t.start();
        }
    }
}
