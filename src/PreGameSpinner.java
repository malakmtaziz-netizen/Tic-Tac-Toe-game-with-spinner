// A custom event listener that extends JDialog to handle pre-game lucky wheel selections.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreGameSpinner extends JDialog {
    private final Main parent;
    private double currentAngle = 0;
    private boolean spinning = false;
    private int selectedReward = -1;
    private final JButton spinButton;
    private final JPanel wheelPanel;
    
    // Slices are drawn at exactly 90 degrees each to keep the real probabilities secret
    private final int[] arcAngles = {90, 90, 90, 90};
    private final String[] shortNames = {
        "Play First",
        "Try Again",
        "Skip 1 Q",
        "No Qs"
    };
    private final String[] rewardNames = {
        "Play First (Auto Player Turn)", 
        "Good Luck Next Time (No Reward)", 
        "Skip One Question (One-Time Pass)", 
        "Play Without Any Questions (Unlimited Pass)"
    };
    private final Color[] colors = {
        new Color(46, 204, 113),  // Emerald Green
        new Color(231, 76, 60),   // Alizarin Red
        new Color(241, 196, 15),  // Sunflower Yellow
        new Color(52, 152, 219)   // Peter River Blue
    };

    public PreGameSpinner(Main parent) {
        super(parent, "Lucky Pre-Game Spinner", true);
        this.parent = parent;
        setSize(400, 450);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        wheelPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2D = (Graphics2D) g;
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int size = 260;
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;

                double startAngle = currentAngle;
                for (int i = 0; i < 4; i++) {
                    g2D.setColor(colors[i]);
                    g2D.fillArc(x, y, size, size, (int) startAngle, arcAngles[i]);
                    
                    g2D.setColor(Color.WHITE);
                    g2D.setStroke(new BasicStroke(2.0f));
                    g2D.drawArc(x, y, size, size, (int) startAngle, arcAngles[i]);
                    
                    // Draw text labels dynamically rotated with the slice midpoints
                    g2D.setColor(Color.WHITE);
                    g2D.setFont(new Font("Arial", Font.BOLD, 13));
                    double rad = Math.toRadians(startAngle + 45.0);
                    int textX = (int) (getWidth() / 2 + Math.cos(rad) * (size * 0.32)) - 32;
                    int textY = (int) (getHeight() / 2 - Math.sin(rad) * (size * 0.32)) + 5;
                    g2D.drawString(shortNames[i], textX, textY);
                    
                    startAngle += arcAngles[i];
                }

                // Draw center cap
                g2D.setColor(Color.WHITE);
                g2D.fillOval(x + size / 2 - 15, y + size / 2 - 15, 30, 30);
                g2D.setColor(Color.DARK_GRAY);
                g2D.drawOval(x + size / 2 - 15, y + size / 2 - 15, 30, 30);

                // Draw outer wheel boundary border
                g2D.setColor(Color.DARK_GRAY);
                g2D.setStroke(new BasicStroke(4.0f));
                g2D.drawOval(x, y, size, size);

                // Draw pointer arrow (positioned at top)
                g2D.setColor(Color.BLACK);
                Polygon pointer = new Polygon();
                pointer.addPoint(getWidth() / 2, y - 10);
                pointer.addPoint(getWidth() / 2 - 10, y - 25);
                pointer.addPoint(getWidth() / 2 + 10, y - 25);
                g2D.fillPolygon(pointer);
            }
        };
        wheelPanel.setBackground(Color.WHITE);
        add(wheelPanel, BorderLayout.CENTER);

        spinButton = new JButton("SPIN THE WHEEL!");
        spinButton.setFont(new Font("Arial", Font.BOLD, 16));
        spinButton.setBackground(new Color(155, 89, 182));
        spinButton.setForeground(Color.WHITE);
        spinButton.setFocusPainted(false);
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!spinning) {
                    spinWheel();
                }
            }
        });
        add(spinButton, BorderLayout.SOUTH);
    }

    private void spinWheel() {
        spinning = true;
        spinButton.setEnabled(false);

        // Calculate rewards strictly using your custom probability weightings
        double roll = Math.random() * 100;
        if (roll < 20) {
            selectedReward = 0; // Play First (20%)
        } else if (roll < 50) {
            selectedReward = 1; // Good Luck (30%)
        } else if (roll < 85) {
            selectedReward = 2; // Skip One Question (35%)
        } else {
            selectedReward = 3; // Play Without Questions (15%)
        }

        Timer timer = new Timer(20, null);
        final long startTime = System.currentTimeMillis();
        final long duration = 2500; // 2.5 seconds total spin

        timer.addActionListener(new ActionListener() {
            double speed = 35.0; 
            
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - startTime;
                if (elapsed >= duration) {
                    timer.stop();
                    
                    // Align selected segment midpoint perfectly under the top pointer
                    double cumulative = selectedReward * 90.0 + 45.0;
                    currentAngle = 90.0 - cumulative;
                    wheelPanel.repaint();
                    
                    applyRewardToGame();
                    
                    JOptionPane.showMessageDialog(PreGameSpinner.this, 
                        "🎁 Reward: " + rewardNames[selectedReward], 
                        "Lucky Winner", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    spinning = false;
                    dispose();
                } else {
                    double ratio = (double) elapsed / duration;
                    speed = 35.0 * (1.0 - ratio);
                    currentAngle = (currentAngle + speed) % 360;
                    wheelPanel.repaint();
                }
            }
        });
        timer.start();
    }

    private void applyRewardToGame() {
        switch (selectedReward) {
            case 0:
                parent.youFirstRadioButton.setSelected(true);
                parent.computerFirstRadioButton.setSelected(false);
                break;
            case 1:
                break;
            case 2:
                parent.skipOneQuestionActive = true;
                break;
            case 3:
                parent.playNoQuestionsActive = true;
                break;
        }
    }
}