import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//stolica/strefa czasowa letnia/szerokosc geo/dl geo
class City {
    private String name;
    private double latitude;
    private double longitude;
    private int timezone; //(letnia)

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getTimezone() {
        return timezone;
    }


    public City(String name, double latitude, double longitude, int timezone) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public static City parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Nieprawidłowy format linii: " + line);
        }

        try {
            String name = parts[0].trim();
            int timezone = Integer.parseInt(parts[1].trim());
            double latitude = parseCoordinate(parts[2].trim());
            double longitude = parseCoordinate(parts[3].trim());

            return new City(name, latitude, longitude, timezone);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Nieprawidłowy format liczby w linii: " + line, e);
        }
    }

    private static double parseCoordinate(String coord) {
        String[] tokens = coord.split(" ");
        double value = Double.parseDouble(tokens[0]);

        if (tokens.length > 1) {
            String direction = tokens[1].toUpperCase();
            if (direction.equals("S") || direction.equals("W")) {
                value = -value;
            }
        }

        return value;
    }

    public static Map<String, City> parseFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1)
                .filter(line -> !line.trim().isEmpty())
                .map(City::parseLine)
                .collect(Collectors.toMap(City::getName, city -> city));
    }

    public LocalTime localMeanTime(LocalTime zoneTime) {
        double longitudeHours = longitude / 15.0; //TODO: 360stopni/24h->15stopni-1h nie wiem czy to ma sens?
        long totalSeconds = (long) (longitudeHours * 3600);
        return zoneTime.plusSeconds(totalSeconds);
    }

    public static Comparator<City> worstTimezoneFit() {
        return (c1, c2) -> {
            double diff1 = Math.abs(c1.getLongitude() / 15.0 - c1.getTimezone());
            double diff2 = Math.abs(c2.getLongitude() / 15.0 - c2.getTimezone());
            return Double.compare(diff2, diff1);
        };
    }

    public static void generateAnalogClocksSvg(List<City> cities, AnalogClock clock) throws IOException {
        String dirName = clock.toString().replace(":", "_");
        Path dirPath = Paths.get(dirName);

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        for (City city : cities) {
            clock.setCity(city);
            String svgContent = clock.toSvg();
            Path filePath = dirPath.resolve(city.getName() + ".svg");
            Files.writeString(filePath, svgContent);
        }
    }

}