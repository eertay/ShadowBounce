import bagel.util.Point;

public class RedPeg extends Peg {

    /** This method constructs a red peg
     *
     * @param p coordinates of the red peg
     * @param shape shape of the red peg
     */
    public RedPeg(Point p, String shape) {
        super(p, "res/peg.png", shape);

        if (shape.equals("horizontal")) {
            this.setImage("res/red-horizontal-peg.png");
        }
        else if (shape.equals("vertical")) {
            this.setImage("res/red-vertical-peg.png");
        }
        else {
            this.setImage("res/red-peg.png");
        }
    }
}
