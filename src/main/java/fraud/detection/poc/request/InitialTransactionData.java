package fraud.detection.poc.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InitialTransactionData {

    private Double transactionAmount;
    private Integer transactionCardPinIsUse;
    private Integer transactionCardPresent;
    private LocalDateTime transactionDate;
    private Integer transactionAcocuntNumber;
    private Integer transactionBankId;
    private Integer transactionPosId;

    public InitialTransactionData(Double transactionAmount, Integer transactionCardPinIsUse, Integer transactionCardPresent, Long timestamp, Integer transactionAcocuntNumber, Integer transactionBankId, Integer transactionPosId) {
        this.transactionAmount = transactionAmount;
        this.transactionCardPinIsUse = transactionCardPinIsUse;
        this.transactionCardPresent = transactionCardPresent;
        Timestamp ts = new Timestamp(timestamp);
        this.transactionDate = ts.toLocalDateTime();
        this.transactionAcocuntNumber = transactionAcocuntNumber;
        this.transactionBankId = transactionBankId;
        this.transactionPosId = transactionPosId;
    }

    private InitialTransactionData(Builder builder) {
        this.transactionAmount = builder.transactionAmount;
        this.transactionCardPinIsUse = builder.transactionCardPinIsUse;
        this.transactionCardPresent = builder.transactionCardPresent;
        this.transactionDate = builder.transactionDate;
        this.transactionAcocuntNumber = builder.transactionAcocuntNumber;
        this.transactionBankId = builder.transactionBankId;
        this.transactionPosId = builder.transactionPosId;
    }

    public static Builder newInitialTransactionData() {
        return new Builder();
    }


    public static final class Builder {
        private Double transactionAmount;
        private Integer transactionCardPinIsUse;
        private Integer transactionCardPresent;
        private LocalDateTime transactionDate;
        private Integer transactionAcocuntNumber;
        private Integer transactionBankId;
        private Integer transactionPosId;

        private Builder() {
        }

        public InitialTransactionData build() {
            return new InitialTransactionData(this);
        }

        public Builder transactionAmount(Double transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public Builder transactionCardPinIsUse(Integer transactionCardPinIsUse) {
            this.transactionCardPinIsUse = transactionCardPinIsUse;
            return this;
        }

        public Builder transactionCardPresent(Integer transactionCardPresent) {
            this.transactionCardPresent = transactionCardPresent;
            return this;
        }

        public Builder transactionDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder transactionAcocuntNumber(Integer transactionAcocuntNumber) {
            this.transactionAcocuntNumber = transactionAcocuntNumber;
            return this;
        }

        public Builder transactionBankId(Integer transactionBankId) {
            this.transactionBankId = transactionBankId;
            return this;
        }

        public Builder transactionPosId(Integer transactionPosId) {
            this.transactionPosId = transactionPosId;
            return this;
        }
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public Integer getTransactionCardPinIsUse() {
        return transactionCardPinIsUse;
    }

    public Integer getTransactionCardPresent() {
        return transactionCardPresent;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Integer getTransactionAcocuntNumber() {
        return transactionAcocuntNumber;
    }

    public Integer getTransactionBankId() {
        return transactionBankId;
    }

    public Integer getTransactionPosId() {
        return transactionPosId;
    }
}