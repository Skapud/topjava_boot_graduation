package com.github.skapud.vote.util;

import com.github.skapud.vote.model.Vote;
import com.github.skapud.vote.to.VoteTo;
import lombok.experimental.UtilityClass;

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
