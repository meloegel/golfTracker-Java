package mark.golfTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "holes")
public class Hole extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long holeId;

    @Column(nullable = false)
    private long par;

    @Column(nullable = false)
    private long score;

    @Column
    private long putts;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name="roundId", nullable = false)
    @JsonIgnoreProperties(value = {"date", "totalScore", "courseName", "description", "score"}, allowSetters = true)
    private Round round;

    public Hole() {
    }

    public Hole(long par, long score, Round round) {
        this.par = par;
        this.score = score;
        this.round = round;
    }

    public Hole(long par, long score, long putts, String notes, Round round) {
        this.par = par;
        this.score = score;
        this.putts = putts;
        this.notes = notes;
        this.round = round;
    }

    public long getHoleId() {
        return holeId;
    }

    public void setHoleId(long holeId) {
        this.holeId = holeId;
    }

    public long getPar() {
        return par;
    }

    public void setPar(long par) {
        this.par = par;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getPutts() {
        return putts;
    }

    public void setPutts(long putts) {
        this.putts = putts;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}
