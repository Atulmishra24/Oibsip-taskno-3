import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface ATMMachine {
    void showTransHistory();

    void withdraw(double amount);

    void deposit(double amount);

    void transfer(double amount, ATMMachine destination);

    void quit();
}

class Decoration {
    public void welcome() {
        verticalLine(12);
        System.out
                .println("\t\t\t\t\t\tWELCOME TO\n\n\n\t\t\tAUTOMATIC\t\t\t\t\t\t TAILOR\n\n\n\t\t\t\t\t\tMACHINE");
        verticalLine(12);

    }

    public void verticalLine(int n) {
        System.out.println("\n");
        for (int i = 1; i <= n; i++) {
            // Pattern Style
            System.out.print("~~<_*_>~");
        }
        System.out.println("\n\n");
    }
}

class BankAccount implements ATMMachine {
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getTransHistory() {
        return TransHistory;
    }

    public void setTransHistory(List<String> TransHistory) {
        this.TransHistory = TransHistory;
    }

    private String accountHolderName;
    private double balance;
    private List<String> TransHistory;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.TransHistory = new ArrayList<>();
    }

    @Override
    public void showTransHistory() {
        System.out.println("Trans history:");
        for (String Trans : TransHistory) {
            System.out.println(Trans);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
            TransHistory.add(String.format("Withdrawn $%.2f", amount));
            System.out.println(String.format("$%.2f withdrawn successfully", amount));
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        TransHistory.add(String.format("Deposited $%.2f", amount));
        System.out.println(String.format("$%.2f deposited successfully", amount));
    }

    @Override
    public void transfer(double amount, ATMMachine destination) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else if (destination instanceof BankAccount) {
            BankAccount destAccount = (BankAccount) destination;
            balance -= amount;
            destAccount.balance += amount;
            TransHistory.add(String.format("Transferred $%.2f to %s", amount, destAccount.accountHolderName));
            System.out.println(
                    String.format("$%.2f transferred successfully to %s", amount, destAccount.accountHolderName));
        } else {
            System.out.println("Invalid destination account");
        }
    }

    @Override
    public void quit() {
        System.out.println("Thanks for coming here .. Please come again ");
        System.exit(0);
    }
}

public class AtmMachineInterface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Decoration d = new Decoration();
        d.welcome();

        BankAccount account1 = new BankAccount("000000", "Atul Mishra", 20000);
        BankAccount account2 = new BankAccount("111111", "Gopal Tiwari", 24000);

        ATMMachine currentATM = account1;
        while (true) {
            d.verticalLine(12);
            System.out.println("Choose an option for the further Proceduce:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction history");
            System.out.println("5. Quit");
            d.verticalLine(12);
            int Choice = scanner.nextInt();

            switch (Choice) {
                case 1:
                    d.verticalLine(12);
                    System.out.println("Enter amount to withdraw Which you want to withdraw:");
                    double withdrawAmount = scanner.nextDouble();
                    d.verticalLine(12);
                    currentATM.withdraw(withdrawAmount);
                    d.verticalLine(12);
                    break;
                case 2:

                    d.verticalLine(12);
                    System.out.println("Enter amount to deposit Which you want to deposit :");
                    double depositAmount = scanner.nextDouble();
                    currentATM.deposit(depositAmount);
                    d.verticalLine(12);
                    break;
                    case 3:
                    d.verticalLine(12);
                    System.out.println("Enter amount to transfer Which you want to transfer:");
                    double transferAmount = scanner.nextDouble();
                    d.verticalLine(12);
                    System.out.println("Enter destination account number Where you want to transfer money:");
                    String destinationAccountNumber = scanner.next();
                    
                    if (destinationAccountNumber.equals(account1.getAccountNumber())) {
                        d.verticalLine(12);
                        currentATM.transfer(transferAmount, account1);
                        d.verticalLine(12);
                    } else if (destinationAccountNumber.equals(account2.getAccountNumber())) {
                        d.verticalLine(12);
                        currentATM.transfer(transferAmount, account2);
                        d.verticalLine(12);
                    } else {
                        d.verticalLine(12);
                        System.out.println("Invalid destination account number. Make sure You enter right account number which is register in our Bank");
                        d.verticalLine(12);
                    }
                    break;
                    case 4:
                    
                    currentATM.showTransHistory();
                    d.verticalLine(12);
                    break;
                    case 5:
                    currentATM.quit();
                    break;
                default:
                d.verticalLine(12);
                System.out.println("Invalid Choice");
                d.verticalLine(12);
            }
        }
    }
}
