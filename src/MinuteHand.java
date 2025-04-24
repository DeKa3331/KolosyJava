import java.time.LocalTime;

class MinuteHand extends ClockHand {
    public MinuteHand() {
        this.color = "red";
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
                "<!-- Minuty -->\n" +
                        "<line x1='0' y1='0' x2='0' y2='-%d' " +
                        "stroke='%s' stroke-width='%d' " +
                        "transform='rotate(%s)'/>",
                length, color, thickness,
                String.format("%.1f", angle).replace(',', '.'));
    }
}