package ua.lviv.iot;

import java.io.Serializable;

public class Clothes implements Serializable {
    private Integer id;
    private double price;
    private Size size;
    private Material material;

    public Clothes(Integer id, double price, Size size, Material material) {
        this.id = id;
        this.price = price;
        this.size = size;
        this.material = material;
    }

    public Clothes() {

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
