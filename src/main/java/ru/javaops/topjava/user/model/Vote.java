package ru.javaops.topjava.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.topjava.common.model.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"vote_date", "user_id"},
        name = "user_vote_unique_date_idx")})
@Getter
@Setter
public class Vote extends BaseEntity {
    @Column(name = "vote_date", nullable = false)
    @NotNull
//    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDate voteDate;

    @Column(name = "vote_time", nullable = false)
    @NotNull
//    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalTime voteTime;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDate voteDate, LocalTime voteTime, Restaurant restaurant, User user) {
        super(id);
        this.voteDate = voteDate;
        this.voteTime = voteTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteDate=" + voteDate +
                ", voteTime=" + voteTime +
                '}';
    }
}
