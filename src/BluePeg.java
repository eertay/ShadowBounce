import bagel.util.Point;

public class BluePeg extends Peg{

    /** This method constructs the blue peg object with the correct shape
     *
     * @param p coordinates of the blue peg to be constructed
     * @param shape shape of the peg
     */
    public BluePeg(Point p, String shape) {
        super(p, "res/peg.png", shape);

        if (shape.equals("vertical")) {
            this.setImage("res/vertical-peg.png");
        }
        else if (shape.equals("horizontal")) {
            this.setImage("res/horizontal-peg.png");
        }
        else {
            this.setImage("res/peg.png");
        }

    }

    // TAKEN FROM PROJECT 1 SAMPLE SOLUTION

    /** This method renders the peg
     *
     */
    @Override
    public void update() {
        super.draw();
    }
}
