package assignment4.AccauntingComponent;

import assignment4.AccauntingComponent.entities.Payment;
import assignment4.AccauntingComponent.repositories.PaymentRepository;
import assignment4.AccauntingComponent.repositories.PaymentRepositoryImpl;
import assignment4.AccauntingComponent.repositories.ReportingRepository;
import assignment4.AccauntingComponent.repositories.ReportingRepositoryImpl;
import assignment4.ReservationComponent.repositories.ReservationRepository;
import assignment4.ReservationComponent.repositories.ReservationRepositoryImpl;
import assignment4.RoomManagementComponent.entities.RoomType;
import assignment4.AccauntingComponent.exceptions.PaymentDeclinedException;
import assignment4.AccauntingComponent.pattern.PricingPolicy;
import assignment4.AccauntingComponent.services.PaymentService;

import java.time.LocalDate;

public class AccountingComponent {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final ReportingRepository reportingRepository;

    private final PaymentService paymentService;

    public AccountingComponent() {
        this(new PaymentRepositoryImpl(), new ReservationRepositoryImpl(), new ReportingRepositoryImpl());
    }

    public AccountingComponent(PaymentRepository paymentRepository,
                               ReservationRepository reservationRepository,
                               ReportingRepository reportingRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.reportingRepository = reportingRepository;

        this.paymentService = new PaymentService(paymentRepository, reservationRepository);
    }

    public double pricePerNight(RoomType type, LocalDate date) {
        return PricingPolicy.getInstance().pricePerNight(type, date);
    }

    public int processPayment(Payment payment) throws PaymentDeclinedException {
        return paymentService.processPayment(payment);
    }

    public double totalRevenue(LocalDate from, LocalDate to) {
        return reportingRepository.totalPaid(from, to);
    }
}
