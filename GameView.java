import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE

    private DotButton[][] board;
    private GameModel gameModel;
    private javax.swing.JLabel nbreOfStepsLabel;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE

        this.gameModel=gameModel;

        setTitle("MineSweeper it -- the ITI 1121 version");

        // For minesweeper matrix
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(gameModel.getHeigth(),gameModel.getWidth(),0,0));

        board = new DotButton[gameModel.getHeigth()][gameModel.getWidth()];

        // Integer temp used to store the tile's setActionCommand number so we can identify which tile is being clicked
        int temp=0;

        // Adding buttons to board matrix
        for (int i=0; i<gameModel.getHeigth(); i++) {

           for (int j=0; j<gameModel.getWidth(); j++) {

                board[i][j] = new DotButton(j,i,11);

                board[i][j].setPreferredSize(new Dimension(28,28));
                
                board[i][j].addActionListener(gameController);
                board[i][j].setActionCommand(Integer.toString(temp));
                
                temp++;

                p1.add(board[i][j]);
                
            }
            
        }

        // For buttons and text field
        JPanel p2 = new JPanel();

        JButton reset = new JButton("Reset");
        reset.addActionListener(gameController);
        
        JButton quit = new JButton("Quit");
        quit.addActionListener(gameController);

        nbreOfStepsLabel = new JLabel("Number of steps: " + Integer.toString(gameModel.getNumberOfSteps()));

        p2.add(nbreOfStepsLabel);
        p2.add(reset);
        p2.add(quit);
        
        // Frame related stuff
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Window isn't resizable so it doesnt look bad when stretched out
        setVisible(true);
    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE

        // Updating the number of steps label
        nbreOfStepsLabel.setText("Number of steps: " + Integer.toString(gameModel.getNumberOfSteps()));
   
        // Updating the icons
        for (int i=0; i<gameModel.getHeigth(); i++) {

            for (int j=0; j<gameModel.getWidth(); j++) {

                board[i][j].setIconNumber(getIcon(j,i));
                
            }   
        }

        pack();
    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
    // ADD YOU CODE HERE

        // Case 11 (unopened fields)
        if (gameModel.isCovered(i,j)) {

            return 11;

        }

        // Case 10 (user presses on a field that is mined)
        else if (gameModel.hasBeenClicked(i,j) && gameModel.isMined(i,j)) {

            return 10;

        }

        // Case 9 (if the field is a mine)
        else if (gameModel.isMined(i,j)) {

            return 9;
            
        }

        // Case 0 to 8 (user presses on a field that is not mined and program displays neighboring mines)
        else {

            return gameModel.getNeighbooringMines(i,j);

        }

        // CASE 12 BONUS

    }
}