/**
 * DIP abstraction: booking service depends on this, not on DistanceCalculator directly.
 */
public interface DistanceCalculate {
    double km(GeoPoint a, GeoPoint b);
}
