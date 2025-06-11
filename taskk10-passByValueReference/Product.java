
public class Product {
    private String productName;
    private double price;

    
    public Product(String productName, double price) {
    this.productName = productName;
    this.price = price;
    }

    // Getters and setters
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        // return "Product{name='" + productName + "', price=" + price + "}";
        return String.format("Product {name='%s', price=%.2f}",productName, price);

        
    }
      
}
