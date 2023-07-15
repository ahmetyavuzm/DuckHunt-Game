import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/**
 * The Page class represents a UI page in the application.
 */
public abstract class Page {
    private EventHandler<?> eventHandler;
    public ArrayList<Component<?>> components = new ArrayList<>();
    private MediaComponent mediaComponent;
    private Scene scene;

    /**
     * Creates the scene for the page.
     */
    protected abstract void createScene();

    /**
     * Retrieves the event handler associated with the page.
     *
     * @return the event handler for the page
     */
    public EventHandler<?> getEventHandler() {
        return eventHandler;
    }

    /**
     * Adds a component to the page.
     *
     * @param component the component to be added
     * @param <T>       the type of the component
     * @return the added component
     */
    public <T extends Component<?>> T addComponent(T component){
        components.add(component);
        Pane parent = (Pane) scene.getRoot();
        parent.getChildren().add(component.getNode());
        return component;
    }

    /**
     * Retrieves the media component associated with the page.
     *
     * @return the media component for the page
     */
    public MediaComponent getMediaComponent() {
        return mediaComponent;
    }

    /**
     * Retrieves the scene associated with the page.
     *
     * @return the scene for the page
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Sets the scene for the page.
     *
     * @param scene the scene to be set
     */
    protected void setScene(Scene scene) {
        this.scene = scene;
    }
    /**
     * Sets the media component for the page.
     *
     * @param mediaComponent the media component to be set
     */
    public void setMediaComponent(MediaComponent mediaComponent) {
        this.mediaComponent = mediaComponent;
    }
    /**
     * Sets the event handler for the page.
     *
     * @param eventHandler the event handler to be set
     */
    public void setEventHandler(EventHandler<?> eventHandler) {
        this.eventHandler = eventHandler;
    }
    /**
     * Retrieves a component of the specified class from the page.
     *
     * @param cls the class of the component to retrieve
     * @param <T> the type of the component
     * @return an optional containing the retrieved component, or empty if not found
     */
    public <T extends Component<?>> Optional<T> getComponent(Class<T> cls){
        return Optional.ofNullable((T) this.components.stream().filter(c-> c.getClass().equals(cls)).collect(Collectors.toList()).get(0));
    }

    /**
     * Retrieves a list of components of the specified class.
     *
     * @param cls the class of components to retrieve
     * @param <T> the type of components
     * @return an ArrayList containing the components of the specified class
     */
    public <T extends Component<?>> ArrayList<T> getComponentsAsList(Class<T> cls){
        List<T> list = (List<T>) this.components.stream().filter(c-> c.getClass().equals(cls)).collect(Collectors.toList());
        return new ArrayList<>(list);
    }

}