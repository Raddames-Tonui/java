
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BankAccount account = new BankAccount(500);

        Scanner sc = new Scanner(System.in);
        Boolean running = true;

        while (running) {
            System.out.println("\n--- 🏦 Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {

                // DEPOSIT
                case "1":
                    try {
                        System.out.println("Enter deposit amount: ");
                        double depositAmount = Double.parseDouble(sc.nextLine());
                        account.deposit(depositAmount);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Deposit Error: " + e.getMessage());
                    } finally {
                        System.out.println("Transaction complete.");
                    }
                    break;

                // WITHDRAW
                case "2":
                    try {
                        System.out.println("Enter amount to withdraw: ");
                        double withrawalAmount = Double.parseDouble(sc.nextLine());
                        account.withdraw(withrawalAmount);
                    } catch (InsufficientFundsException e) {
                        System.out.println("Withdrawal error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Withdrawal error: " + e.getMessage());
                    } finally {
                        System.out.println("Transaction complete.");
                    }
                    ;
                    break;

                // BALANCE
                case "3":
                    System.out.println("Account balance is: " + account.getBalance());
                    break;

                case "4":
                    running = false;
                    System.out.println("\nExiting... Thank you.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        sc.close();

    }

    public class InnerMain {
        public void account() {

            BankAccount account = new BankAccount(700);
            // Test deposit
            try {
                account.deposit(100);
            } catch (IllegalArgumentException e) {
                System.out.println("\nDeposit error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Deposit error: " + e.getMessage());
            } finally {
                System.out.println("Transaction complete. \n");
            }

            // Test Withdraw
            try {
                account.withdraw(700);
            } catch (InsufficientFundsException e) {
                System.out.println("Withdrawal error: " + e.getMessage());
            } finally {
                System.out.println("Transaction complete.\n");
            }

            // Final Balance
            System.out.println("Balance: " + account.getBalance());
        }

    }

}
