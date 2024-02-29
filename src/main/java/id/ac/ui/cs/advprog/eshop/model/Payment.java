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
        this.id = id;
        this.paymentData = paymentData;

        if (method.equals("Voucher")) {
            this.method = method;

            int numCharAmount = 0;
            for (char c : paymentData.get("voucherCode").toCharArray()) {
                if (Character.isDigit(c)) {
                    numCharAmount += 1;
                }
            }

            if (paymentData.get("voucherCode").length() == 16 &&
                    paymentData.get("voucherCode").startsWith("ESHOP") &&
                    numCharAmount == 8)  {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }

        } else if (method.equals("COD")) {
            this.method = method;
            if (paymentData.get("address") != null &&
                    !paymentData.get("address").isEmpty() &&
                    paymentData.get("deliveryFee") != null &&
                    !paymentData.get("deliveryFee").isEmpty()) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }

        } else {
            throw new IllegalArgumentException();
        }
    }
}
