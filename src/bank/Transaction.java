package bank;

import java.io.Serializable;

public class Transaction implements Serializable {
   private String transactionType;
   private double transAmount;

    public Transaction(String transactionType, double transAmount) {
        this.transactionType = transactionType;
        this.transAmount = transAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }



    public double getTransAmount() {
        return transAmount;
    }


}
