// A dedicated class that extends JPanel to handle the custom drawing at the bottom.

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        
        // Render 7 images sequentially horizontally
        for (int i = 0; i < 7; i++) {
            Image myImage = new ImageIcon("jpg").getImage();
            g2D.drawImage(myImage, i * 70, 0, 70, 100, this);
        }
        
        g2D.dispose();
    }
}
