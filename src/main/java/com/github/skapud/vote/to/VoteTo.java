package com.github.skapud.vote.to;

import com.github.skapud.common.to.BaseTo;
import lombok.EqualsAndHashCode;
import lombok.Value;

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
