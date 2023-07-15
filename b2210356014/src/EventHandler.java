import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The EventHandler class provides a base implementation for handling events in the game.
 *
 * @param <T> the type of page associated with the event handler
 */
public abstract class EventHandler<T extends Page> {

    private boolean active = true;
    /**
     * Handles the specified action.
     *
     * @param action the action to handle
     * @return the result of the action handling
     */
    public Object handle(Action action){
        if (!active){
            return null;
        }
        try {
            return actionHandler(action);
        } catch (Exception e) {
            return null;
        }
    }
    private Optional<?> actionHandler(Action action) throws Exception {
        Method method = getMethod(action.event).orElseThrow(()->
                new Exception("method hatası")
        );
        return invoke(method,this,action);
    }
    private Optional<Method> getMethod(Event event){
        List<Method> methods = Arrays.stream(this.getClass().getDeclaredMethods()).collect(Collectors.toList());
        for(Method method : methods){
            if(checkForKeyEvent(event,method) || checkForMouseEvent(event,method)){
                return Optional.of(method);
            }
        }
        return Optional.empty();
    }
    private Optional<?> invoke(Method method, Object obj, Object... params) throws Exception {
        try {
            return Optional.ofNullable(method.invoke(obj,params));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new Exception(e.getCause());
        }
    }

    private boolean checkForKeyEvent(Event event ,Method method){
        if(method.isAnnotationPresent(KeyEventRouter.class) & event.getClass().equals(KeyEvent.class)){
            KeyEventRouter router = method.getDeclaredAnnotation(KeyEventRouter.class);
            KeyEvent keyEvent = (KeyEvent) event;
            return router.eventType().equals(keyEvent.getEventType().toString()) &
                    router.keyValue().equals(keyEvent.getCode().toString());
        }
        return false;
    }

    private boolean checkForMouseEvent(Event event, Method method){
        if(method.isAnnotationPresent(EventRouter.class) & event.getClass().equals(MouseEvent.class)){
            EventRouter router = method.getDeclaredAnnotation(EventRouter.class);
            MouseEvent MouseEvent = (MouseEvent) event;
            return  router.eventType().equals(MouseEvent.getEventType().toString());
        }
        return false;
    }

    /**
     * Returns whether the event handler is active.
     *
     * @return true if the event handler is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the activity status of the event handler.
     *
     * @param active the activity status to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
