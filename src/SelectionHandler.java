
public class SelectionHandler extends EventHandler<SelectionPage>{
    private static SelectionHandler instance = new SelectionHandler();

    private SelectionHandler() {
    }
    /**
     * Returns the singleton instance of the SelectionHandler.
     *
     * @return the SelectionHandler instance
     */
    public static SelectionHandler getInstance() {
        instance.setActive(true);
        return instance;
    }
    /**
     * Handles the start action when the ENTER key is pressed.
     * It stops the current music, sets the new media component for the page, sets the selected background
     * and cursor images for the LevelPage, and plays the new media component. Finally, it navigates to Level1.
     *
     * @param action the action to handle
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "ENTER")
    public void start(Action action) {
        this.setActive(false);
        DuckHunt.page.getMediaComponent().stopMusic();
        DuckHunt.page.setMediaComponent(new MediaComponent("assets/effects/Intro.mp3"));

        String selectedBgPath = action.page.getComponent(SelectionPage.BgImageComponent.class).get().getImagePath();
        String selectedCursorPath = action.page.getComponent(SelectionPage.CursorComponent.class).get().getImagePath();

        LevelPage.bgImagePath = selectedBgPath;
        LevelPage.cursorImagePath = selectedCursorPath;

        DuckHunt.page.getMediaComponent().getNode().getMediaPlayer().setOnEndOfMedia(()->{
            DuckHunt.page = new Level1();
            DuckHunt.stage.setScene(DuckHunt.page.getScene());
        });
        DuckHunt.page.getMediaComponent().play();
    }
    /**
     * Handles the exit action when the ESCAPE key is pressed.
     * It navigates back to the main menu page and resets the selection page.
     *
     * @param action the action to handle
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "ESCAPE")
    public void exit(Action<SelectionPage> action){
        DuckHunt.page = MainMenuPage.getInstance();
        DuckHunt.stage.setScene(DuckHunt.page.getScene());
    }

    /**
     * Handles the action when the RIGHT arrow key is pressed.
     * It updates the background image component to the next image.
     *
     * @param action the action to handle
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "RIGHT")
    public void nextBg(Action action){
        SelectionPage.BgImageComponent bgComp = action.page.getComponent(SelectionPage.BgImageComponent.class).get();
        Integer num = Integer.parseInt(bgComp.getImageName());
        String newPath = String.format("assets/background/%d.png",num%6+1);
        bgComp.updateImage(newPath);
    }
    /**
     * Handles the action when the LEFT arrow key is pressed.
     * It updates the background image component to the previous image.
     *
     * @param action the action to handle
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "LEFT")
    public void beforeBg(Action action){
        SelectionPage.BgImageComponent bgComp = action.page.getComponent(SelectionPage.BgImageComponent.class).get();
        Integer num = Integer.parseInt(bgComp.getImageName());
        if(num == 1){
            num = 7;
        }
        String newPath = String.format("assets/background/%d.png", num-1);
        bgComp.updateImage(newPath);
    }
    /**
     * Handles the action when the UP arrow key is pressed.
     * It updates the cursor image component to the next image.
     *
     * @param action the action to handle
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "UP")
    public void nextCursor(Action action){
        SelectionPage.CursorComponent cursorComp = action.page.getComponent(SelectionPage.CursorComponent.class).get();
        Integer num = Integer.parseInt(cursorComp.getImageName());
        String newPath = String.format("assets/crosshair/%d.png", num%7+1);
        cursorComp.updateImage(newPath);
    }
    /**
     * Handles the action when the DOWN arrow key is pressed.
     * It updates the cursor image component to the previous image.
     *
     * @param action the action to handle
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "DOWN")
    public void beforeCursor(Action action){
        SelectionPage.CursorComponent cursorComp = action.page.getComponent(SelectionPage.CursorComponent.class).get();
        Integer num = Integer.parseInt(cursorComp.getImageName());
        if(num == 1){
            num = 8;
        }
        String newPath = String.format("assets/crosshair/%d.png", num-1);
        cursorComp.updateImage(newPath);
    }
}
