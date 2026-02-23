import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Registry that maps room types and add-ons to their pricing objects.
// OCP: add a new room/add-on by registering a new object here — no edits to the calculator needed.
public class PricingRegistry {
    private final Map<Integer, RoomPricing> rooms = new HashMap<>();
    private final Map<AddOn, AddOnPricing> addOns = new HashMap<>();

    public PricingRegistry register(RoomPricing rp) {
        rooms.put(rp.roomType(), rp);
        return this;
    }

    public PricingRegistry register(AddOnPricing ap) {
        addOns.put(ap.addOn(), ap);
        return this;
    }

    /** Returns the monthly base for the requested room type (falls back to 0 if unknown). */
    public double roomBase(int roomType) {
        RoomPricing rp = rooms.get(roomType);
        return rp != null ? rp.monthlyBase() : 0.0;
    }

    /** Returns the cost for the requested add-on (falls back to 0 if unknown). */
    public double addOnCost(AddOn addOn) {
        AddOnPricing ap = addOns.get(addOn);
        return ap != null ? ap.cost() : 0.0;
    }
}
