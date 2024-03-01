package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        PaymentMethod paymentMethod;

        if (method.equals("Voucher")) {
            paymentMethod = new Voucher(paymentData);
        } else if (method.equals("COD")) {
            paymentMethod = new COD(paymentData);
        } else {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.method = method;
        this.status = paymentMethod.isValid() ? "SUCCESS" : "REJECTED";
        this.paymentData = paymentData;
    }
}
