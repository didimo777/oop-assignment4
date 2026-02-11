package assignment4.AccauntingComponent.services;

import assignment4.AccauntingComponent.entities.Payment;
import assignment4.AccauntingComponent.exceptions.PaymentDeclinedException;
import assignment4.AccauntingComponent.repositories.PaymentRepository;
import assignment4.AccauntingComponent.repositories.PaymentRepositoryImpl;
import assignment4.ReservationComponent.repositories.ReservationRepository;
import assignment4.ReservationComponent.repositories.ReservationRepositoryImpl;

public class PaymentService {

    private final PaymentRepository payments;
    private final ReservationRepository reservations;

    public PaymentService() {
        this(new PaymentRepositoryImpl(), new ReservationRepositoryImpl());
    }

    public PaymentService(PaymentRepository payments, ReservationRepository reservations) {
        this.payments = payments;
        this.reservations = reservations;
    }

    public int processPayment(Payment p) throws PaymentDeclinedException {
        if (reservations.findById(p.getReservationId()).isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }
        if (p.getAmount() <= 0) {
            throw new PaymentDeclinedException("Payment declined");
        }
        if ("DECLINED".equalsIgnoreCase(p.getStatus())) {
            throw new PaymentDeclinedException("Payment declined");
        }
        return payments.save(p);
    }
}
