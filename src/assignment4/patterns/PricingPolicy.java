package assignment4.patterns;

import assignment4.entities.RoomType;

import java.time.LocalDate;

public final class PricingPolicy {
    private static final PricingPolicy INSTANCE = new PricingPolicy();

    private PricingPolicy() {}

    public static PricingPolicy getInstance() {
        return INSTANCE;
    }

    public double pricePerNight(RoomType type, LocalDate date) {
        // минимальная логика для демонстрации singleton
        return switch (type) {
            case STANDARD -> 50.0;
            case SUITE -> 120.0;
            case DORM_BED -> 25.0;
        };
    }
}
