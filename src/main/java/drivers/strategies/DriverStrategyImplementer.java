package drivers.strategies;

public class DriverStrategyImplementer {

    public static DriverStrategy selectedStrategy(String strategy) {
        return switch (strategy) {
            case "Android" -> new Android();
            case "IOS" -> new IOS();
            default -> null;
        };
    }
}
