import bagel.*;
import bagel.MouseButtons;
import bagel.util.Point;
import bagel.util.Side;
import bagel.util.Vector2;

import java.util.Random;

public class ShadowBounce extends AbstractGame {

    // Initialising al classes
    private Ball ball;
    private Peg[] allPegs;
    private Bucket bucket;
    private Player player;
    private Board board;
    private Powerup powerup;

    private Random randomGenerator = new Random();
    private static final Point BALL_POSITION = new Point(512, 32);
    private static final Point BUCKET_POSITION = new Point(512, 744);
    private Vector2 zero = new Vector2();
    private String[] boards = {"res/0.csv", "res/1.csv", "res/2.csv", "res/3.csv", "res/4.csv"};
    private int boardNum = 0;
    private int greenPos = -1;
    private Peg oldPeg;
    private Ball[] ballArray = new Ball[3];
    private Point powerupCoord =  new Point(randomGenerator.nextInt(Window.getWidth()), randomGenerator.nextInt(Window.getHeight()));

    /** This method constructs the ShadowBounce class with initialised objects
     *
     */
    public ShadowBounce() {

        board = new Board();
        allPegs = board.readBoard(boards[boardNum]);
        bucket = new Bucket(BUCKET_POSITION);
        player = new Player();

    }

    /**
     * Program starts here:
     */
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }

    // Following method has been adapted from Project 1 sample solution

    /** This method runs the program and updates the objects in the game
     *
     * @param input the input to the program
     */
    @Override
    protected void update(Input input) {

        bucket.update();

        // Collision detection for all balls in the ballArray and changing the velocity components for
        // the bouncing movement if a peg has been hit
        for (int k = 0; k < 3; k++) {
            ball = ballArray[k];
            for (int i = 0; i < allPegs.length; ++i) {
                if (allPegs[i] != null) {
                    if (ball != null && ball.intersectedAt(allPegs[i].getCoordinates(), zero) != Side.NONE) {

                        // Only normal ball bounces off the pegs
                        if (!(ball instanceof FireBall)) {
                            ball.pegBounce(allPegs[i]);
                        }
                        // Fireball destroys pegs within 70 pixels of the hit peg
                        if (ball instanceof FireBall) {
                            allPegs[i].getBiggerRect();
                            allPegs[i].destroyPegsAround(allPegs, board);
                        }

                        // Decreasing the number of red pegs every time one is destroyed
                        if (allPegs[i] instanceof RedPeg) {
                            board.setRedPegNum(board.getRedPegNum() - 1);
                        }
                        // When the green peg is hit, filling up the ballArray with new balls and starting their movement
                        if (!(allPegs[i] instanceof GreyPeg)) {
                            if (allPegs[i] instanceof GreenPeg) {
                                ballArray[1] = new Ball(allPegs[i].getCoordinates(), Vector2.up.add(Vector2.left));
                                ballArray[2] = new Ball(allPegs[i].getCoordinates(), Vector2.up.add(Vector2.right));
                                ballArray[0].update();
                            }
                            allPegs[i] = null;
                        }

                    } else {
                        allPegs[i].update();
                    }
                }
            }
        }

        // Choosing a random peg each turn
        int rand = randomGenerator.nextInt(allPegs.length);

        while(allPegs[rand] == null) {
            rand = randomGenerator.nextInt(allPegs.length);
        }

        // Creating a ball when the mouse button was clicked if there wasn't an existing ball and allocating a green peg
        if (input.wasPressed(MouseButtons.LEFT) && ballArray[0] == null) {

            ball = new Ball(BALL_POSITION, input.directionToMouse(BALL_POSITION));

            // Adding the ball to the ball array
            ballArray[0] = ball;

            // Checking if the green peg has been chosen and making a copy of the old peg before turning it into green
            if(allPegs[rand] != null && allPegs[rand] instanceof BluePeg) {
                if(this.greenPos >  0 && allPegs[this.greenPos] != null ){
                    allPegs[this.greenPos] = oldPeg;
                }
                this.greenPos = rand;
                oldPeg = allPegs[rand];
                allPegs[rand] = new GreenPeg(allPegs[rand].getCoordinates(), allPegs[rand].getShape());
            }
        }

        // Moving onto the next board when all red pegs are destroyed
        if (board.getRedPegNum() == 0) {
            board = new Board();
            boardNum++;
            allPegs = board.readBoard(boards[boardNum]);
        }

        player.makeTurn(ballArray);
        bucket.catchBall(ballArray, player);
        player.update(ballArray);

        // If the player's currently in a turn and there isn't a powerup, depending on the probability create a powerup
        if (player.getTurn() != null && powerup == null) {
            if (player.getTurn().getProb() && ball !=null) {
                powerup = new Powerup(powerupCoord);
            }
        }
        // Delete the powerup when the turn ends (is null)
       if (player.getTurn() == null) {
           powerup = null;
       }

       // While the powerup is on the screen, update its location
        if (powerup != null) {
            powerup.update();
        }

        // Check if any ball hits the powerup, if so cause fireball and destroy the powerup
        for (int i = 0; i < 3; i++) {
            if (ballArray[i] != null && powerup != null && ballArray[i].intersects(powerup)){
                ballArray[i] = new FireBall(ballArray[i].getRect().centre(), ballArray[i].getVelocity().normalised());
                ballArray[i].update();
                powerup = null;
            }
        }

        // Marvel pun intended
        player.endGame();

    }
}