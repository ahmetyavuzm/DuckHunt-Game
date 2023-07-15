import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * The LabelAnimations class provides animation functionality for labels.
 */
public class LabelAnimations extends Animations<Component<Label>> {
    /**
     * Constructs a new LabelAnimations object with the specified component.
     *
     * @param component the label component to animate
     */
    public LabelAnimations(Component<Label> component) {
        super(component);
    }

    /**
     * Creates a fade transition animation for the label.
     *
     * @param duration  the duration of the animation in milliseconds
     * @param fromValue the starting opacity value
     * @param toValue   the ending opacity value
     * @return the created FadeTransition object
     */
    public FadeTransition fade(double duration, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), getComponent().getNode());
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        return fadeTransition;
    }
}
