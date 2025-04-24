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
                "<!-- Godziny -->\n" +
                        "<line x1='0' y1='0' x2='0' y2='-%d' " +
                        "stroke='%s' stroke-width='%d' " +
                        "transform='rotate(%s)'/>",
                length, color, thickness,
                String.format("%.1f", angle).replace(',', '.'));
    }

}