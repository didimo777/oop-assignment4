package assignment4.AccauntingComponent.repositories;

import assignment4.AccauntingComponent.entities.Payment;

public interface PaymentRepository {
    int save(Payment payment); // returns generated id
}
