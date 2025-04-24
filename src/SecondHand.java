import java.time.LocalTime;

class SecondHand extends ClockHand {
    public SecondHand() {
        this.color = "red";
        this.length = 80;
        this.thickness = 1;
    }

    @Override
    public void setTime(LocalTime time) {
        this.angle = time.getSecond() * 6; //TODO: 1sekunda-6stopni
    }


    @Override
    public String toSvg() {
        return String.format(
                "<!-- Sekundy -->\n" +
                        "<line x1='0' y1='0' x2='0' y2='-%d' " +
                        "stroke='%s' stroke-width='%d' " +
                        "transform='rotate(%s)'/>",
                length, color, thickness,
                String.format("%.1f", angle).replace(',', '.'));
    }
}