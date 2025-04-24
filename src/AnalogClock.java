
import java.util.List;

class AnalogClock extends Clock {
    private final List<ClockHand> hands;

    public AnalogClock(City city) {
        super(city);
        this.hands = List.of(new HourHand(), new MinuteHand(), new SecondHand());
        updateHands();
    }

    private void updateHands() {
        for (ClockHand hand : hands) {
            hand.setTime(time);
        }
    }

    @Override
    public void setCurrentTime() {
        super.setCurrentTime();
        updateHands();
    }

    @Override
    public void setTime(int hour, int minute, int second) {
        super.setTime(hour, minute, second);
        updateHands();
    }

    @Override
    public void setCity(City city) {
        super.setCity(city);
        updateHands();
    }

    public String toSvg(String path) {
        StringBuilder svg = new StringBuilder()
                .append("<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\">\n")
                .append("  <!-- Tarcza zegara -->\n")
                .append("  <circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"white\" stroke=\"black\" stroke-width=\"2\"/>\n")
                .append("  <!-- Znaczniki godzin -->\n")
                .append("  <g text-anchor=\"middle\">\n")
                .append("    <text x=\"0\" y=\"-80\" dy=\"6\">12</text>\n")
                .append("    <text x=\"80\" y=\"0\" dy=\"4\">3</text>\n")
                .append("    <text x=\"0\" y=\"80\" dy=\"6\">6</text>\n")
                .append("    <text x=\"-80\" y=\"0\" dy=\"4\">9</text>\n")
                .append("  </g>\n");

        for (ClockHand hand : hands) {
            svg.append("  ").append(hand.toSvg()).append("\n");
        }

        svg.append("</svg>");
        return svg.toString();
    }
}