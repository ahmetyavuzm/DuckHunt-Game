import javafx.animation.FadeTransition;
import javafx.event.EventType;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Abstract class representing a level page in the game.
 * Provides common functionality and components for level pages.
 */
public abstract class LevelPage extends Page{

    public int ammo;

    public static String bgImagePath;

    public static  String cursorImagePath;

    public Class<?> nextLevelPage;

    public  boolean finished = false;

    public boolean win = false;

    /**
     * Constructs a LevelPage object and initializes the scene.
     */
    public LevelPage() {
        createScene();
        setEventHandler(LevelHandler.getInstance());
    }

    /**
     * Creates the scene for the level page and sets up the components.
     */
    @Override
    public void createScene() {
        StackPane stackPane = new StackPane();

        BgImageComponent bgComp = new BgImageComponent();
        FgImageComponent fgComp = new FgImageComponent();
        this.components.add(bgComp);
        this.components.add(fgComp);
        setPlayGround();

        Scene scene = new Scene(
                stackPane,
                bgComp.getFitImageWidth(),
                bgComp.getFitImageHeight()
        );

        scene.addEventHandler(EventType.ROOT, e->{
            getEventHandler().handle(new Action(e,this));
        });
        setScene(scene);


        setCursor();

        LevelComponent levelNameComp = new LevelComponent();
        levelNameComp.getNode().setTranslateY(-1*bgComp.getFitImageHeight()/2 + 5*DuckHunt.scale);

        AmmoComponent ammoComp = new AmmoComponent();
        ammoComp.getNode().setTranslateY(-1*bgComp.getFitImageHeight()/2 + 5*DuckHunt.scale);
        ammoComp.getNode().setTranslateX(bgComp.getFitImageWidth()/2 - 25*DuckHunt.scale);


        this.components.add(levelNameComp);
        this.components.add(ammoComp);



        stackPane.getChildren().addAll(
                bgComp.getNode(),
                ammoComp.getNode(),
                levelNameComp.getNode(),
                fgComp.getNode()
        );

        fgComp.getNode().toFront();
        bgComp.getNode().toBack();

    }

    /**
     * Updates the ammo count on the level page.
     *
     * @param ammo the new ammo count
     */
    public void updateAmmo(int ammo){
        this.ammo = ammo;
        this.getComponent(AmmoComponent.class).get().getNode().setText(String.format("Ammo Left: %d", this.ammo));
    }

    public void setAmmo(){
        this.ammo = 3*this.components.stream().filter(c->c.getClass().equals(DuckComponent.class)).collect(Collectors.toList()).size();
        updateAmmo(this.ammo);
    }


    /**
     * Retrieves the number of ducks left on the level page.
     *
     * @return the number of ducks left
     */
    public int getNumberOfDuckLeft(){
        return this.components.stream().filter(c->{
            if(c.getClass().equals(DuckComponent.class)){
                DuckComponent duck = (DuckComponent) c;
                return duck.alive;
            }return false;
        }).collect(Collectors.toList()).size();
    }

    /**
     * Sets up the background and foreground images for the level page.
     */
    public void setPlayGround(){
        BgImageComponent bgComp = this.getComponent(BgImageComponent.class).get();
        bgComp.updateImage(bgImagePath);
        bgComp.setScale(DuckHunt.scale);
        String fgImagePath = String.format("assets/foreground/%s.png",bgComp.getImageName());
        FgImageComponent fgComp = this.getComponent(FgImageComponent.class).get();
        fgComp.updateImage(fgImagePath);
        fgComp.setScale(DuckHunt.scale);
    }

    /**
     * Sets the cursor for the level page.
     */
    public void setCursor(){
        Image image = new Image("file:"+cursorImagePath);
        Image cursorImage = new Image("file:"+cursorImagePath, image.getWidth()*DuckHunt.scale,image.getHeight()*DuckHunt.scale,true,false);
        ImageCursor cursor = new ImageCursor(cursorImage);
        this.getScene().setCursor(cursor);
    }
    /**
     * Component representing the background image of the level page.
     */
    protected class BgImageComponent extends PlaygroundComponent{
        public BgImageComponent() {
            super(3);
        }
    }

    /**
     * Component representing the foreground image of the level page.
     */
    protected class FgImageComponent extends PlaygroundComponent{
        public FgImageComponent() {
            super(3);
        }
    }

    /**
     * Component representing the level name label in the level page.
     */
    protected class LevelComponent extends LabelComponent {
        public LevelComponent() {
            getNode().setFont(new Font("Ariel",7*DuckHunt.scale));
        }
    }
    /**
     * Component representing the ammo label in the level page.
     */
    protected class AmmoComponent extends LabelComponent{
        public AmmoComponent() {
            getNode().setFont(new Font("Ariel",7*DuckHunt.scale));
            setMedia(new MediaComponent("assets/effects/Gunshot.mp3"));
        }

    }
    /**
     * Displays the game completion message when the game is finished.
     */
    public void gameFinished(){
        setMediaComponent(new MediaComponent("assets/effects/GameCompleted.mp3"));
        getMediaComponent().play();

        LabelComponent lbl1 = addComponent(new LabelComponent());
        lbl1.getNode().setText("You have completed the game!");
        lbl1.getNode().setTranslateY(-10*DuckHunt.scale);

        LabelComponent lbl2 = addComponent(new LabelComponent());
        lbl2.getNode().setText("Press ENTER to play again\nPress ESC to exit");
        lbl2.getNode().setTranslateY(10*DuckHunt.scale);
        FadeTransition fade = lbl2.animations.fade(1000,1,0);
        lbl2.animations.play(fade);
    }

    /**
     * Displays the win message when the player wins the level.
     */
    public void win(){
        setMediaComponent(new MediaComponent("assets/effects/LevelCompleted.mp3"));
        getMediaComponent().play();

        LabelComponent lbl1 = addComponent(new LabelComponent());
        lbl1.getNode().setText("YOU WIN!!!");
        lbl1.getNode().setTranslateY(-10*DuckHunt.scale);

        LabelComponent lbl2 = addComponent(new LabelComponent());
        lbl2.getNode().setText("Press ENTER to play next level");
        lbl2.getNode().setTranslateY(10*DuckHunt.scale);
        FadeTransition fade = lbl2.animations.fade(1000,1,0);
        lbl2.animations.play(fade);
    };

    /**
     * Displays the game over message when the player loses the level.
     */
    public void lose(){
        setMediaComponent(new MediaComponent("assets/effects/GameOver.mp3"));
        getMediaComponent().play();

        LabelComponent lbl1 = addComponent(new LabelComponent());
        lbl1.getNode().setText("GAME OVER!");
        lbl1.getNode().setTranslateY(-10*DuckHunt.scale);

        LabelComponent lbl2 = addComponent(new LabelComponent());
        lbl2.getNode().setText("Press ENTER to play again\nPress ESC to exit");
        lbl2.getNode().setTranslateY(10*DuckHunt.scale);
        FadeTransition fade = lbl2.animations.fade(1000,1,0);
        lbl2.animations.play(fade);
    };
    /**
     * Proceeds to the next level.
     */
    public void nextLevel(){
        try {
            LevelPage page= (LevelPage) nextLevelPage.newInstance();
            page.setPlayGround();
            page.setCursor();
            DuckHunt.page = page;
            DuckHunt.stage.setScene(DuckHunt.page.getScene());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
