import java.time.LocalTime;

class MinuteHand extends ClockHand {
    public MinuteHand() {
        this.color = "blue";
        this.length = 70;
        this.thickness = 2;
    }

    @Override
    public void setTime(LocalTime time) {
        // Ciągły ruch z uwzględnieniem sekund
        this.angle = time.getMinute() * 6 +      // 6° na minutę
                time.getSecond() * 0.1;      // 0.1° na sekundę
    }

    @Override
    public String toSvg() {
        return String.format(
                "<!-- Wskazówka minutowa -->\n" +
                        "<line x1='0' y1='0' x2='%d' y2='%d' " +
                        "stroke='%s' stroke-width='%d' " +
                        "transform='rotate(%.1f,150,150)'/>",
                150 + (int)(length * Math.sin(Math.toRadians(angle))),
                150 - (int)(length * Math.cos(Math.toRadians(angle))),
                color, thickness, angle);
    }
}