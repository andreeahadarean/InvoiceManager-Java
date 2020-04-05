package Model;

public class Product {
    private int id;
    private String name;
    private float price;

    public Product() {

    }

    public Product(int productNumber, String name, float price) {
        this.id = productNumber;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product " + id + ": " + name + ", price: " + price + "\n";
    }
}
