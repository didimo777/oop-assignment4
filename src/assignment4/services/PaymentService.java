package assignment4.services;

import assignment4.entities.Payment;
import assignment4.repositories.*;

public class PaymentService {

    private final PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private final ReservationRepository reservationRepository = new ReservationRepositoryImpl();

    public void processPayment(Payment p) {
        if (reservationRepository.findById(p.getReservationId()).isEmpty()) {
            throw new RuntimeException("Reservation not found");
        }
        paymentRepository.save(p);
    }
}

