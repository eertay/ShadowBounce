import bagel.Window;
import bagel.util.Vector2;
import bagel.util.Point;

public class Bucket extends Figure implements Movable{

    private int speed = 4;
    private Vector2 velocity = new Vector2(speed, 0);

    /** This method constructs the bucket
     *
     * @param p coordinates of the bucket on the screen
     */
    public Bucket(Point p) {

        super(p, "res/bucket.png");
    }

    /** This method completes the functionality of the bucket, when the bucket catches a ball the player's number of
     * shots increase by one
     * @param ballArray array of balls to be caught
     * @param player player who has a limited number of shots
     */
    public void catchBall(Ball[] ballArray, Player player) {
        for (int i = 0; i < 3; i++) {
            if (ballArray[i] != null && this.intersects(ballArray[i])) {
                player.setNumShots(player.getNumShots() + 1);
            }
        }
    }

    /** This method moves the bucket
     *
     */
    @Override
    public void move() {  }

    /** This method updates the bucket's velocity and renders it accordingly
     *
     */
    @Override
    public void update(){
        super.move(velocity);

        if (super.getRect().left() < 0 || super.getRect().right() > Window.getWidth()) {
            //Changing the direction of the x component of the velocity vector
            this.velocity = new Vector2(this.velocity.x * (-1), this.velocity.y);
        }
        super.draw();
    }

}
