import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The KeyEventRouter annotation is used to mark methods in an event handler class that handle specific key events.
 * The annotated methods will be dynamically invoked based on the key event type and key value.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyEventRouter {
    /**
     * Specifies the key event type that the annotated method handles.
     *
     * @return the key event type as a string
     */
    String eventType();
    /**
     * Specifies the key value that the annotated method handles.
     *
     * @return the key value as a string
     */
    String keyValue();
}
