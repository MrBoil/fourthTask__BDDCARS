package cars.models;

import java.util.HashMap;

public class CarModel {
    private static HashMap<String, String> firstCar = new HashMap<>();
    private static HashMap<String, String> secondCar = new HashMap<>();

    public static void putValue(String carName, String key, String value) {
        switch (carName) {
            case "firstCar":
                firstCar.put(key, value);
            case "secondCar":
                secondCar.put(key, value);
                break;
        }
    }

    public static String getCarMakeModelYear(String carName, String key) {
        switch (carName) {
            case "firstCar":
                return firstCar.get(key);
            case "secondCar":
                return secondCar.get(key);
            default:
                    return null;
        }
    }

    public static String getEngine(String carName) {
        switch (carName) {
            case "firstCar":
                return firstCar.get("engine");
            case "secondCar":
                return secondCar.get("engine");
            default:
                return null;
        }
    }

    public static String getTransmission(String carName) {
        switch (carName) {
            case "firstCar":
                return firstCar.get("transmission");
            case "secondCar":
                return secondCar.get("transmission");
            default:
                return null;
        }
    }

}
