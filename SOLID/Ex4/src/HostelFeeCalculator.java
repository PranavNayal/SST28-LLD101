import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        // Get base price using strategy pattern
        RoomPricingStrategy roomStrategy = RoomPricingFactory.createStrategy(req.roomType);
        Money base = roomStrategy.getMonthlyPrice();

        // Add all add-on charges using composition
        Money addOns = new Money(0.0);
        for (AddOn addOn : req.addOns) {
            AddOnCharge charge = AddOnChargeFactory.createCharge(addOn);
            addOns = addOns.plus(charge.getCost());
        }

        return base.plus(addOns);
    }
}
