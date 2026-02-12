package com.github.skapud.vote.service;

import com.github.skapud.common.error.DataConflictException;
import com.github.skapud.common.error.NotFoundException;
import com.github.skapud.vote.model.Vote;
import com.github.skapud.vote.repository.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.skapud.restaurant.RestaurantTestData.restaurant1;
import static com.github.skapud.restaurant.RestaurantTestData.restaurant4;
import static com.github.skapud.user.UserTestData.ADMIN_ID;
import static com.github.skapud.user.UserTestData.USER_ID;
import static com.github.skapud.vote.VoteTestData.VOTE_MATCHER;
import static com.github.skapud.vote.VoteTestData.getUpdatedBeforeDeadline;
import static com.github.skapud.vote.service.VoteService.DEADLINE;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class VoteServiceTest {

    @Autowired
    private VoteService service;

    @Autowired
    private VoteRepository repository;

    @Test
    void saveDuplicate() {
        Assertions.assertThrows(DataConflictException.class,
                () -> service.save(USER_ID,restaurant4.id(), LocalDateTime.of(LocalDate.now(), DEADLINE)));
    }

    @Test
    void update() {
        Vote expected = getUpdatedBeforeDeadline();
        service.update(expected.getUser().getId(), expected.getRestaurant().id(), LocalDateTime.of(LocalDate.now(), expected.getVoteTime()));
        Vote actual = repository.getByDate(USER_ID, LocalDate.now()).orElseThrow();

        VOTE_MATCHER.assertMatch(actual, expected);
        Assertions.assertEquals(actual.getUser().getId(), expected.getUser().getId());
        Assertions.assertEquals(actual.getRestaurant().getId(), expected.getRestaurant().getId());
    }

    @Test
    void updateAfterDeadline() {
        Assertions.assertThrows(DataConflictException.class,
                () -> service.update(USER_ID, restaurant1.id(), LocalDateTime.of(LocalDate.now(), DEADLINE)));
    }

    @Test
    void updateNotVoted() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.update(ADMIN_ID, restaurant1.id(), LocalDateTime.of(LocalDate.now(), DEADLINE)));
    }
}