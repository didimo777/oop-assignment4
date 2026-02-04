package assignment4.repositories;

import assignment4.entities.Payment;

public interface PaymentRepository {
    int save(Payment payment); // returns generated id
}
