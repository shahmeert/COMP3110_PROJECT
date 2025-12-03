public class BankAccount_2 {

    private String ownerName;
    private double balance;
    private double overdraftLimit = 0.0;

    public BankAccount_2(String ownerName, double startingBalance) {
        this.ownerName = ownerName;
        this.balance = startingBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance + overdraftLimit) return false;
        balance -= amount;
        return true;
    }

    public void setOverdraftLimit(double limit) {
        this.overdraftLimit = limit;
    }

    public double getBalance() {
        return balance;
    }
}
