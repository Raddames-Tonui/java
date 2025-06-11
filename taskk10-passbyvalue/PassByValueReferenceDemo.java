

// Demonstrates Java's pass-by-value behavior with primitives and object references.
public class PassByValueReferenceDemo {

    /*
      Attempts to update a primitive price inside the method.
      This change will NOT affect the original variable outside.
     */
    public void updatePrice(double price) {
        System.out.println("Inside updatePrice() before update: price = " + price);
        price = price + 50;  // Arbitrary update
        System.out.println("Inside updatePrice() after update: price = " + price);
    }

    /*
      Modifies fields of the passed Product object.
      Changes will reflect outside the method since the object is the same.
     */
    public void updateProduct(Product product) {
        System.out.println("Inside updateProduct() before update: " + product);
        product.setProductName("Updated Product");
        product.setPrice(product.getPrice() + 100);
        System.out.println("Inside updateProduct() after update: " + product);
    }

    /*
      Reassigns the passed product reference to a new Product object.
      This reassignment does NOT affect the original object reference outside.
      @param product Product reference to reassign
     */
    public void reassignProduct(Product product) {
        System.out.println("Inside reassignProduct() before reassignment: " + product);
        product = new Product("New Product", 999.99);
        System.out.println("Inside reassignProduct() after reassignment: " + product);
    }

    public static void main(String[] args) {
        PassByValueReferenceDemo demo = new PassByValueReferenceDemo();

        // Test 1: Primitive pass-by-value
        double price = 200.0;
        System.out.println("Before updatePrice(): price = " + price);
        demo.updatePrice(price);
        System.out.println("After updatePrice(): price = " + price);
        System.out.println("------------------------------------------");

        // Test 2: Object reference modification
        Product product = new Product("Original Product", 500.0);
        System.out.println("Before updateProduct(): " + product);
        demo.updateProduct(product);
        System.out.println("After updateProduct(): " + product);
        System.out.println("------------------------------------------");

        // Test 3: Reassign object reference
        System.out.println("Before reassignProduct(): " + product);
        demo.reassignProduct(product);
        System.out.println("After reassignProduct(): " + product);
        System.out.println("------------------------------------------");
    }
}
