package model;

import java.io.Serializable;
import java.util.Date;

public class Membership implements Serializable {
    private int id;
    private Date startTime;
    private Date endTime;
    private RaceTeam raceTeam;
    private Driver driver;

    public Membership() {
        super();
    }

    public Membership(int id, Date startTime, Date endTime, RaceTeam raceTeam, Driver driver) {
        super();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.raceTeam = raceTeam;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public RaceTeam getRaceTeam() {
        return raceTeam;
    }

    public void setRaceTeam(RaceTeam raceTeam) {
        this.raceTeam = raceTeam;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
