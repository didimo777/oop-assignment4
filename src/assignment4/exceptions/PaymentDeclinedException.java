package assignment4.exceptions;

public class PaymentDeclinedException extends RuntimeException {
    public PaymentDeclinedException(String message) { super(message); }
}