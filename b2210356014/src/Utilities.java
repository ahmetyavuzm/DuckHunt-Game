public class Utilities {
    /**
     * Reflects the angle to the Y-axis.
     *
     * @param angle the angle to reflect
     * @return the reflected angle
     */
    public static double reflectToY(double angle){
        return -1*angle;
    }
    /**
     * Reflects the angle to the X-axis.
     *
     * @param angle the angle to reflect
     * @return the reflected angle
     */
    public static double reflectToX(double angle){
        return 180-angle;
    }
}
