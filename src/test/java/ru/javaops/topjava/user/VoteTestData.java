package ru.javaops.topjava.user;

import ru.javaops.topjava.MatcherFactory;
import ru.javaops.topjava.user.model.Vote;
import ru.javaops.topjava.user.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaops.topjava.user.RestaurantTestData.*;
import static ru.javaops.topjava.user.UserTestData.admin;
import static ru.javaops.topjava.user.UserTestData.user;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static MatcherFactory.Matcher<Vote> VOTES_WITH_USER_MATCHER =
            MatcherFactory.usingAssertions(Vote.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("voteTime", "user.votes", "restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int VOTE1_ID = 1000;

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.now().minusDays(1), LocalTime.of(10, 0), restaurant5, user);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, LocalDate.now(), LocalTime.of(11, 0), restaurant2, user);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.now().plusDays(1), LocalTime.of(15, 30), restaurant1, user);
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, LocalDate.now().minusDays(1), LocalTime.of(12, 15), restaurant4, admin);
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, LocalDate.now(), LocalTime.of(9, 5), restaurant3, admin);
    public static final Vote vote6 = new Vote(VOTE1_ID + 5, LocalDate.now().plusDays(1), LocalTime.of(14, 25), restaurant4, admin);

    public static final List<Vote> votes = List.of(vote6, vote5, vote4, vote3, vote2, vote1);

    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now().plusDays(2), LocalTime.of(0,0), restaurant1, user);
    }

    public static Vote getNewAfterDeadline() {
        return new Vote(null, LocalDate.now().plusDays(2), LocalTime.of(11,0), restaurant1, user);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, LocalDate.now().minusDays(1), LocalTime.of(10, 59), restaurant2, user);
    }

    public static Vote getUpdatedAfterDeadline() {
        return new Vote(VOTE1_ID, LocalDate.now().minusDays(1), LocalTime.of(11, 0), restaurant2, user);
    }
}
