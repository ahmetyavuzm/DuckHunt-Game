import javafx.animation.Animation;
import java.util.ArrayList;

/**
 * The Animations class represents a collection of animations associated with a component in a JavaFX application.
 *
 * @param <T> the type of component associated with the animations
 */
public class Animations<T extends Component<?>> {
    private T component;

    /**
     * The list of animations that have been played.
     */
    public ArrayList<Animation> playedAnimations = new ArrayList<>();

    /**
     * Constructs a new Animations object with the specified component.
     *
     * @param component the component associated with the animations
     */
    public Animations(T component) {
        this.component = component;
    }

    /**
     * Returns the component associated with the animations.
     *
     * @return the component associated with the animations
     */
    public T getComponent() {
        return component;
    }
    /**
     * Plays the specified animation and adds it to the list of played animations.
     *
     * @param animation the animation to be played
     */
    public void play(Animation animation) {
        animation.play();
        playedAnimations.add(animation);
    }
    /**
     * Stops the specified animation and removes it from the list of played animations.
     *
     * @param animation the animation to be stopped
     */
    public void stop(Animation animation) {
        animation.stop();
        playedAnimations.remove(animation);
    }
    /**
     * Stops all currently playing animations and clears the list of played animations.
     */
    public void stopAll(){
        playedAnimations.forEach(Animation::stop);
        playedAnimations.clear();
    }
}
