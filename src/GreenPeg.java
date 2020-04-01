import bagel.util.Point;
import bagel.util.Vector2;

import java.util.Random;

public class GreenPeg extends Peg implements Changeable{

    /** This method constructs a green peg
     *
     * @param p coordinates of the green peg
     * @param shape shape of the green peg
     */
    public GreenPeg(Point p, String shape) { // string shape
        super(p, "res/peg.png", shape);

        if (shape.equals("vertical")) {
            this.setImage("res/green-vertical-peg.png");
        }
        else if (shape.equals("horizontal")) {
            this.setImage("res/green-horizontal-peg.png");
        }
        else {
            this.setImage("res/green-peg.png");
        }
    }

    @Override
    public Ball[] change(Ball[] ballArray, Peg[] allPegs, int index) {
        ballArray[1] = new Ball(allPegs[index].getCoordinates(), Vector2.up.add(Vector2.left));
        ballArray[2] = new Ball(allPegs[index].getCoordinates(), Vector2.up.add(Vector2.right));
        ballArray[0].update();

        return ballArray;
    }
}
