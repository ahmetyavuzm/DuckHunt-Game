/**
 * Represents Level 2 of the game.
 */
public class Level2 extends LevelPage{
    /**
     * Creates a new Level2 instance.
     */
    public Level2() {
        this.nextLevelPage = Level3.class;

        DuckComponent duck1 = addComponent(new DuckComponent(DuckComponent.Duck.RED.getPath(),DuckHunt.scale*3/4));
        DuckComponent duck2 = addComponent(new DuckComponent(DuckComponent.Duck.BLACK.getPath(),DuckHunt.scale*3/4));
        duck1.getNode().setTranslateY(-35*DuckHunt.scale);

        duck1.animations.duckStraitFlyPlay(1*DuckHunt.scale,false);
        duck2.animations.duckCrossFlyPlay(DuckHunt.scale*2/3,-150);

        this.getComponent(FgImageComponent.class).get().getNode().toFront();
        setAmmo();
        this.getComponent(LevelComponent.class).get().getNode().setText("Level 2/6");
    }
}
