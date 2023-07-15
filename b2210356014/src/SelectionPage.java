import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * The SelectionPage class represents the selection page of the game.
 * It extends the Page class and provides methods to create the scene and handle events on the selection page.
 */
public class SelectionPage extends Page{
    private static final String defaultBg = "assets/background/1.png" ;

    private static final String defaultCrosshair = "assets/crosshair/1.png";
    private static  SelectionPage instance = new SelectionPage();
    private SelectionPage() {
        createScene();
        setEventHandler(SelectionHandler.getInstance());
    }

    /**
     * Returns the singleton instance of the SelectionPage.
     *
     * @return the SelectionPage instance
     */
    public static SelectionPage getInstance() {
        instance.getEventHandler().setActive(true);
        SelectionPage.reset();
        return instance;
    }

    /**
     * Resets the selection page to its default state.
     * It restores the default background image and crosshair image.
     */
    public static void reset(){
        SelectionPage.instance.getComponent(BgImageComponent.class).get().updateImage(defaultBg);
        SelectionPage.instance.getComponent(CursorComponent.class).get().updateImage(defaultCrosshair);
    }

    /**
     * Creates the scene for the selection page.
     * It sets up the label, background image, and cursor components.
     */
    protected void createScene() {
        LabelComponent labelComponent = new LabelComponent();
        labelComponent.setText("USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT");
        BgImageComponent bgImageComponent = new BgImageComponent();
        CursorComponent cursorComponent = new CursorComponent();

        this.components.add(labelComponent);
        this.components.add(bgImageComponent);
        this.components.add(cursorComponent);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(
                bgImageComponent.getNode(),
                labelComponent.getNode(),
                cursorComponent.getNode()
        );

        labelComponent.getNode().setTranslateY(bgImageComponent.getNode().getFitHeight()/2 *-1 +20*DuckHunt.scale);

        Scene scene = new Scene(
                stackPane,
                bgImageComponent.getNode().getFitWidth(),
                bgImageComponent.getNode().getFitHeight()
        );
        scene.addEventHandler(KeyEvent.ANY, e-> {
            this.getEventHandler().handle(new Action(e,this));
        });

        setScene(scene);
    }

    /**
     * The BgImageComponent class represents the background image component on the selection page.
     * It extends the ImageComponent class.
     */
    protected class BgImageComponent extends ImageComponent{
        public BgImageComponent() {
            super(defaultBg, DuckHunt.scale);
        }
    }
    /**
     * The CursorComponent class represents the cursor image component on the selection page.
     * It extends the ImageComponent class.
     */
    protected class CursorComponent extends ImageComponent{

        public CursorComponent() {
            super(defaultCrosshair, DuckHunt.scale);
        }
    }
}
