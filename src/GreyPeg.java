import bagel.util.Point;

public class GreyPeg extends Peg {

    /** This method constucts a grey peg
     *
     * @param p coordinates of the grey peg
     * @param shape shape of the grey peg
     */
    public GreyPeg(Point p, String shape) { // string shape
        super(p, "res/peg.png", shape);

        if (shape.equals("vertical")) {
            this.setImage("res/grey-vertical-peg.png");
        }
        else {
            this.setImage("res/grey-peg.png");
        }
    }
}
