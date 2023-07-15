import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ImageComponent class represents a graphical component that displays an image in a JavaFX application.
 */
public class ImageComponent extends Component<ImageView>{
    private String imagePath;

    private double scale;
    /**
     * Constructs a new ImageComponent object with a default ImageView node.
     */
    public ImageComponent() {
        super(new ImageView());
    }

    /**
     * Constructs a new ImageComponent object with the specified image path, width, and height.
     *
     * @param imagePath the path to the image
     * @param width     the width of the image component
     * @param height    the height of the image component
     */
    public ImageComponent(String imagePath, int width, int height) {
        super(new ImageView());
        this.imagePath = imagePath;
        createNode();
        setWidth(width);
        setHeight(height);
    }

    /**
     * Constructs a new ImageComponent object with the specified image path and scale.
     *
     * @param imagePath the path to the image
     * @param scale     the scale of the image component
     */
    public ImageComponent(String imagePath, double scale) {
        super(new ImageView());
        this.imagePath = imagePath;
        this.scale = scale;
        createNode();
        setScale(scale);
    }

    /**
     * Creates the ImageView node and sets the image.
     */
    protected void createNode() {
        Image image = findImage(imagePath);
        getNode().setImage(image);
    }

    /**
     * Sets the width of the image component.
     *
     * @param width the width of the image component
     */
    public void setWidth(double width) {
        getNode().setFitWidth(width);
    }
    /**
     * Sets the height of the image component.
     *
     * @param height the height of the image component
     */
    public void setHeight(double height) {
        getNode().setFitHeight(height);
    }
    /**
     * Sets the scale of the image component.
     *
     * @param scale the scale of the image component
     */
    public void setScale(double scale){
        setWidth(getImage().getWidth()*scale);
        setHeight(getImage().getHeight()*scale);
    }
    /**
     * Returns the image associated with the image component.
     *
     * @return the image associated with the image component
     */
    public Image getImage(){
        return this.getNode().getImage();
    }
    /**
     * Returns the path of the image associated with the image component.
     *
     * @return the path of the image associated with the image component
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Returns the name of the image associated with the image component.
     *
     * @return the name of the image associated with the image component
     */
    public String getImageName(){
        String[] arr = imagePath.split("/");
        return arr[arr.length-1].split("\\.")[0];
    }
    /**
     * Updates the image of the image component with the image at the specified path.
     *
     * @param path the new path to the image
     */
    public void updateImage(String path){
        getNode().setImage(findImage(path));
        setScale(this.scale);
        this.imagePath = path;
    }

    /**
     * Finds the image in your computer and returns the image
     * @param path the path of the image
     * @return Image that located in specified path.
     */
    private Image findImage(String path){
        return new Image("file:"+path);
    }
}
