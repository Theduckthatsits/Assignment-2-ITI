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

        JFrame f = new JFrame("MineSweeper it -- the ITI 1121 version");
        f.setSize(500,500);

        // For minesweeper matrix
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2)); // Fix this


        // For buttons and text field
        JPanel p2 = new JPanel();

        JButton b1 = new JButton("Reset");
        b1.addActionListener(this); // FIX
        
        JButton b2 = new JButton("Quit");
        b2.addActionListener(this); // FIX

        JTextField f1 = new JTextField("Number of steps: ");
        f1.setEditable(false);

        p2.add(f1);
        p2.add(b1);
        p2.add(b2);
        

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    }


}
