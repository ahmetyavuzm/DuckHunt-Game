/**
 * The MainMenuHandler class handles the events and actions related to the main menu page.
 * It extends the EventHandler class and provides event handling methods for the main menu page.
 */
public class MainMenuHandler extends EventHandler<MainMenuPage>{

    private static MainMenuHandler instance = new MainMenuHandler();

    private MainMenuHandler() {
    }
    /**
     * Returns the singleton instance of the MainMenuHandler.
     *
     * @return the MainMenuHandler instance
     */
    public static MainMenuHandler getInstance() {
        instance.setActive(true);
        return instance;
    }
    /**
     * Event handler method for quitting the game.
     * It is triggered when the ESCAPE key is pressed.
     *
     * @param action the action that triggered the event
     */
    @KeyEventRouter(eventType = "KEY_PRESSED",keyValue = "ESCAPE")
    public void quit(Action action){
        System.exit(1);
    }
    /**
     * Event handler method for starting the game.
     * It is triggered when the ENTER key is pressed.
     *
     * @param action the action that triggered the event
     */
    @KeyEventRouter(eventType = "KEY_PRESSED",keyValue = "ENTER")
    public void start(Action action){
        DuckHunt.page = SelectionPage.getInstance();
        DuckHunt.stage.setScene(DuckHunt.page.getScene());
        DuckHunt.page.setMediaComponent(action.page.getMediaComponent());
    }
}
