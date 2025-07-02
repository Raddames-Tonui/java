package taskk24_Enums.advanced;

public class PaymentApp {



    public static void main(String[] args) {
        PaymentMethod method = PaymentMethod.MPESA;
        // Calls Mpesa Version
        method.process(500);

        PaymentMethod method2 = PaymentMethod.CREDIT_CARD;
         // Call the Credit card verson
        method2.process(1700);

        PaymentMethod method3 = null;
    }
}
