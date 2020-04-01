import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class FireBall extends  Ball {
    private static final int DESTROY_RANGE = 70;
    private double width = getRect().right() - getRect().left();
    private double height = getRect().bottom() - getRect().top();

    /** Constructs fireball which destroys more pegs than the normall ball
     *
     * @param point start coordinates of the fireball
     * @param direction direction vector to get the velocity of the fireball
     */
    public FireBall(Point point, Vector2 direction) {
        super(point, direction);
        this.setImage("res/fireball.png");
        //this.setRect(new Rectangle(getRect().centre().x, getRect().centre().y, width + DESTROY_RANGE, height + DESTROY_RANGE));
    }

}
