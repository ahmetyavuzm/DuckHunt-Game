
/**
 * Represents Level 1 of the game.
 */
public class Level1 extends LevelPage{
    /**
     * Creates a new Level1 instance.
     */
    public Level1() {
        this.nextLevelPage = Level2.class;

        DuckComponent duck1 = addComponent(new DuckComponent(DuckComponent.Duck.BLACK.getPath(),DuckHunt.scale*3/4));
        duck1.getNode().setTranslateY(-35*DuckHunt.scale);
        duck1.animations.duckStraitFlyPlay(1*DuckHunt.scale,false);

        this.getComponent(FgImageComponent.class).get().getNode().toFront();
        setAmmo();
        this.getComponent(LevelComponent.class).get().getNode().setText("Level 1/6");

    }
}
