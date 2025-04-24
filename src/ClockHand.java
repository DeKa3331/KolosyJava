import java.time.LocalTime;

abstract class ClockHand {
    protected double angle;
    protected String color;
    protected int length;
    protected int thickness;

    public abstract void setTime(LocalTime time);

    public abstract String toSvg();
}