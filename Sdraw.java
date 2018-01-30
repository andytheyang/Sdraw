import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
// Swing Program Template
// from https://www.ntu.edu.sg/home/ehchua/programming/java/J8b_Game_2DGraphics.html
@SuppressWarnings("serial")
public class Sdraw extends JPanel {
    // Name-constants
    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 480;
    public static final String TITLE = "S Drawer";
 
    /** Constructor to setup the GUI components */
    public Sdraw() {
        this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        // "this" JPanel container sets layout
        // setLayout(new ....Layout());

        // Allocate the UI components
        // .....

        // "this" JPanel adds components
        // add(....)

        // Source object adds listener
        // .....
    }
 
    /** Custom painting codes on this JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint background
        setBackground(Color.BLACK);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        
        S s = new S(g2, 50, 75, 50);

        s.draw(100, 100, 0, Direction.DOWN);
        
        

        // Your custom painting codes
        // ......
    }
 
    /** The entry main() method */
    public static void main(String[] args) {
        // Run GUI codes in the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(TITLE);
                frame.setContentPane(new Sdraw());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();             // "this" JFrame packs its components
                frame.setLocationRelativeTo(null); // center the application window
                frame.setVisible(true);            // show it
            }
        });
    }
}