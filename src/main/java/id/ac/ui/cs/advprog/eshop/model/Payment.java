package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        // Will throw IllegalArgumentException if invalid
        PaymentMethod paymentMethod = getAndValidatePaymentMethod(method, paymentData);

        this.id = id;
        this.method = method;
        this.status = checkStatus(paymentMethod);
        this.paymentData = getPaymentData(method, paymentData);
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private PaymentMethod getAndValidatePaymentMethod(String method, Map<String, String> paymentData) {
        PaymentMethod paymentMethod;

        if (method.equals("Voucher")) {
            paymentMethod = new Voucher(paymentData);
        } else if (method.equals("COD")) {
            paymentMethod = new COD(paymentData);
        } else {
            throw new IllegalArgumentException();
        }

        return paymentMethod;
    }

    private String checkStatus(PaymentMethod paymentMethod) {
        return (paymentMethod.isValid() ? "SUCCESS" : "REJECTED");
    }

    private Map<String, String> getPaymentData(String method, Map<String, String> paymentData) {
        if (method.equals("COD") && paymentData.get("deliveryFee") == null) {
            paymentData.put("deliveryFee", String.valueOf(5 + (int)(Math.random() * ((50 - 5) + 1))*1000));
        }

        return paymentData;
    }
}
