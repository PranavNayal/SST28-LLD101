public class StandardFareCalculator implements FareCalculator {
    @Override
    public double calculateFare(double km) {
        double fare = 50.0 + km * 6.6666666667; // pricing logic
        fare = Math.round(fare * 100.0) / 100.0;
        return fare;
    }
}
