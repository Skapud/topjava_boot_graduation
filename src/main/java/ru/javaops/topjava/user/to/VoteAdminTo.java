package ru.javaops.topjava.user.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.topjava.common.to.BaseTo;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteAdminTo extends BaseTo {

    LocalDate voteDate;

    LocalTime voteTime;

    Integer userId;

    String name;

    String email;

    public VoteAdminTo(Integer id, LocalDate voteDate, LocalTime voteTime, Integer userId, String name, String email) {
        super(id);
        this.voteDate = voteDate;
        this.voteTime = voteTime;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
