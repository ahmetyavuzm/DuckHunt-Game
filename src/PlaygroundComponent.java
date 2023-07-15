import javafx.scene.layout.HBox;
import java.util.ArrayList;

/**
 * Component representing the playground in the level page.
 */
public class PlaygroundComponent extends Component<HBox>{
    public ArrayList<ImageComponent> components = new ArrayList<>();
    /**
     * Constructs a new instance of PlaygroundComponent.
     */
    public PlaygroundComponent(int repeatNum) {
        super(new HBox());
        createNode(repeatNum);
    }

    /**
     * Creates the nodes for the playground component.
     */
    public void createNode(int repeatNum){
        ImageComponent[] images = new ImageComponent[repeatNum];
        for (int i = 0; i<repeatNum; i++){
            images[i] = new ImageComponent();
            components.add(images[i]);
            getNode().getChildren().add(images[i].getNode());
        }
    }

    /**
     * Updates the image for the playground component.
     *
     * @param imagePath the path of the image to update
     */
    public void updateImage(String imagePath){
        components.forEach(c->c.updateImage(imagePath));
    }

    /**
     * Sets the scale of the playground component.
     *
     * @param scale the scale to set
     */
    public void setScale(double scale){
        components.forEach(c->c.setScale(scale));
    }

    /**
     * Returns the image name of the playground component.
     *
     * @return the image name
     */
    public String getImageName(){
        return components.get(0).getImageName();
    }

    /**
     * Returns the width of the fitted image in the playground component.
     *
     * @return the width of the fitted image
     */
    public double getFitImageWidth(){
        return components.get(0).getNode().getFitWidth();
    }
    /**
     * Returns the height of the fitted image in the playground component.
     *
     * @return the height of the fitted image
     */
    public double getFitImageHeight(){
        return components.get(0).getNode().getFitHeight();
    }
}
