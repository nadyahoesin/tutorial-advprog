package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class Voucher extends PaymentMethod {
    private static final int VOUCHER_CODE_LENGTH = 16;
    private static final String VOUCHER_CODE_PREFIX = "ESHOP";
    private static final int VOUCHER_CODE_NUMERIC_CHAR_AMOUNT = 8;

    public Voucher(Map<String, String> paymentData) {
        super(paymentData);
    }

    @Override
    public boolean isValid() {
       return this.isVoucherCodeLengthValid() &&
               this.isVoucherCodePrefixValid() &&
               this.isVoucherCodeNumCharAmountValid();
    }

    boolean isPaymentDataSizeIncorrect(Map<String, String> paymentData) {
        return paymentData.size() != 1;
    }

    boolean doesPaymentDataNotContainTheRightKey(Map<String, String> paymentData) {
        return !paymentData.containsKey("voucherCode");
    }

    private boolean isVoucherCodeLengthValid() {
        String voucherCode = this.paymentData.get("voucherCode");
        return voucherCode.length() == VOUCHER_CODE_LENGTH;
    }

    private boolean isVoucherCodePrefixValid() {
        String voucherCode = this.paymentData.get("voucherCode");
        return voucherCode.startsWith(VOUCHER_CODE_PREFIX);
    }

    private boolean isVoucherCodeNumCharAmountValid() {
        String voucherCode = this.paymentData.get("voucherCode");
        return voucherCode.chars()
                .filter(Character::isDigit)
                .count() == VOUCHER_CODE_NUMERIC_CHAR_AMOUNT;
    }
}
