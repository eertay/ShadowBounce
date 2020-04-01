import bagel.Window;
import bagel.util.Point;
import bagel.util.Side;
import bagel.util.Vector2;

public class Ball extends Figure implements Movable {
    private Vector2 velocity;
    private static final double GRAVITY = 0.15;
    private static final double SPEED = 10;
    private Vector2 zero = new Vector2();

    public Vector2 getVelocity() {
        return this.velocity;
    }

    // Following methods have been taken from Project 1 sample solution
    /** Constructs Ball, which is the superclass of all balls, where the purpose of the balls is to destroy pegs
     *
     * @param point start coordinates of the ball
     * @param direction vector to get the velocity
     */
    public Ball(Point point, Vector2 direction) {
        super(point, "res/ball.png");
        velocity = direction.mul(SPEED);
    }

    /** This method gives information about the ball's position
     *
     * @return returns whether the ball is out of the screen or not
     */
    public boolean outOfScreen() {
        return super.getRect().top() > Window.getHeight();
    }

    /** This method moves the ball
     *
     */
    @Override
    public void move() { }

    /** This method updates ball's velocity and renders it
     *
     */
    @Override
    public void update() {
        velocity = velocity.add(Vector2.down.mul(GRAVITY));
        super.move(velocity);

        if (super.getRect().left() < 0 || super.getRect().right() > Window.getWidth()) {
            velocity = new Vector2(-velocity.x, velocity.y);
        }
        super.draw();
    }

    /** This method keeps track of the change in velocity after the ball hits a peg
     *
     * @param figure the other object that the ball is bouncing off
     */
    public void pegBounce(Figure figure) {

        // If the peg is hit on the top or bottom, change the y component of the velocity
        if (this.intersectedAt(figure.getCoordinates(), zero).equals(Side.TOP) ||
                this.intersectedAt(figure.getCoordinates(),zero).equals(Side.BOTTOM)) {
            velocity = new Vector2(velocity.x, -velocity.y);
        }

        // If the peg is hit on the left or right, change the y component of the velocity
        if (this.intersectedAt(figure.getCoordinates(),zero).equals(Side.LEFT) ||
                this.intersectedAt(figure.getCoordinates(),zero).equals(Side.RIGHT)) {
            velocity = new Vector2(-velocity.x, velocity.y);
        }
        this.update();
    }
}