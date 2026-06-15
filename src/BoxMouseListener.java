 //A custom event listener that extends MouseAdapter to handle clicking on the grid squares.

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxMouseListener extends MouseAdapter {
    private final Main gameFrame;
    private final int boxIndex;

    public BoxMouseListener(Main gameFrame, int boxIndex) {
        this.gameFrame = gameFrame;
        this.boxIndex = boxIndex;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Safe check to confirm the grid coordinate matches the UI element source
        if (e.getComponent().getX() == gameFrame.boxTextField[boxIndex].getX() && 
            e.getComponent().getY() == gameFrame.boxTextField[boxIndex].getY()) {
            gameFrame.handleBoxClick(boxIndex);
        }
    }
}