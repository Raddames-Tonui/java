package org.example.advanced;


//🔁 1. Strategy Pattern Using Enums
//Enums can hold behavior by overriding abstract methods, just like classes.
// This is great for strategy-style decisions like payments.

//In Java, an enum is much more than just a list of constants —
// it's a full-fledged class (technically it extends java.lang.Enum). That means:
//You can define fields, constructors, and methods
//You can even define abstract methods and override them inside each constant
public enum PaymentMethod {
    MPESA {
        @Override
        public void process (double amount){
            // @Override allows each constant to define its own behavior for this method
            System.out.println("Processing MPESA payment of KES " + amount);
        }
    },
    CREDIT_CARD {
        @Override
        public void process(double amount){
            System.out.println("Processing CREDIT_CARD payment of KES " + amount);
        }

    },
    PAYPAL{
        @Override
        public void process(double amount){
            System.out.println("Processing PAYPAL payment of KES " + amount);
        }
    };

    //    Abstract Method: Must be overridden by all enum constants
    public abstract void process (double amount);

}
