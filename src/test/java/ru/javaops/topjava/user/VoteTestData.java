package ru.javaops.topjava.user;

import ru.javaops.topjava.MatcherFactory;
import ru.javaops.topjava.user.model.Vote;
import ru.javaops.topjava.user.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaops.topjava.user.RestaurantTestData.*;
import static ru.javaops.topjava.user.UserTestData.admin;
import static ru.javaops.topjava.user.UserTestData.user;
import static ru.javaops.topjava.user.service.VoteService.DEADLINE;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "voteTime", "restaurant");
    public static MatcherFactory.Matcher<Vote> VOTES_WITH_USER_MATCHER =
            MatcherFactory.usingAssertions(Vote.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("voteTime", "user.votes", "restaurant").isEqualTo(e),
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("voteTime", "user.votes", "restaurant", "user.registered", "user.password").isEqualTo(e)
            );

    public static final int VOTE1_ID = 1000;

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.now().minusDays(2), LocalTime.of(14, 35), restaurant1, user);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, LocalDate.now().minusDays(1), LocalTime.of(10, 20), restaurant2, user);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.now(), LocalTime.of(9, 15), restaurant4, user);
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, LocalDate.now().minusDays(2), LocalTime.of(15, 30), restaurant1, admin);
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, LocalDate.now().minusDays(1), LocalTime.of(12, 15), restaurant1, admin);

    public static final List<Vote> USER_VOTE_LIST = getFiltered(List.of(vote3, vote2, vote1));

    public static final List<Vote> RESTAURANT1_VOTE_LIST = getFiltered(List.of(vote1,vote4,vote5));


    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), LocalTime.of(15, 0), restaurant1, admin);
    }

    public static Vote getUpdatedBeforeDeadline() {
        return new Vote(vote3.id(), LocalDate.now(), DEADLINE.minusMinutes(1), restaurant1, user);
    }

    public static Vote getUpdatedAfterDeadline() {
        return new Vote(vote3.id(), LocalDate.now(), DEADLINE, restaurant1, user);
    }

    @SafeVarargs
    private static List<Vote> getFiltered(List<Vote>... votes) {
        return Stream.of(votes)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Vote::getVoteDate).reversed()
                        .thenComparing(Vote::getVoteTime,Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
