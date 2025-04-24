import java.time.LocalTime;

class HourHand extends ClockHand {
    public HourHand() {
        this.color = "black";
        this.length = 50;
        this.thickness = 4;
    }

    @Override
    public void setTime(LocalTime time) {
        this.angle = (time.getHour() % 12) * 30 + // 30° na godzinę
                time.getMinute() * 0.5 +      // 0.5° na minutę
                time.getSecond() * (0.5/60);   // Ciągłość ruchu
    }

    @Override
    public String toSvg() {
        return String.format(
                "<!-- Wskazówka godzinowa -->\n" +
                        "<line x1='150' y1='150' x2='%d' y2='%d' " +
                        "stroke='%s' stroke-width='%d' " +
                        "transform='rotate(%.1f,150,150)'/>",
                150 + (int)(length * Math.sin(Math.toRadians(angle))),
                150 - (int)(length * Math.cos(Math.toRadians(angle))),
                color, thickness, angle);
    }

}