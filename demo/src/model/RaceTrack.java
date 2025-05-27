package model;

import java.io.Serializable;
import java.util.Date;

public class RaceTrack implements Serializable{
	private int id;
    private String name;
    private String location;
    private int numOfLaps;
    private Date time;
    private String des;

    public RaceTrack() {}

    public RaceTrack(int id, String name, String location, int numOfLaps, Date time, String des) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.numOfLaps = numOfLaps;
        this.time = time;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumOfLaps() {
        return numOfLaps;
    }

    public void setNumOfLaps(int numOfLaps) {
        this.numOfLaps = numOfLaps;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    @Override
    public String toString() {
        return this.name; 
    }
}
