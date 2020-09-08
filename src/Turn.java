import java.util.Random;

public class Turn {
    private boolean prob;
    private Powerup powerup;

    /** Constructs Turn, which represents at least one ball being on the screen
     *
     */
    public Turn() {
        Random randomGenerator = new Random();
        this.prob = (randomGenerator.nextInt(10) == 0);
    }

    /** This method gives information on the probability of powerup appearing
     *
     * @return the probability of powerup appearing
     */
    public boolean getProb() { return this.prob; }

    /** This setter changes the powerup
     *
     * @param pU the new powerup to be adapted
     */
    public void setPowerup(Powerup pU) { this.powerup = pU; }

    /** This getter returns the powerup
     *
     * @return powerup of the associated turn
     */
    public Powerup getPowerup() { return this.powerup; }
   
}
