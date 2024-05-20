/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class Product {

    private int id;
    private String description, image, name;
    private double price;
    private int quantity;
    private ProductCategory productCategoty;
    private ProductType productType;
    

    public Product() {
    }

    public Product(int id, String description, String image, String name, double price, int quantity, ProductCategory productCategoty, ProductType productType) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productCategoty = productCategoty;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getProductCategoty() {
        return productCategoty;
    }

    public void setProductCategoty(ProductCategory productCategoty) {
        this.productCategoty = productCategoty;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", description=" + description + ", image=" + image + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", productCategoty=" + productCategoty + ", productType=" + productType + '}';
    }
    
}
