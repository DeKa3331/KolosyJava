import java.time.LocalTime;

class SecondHand extends ClockHand {
    public SecondHand() {
        this.color = "red";
        this.length = 90;
        this.thickness = 1;
    }

    @Override
    public void setTime(LocalTime time) {
        this.angle = time.getSecond() * 6; //TODO: 1sekunda-6stopni
    }


    @Override
    public String toSvg() {
        int x2 = 150 + (int) (length * Math.sin(Math.toRadians(angle)));
        int y2 = 150 - (int) (length * Math.cos(Math.toRadians(angle)));

        return String.format(
                "<line x1='150' y1='150' x2='%d' y2='%d' " +
                        "stroke='%s' stroke-width='%d' " +
                        "transform='rotate(%.1f,150,150)'/>",
                x2, y2, color, thickness, angle
        );
    }
}