public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.94, 77.59));
        
        DistanceService distanceService = new DistanceCalculator();
        DriverService driverService = new DriverAllocator();
        PaymentService paymentService = new PaymentGateway();
        FareCalculator fareCalculator = new StandardFareCalculator();
        
        TransportBookingService svc = new TransportBookingService(distanceService, driverService, paymentService, fareCalculator);
        svc.book(req);
    }
}
