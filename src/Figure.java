import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Side;
import bagel.util.Vector2;

public abstract class Figure {

    private Point coordinates;
    private Image image;
    private Rectangle rect;

    /** This method constructs the superclass of all objects in the game, with coordinates, an image and a rectangle
     *
     * @param p coordinates of the object
     * @param newImg image string of the object
     */
    public Figure(Point p, String newImg) {
        coordinates = new Point(p.x, p.y);
        image = new Image(newImg);
        rect = image.getBoundingBoxAt(p);
    }


    /** This getter returns coordinates of the object
     *
     * @return coordinates
     */
    public Point getCoordinates() { return coordinates; }

    /** This setter changes the coordinates of an object
     *
     * @param p new coordinates
     */
    public void setCoordinates(Point p) { this.coordinates = p; }

    /** This setter changes the image of an object
     *
     * @param img new image
     */
    public void setImage(String img) {
        this.image = new Image(img);
    }

    // The following methods has been taken from Project 1 sample solution

    /** This method returns the rectangle of an object
     *
     * @return rectangle attribute
     */
    public Rectangle getRect() {
        return rect;
    }

    /** This method changes the rectangle attribute of the object
     *
     * @param rectangle new rectangle
     */
    public void setRect(Rectangle rectangle) { rect = rectangle; }

    /** This method renders the object
     *
     */
    public void draw() { image.draw(rect.centre().x, rect.centre().y); }

    /** This method detects collision between objects
     *
     * @param other second object
     * @return true or false depending on whether objects intersected or not
     */
    public boolean intersects(Figure other) { return rect.intersects(other.rect); }

    /** This method returns the side of the rectangle where another rectangle is intersected at
     *
     * @param point other object's coordinates
     * @param velocity other object's velocity
     * @return side where the intersection happens
     */
    public Side intersectedAt(Point point, Vector2 velocity) { return rect.intersectedAt(point, velocity); }

    /** This method moves the rectangle of the given object
     *
     * @param dx difference in x coordinates
     */
    public void move(Vector2 dx) {
        rect.moveTo(rect.topLeft().asVector().add(dx).asPoint());
    }

    /** This is the abstract update method
     *
     */
    public abstract void update();

}
