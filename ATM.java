import java.util.Scanner;

 class ATM {

    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } 
            catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
               scanner.next(); // Clear the invalid input
           }
        }
    }

    private void checkBalance() {
        System.out.printf("Your balance is: $%.2f\n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        try {
            account.deposit(amount);
            System.out.printf("Deposited $%.2f successfully.\n", amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.printf("Withdrew $%.2f successfully.\n", amount);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public static void main(String[] args) {
        // Initial account setup
        BankAccount account = new BankAccount(1000.00); // Starting with $1000
        ATM atm = new ATM(account);
        atm.displayMenu();
    }
}
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
 
