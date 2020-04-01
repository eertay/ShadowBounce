import bagel.Window;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.Random;

public class Powerup extends Figure {

    Random randomGenerator = new Random();
    private Vector2 velocity;
    private static final double SPEED = 3;
    private static final int COLLISION = 50;
    private Point nextPoint;

    /** Constructs powerup which activates the fireball
     *
     * @param p start coordinates of the powerup
     */
    public Powerup(Point p) {
        super(p, "res/powerup.png");
        this.nextPoint = new Point(randomGenerator.nextInt(Window.getWidth()), randomGenerator.nextInt(Window.getHeight()));
    }

    /** This method updates the powerup's velocity and renders it
     *
     */
    @Override
    public void update() { //super.draw(); }

        velocity = new Vector2(nextPoint.x - this.getRect().centre().x, nextPoint.y - this.getRect().centre().y).normalised().mul(SPEED);
        super.move(velocity);

        if ((Math.abs(nextPoint.x - this.getRect().centre().x) < COLLISION) &&  Math.abs(nextPoint.y - this.getRect().centre().y) < COLLISION) {
            this.nextPoint = new Point(randomGenerator.nextInt(Window.getWidth()), randomGenerator.nextInt(Window.getHeight()) + 100);
        }
        super.draw();
    }

}
