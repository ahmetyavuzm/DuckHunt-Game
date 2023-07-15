import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * The LabelComponent class represents a label component.
 */
public class LabelComponent extends Component<Label> {
    LabelAnimations animations = new LabelAnimations(this);
    /**
     * Constructs a new LabelComponent with the specified text.
     *
     * @param text the text to be displayed in the label
     */
    public LabelComponent(String text) {
        super(new Label());
        createNode(text);
    }
    /**
     * Constructs a new LabelComponent with an empty text.
     */
    public LabelComponent() {
        super(new Label());
        createNode("");
    }
    /**
     * Creates the label node with the specified text.
     *
     * @param text the text to be displayed in the label
     */
    protected void createNode(String text) {
        getNode().setText(text);
        getNode().setFont(new Font("Ariel",10*DuckHunt.scale));
        getNode().setTextFill(Color.valueOf("#FF9300"));
        getNode().setTextAlignment(TextAlignment.CENTER);
    }
    /**
     * Sets the text of the label.
     *
     * @param text the text to be displayed in the label
     */
    public void setText(String text){
        getNode().setText(text);
    }
}
