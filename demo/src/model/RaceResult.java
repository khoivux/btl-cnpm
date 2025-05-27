package model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;


public class RaceResult implements Serializable {
    private int id;
    private LocalTime   completionTime;
    private int completedLaps;
    private RaceTrack raceTrack;
    private Membership membership;

    public RaceResult() {
        super();
    }

    public RaceResult(int id, LocalTime  completionTime, int completedLaps, RaceTrack raceTrack, Membership membership) {
        super();
        this.id = id;
        this.completionTime = completionTime;
        this.completedLaps = completedLaps;
        this.raceTrack = raceTrack;
        this.membership = membership;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime  getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalTime  completionTime) {
        this.completionTime = completionTime;
    }

    public int getCompletedLaps() {
        return completedLaps;
    }

    public void setCompletedLaps(int completedLaps) {
        this.completedLaps = completedLaps;
    }

    public RaceTrack getRaceTrack() {
        return raceTrack;
    }

    public void setRaceTrack(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
