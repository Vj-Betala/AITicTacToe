import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Random;

public class TicTacPanel extends JPanel implements MouseListener {
        private Random rand;
        private BufferedImage buffer;

        public TicTacPanel() {
            super();
            setSize(800,600);
            buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
            addMouseListener(this);
            rand = new Random();
        }

        public void paint(Graphics g) {
            Graphics bg = buffer.getGraphics();
            bg.setColor(Color.BLACK);
            bg.fillRect(0,0,getWidth(),getHeight());
            bg.setColor(Color.WHITE);
            bg.drawRect(10,10,40,40);


            g.drawImage(buffer,0,0,null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            repaint();
        }

//        public int getStatus() {
  //          return PLAYING;
    //    }

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

        public void update() {
            //       game.update();
        }
    }
