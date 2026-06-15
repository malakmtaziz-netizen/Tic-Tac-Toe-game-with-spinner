//Contains the core rules of Tic-Tac-Toe, winning calculations, and both the "Random" and "Smart" AI moves.

import java.util.Random;

public class GameEngine {
    private final String[] possibleWins = {
        "012", "345", "678", "036", "147", "258", "048", "246"
    };
    private final int[] bestMoves = { 4, 0, 2, 6, 8, 1, 3, 5, 7 };
    private final Random myRandom = new Random();

    public String[] getPossibleWins() {
        return possibleWins;
    }

    public int getRandomMove(String[] board, int numberClicks) {
        int n = myRandom.nextInt(9 - numberClicks) + 1;
        int count = 0;
        for (int selectedBox = 0; selectedBox < 9; selectedBox++) {
            if (board[selectedBox].equals("")) {
                count++;
            }
            if (count == n) {
                return selectedBox;
            }
        }
        return -1;
    }

    public int getSmartMove(String[] board, boolean computerFirst) {
        String computerMark = computerFirst ? "X" : "O";
        String playerMark = computerFirst ? "O" : "X";

        // Step 1: Check for potential computer win. Step 2: Check for potential block.
        for (int k = 1; k <= 2; k++) {
            String markToFind = (k == 1) ? computerMark : playerMark;
            for (int i = 0; i < 8; i++) {
                int matchCount = 0;
                int emptyBox = -1;
                for (int j = 0; j < 3; j++) {
                    int boxIndex = Character.getNumericValue(possibleWins[i].charAt(j));
                    String mark = board[boxIndex];
                    if (mark.equals(markToFind)) {
                        matchCount++;
                    } else if (mark.equals("")) {
                        emptyBox = boxIndex;
                    }
                }
                if (matchCount == 2 && emptyBox != -1) {
                    return emptyBox;
                }
            }
        }

        // Step 3: Find next best move
        for (int bestMove : bestMoves) {
            if (board[bestMove].equals("")) {
                return bestMove;
            }
        }
        return -1;
    }
}