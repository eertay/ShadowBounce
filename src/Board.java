import bagel.util.Point;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;

public class Board {

    private static final int MAX_PEGS = 165;
    private Peg[] allPegs = new Peg[MAX_PEGS];
    private int redPegNum;
    private int numBluePegs = 0;

    /** Empty constructor that represents a board to be read in the game which represents different levels
     *
     */
    public Board() { };

    /** This method returns the number of red pegs on the board
     *
     * @return number of red pegs of the board
     */
    public int getRedPegNum() { return redPegNum; }

    /** This method updates the number of red pegs on the board
     *
     * @param num the new number of red pegs
     */
    public void setRedPegNum(int num) { redPegNum = num; }

    /** This method reads the given csv file which is the next board to be loaded and allocates red pegs
     *
     * @param filename file to be read
     * @return array of pegs
     */
    public Peg[] readBoard(String filename) {

        //Reading the csv file to construct an array of pegs
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String file;
            int i = 0;
            while ((file = br.readLine()) != null) {
                String[] columns = file.split(",");
                String newPeg = columns[0];

                // Extract the coordinates of each peg
                Point cord = new Point(Integer.parseInt(columns[1]), Integer.parseInt(columns[2]));
                allPegs[i] = new Peg(cord, "res/peg.png","normal");
                String[] pegDetails = extractInfo(newPeg, allPegs[i]);

                // Create new blue peg and set its colour
                if (pegDetails[0].equals("blue")) {
                    allPegs[i] = new BluePeg(cord, pegDetails[1]);
                    allPegs[i].setColour("blue");
                    numBluePegs++;
                }
                // Create new grey peg and set its colour
                else if (pegDetails[0].equals("grey")) {
                    allPegs[i] = new GreyPeg(cord, pegDetails[1]);
                    allPegs[i].setColour("grey");

                }

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Randomly choosing the red pegs from the peg array
        Random randomGenerator = new Random();

        // Getting the right number of red pegs
        redPegNum = numBluePegs / 5;
        int redPegLength = redPegNum;
        int i = 0;

        // Randomly choosing pegs to be red among all blue pegs
        while (i < redPegLength){
            int rand = randomGenerator.nextInt(MAX_PEGS);
            if (allPegs[rand] != null && allPegs[rand] instanceof BluePeg) {
                Point coordinates = allPegs[rand].getCoordinates();
                allPegs[rand] = new RedPeg(coordinates, allPegs[rand].getShape());
                allPegs[rand].setColour("red");

                i++;
            }
        }

        return allPegs;
    }

    /** This method is for getting the shape and colour of the peg after reading the csv document
     *
     * @param pegString String of pegs
     * @param peg peg
     * @return peg's details
     */
    public String[] extractInfo(String pegString, Peg peg) {

        String[] info = new String[2];

        // Extracting the colour component of the peg
        if (pegString.contains("blue")){
            peg.setColour("blue");
        } else if (pegString.contains("grey")) {
            peg.setColour("grey");
        } else if (pegString.contains("green")) {
            peg.setColour("green");
        }

        // Extracting the shape component of the peg
        if (pegString.contains("vertical")) {
            peg.setShape("vertical");
        } else if (pegString.contains("horizontal")) {
            peg.setShape("horizontal");
        } else {
            peg.setShape("normal");
        }

        // Creating array with shape and colour to pass onto readBoard()
        info[0] = peg.getColour();
        info[1] = peg.getShape();
        return info;

    }

    /** This method generates a random peg
     *
     * @param allPegs the array of pegs where the random peg is chosen from
     * @return the randomly chosen peg
     */
    public Peg randPeg(Peg[] allPegs) {

        Random randomGenerator = new Random();
        int rand = randomGenerator.nextInt(allPegs.length);

        if (allPegs[rand] != null && allPegs[rand] instanceof BluePeg) {
            return allPegs[rand];
        }
        else {
            randPeg(allPegs);
        }
        return allPegs[rand];
    }
}