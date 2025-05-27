package model;

import java.io.Serializable;

public class RaceTeam implements Serializable {
    private int id;
    private String name;
    private String brand;
    private String des;

    public RaceTeam() {}

    public RaceTeam(int id, String name, String brand, String des) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
