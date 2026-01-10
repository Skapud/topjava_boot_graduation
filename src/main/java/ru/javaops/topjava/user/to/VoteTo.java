package ru.javaops.topjava.user.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.topjava.common.to.BaseTo;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    LocalDate voteDate;

    LocalTime voteTime;

    Integer restaurantId;

    String restaurantName;

    public VoteTo(Integer id, LocalDate voteDate, LocalTime voteTime, Integer restaurantId, String restaurantName) {
        super(id);
        this.voteDate = voteDate;
        this.voteTime = voteTime;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }
}
