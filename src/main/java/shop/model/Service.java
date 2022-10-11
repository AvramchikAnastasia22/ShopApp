package shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName_photo_file() {
        return name_photo_file;
    }

    public void setName_photo_file(String name_photo_file) {
        this.name_photo_file = name_photo_file;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name_service;
    String type;

    @Override
    public String toString() {
        return "Услуга: " +

                "Название услуги:" + name_service +
                ", тип услуги:" + type +
                ", стоимость:" + price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    double price;
    String name_photo_file;
}
