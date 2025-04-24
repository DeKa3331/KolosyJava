import java.time.LocalTime;

class MinuteHand extends ClockHand {
    public MinuteHand() {
        this.color = "blue";
        this.length = 70;
        this.thickness = 3;
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
                        "<line x1=\"150\" y1=\"150\" x2=\"150\" y2=\"70\" " +
                        "stroke=\"%s\" stroke-width=\"%d\" " +
                        "transform=\"rotate(%.1f 150 150)\"/>",
                color, thickness, angle);
    }
}