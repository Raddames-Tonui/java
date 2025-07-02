package taskk26_Annotations.b;

public class PaymentProcessor {
    @ImportantMethod
    public void processSalary(){
        System.out.println("Processing salary...");
    }

    @ImportantMethod
    public void handleDeductions(){
        System.out.println("Handling deductions...");
    }

    @ImportantMethod
    public void generatePayslip(){
        System.out.println("Generating payslip...");
    }

    public void logTransaction(){
        System.out.println("Logging transaction...");
    }

    public void sendReport(){
        System.out.println("Sending report...");
    }
}
