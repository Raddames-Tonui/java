class Mobile {
    String brand;
    String model;
    int price;
    String description;
    static String name = "Smartphone";

    public void show() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Price: " + price + ", Name: " + name +
                           ", Description: " + description);
    }
}

public class StringBufferDemo {
    public static void main(String[] args) {
        Mobile m1 = new Mobile();
        m1.brand = "Samsung";
        m1.model = "Galaxy S21";
        m1.price = 799;
        m1.description = "This is a mobile phone.";

        Mobile m2 = new Mobile();
        m2.brand = "Apple";
        m2.model = "iPhone 13";
        m2.price = 999;
        m2.description = "This is another mobile phone.";
        m2.name = "Phone"; // This affects both m1 and m2

        m2.show();
        m1.show();
    }
}
