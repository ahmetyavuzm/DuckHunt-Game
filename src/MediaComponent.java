import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import java.io.File;

public class MediaComponent extends Component<MediaView>{
    private String mediaPath;
    private File file;
    /**
     * The volume level of the media component.
     */
    public double volume = DuckHunt.volume;
    /**
     * Constructs a MediaComponent with a default constructor.
     */
    public MediaComponent() {
        super(new MediaView());
    }
    /**
     * Constructs a MediaComponent with the specified media path.
     *
     * @param mediaPath the path to the media file
     */
    public MediaComponent(String mediaPath) {
        super(new MediaView());
        this.mediaPath = mediaPath;
        createNode();
    }

    /**
     * Creates the underlying JavaFX node for the media component.
     * This method is called during initialization.
     */
    protected void createNode() {
        setMedia(this.mediaPath);
    }

    /**
     * Sets the media for the component.
     *
     * @param mediaPath the path to the media file
     */
    public void setMedia(String mediaPath){
        this.mediaPath = mediaPath;
        getNode().setMediaPlayer(new MediaPlayer(createMedia(this.mediaPath)));
        getNode().getMediaPlayer().setVolume(volume);
    }
    /**
     * Creates a JavaFX Media object from the specified media path.
     *
     * @param mediaPath the path to the media file
     * @return the created Media object
     */
    private Media createMedia(String mediaPath){
        this.file = new File(mediaPath);
        return new javafx.scene.media.Media(this.file.toURI().toString());
    }
    /**
     * Returns the file associated with the media component.
     *
     * @return the file associated with the media component
     */
    public File getFile() {
        return file;
    }
    /**
     * Starts playing the media in a loop indefinitely.
     */
    public void playForever(){
        getNode().getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
        getNode().getMediaPlayer().play();
    }
    /**
     * Starts playing the media.
     */
    public void play(){
        getNode().getMediaPlayer().play();
    }
    /**
     * Stops the media playback.
     */
    public void stopMusic(){
        getNode().getMediaPlayer().stop();
    }
    /**
     * Pauses the media playback.
     */
    public void pauseMusic(){
        getNode().getMediaPlayer().pause();
    }

    /**
     * Starts playing the media, with an option to reset the playback when it reaches the end.
     *
     * @param reset if true, the media playback will be reset when it reaches the end
     */
    public void play(boolean reset){
        if(reset){
            getNode().getMediaPlayer().setOnEndOfMedia(()->{
                getNode().getMediaPlayer().stop();
            });
        }
        getNode().getMediaPlayer().play();
    }
}
