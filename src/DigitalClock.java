class DigitalClock extends Clock {
    public enum DisplayMode { MODE_12H, MODE_24H } //deklaracja zegara
    private DisplayMode displayMode; //biezacy stan zegara

    public DigitalClock(City city, DisplayMode displayMode) {
        super(city);
        this.displayMode = displayMode;
    }


    public String toString() {
        if (displayMode == DisplayMode.MODE_24H) {
            return super.toString();
        } else {
            int hour = time.getHour() % 12;
            if (hour == 0) hour = 12;
            String ampm;
            if (time.getHour() < 12) {
                ampm = "AM";
            } else {
                ampm = "PM";
            }

            return String.format("%d:%02d:%02d %s", hour, time.getMinute(), time.getSecond(), ampm);
        }
    }
}