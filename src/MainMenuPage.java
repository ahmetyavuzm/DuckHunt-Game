import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * The MainMenuPage class represents the main menu page of the game.
 * It extends the Page class and provides the functionality to create and manage the main menu scene.
 */
public class MainMenuPage extends Page {

    private static MainMenuPage instance = new MainMenuPage();

    private MainMenuPage() {
        createScene();
        setEventHandler(MainMenuHandler.getInstance());
        getMediaComponent().playForever();
    }

    /**
     * Returns the singleton instance of the MainMenuPage.
     *
     * @return the MainMenuPage instance
     */
    public static MainMenuPage getInstance() {
        instance.getEventHandler().setActive(true);
        return instance;
    }

    /**
     * Creates the main menu scene with its components and sets it as the current scene of the page.
     */
    protected void createScene() {
        LabelComponent labelComponent = new LabelComponent();
        labelComponent.setText("Press ENTER to play\nPress ESC to exit");
        labelComponent.animations.play(labelComponent.animations.fade(1000,1,0));
        BgImageComponent bgImageComponent = new BgImageComponent();
        MediaComponent mediaComponent = new MediaComponent("assets/effects/Title.mp3");
        this.setMediaComponent(mediaComponent);

        this.components.add(labelComponent);
        this.components.add(bgImageComponent);
        this.components.add(mediaComponent);

        labelComponent.getNode().setTranslateY(bgImageComponent.getNode().getFitHeight()/5);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(
                bgImageComponent.getNode(),
                labelComponent.getNode()

        );
        Scene scene = new Scene(
                stackPane,
                bgImageComponent.getNode().getFitWidth(),
                bgImageComponent.getNode().getFitHeight()
        );
        scene.addEventHandler(KeyEvent.ANY, e-> {
            this.getEventHandler().handle(new Action(e,this));
        });
        this.setScene(scene);
    }

    /**
     * The BgImageComponent class represents the background image component in the main menu page.
     * It extends the ImageComponent class and provides additional functionality specific to the main menu page.
     */
    protected class BgImageComponent extends ImageComponent{
        public BgImageComponent() {
            super("assets/welcome/1.png", DuckHunt.scale);
        }
    }
}
