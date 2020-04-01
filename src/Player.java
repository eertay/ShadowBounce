import bagel.Window;
import org.lwjgl.system.CallbackI;

public class Player {

    private int numShots = 20;
    private Turn turn;

    /** Constructs Player, which keeps count of the shots and turns of the player
     *
     */
    public Player() { }

    /** This method gives information on player's number of shots
     *
     * @return number of shots left for the player
     */
    public int getNumShots() { return numShots; }

    /** This method updates the number of shots
     *
     * @param numShots old number of shots to be updated
     */
    public void setNumShots(int numShots) {
        this.numShots = numShots;
    }

    /** This method returns the turn of the player
     *
     * @return current turn
     */
    public Turn getTurn() { return turn; }

    /** This method changes the turn of the player
     *
     * @param turn player's current turn to be changed
     */
    public void setTurn(Turn turn) { this.turn = turn; }

    /** This method decides on whether a turn is started or not
     *
     * @param ballArray array containing all balls, to check if they are on the screen
     */
    public void startTurn(Ball[] ballArray) {
        int num = 0;
        for (int i = 0; i < 3; i++) {
            if (ballArray[i] != null && !(ballArray[i].outOfScreen())) {
                num++;
            }
        }

        if (num != 0) {
            this.turn = new Turn();
        }
    }

    /** This method ends the turn
     *
     * @param ballArray array of balls to see if they are all on screen
     */
    public void endTurn(Ball[] ballArray) {
        int num = 0;
        for (int i = 0; i < 3; i++) {
            if (ballArray[i] != null && !(ballArray[i].outOfScreen())) {
                num++;
            }
        }

        if (num == 0) {
            this.turn = null;
        }
    }

    /** This method updates the turn's situation
     *
     * @param ballArray array of balls to see if they are all on screen
     */
    public void update(Ball[] ballArray) {
        this.startTurn(ballArray);
        this.endTurn(ballArray);
    }

    /** This method updates the balls which are either on the screen or not
     *
     * @param ballArray array of balls to be updated
     */
    public void makeTurn(Ball[] ballArray) {


        int numBalls = 0;

        for (int i = 0; i < 3; i++) {
            if (ballArray[i] != null) {
                ballArray[i].update();

                // Deleting the ball when it leaves the screen and decreasing the number of shots
                if (ballArray[i].outOfScreen()) {
                    this.setNumShots(this.getNumShots() - 1);
                    ballArray[i] = null;
                    numBalls++;
                }
            }
        }
        /*
        if (numBalls != 3) {
            this.turn = new Turn();
        } */
    }

    /** This method detects when the game is ended, in other words when 20 shots are consumed
     *
     */
    public void endGame() {
        if (numShots == 0) {
            Window.close();
        }
    }

}
