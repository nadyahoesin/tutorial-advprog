package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public abstract class PaymentMethod {
    protected Map<String, String> paymentData;

    public PaymentMethod(Map<String, String> paymentData) {
        if (this.isPaymentDataSizeIncorrect(paymentData) ||
                this.doesPaymentDataNotContainTheRightKey(paymentData)) {
            throw new IllegalArgumentException();
        }

        this.paymentData = paymentData;
    }

    abstract boolean isPaymentDataSizeIncorrect(Map<String, String> paymentData);
    abstract boolean doesPaymentDataNotContainTheRightKey(Map<String, String> paymentData);
    public abstract boolean isValid();
}
