package ru.javaops.topjava.user.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.user.model.Vote;
import ru.javaops.topjava.user.to.VoteTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VotesUtil {
    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VotesUtil::createTo)
                .collect(Collectors.toList());
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getVoteDate(), vote.getVoteTime(), vote.getRestaurant().id(), vote.getRestaurant().getName());
    }
}
