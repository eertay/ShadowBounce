import bagel.util.Point;
import bagel.util.Rectangle;

public class Peg extends Figure{

    private String colour;
    private String shape;
    private static final int DESTROY_RANGE = 70;
    private double width = getRect().right() - getRect().left();
    private double height = getRect().bottom() - getRect().top();

    /** This method constructs the peg object
     *
     * @param p coordinates of the peg
     * @param s image string
     * @param shape shape of the peg
     */
    public Peg(Point p, String s, String shape){
        super(p, "res/peg.png");
        this.shape = shape;
    }

    /** This method extends the peg's rectangle to destroy surrounding pegs if the peg has been hit by a fireball
     *
     */
    public void getBiggerRect() { this.setRect(new Rectangle(getRect().centre().x, getRect().centre().y,
            width + DESTROY_RANGE, height + DESTROY_RANGE)); }

    /** This method detects the pegs around the peg that has been hit by a fireball
     *
     */
    public Peg[] destroyPegsAround(Peg[] allPegs, Board board) {

        for (int i = 0; i < allPegs.length; i++) {
            if (allPegs[i] != null) {
                if (this.intersects(allPegs[i]) && !(allPegs[i] instanceof GreyPeg)) {
                    if (allPegs[i] instanceof RedPeg) {
                        board.setRedPegNum(board.getRedPegNum() - 1);
                    }
                    allPegs[i] = null;
                }
            }
        }
        return allPegs;
    }

    /** This method renders pegs
     *
     */
    @Override
    public void update() {
        super.draw();
    }

    /** This getter returns the colour of the peg
     *
     * @return peg's colour
     */
    public String getColour() { return colour; }

    /** This getter returns the shape of the peg
     *
     * @return peg's shape
     */
    public String getShape() { return shape; }

    /** This setter sets the shape of the peg
     *
     * @param shape shape to be set
     */
    public void setShape(String shape) { this.shape = shape; }

    /** This setter sets the colour of the peg
     *
     * @param colour colour to be set
     */
    public void setColour(String colour) { this.colour = colour; }

}
