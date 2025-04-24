import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, City> cities = City.parseFile("strefy.csv");
            /*
            DigitalClock digitalClock = new DigitalClock(cities.get("Warszawa"), DigitalClock.DisplayMode.MODE_12H);
            System.out.println("12h mode: " + digitalClock);
            digitalClock.setTime(23, 59, 59);
            System.out.println("Set time: " + digitalClock);

            digitalClock.setCity(cities.get("Kijów"));
            System.out.println("After city change: " + digitalClock);*/

            /*
            LocalTime zoneTime = LocalTime.of(12, 0, 0);
            LocalTime localTime = cities.get("Lublin").localMeanTime(zoneTime);
            System.out.println("Local mean time for Lublin: " + localTime);
            */


            List<City> cityList = new ArrayList<>(cities.values());
            cityList.sort(City.worstTimezoneFit());
            //System.out.println("Cities sorted by worst timezone fit:");
//            for (City city : cityList) {
//                System.out.println(city.getName());
//            }
            AnalogClock analogClock = new AnalogClock(cities.get("Warszawa"));
            City.generateAnalogClocksSvg(cityList, analogClock);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}