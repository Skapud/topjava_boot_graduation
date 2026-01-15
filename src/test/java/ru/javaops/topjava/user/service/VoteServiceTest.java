package ru.javaops.topjava.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.common.error.DataConflictException;
import ru.javaops.topjava.user.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.javaops.topjava.user.RestaurantTestData.restaurant1;
import static ru.javaops.topjava.user.UserTestData.USER_ID;
import static ru.javaops.topjava.user.VoteTestData.*;
import static ru.javaops.topjava.user.service.VoteService.DEADLINE;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class VoteServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void updateBeforeDeadline() {
        Vote actual = service.save(USER_ID, restaurant1.id(), LocalDateTime.of(LocalDate.now(), DEADLINE.minusMinutes(1)));
        VOTE_MATCHER.assertMatch(actual, getUpdatedBeforeDeadline());
    }

    @Test
    void updateAfterDeadline() {
        Assertions.assertThrows(DataConflictException.class,
                () -> service.save(USER_ID, restaurant1.id(), LocalDateTime.of(LocalDate.now(), DEADLINE)));
    }
}
