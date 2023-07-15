import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The EventRouter annotation is used to mark methods in an event handler class that handle specific event types.
 * The annotated methods will be dynamically invoked based on the event type.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventRouter {
    /**
     * Specifies the event type that the annotated method handles.
     *
     * @return the event type as a string
     */
    String eventType();
}
