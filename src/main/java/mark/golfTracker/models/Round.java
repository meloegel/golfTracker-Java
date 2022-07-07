package mark.golfTracker.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "rounds")
public class Round extends Auditable{
    @Id
    @GeneratedValue(strategy = AUTO)
    private long roundId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private long totalScore;

    @Column(nullable = false)
    private String courseName;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnoreProperties(value = {"rounds", "password"}, allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"holeId", "round"}, allowSetters = true)
    private List<Hole> score = new ArrayList<>();

    public Round() {
    }

    public Round(Date date, long totalScore, String courseName, String description, User user) {
        this.date = date;
        this.totalScore = totalScore;
        this.courseName = courseName;
        this.description = description;
        this.user = user;
    }

    public Round(Date date, long totalScore, String courseName, User user, List<Hole> score) {
        this.date = date;
        this.totalScore = totalScore;
        this.courseName = courseName;
        this.user = user;
        this.score = score;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Hole> getScore() {
        return score;
    }

    public void setScore(List<Hole> score) {
        this.score = score;
    }
}
