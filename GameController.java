import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE

    private GameView gameView;
    private GameModel gameModel;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE

        gameModel = new GameModel(width, height, numberOfMines);
        gameView = new GameView(gameModel,this);

    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
    // ADD YOU CODE HERE

        // If the user presses on the reset button
    	if (e.getActionCommand().equals("Reset")) {

			this.reset();
		}

        // If the user presses on the quit button
    	else if (e.getActionCommand().equals("Quit")) {

            System.exit(0);
		}

        // If the user presses on any tile (button)
        else {

            for (int i=0; i<gameModel.getWidth(); i++) {

                for (int j=0; j<gameModel.getHeigth(); j++) {

                    if (e.getActionCommand().equals(Integer.toString(gameModel.getWidth()*j+i))) {

                        // Increments the number of steps by one
                        gameModel.step();

                        // tile is set to click and game logic applies to that tile
                        gameModel.click(i,j);
                        play(i,j);

                        // Prints out a string representation of the board
                        System.out.println(gameModel.toString());
                        
                    }
                }
            }
        }
    }

    /**
     * resets the game
     */
    private void reset(){

    // ADD YOU CODE HERE

        gameModel.reset();
        gameView.update();

    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE

        Object[] options = {"Quit", "Play Again"};

        // If the user presses on a tile that contains a mine, all the tiles are uncovered and the game finishes.
        if (gameModel.isMined(width,heigth)) {

            gameModel.uncoverAll();
            gameView.update();

            // Pop up window
            int result=JOptionPane.showOptionDialog(null, "Aouch, you lost in "+Integer.toString(gameModel.getNumberOfSteps())+" steps!\nWould you like to play again?","Boom!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            // If the user wants to quit the game
            if (result == JOptionPane.YES_OPTION) {

                System.exit(0);
            }

            // If the user wants to play again
            if (result == JOptionPane.NO_OPTION) {

                gameModel.reset();
                gameView.update();
                
            }
        }

        else if (gameModel.isCovered(width,heigth)) {

            // Set the tile to clicked and uncover it
            gameModel.click(width,heigth);
            gameModel.uncover(width,heigth);

            if (gameModel.isFinished()) {

                gameModel.uncoverAll();
                gameView.update();

                // Pop up window
                int result=JOptionPane.showOptionDialog(null,"Congratulations! You won in "+Integer.toString(gameModel.getNumberOfSteps())+" steps!\nWould you like to play again?","Won", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

                // If the user wants to quit the game
                if (result == JOptionPane.YES_OPTION) {

                    System.exit(0);

                }

                // If the user wants to play again
                if (result == JOptionPane.NO_OPTION) {

                    gameModel.reset();
                    gameView.update();

                }
            }

            gameView.update();

            // Add clearZone code
            
        }

    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {


    // ADD YOU CODE HERE

    }



}
