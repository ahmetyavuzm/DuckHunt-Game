import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The DuckHunt class represents the main entry point for the Duck Hunt game application.
 */
public class DuckHunt extends Application {
    /**
     * The title of the game.
     */
    static String title = "HUBBM Duck Hunt";
    /**
     * The primary stage of the application.
     */
    static Stage stage;
    /**
     * The current page being displayed in the application.
     */
    static Page page;
    /**
     * The icon of the application.
     */
    static Image icon = new Image("file:assets/favicon/1.png");
    /**
     * The scale factor applied to the game components.
     */
    static double scale = 3;
    /**
     * The volume level for sound effects.
     */
    static double volume = 0.025;
    /**
     * The main method that launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        page = MainMenuPage.getInstance();
        stage.setScene(page.getScene());
        stage.setResizable(false);
        stage.setTitle(title);
        stage.getIcons().add(icon);
        stage.show();
    }
}