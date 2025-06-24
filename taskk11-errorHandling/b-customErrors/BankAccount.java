
public class BankAccount {

    private double balance;

    public BankAccount(double initialBalance){
        this.balance = initialBalance;
    };

    // getter
    public double getBalance(){
        return this.balance;
    }
    // Setter
    public void setBalance(double balance){
        this.balance = balance;
    }


    public void deposit(double amount){
       if (amount <= 0){
        throw new IllegalArgumentException("Deposit must be greater than zero.");
       }
       this.balance += amount;
       System.out.println("Deposited: " + amount);
    };

    public void withdraw(double amount) throws InsufficientFundsException{
      if (amount > balance ){
        throw new InsufficientFundsException("Cannot withdraw more than existing balance: " + this.balance);
      } 
      if (amount <= 0 ){
        throw new InsufficientFundsException("Withdrawal amount must be greater than zero." );
      }
      balance -= amount;
      System.out.println("Withdrawn: "+ amount);
      
    };

    
}
