import javafx.animation.*;
import javafx.util.Duration;

public class DuckAnimations extends Animations<DuckComponent>{
    public DuckAnimations(DuckComponent component) {
        super(component);
    }
    public double velocity;
    public double angle;

    /**
     * Performs the animation sequence for the duck when it dies.
     *
     * @return the sequential transition for the die animation
     */
    public SequentialTransition die(){
        // Create keyframe animation to change the duck image
        Timeline keyFrame = TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.millis(0),e->{
                    getComponent().updateImage(getComponent().folderPath +"/7.png");
                }),
                new KeyFrame(Duration.millis(750),e->{
                    getComponent().updateImage(getComponent().folderPath+"/8.png");
                })
        ).build();

        // Create animation to make the duck fall
        Animation fall = move(0.5,getComponent().getNode().getTranslateX(), getComponent().getNode().getScene().getHeight()/5);
        SequentialTransition transition = new SequentialTransition();
        transition.getChildren().addAll(keyFrame, fall);
        return transition;
    }
    /**
     * Plays the animation for the duck's straight fly.
     *
     * @param velocity the velocity of the fly animation
     * @param reverse  true if the duck should fly in reverse direction, false otherwise
     */
    public void duckStraitFlyPlay(double velocity,boolean reverse){
        this.angle = reverse?180:0;
        this.velocity = velocity;
        play(straightFly());
        play(crossMoveAnimation());
    }
    /**
     * Plays the animation for the duck's cross fly.
     *
     * @param velocity the velocity of the fly animation
     * @param angle    the angle of the fly animation
     */
    public void duckCrossFlyPlay(double velocity, double angle){
        this.angle = angle;
        this.velocity = velocity;
        play(crossFly());
        play(crossMoveAnimation());
    }

    /**
     * Initiates the hunting process for the duck.
     *
     * @param duck the duck component to be hunted
     */
    public void huntDuck(DuckComponent duck){
        duck.getMedia().play();
        duck.animations.stopAll();
        duck.animations.play(duck.animations.die());
        duck.alive = false;
    }

    /**
     * Creates a timeline animation for the straight fly animation of the duck.
     *
     * @return the timeline animation for the straight fly
     */
    public Timeline straightFly(){
        return TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(
                new KeyFrame(Duration.millis(200), e->{
                    getComponent().updateImage(getComponent().folderPath + "/4.png");
                    getComponent().getNode().setTranslateY(5*DuckHunt.scale);
                }),
                new KeyFrame(Duration.millis(400),e->{
                    getComponent().updateImage(getComponent().folderPath +  "/5.png");
                    getComponent().getNode().setTranslateY(0);
                }),
                new KeyFrame(Duration.millis(600),e->{
                    getComponent().updateImage(getComponent().folderPath +  "/6.png");
                    getComponent().getNode().setTranslateY(-5*DuckHunt.scale);
                })
        ).build();
    }
    /**
     * Creates a timeline animation for the cross fly animation of the duck.
     *
     * @return the timeline animation for the cross fly
     */
    public Timeline crossFly(){
        return TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(
                new KeyFrame(Duration.millis(200), e->{
                    getComponent().updateImage(getComponent().folderPath + "/1.png");
                }),
                new KeyFrame(Duration.millis(400),e->{
                    getComponent().updateImage(getComponent().folderPath +  "/2.png");
                }),
                new KeyFrame(Duration.millis(600),e->{
                    getComponent().updateImage(getComponent().folderPath +  "/3.png");
                })
        ).build();
    }

    /**
     * Creates a timeline animation for the cross move animation of the duck.
     *
     * @return the timeline animation for the cross move
     */
    public Timeline crossMoveAnimation(){
        final double[] duckX = {getComponent().getNode().getTranslateX()}; // Ördek X pozisyonu
        final double[] duckY = {getComponent().getNode().getTranslateY()}; // Ördek Y pozisyonu
        final double Xradius = getComponent().getNode().getScene().getWidth()/2;
        final double Yradius = getComponent().getNode().getScene().getHeight()/2;
        if(this.angle >90 || this.angle<-90){
            getComponent().getNode().setScaleX(-1 * getComponent().getNode().getScaleX());
        }

        final double[] duckDX = {velocity * Math.cos(Math.toRadians(angle))}; // Ördek X yönü
        final double[] duckDY = {velocity * Math.sin(Math.toRadians(angle))};
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            double Xmax = Xradius - getComponent().getNode().getBoundsInParent().getWidth()/2;
            double Xmin = -Xradius + getComponent().getNode().getBoundsInParent().getWidth()/2;
            double Ymax = Yradius - getComponent().getNode().getBoundsInParent().getHeight()/2;
            double Ymin = -Yradius + getComponent().getNode().getBoundsInParent().getHeight()/2;
            boolean isOutXmax = Xmax < duckX[0];
            boolean isOutXmin = Xmin > duckX[0];
            boolean isOutYmax = Ymax < duckY[0];
            boolean isOutYmin = Ymin> duckY[0];

            if(isOutXmax || isOutXmin){
                angle = Utilities.reflectToX(angle);
                getComponent().getNode().setScaleX(-1 * getComponent().getNode().getScaleX());
                duckDX[0] *= -1;
                duckX[0] = duckX[0] > 0 ? Xmax + duckDX[0]: Xmin + duckDX[0];
            }else{
                duckX[0] += duckDX[0];
            }

            if (isOutYmax || isOutYmin){
                angle = Utilities.reflectToY(angle);
                getComponent().getNode().setScaleY(-1 * getComponent().getNode().getScaleY());
                duckDY[0] *= -1;
                duckY[0] = duckY[0] > 0 ? Ymax + duckDY[0]: Ymin + duckDY[0];
            }else{
                duckY[0] += duckDY[0];
            }


            getComponent().getNode().setTranslateX(duckX[0]);
            getComponent().getNode().setTranslateY(duckY[0]);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }

    /**
     * Creates a TranslateTransition animation to move the duck.
     *
     * @param second the duration of the animation in seconds
     * @param toX    the target X coordinate
     * @param toY    the target Y coordinate
     * @return the translate transition animation
     */
    public Animation move(double second, double toX, double toY){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(second));
        transition.setNode(getComponent().getNode());
        transition.setToX(toX);
        transition.setToY(toY);
        return transition;
    }
}
