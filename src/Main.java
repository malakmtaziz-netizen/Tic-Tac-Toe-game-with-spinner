//The main GUI window that coordinates the game. It extends JFrame and implements ActionListener to manage UI buttons.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class Main extends JFrame implements ActionListener {
    JTextField messageTextField;
    JPanel gamePanel;
    JTextField[] boxTextField = new JTextField[9];
    JLabel[] gridLabel = new JLabel[4];
    
    JPanel playersPanel;
    ButtonGroup playersButtonGroup;
    JRadioButton twoPlayersRadioButton;
    JRadioButton onePlayerRadioButton;
    
    JPanel firstPanel;
    ButtonGroup firstButtonGroup;
    JRadioButton youFirstRadioButton;
    JRadioButton computerFirstRadioButton;
    
    JPanel computerPanel;
    ButtonGroup computerButtonGroup;
    JRadioButton randomRadioButton;
    JRadioButton smartRadioButton;
    
    JPanel buttonsPanel;
    JButton startStopButton;
    JButton exitButton;
    
    JTextArea questionTextArea;
    JTextField answerTextField;
    JButton checkButton;
    GraphicsPanel displayPanel;

    // Game state variables
    boolean xTurn;
    boolean canClick = false;
    int numberClicks;
    boolean gameOver;
    int tries;
    boolean correctAnswer;
    int questionIndex;

    // Pre-game lucky wheel states
    boolean skipOneQuestionActive = false;
    boolean playNoQuestionsActive = false;

    // References to helper classes
    final GameEngine gameEngine;
    final QuestionDatabase questionDb;

    public static void main(String args[]) {
        new Main().setVisible(true);
    }

    public Main() {
        gameEngine = new GameEngine();
        questionDb = new QuestionDatabase();

        setTitle("Tic Tac Toe");
        getContentPane().setBackground(new Color(200,191,231));
        setResizable(false);
        
        // Prevent automatic closure so our custom listener has full decision-making control
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                confirmAndExit();
            }
        });
        getContentPane().setLayout(new GridBagLayout());

        // Message text field
        messageTextField = new JTextField();
        messageTextField.setPreferredSize(new Dimension(320, 60));
        messageTextField.setEditable(false);
        messageTextField.setBackground(Color.BLACK);
        messageTextField.setForeground(Color.WHITE);
        messageTextField.setText("X's Move");
        messageTextField.setHorizontalAlignment(SwingConstants.CENTER);
        messageTextField.setFont(new Font("Arial", Font.BOLD, 30));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(20, 20, 20, 90);
        getContentPane().add(messageTextField, gridConstraints);

        // Game Panel Setup
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(320, 320));
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridheight = 3;
        gridConstraints.insets = new Insets(20, 20, 20, 90);
        getContentPane().add(gamePanel, gridConstraints);

        // Grid buttons
        for (int i = 0; i < 9; i++) {
            boxTextField[i] = new JTextField();
            boxTextField[i].setPreferredSize(new Dimension(104, 104));
            boxTextField[i].setEditable(false);
            boxTextField[i].setBackground(Color.WHITE);
            boxTextField[i].setHorizontalAlignment(SwingConstants.CENTER);
            boxTextField[i].setFont(new Font("Arial", Font.BOLD, 58));
            boxTextField[i].setBorder(null);
            
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = 2 * (i % 3);
            gridConstraints.gridy = 2 * (i / 3);
            gamePanel.add(boxTextField[i], gridConstraints);
            
            // Registering our custom mouse listener class
            boxTextField[i].addMouseListener(new BoxMouseListener(this, i));
        }

        // Draw visual grid lines
        setupGridLines();

        // Players selection panel
        playersPanel = new JPanel();
        playersPanel.setPreferredSize(new Dimension(160, 75));
        playersPanel.setBackground(Color.WHITE);
        playersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playersPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(playersPanel, gridConstraints);

        playersButtonGroup = new ButtonGroup();
        twoPlayersRadioButton = new JRadioButton("Two Players");
        twoPlayersRadioButton.setBackground(Color.WHITE);
        twoPlayersRadioButton.setSelected(true);
        playersButtonGroup.add(twoPlayersRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(twoPlayersRadioButton, gridConstraints);
        twoPlayersRadioButton.addActionListener(this);

        onePlayerRadioButton = new JRadioButton("One Player");
        onePlayerRadioButton.setBackground(Color.WHITE);
        playersButtonGroup.add(onePlayerRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(onePlayerRadioButton, gridConstraints);
        onePlayerRadioButton.addActionListener(this);

        // Turn selection panel
        firstPanel = new JPanel();
        firstPanel.setPreferredSize(new Dimension(160, 75));
        firstPanel.setBackground(Color.WHITE);
        firstPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        firstPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(firstPanel, gridConstraints);

        firstButtonGroup = new ButtonGroup();
        youFirstRadioButton = new JRadioButton("You First");
        youFirstRadioButton.setBackground(Color.WHITE);
        youFirstRadioButton.setSelected(true);
        firstButtonGroup.add(youFirstRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        firstPanel.add(youFirstRadioButton, gridConstraints);

        computerFirstRadioButton = new JRadioButton("Computer First");
        computerFirstRadioButton.setBackground(Color.WHITE);
        firstButtonGroup.add(computerFirstRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        firstPanel.add(computerFirstRadioButton, gridConstraints);

        // Computer AI selection panel
        computerPanel = new JPanel();
        computerPanel.setPreferredSize(new Dimension(160, 75));
        computerPanel.setBackground(Color.WHITE);
        computerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        computerPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(computerPanel, gridConstraints);

        computerButtonGroup = new ButtonGroup();
        randomRadioButton = new JRadioButton("Random Computer");
        randomRadioButton.setBackground(Color.WHITE);
        randomRadioButton.setSelected(true);
        computerButtonGroup.add(randomRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        computerPanel.add(randomRadioButton, gridConstraints);

        smartRadioButton = new JRadioButton("Smart Computer");
        smartRadioButton.setBackground(Color.WHITE);
        computerButtonGroup.add(smartRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        computerPanel.add(smartRadioButton, gridConstraints);

        // Buttons Panel Setup
        buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(160, 75));
        buttonsPanel.setBackground(new Color(200,191,231));
        buttonsPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        getContentPane().add(buttonsPanel, gridConstraints);

        startStopButton = new JButton("Start Game");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        buttonsPanel.add(startStopButton, gridConstraints);
        startStopButton.addActionListener(this);

        exitButton = new JButton("Exit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        buttonsPanel.add(exitButton, gridConstraints);
        exitButton.addActionListener(this);

        // Trivia inputs and labels
        questionTextArea = new JTextArea();
        questionTextArea.setPreferredSize(new Dimension(450, 40));
        questionTextArea.setBackground(new Color(200,191,231));
        questionTextArea.setFont(new Font("Arial", Font.BOLD, 16));
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth = 1;
        gridConstraints.insets = new Insets(10, 60, 0, 0);
        getContentPane().add(questionTextArea, gridConstraints);

        answerTextField = new JTextField();
        answerTextField.setPreferredSize(new Dimension(280, 30));
        answerTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        gridConstraints.gridwidth = 1;
        gridConstraints.insets = new Insets(10, -100, 10, 0);
        getContentPane().add(answerTextField, gridConstraints);

        checkButton = new JButton("Check Answer");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        gridConstraints.insets = new Insets(0, 0, 10, 0);
        getContentPane().add(checkButton, gridConstraints);
        checkButton.addActionListener(this);

        displayPanel = new GraphicsPanel();
        displayPanel.setPreferredSize(new Dimension(490, 100));
        displayPanel.setBackground(new Color(200,191,231));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 6;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(0, 0, 10, 0);
        getContentPane().add(displayPanel, gridConstraints);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());

        messageTextField.setText("Game Stopped");
        toggleComputerOptions(false);

        questionTextArea.setVisible(false);
        answerTextField.setVisible(false);
        checkButton.setVisible(false);
        displayPanel.repaint();
    }

    private void setupGridLines() {
        GridBagConstraints gridConstraints;
        for (int i = 0; i < 4; i++) {
            gridLabel[i] = new JLabel();
            gridLabel[i].setOpaque(true);
            gridLabel[i].setBackground(Color.BLACK);
        }
        gridLabel[0].setPreferredSize(new Dimension(320, 4));
        gridLabel[1].setPreferredSize(new Dimension(320, 4));
        gridLabel[2].setPreferredSize(new Dimension(4, 320));
        gridLabel[3].setPreferredSize(new Dimension(4, 320));

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0; gridConstraints.gridy = 1; gridConstraints.gridwidth = 5;
        gamePanel.add(gridLabel[0], gridConstraints);

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0; gridConstraints.gridy = 3; gridConstraints.gridwidth = 5;
        gamePanel.add(gridLabel[1], gridConstraints);

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1; gridConstraints.gridy = 0; gridConstraints.gridheight = 5;
        gamePanel.add(gridLabel[2], gridConstraints);

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3; gridConstraints.gridy = 0; gridConstraints.gridheight = 5;
        gamePanel.add(gridLabel[3], gridConstraints);
    }

    // Implementing ActionListener allows this centralized method to handle event actions cleanly
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == twoPlayersRadioButton) {
            toggleComputerOptions(false);
        } else if (source == onePlayerRadioButton) {
            toggleComputerOptions(true);
        } else if (source == startStopButton) {
            startStopButtonActionPerformed();
        } else if (source == exitButton) {
            confirmAndExit();
        } else if (source == checkButton) {
            checkButtonActionPerformed();
        }
    }

    private void confirmAndExit() {
        int response = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to exit?", 
            "Exit Confirmation", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void toggleComputerOptions(boolean enable) {
        // Force the player to play second ("Computer First") unless they win the Play First reward
        if (onePlayerRadioButton.isSelected()) {
            youFirstRadioButton.setEnabled(false);
            computerFirstRadioButton.setEnabled(false);
            computerFirstRadioButton.setSelected(true); // Default starting turn set to Computer First
        } else {
            youFirstRadioButton.setEnabled(enable);
            computerFirstRadioButton.setEnabled(enable);
        }
        randomRadioButton.setEnabled(enable);
        smartRadioButton.setEnabled(enable);
    }

    private void startStopButtonActionPerformed() {
        if (startStopButton.getText().equals("Start Game")) {
            startStopButton.setText("Stop Game");
            twoPlayersRadioButton.setEnabled(false);
            onePlayerRadioButton.setEnabled(false);
            toggleComputerOptions(false);
            exitButton.setEnabled(false);
            
            xTurn = true;
            messageTextField.setText("X's Turn");
            for (int i = 0; i < 9; i++) {
                boxTextField[i].setText("");
                boxTextField[i].setBackground(Color.WHITE);
            }
            canClick = true;
            numberClicks = 0;
            gameOver = false;

            // Trigger and evaluate Pre-Game Lucky Wheel Spinner
            if (onePlayerRadioButton.isSelected()) {
                skipOneQuestionActive = false;
                playNoQuestionsActive = false;

                // Reset standard starting turn to Computer First prior to spinning
                computerFirstRadioButton.setSelected(true);
                youFirstRadioButton.setSelected(false);

                PreGameSpinner spinner = new PreGameSpinner(this);
                spinner.setVisible(true);

                if (playNoQuestionsActive) {
                    questionTextArea.setText("Lucky Spinner Reward Active: Unlimited Trivia Skip!");
                    answerTextField.setText("");
                    questionTextArea.setVisible(true);
                    answerTextField.setVisible(false);
                    checkButton.setVisible(false);
                    correctAnswer = true;
                } else {
                    questionTextArea.setText("");
                    answerTextField.setText("");
                    questionTextArea.setVisible(true);
                    answerTextField.setVisible(true);
                    checkButton.setVisible(true);
                    correctAnswer = false;
                }
                
                if (computerFirstRadioButton.isSelected()) {
                    computerTurn();
                } else {
                    if (playNoQuestionsActive) {
                        messageTextField.setText("X's Turn (No Questions!)");
                    } else if (skipOneQuestionActive) {
                        messageTextField.setText("X's Turn (One-Time Skip Active!)");
                        correctAnswer = true; 
                    } else {
                        askQuestion();
                    }
                }
            } else {
                questionTextArea.setVisible(false);
                answerTextField.setVisible(false);
                checkButton.setVisible(false);
            }
        } else {
            startStopButton.setText("Start Game");
            if (!gameOver) {
                messageTextField.setText("Game Stopped");
            }
            twoPlayersRadioButton.setEnabled(true);
            onePlayerRadioButton.setEnabled(true);
            if (onePlayerRadioButton.isSelected()) {
                toggleComputerOptions(true);
            }
            exitButton.setEnabled(true);
            canClick = false;
            questionTextArea.setText("");
            answerTextField.setText("");
        }
    }

    private void askQuestion() {
        tries = 0;
        correctAnswer = false;
        questionIndex = questionDb.getRandomQuestionIndex();
        questionTextArea.setText(questionDb.getQuestion(questionIndex));
        answerTextField.setText("");
    }

    private void checkButtonActionPerformed() {
        tries++;
        String correctAnswerStr = questionDb.getAnswer(questionIndex);
        if (tries > correctAnswerStr.length()) {
            tries = correctAnswerStr.length();
        }
        
        // Use Turkish Locale to ensure accurate case-insensitive comparison of Turkish characters
        Locale trLocale = new Locale("tr", "TR");
        String yourAnswer = answerTextField.getText().toUpperCase(trLocale).trim();
        String targetAnswer = correctAnswerStr.toUpperCase(trLocale).trim();
        
        if (yourAnswer.equals(targetAnswer)) {
            answerTextField.setText(yourAnswer + " - CORRECT. CLICK GRID.");
            correctAnswer = true;
        } else {
            answerTextField.setText(correctAnswerStr.substring(0, tries));
        }
    }

    // This gets called by BoxMouseListener when a valid grid box is clicked
    public void handleBoxClick(int index) {
        if (canClick) {
            if (onePlayerRadioButton.isSelected() && !correctAnswer) {
                return;
            }

            // Reward gets consumed IMMEDIATELY before processing any turn executions
            if (onePlayerRadioButton.isSelected() && skipOneQuestionActive) {
                skipOneQuestionActive = false;
                correctAnswer = false;
            }

            markClickedBox(index);
        }
    }

    private void markClickedBox(int i) {
        if (!boxTextField[i].getText().equals("")) {
            return;
        }
        numberClicks++;
        if (xTurn) {
            boxTextField[i].setText("X");
            xTurn = false;
            messageTextField.setText("O's Turn");
        } else {
            boxTextField[i].setText("O");
            xTurn = true;
            messageTextField.setText("X's Turn");
        }

        String whoWon = checkForWin();
        if (!whoWon.equals("")) {
            messageTextField.setText(whoWon + " wins!");
            gameOver = true;
            startStopButton.doClick();
            return;
        } else if (numberClicks == 9) {
            messageTextField.setText("It's a draw!");
            for (int j = 0; j < 9; j++) {
                boxTextField[j].setBackground(Color.BLUE);
            }
            gameOver = true;
            startStopButton.doClick();
            return;
        }

        if (onePlayerRadioButton.isSelected() && !gameOver) {
            if ((xTurn && computerFirstRadioButton.isSelected()) || (!xTurn && youFirstRadioButton.isSelected())) {
                computerTurn();
            } else {
                if (playNoQuestionsActive) {
                    correctAnswer = true;
                } else if (skipOneQuestionActive) {
                    correctAnswer = true;
                } else {
                    askQuestion();
                }
            }
        }
    }

    private String checkForWin() {
        String winner = "";
        String[] board = getBoardState();
        String[] wins = gameEngine.getPossibleWins();

        for (int i = 0; i < 8; i++) {
            int[] boxNumbers = new int[3];
            String[] marks = new String[3];
            for (int j = 0; j < 3; j++) {
                boxNumbers[j] = Character.getNumericValue(wins[i].charAt(j));
                marks[j] = board[boxNumbers[j]];
            }
            if (marks[0].equals(marks[1]) && marks[0].equals(marks[2]) && !marks[0].equals("")) {
                winner = marks[0];
                for (int j = 0; j < 3; j++) {
                    boxTextField[boxNumbers[j]].setBackground(Color.GREEN);
                }
            }
        }
        return winner;
    }

    private void computerTurn() {
        int selectedBox;
        String[] board = getBoardState();

        if (randomRadioButton.isSelected()) {
            selectedBox = gameEngine.getRandomMove(board, numberClicks);
        } else {
            selectedBox = gameEngine.getSmartMove(board, computerFirstRadioButton.isSelected());
        }

        if (selectedBox != -1) {
            markClickedBox(selectedBox);
        }
    }

    private String[] getBoardState() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = boxTextField[i].getText();
        }
        return board;
    }
}