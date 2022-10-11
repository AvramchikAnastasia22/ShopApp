package shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int id_service;
    String name;
    String second_name;
    String patronymic;
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName_photo_file() {
        return name_photo_file;
    }

    public void setName_photo_file(String name_photo_file) {
        this.name_photo_file = name_photo_file;
    }

    public String getDate_settled() {
        return date_settled;
    }

    public void setDate_settled(String date_settled) {
        this.date_settled = date_settled;
    }

    @Override
    public String toString() {
        return "Сотрудник: " +


                "Имя:" + name +
                ", фамилия:" + second_name +
                ", отчество:" + patronymic +
                ", номер телефона:" + phone +
                ", квалификация:" + position  ;
    }

    String position;
    String name_photo_file;
    String date_settled;
}
