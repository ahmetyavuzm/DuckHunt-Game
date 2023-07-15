import javafx.event.Event;

/**
 * The Action class represents an action associated with an event and a page in a JavaFX application.
 *
 * @param <T> the type of page associated with the action
 */
public class Action<T extends Page> {
    Event event;
    T page;

    /**
     * Constructs a new Action object with the specified event and page.
     *
     * @param event the event associated with the action
     * @param page the page associated with the action
     */
    public Action(Event event, T page) {
        this.event = event;
        this.page = page;
    }
}
