import java.time.*;

abstract class Clock {
    protected LocalTime time;
    private City city;

    public Clock(City city) {
        this.city = city;
        this.time = LocalTime.now();
        adjustTimeForCity();
    }

    public LocalTime getTime() {
        return time;
    }

    //ZAD1
    public void setCurrentTime() {
        this.time = LocalTime.now();
        adjustTimeForCity();
    }

    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Godzina musi być między 0 a 23");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minuta musi być między 0 a 59");
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Sekunda musi być między 0 a 59");
        }
        this.time = LocalTime.of(hour, minute, second);
        adjustTimeForCity();
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
    }


    public void setCity(City city) {
        this.city = city;
        adjustTimeForCity();
    }

    private void adjustTimeForCity() {
        if (city != null) {
            int timezoneOffset = city.getTimezone();
            this.time = this.time.plusHours(timezoneOffset);
        }
    }


}