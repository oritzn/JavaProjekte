import java.awt.Color;
import javax.swing.JFrame;


public class gameFrame extends JFrame{
    
    gamePanel panel;

    public gameFrame() {
      panel = new gamePanel();
      this.add(panel);
      this.setTitle("Pong Game");
      this.setResizable(false);
      this.setBackground(Color.black);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setVisible(true);
      this.setLocationRelativeTo(null);
    }
    
}