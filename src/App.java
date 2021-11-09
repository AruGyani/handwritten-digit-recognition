import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JPanel {
    // Setup
    public static int WIDTH = 1200, HEIGHT = 800;
    private static final int UPS = 60, FPS = 560;
    private static JFrame frame = new JFrame();

    private static final boolean RENDER_TIME = false;
    private static final boolean running = true;
    private RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    private Board board = new Board(28, 28);
    private Clear clear = new Clear(board);

    @SuppressWarnings("unused")
    private static List<Digit> train, test;

    public App() {
        setFocusable(true);
               
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }

            public void keyTyped(KeyEvent e) {

            }
        });

        addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {
                clear.mouseReleased(e);
            }   

            public void mouseClicked(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                board.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                board.mouseMoved(e);
                clear.mouseMoved(e);
            } 
        });
    }

    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;

        g.setColor(new Color(50, 58, 74));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(rh);

        board.render(g);
        clear.render(g);
    }

    public void update() {
        WIDTH = this.getWidth();
        HEIGHT = this.getHeight();

    }

    @SuppressWarnings("unused")
    public void run() {
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                repaint();
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (RENDER_TIME) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                }

                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();

        train = MNISTFile.loadImage("train");
        test = MNISTFile.loadImage("t10k");

        app.setDoubleBuffered(true);
        app.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.add(app);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        app.run();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
