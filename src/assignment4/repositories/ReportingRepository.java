package assignment4.repositories;

import java.time.LocalDate;

public interface ReportingRepository {
    double totalPaid(LocalDate from, LocalDate to);
}
