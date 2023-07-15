import javafx.scene.Node;

/**
 * The Component class represents a graphical component in a JavaFX application.
 *
 * @param <T> the type of node associated with the component
 */
public abstract class Component<T extends Node>{
    private final T node;
    private MediaComponent media;

    /**
     * Constructs a new Component object with the specified node.
     *
     * @param node the node associated with the component
     */
    public Component(T node) {
        this.node = node;
    }

    /**
     * Returns the node associated with the component.
     *
     * @return the node associated with the component
     */
    public T getNode() {
        return node;
    }

    /**
     * Returns the media component associated with the component.
     *
     * @return the media component associated with the component
     */
    public MediaComponent getMedia() {
        return media;
    }

    /**
     * Sets the media component associated with the component.
     *
     * @param media the media component to be associated with the component
     */
    public void setMedia(MediaComponent media) {
        this.media = media;
    }
}
