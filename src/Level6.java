/**
 * Represents Level 6 of the game.
 */
public class Level6 extends LevelPage{
    /**
     * Creates a new Level6 instance.
     */
    public Level6() {
        DuckComponent duck1 = addComponent(new DuckComponent(DuckComponent.Duck.BLACK.getPath(),DuckHunt.scale*3/4));
        duck1.getNode().setTranslateY(-35*DuckHunt.scale);
        duck1.animations.duckStraitFlyPlay(1*DuckHunt.scale,false);

        DuckComponent duck2 = addComponent(new DuckComponent(DuckComponent.Duck.RED.getPath(),DuckHunt.scale*3/4));
        duck2.animations.duckCrossFlyPlay(DuckHunt.scale*2/3,-150);

        DuckComponent duck3 = addComponent(new DuckComponent(DuckComponent.Duck.BLUE.getPath(),DuckHunt.scale/2));
        duck3.getNode().setTranslateY(-15*DuckHunt.scale);
        duck3.animations.duckStraitFlyPlay(DuckHunt.scale*2/3,true);

        DuckComponent duck4 = addComponent(new DuckComponent(DuckComponent.Duck.BLUE.getPath(),DuckHunt.scale/2));
        duck4.getNode().setTranslateY(12*DuckHunt.scale);
        duck4.animations.duckCrossFlyPlay(DuckHunt.scale,-40);


        this.nextLevelPage = null;
        this.getComponent(FgImageComponent.class).get().getNode().toFront();
        setAmmo();
        this.getComponent(LevelComponent.class).get().getNode().setText("Level 6/6");
    }
}
