package assignment4.pricing;

import assignment4.entities.Room;

public class PricingPolicy {

    private static final PricingPolicy INSTANCE = new PricingPolicy();

    private PricingPolicy() {}

    public static PricingPolicy getInstance() {
        return INSTANCE;
    }

    public double calculatePrice(Room room, int nights) {
        return room.getBasePrice() * nights;
    }
}

