/**
 * The DuckComponent class represents a graphical component that displays a duck image in a JavaFX application.
 */
public class DuckComponent extends ImageComponent{

    /**
     * The Duck enumeration represents different types of ducks.
     */
    enum Duck{
        BLACK("assets/duck_black"),
        RED("assets/duck_red"),
        BLUE("assets/duck_blue");

        private String path;
        /**
         * Constructs a Duck with the specified image path.
         *
         * @param path the path to the duck image
         */
        Duck(String path) {
            this.path = path;
        }
        /**
         * Returns the image path of the duck.
         *
         * @return the image path of the duck
         */
        public String getPath() {
            return path;
        }
    }
    public DuckAnimations animations;
    public String folderPath;
    public boolean alive = true;


    /**
     * Constructs a new DuckComponent object with the specified folder path, width, and height.
     *
     * @param folderPath the path to the folder containing the duck images
     * @param width      the width of the duck component
     * @param height     the height of the duck component
     */
    public DuckComponent(String folderPath, int width, int height) {
        super(null, width, height);
        animations = new DuckAnimations(this);
        this.folderPath = folderPath;
        setMedia(new MediaComponent("assets/effects/DuckFalls.mp3"));
    }

    /**
     * Constructs a new DuckComponent object with the specified folder path and scale.
     *
     * @param folderPath the path to the folder containing the duck images
     * @param scale      the scale of the duck component
     */
    public DuckComponent(String folderPath, double scale) {
        super(null, scale);
        animations = new DuckAnimations(this);
        this.folderPath = folderPath;
        setMedia(new MediaComponent("assets/effects/DuckFalls.mp3"));
    }
    /**
     * Returns whether the duck is alive.
     *
     * @return true if the duck is alive, false otherwise
     */
    public boolean isAlive() {
        return alive;
    }
}
