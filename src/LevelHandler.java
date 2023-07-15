import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles the events and actions for the level page.
 */
public class LevelHandler extends EventHandler<LevelPage>{
    private static LevelHandler instance = new LevelHandler();

    private static ParallelTransition slideTransition = new ParallelTransition();

    private LevelHandler() {
    }
    /**
     * Returns the instance of the LevelHandler.
     *
     * @return the LevelHandler instance
     */
    public static LevelHandler getInstance() {
        instance.setActive(true);
        return instance;
    }
    /**
     * Handles the shot action when a mouse click event occurs.
     *
     * @param action the action containing the level page
     */
    @EventRouter(eventType = "MOUSE_CLICKED")
    public static void shot(Action<LevelPage> action){
        if (!action.page.finished){
            shotDuck(action);
            MediaComponent ammoMedia = action.page.getComponent(LevelPage.AmmoComponent.class).get().getMedia();
            ammoMedia.stopMusic();
            ammoMedia.play(true);
            action.page.updateAmmo(action.page.ammo -1);
            if(action.page.getNumberOfDuckLeft() == 0){
                if (action.page.nextLevelPage == null){
                    action.page.gameFinished();
                }else{
                    action.page.win();
                }
                action.page.finished = true;
                action.page.win = true;
            } else if (action.page.ammo == 0) {
                action.page.lose();
                action.page.win = false;
                action.page.nextLevelPage = action.page.getClass();
                action.page.finished = true;
            }
        }
    }

    /**
     * Handles the cursor exit event.
     *
     * @param action the action containing the level page
     */
    @EventRouter(eventType = "MOUSE_EXITED")
    public static void cursorExit(Action<LevelPage> action){
        action.page.getScene().setCursor(Cursor.DEFAULT);
    }
    /**
     * Handles the cursor entered event.
     *
     * @param action the action containing the level page
     */
    @EventRouter(eventType = "MOUSE_ENTERED")
    public static void cursorEntered(Action<LevelPage> action){
        action.page.setCursor();
    }

    /**
     * Handles the slide action when the mouse is moved.
     *
     * @param action the action containing the level page
     */
    @EventRouter(eventType = "MOUSE_MOVED")
    public static void slide(Action<LevelPage> action){
        MouseEvent mouseEvent = (MouseEvent) action.event;

        LevelPage.BgImageComponent bgComp = action.page.getComponent(LevelPage.BgImageComponent.class).get();
        LevelPage.FgImageComponent fgComp = action.page.getComponent(LevelPage.FgImageComponent.class).get();


            boolean rightSide = mouseEvent.getX() > action.page.getScene().getWidth()-20*DuckHunt.scale;
            boolean leftSide = mouseEvent.getX() < 20 *DuckHunt.scale;

            if(rightSide || leftSide){
                double dx;
                double limit;
                if(rightSide){
                    dx = -DuckHunt.scale*2/3;
                    limit = 20 *DuckHunt.scale;
                }else {
                    dx = +DuckHunt.scale*2/3;
                    limit = -20 *DuckHunt.scale;
                }
                Timeline bgTimeline = new Timeline();
                bgTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(10),e->{
                            if(bgComp.getNode().getBoundsInParent().contains(mouseEvent.getSceneX()+limit, mouseEvent.getSceneY())){
                                bgComp.getNode().setTranslateX(bgComp.getNode().getTranslateX() + dx);
                            }
                        })
                );
                bgTimeline.setCycleCount(Animation.INDEFINITE);

                Timeline fgTimeline = new Timeline();
                fgTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(10),e->{
                            if(fgComp.getNode().getBoundsInParent().contains(mouseEvent.getSceneX()+limit, mouseEvent.getSceneY())){
                                fgComp.getNode().setTranslateX(fgComp.getNode().getTranslateX() + dx);
                            }
                        })
                );
                fgTimeline.setCycleCount(Animation.INDEFINITE);

                slideTransition.getChildren().addAll(bgTimeline,fgTimeline);
                slideTransition.setCycleCount(Animation.INDEFINITE);
                slideTransition.play();
            }else{
                slideTransition.stop();
                slideTransition.getChildren().clear();
            }
    }

    /**
     * Handles the quit action when the ESCAPE key is pressed.
     *
     * @param action the action containing the level page
     */
    @KeyEventRouter(eventType = "KEY_PRESSED",keyValue = "ESCAPE")
    public void quit(Action<LevelPage> action){
        if(action.page.finished){
            if(action.page.nextLevelPage == null || !action.page.win){
                action.page.getMediaComponent().stopMusic();
                DuckHunt.page = MainMenuPage.getInstance();
                DuckHunt.page.getMediaComponent().playForever();
                DuckHunt.stage.setScene(DuckHunt.page.getScene());
            }
        }
    }
    /**
     * Handles the next action when the ENTER key is pressed.
     *
     * @param action the action containing the level page
     */
    @KeyEventRouter(eventType = "KEY_PRESSED", keyValue = "ENTER")
    public static void next(Action<LevelPage> action){
        if(action.page.finished){
            if(action.page.nextLevelPage == null){
                action.page.nextLevelPage = Level1.class;
            }
            action.page.getMediaComponent().stopMusic();
            action.page.getComponent(LevelPage.AmmoComponent.class).get().getMedia().stopMusic();
            action.page.getComponentsAsList(DuckComponent.class).forEach(c->c.getMedia().stopMusic());
            action.page.nextLevel();
        }
    }
    /**
     * Shoots the duck when it is clicked.
     *
     * @param action the action containing the level page
     */
    public static void shotDuck(Action<LevelPage> action){
        List<DuckComponent> ducks = (List) action.page.components.stream().filter(c->c.getClass().equals(DuckComponent.class)).collect(Collectors.toList());
        for (DuckComponent duck : ducks){
            if(duck.alive){
                MouseEvent mouseEvent = (MouseEvent) action.event;
                Bounds bounds = duck.getNode().getBoundsInParent();
                boolean isShut = bounds.contains(mouseEvent.getSceneX(),mouseEvent.getSceneY());
                if(isShut){
                    duck.animations.huntDuck(duck);
                }
            }
        }
    }
}
