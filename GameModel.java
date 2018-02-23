import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE

    private int widthOfGame,heigthOfGame,numberOfMines,numberOfSteps,numberUncovered;
    private DotInfo[][] model;
    private Random generator;

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE

        this.widthOfGame=width;
        this.heigthOfGame=heigth;
        this.numberOfMines=numberOfMines;

        numberOfSteps=0;
        numberUncovered=0;
        
        model = new DotInfo[widthOfGame][heigthOfGame];

        generator = new Random();

        reset();

    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

        
    // ADD YOU CODE HERE

    	numberOfSteps=0;
    	numberUncovered=0;
    	model = new DotInfo[widthOfGame][heigthOfGame];

        // Initializing model Array with Dots
        for (int i=0; i<widthOfGame; i++) {

            for (int j=0; j<heigthOfGame; j++) {

                model[i][j]= new DotInfo(i,j);
                
            }
        }

        // Assigning random mines
        for (int s=0; s<numberOfMines ; s++) {

            // Generating random x and y values
            int x=random.nextInt();
            int y=random.nextInt();

            // If the random point (x,y) doesn't contain a mine, place a mine
            // This is done because the generator might generate a point that has already been mined before
            if (!(model[i][j].isMined())) {

                model[i][j].setMined();
                
            }   
        }

        // For loops used to count the number of neighboring mines for a non-mined point
        for (int i=0; i<widthOfGame; i++) {

            for (int j=0; j<heigthOfGame; j++) {

                if (!(model[i][j].isMined())) {

                    int neighbMines=0;

                    for (int a=i-1; a<i+2; a++) {

                        for (int b=j-1; b<j+2; b++) {

                            if (a>=0 && b>=0 && a<widthOfGame && b<heigthOfGame) {

                                if (model[a][b].isMined()) {

                                    neighbMines++;
                                    
                                }   
                            }
                        }
                    } 
                }

                model[i][j].setNeighboringMines(neighbMines);
            }
        }
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE

        return heigthOfGame;

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE

        return widthOfGame;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE

        return model[i][j].isMined();

    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE

        return model[i][j].hasBeenClicked();

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE

        return model[i][j].getNeighbooringMines()==0;

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE

        return model[i][j].isCovered();

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE

        return model[i][j].getNeighbooringMines();

    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE

        model[i][j].uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE

        model[i][j].click();

    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE

        for (int i=0; i<widthOfGame; i++) {

            for (int j=0; j<heigthOfGame; j++) {

                if (model[i][j].isCovered()) {

                    model[i][j].uncover();
                    
                }
            }
        }
    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE

        return numberOfSteps;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE

        return model[i][j];

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
        
    // ADD YOU CODE HERE

        numberOfSteps++;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE

        return (widthOfGame*heigthOfGame-numberOfMines)==numberUncovered;

    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE

        String temp="";

        for (; ; ) {

            for (; ; ) {

                if () {
                    
                }

                else if () {
                    
                }

                else if () {
                    
                }       
            }
        }
    }
}
