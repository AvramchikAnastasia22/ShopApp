package shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int id_employee;
    int id_user;
    int id_service;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFIO_employee() {
        return FIO_employee;
    }

    public void setFIO_employee(String FIO_employee) {
        this.FIO_employee = FIO_employee;
    }

    public String getFIO_user() {
        return FIO_user;
    }

    public void setFIO_user(String FIO_user) {
        this.FIO_user = FIO_user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    String status;

    public Records() {
        status="Ожидается";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String date;
    String FIO_employee;
    String FIO_user;
    double price;
}
