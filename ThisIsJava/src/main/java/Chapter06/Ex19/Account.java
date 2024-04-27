package Chapter06.Ex19;

public class Account {
    private int balance = 0;

    private static final int MIN_BALANCE = 0;
    private static final int MAX_BALANCE = 1000000;
    
    public void setBalance(int balance) {
        if (balance >= MIN_BALANCE && balance <= MAX_BALANCE) {
            this.balance = balance;
        }
    }

    public int getBalance() {
        return balance;
    }
}
