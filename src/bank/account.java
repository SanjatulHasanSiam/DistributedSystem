package bank;

import java.io.Serializable;

public class account implements Serializable {
    private double balance;

    public account(double balance) {
        this.balance = balance;
    }
    public Object deposit(double amount){
        this.balance=balance+amount;
        return "Deposit successfull new balance is " + this.balance;

    }
    public Object withdraw(double amount){
        if(amount<=balance) {
            this.balance = balance - amount;
            return "withdraw successfull new balance is " +this.balance;
        }
        else{
            return "You don't have sufficient balance to withdraw";
        }


    }


    public Object balance() {

        return "Your balance is "+this.balance;
    }
}
