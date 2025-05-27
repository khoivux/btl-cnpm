package model;

import java.io.Serializable;
import java.util.Date;

public class Driver implements Serializable {
    private int id;
    private String name;
    private Date dob;
    private String national;
    private String bio;

    public Driver() {}

    public Driver(int id, String name, Date dob, String national, String bio) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.national = national;
        this.bio = bio;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
