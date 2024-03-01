package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class COD extends PaymentMethod {

    public COD(Map<String, String> paymentData) {
        super(paymentData);
    }

    @Override
    public boolean isValid() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        return address != null &&
                deliveryFee != null &&
                !address.isEmpty() &&
                !deliveryFee.isEmpty();
    }

    @Override
    boolean isPaymentDataSizeIncorrect(Map<String, String> paymentData) {
        return paymentData.size() != 2;
    }

    @Override
    boolean doesPaymentDataNotContainTheRightKey(Map<String, String> paymentData) {
        return !paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee");
    }
}
