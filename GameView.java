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

        JFrame f = new JFrame("MineSweeper it -- the ITI 1121 version");
        f.setSize(380,500);

        // For minesweeper matrix
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(gameModel.getWidth(),gameModel.getHeigth(),0,0));

        JButton tiles[][] = new JButton[gameModel.getWidth()][gameModel.getHeigth()];
        //board = new DotButton[gameModel.getWidth()][gameModel.getHeigth()];
        //board[i][j] = new DotButton(i,j,11);

        for (int i=0; i<gameModel.getWidth(); i++) {

            for (int j=0; j<gameModel.getHeigth(); j++) {

                tiles[i][j] = new JButton(new ImageIcon("icons/MineSweeper_unopened_square.png"));
                tiles[i][j].setSize(new Dimension(28,28));
                tiles[i][j].setBackground(Color.WHITE);
                p1.add(tiles[i][j]);
                
            }
            
        }

        // For buttons and text field
        JPanel p2 = new JPanel();

        JButton b1 = new JButton("Reset");
        b1.addActionListener(gameController);
        
        JButton b2 = new JButton("Quit");
        b2.addActionListener(gameController);

        nbreOfStepsLabel = new JLabel("Number of steps: " + gameModel.getNumberOfSteps());

        p2.add(nbreOfStepsLabel);
        p2.add(b1);
        p2.add(b2);
        

        f.add(p1, BorderLayout.CENTER);
        f.add(p2, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setVisible(true);
    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE

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
