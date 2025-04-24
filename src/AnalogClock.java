
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
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n")
                .append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n")
                .append("<svg width=\"300\" height=\"300\" viewBox=\"0 0 300 300\" xmlns=\"http://www.w3.org/2000/svg\">\n")
                .append("  <!-- Tarcza zegara -->\n")
                .append("  <circle cx=\"150\" cy=\"150\" r=\"140\" fill=\"white\" stroke=\"black\" stroke-width=\"2\"/>\n")
                .append("  <!-- Znaczniki godzin -->\n")
                .append("  <g text-anchor=\"middle\" font-size=\"14\" fill=\"black\">\n")
                .append("    <text x=\"150\" y=\"30\">12</text>\n")
                .append("    <text x=\"270\" y=\"160\">3</text>\n")
                .append("    <text x=\"150\" y=\"290\">6</text>\n")
                .append("    <text x=\"30\" y=\"160\">9</text>\n")
                .append("  </g>\n");

        for (ClockHand hand : hands) {
            svg.append("  ").append(hand.toSvg()).append("\n");
        }

        svg.append("</svg>");
        return svg.toString();
    }
}