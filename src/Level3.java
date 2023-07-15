/**
 * Represents Level 3 of the game.
 */
public class Level3 extends LevelPage{
    /**
     * Creates a new Level3 instance.
     */
    public Level3() {
        DuckComponent duck1 = addComponent(new DuckComponent(DuckComponent.Duck.BLACK.getPath(),DuckHunt.scale*3/4));
        duck1.getNode().setTranslateY(-35*DuckHunt.scale);
        duck1.animations.duckStraitFlyPlay(1*DuckHunt.scale,false);

        DuckComponent duck2 = addComponent(new DuckComponent(DuckComponent.Duck.RED.getPath(),DuckHunt.scale*3/4));
        duck2.animations.duckCrossFlyPlay(DuckHunt.scale*2/3,-150);

        DuckComponent duck3 = addComponent(new DuckComponent(DuckComponent.Duck.BLUE.getPath(),DuckHunt.scale*3/4));
        duck3.getNode().setTranslateY(-15*DuckHunt.scale);
        duck3.animations.duckStraitFlyPlay(DuckHunt.scale*2/3,true);


        this.nextLevelPage = Level4.class;
        this.getComponent(FgImageComponent.class).get().getNode().toFront();
        setAmmo();
        this.getComponent(LevelComponent.class).get().getNode().setText("Level 3/6");
    }
}
