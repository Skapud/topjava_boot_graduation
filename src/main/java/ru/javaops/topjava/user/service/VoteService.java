package ru.javaops.topjava.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.common.error.DataConflictException;
import ru.javaops.topjava.user.model.Restaurant;
import ru.javaops.topjava.user.model.User;
import ru.javaops.topjava.user.model.Vote;
import ru.javaops.topjava.user.repository.RestaurantRepository;
import ru.javaops.topjava.user.repository.UserRepository;
import ru.javaops.topjava.user.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    public static final LocalTime DEADLINE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(int userId, int restaurantId, LocalDateTime localDateTime) {
        LocalDate voteDate = localDateTime.toLocalDate();
        LocalTime voteTime = localDateTime.toLocalTime();
        Restaurant restaurant = restaurantRepository.getExistedById(restaurantId);
        User user = userRepository.getReferenceById(userId);

        Optional<Vote> voteToday = voteRepository.getByDate(userId, voteDate);
        if (voteToday.isEmpty()) {
            return voteRepository.save(new Vote(null, voteDate, voteTime, restaurant, user));
        }
        return voteToday
                .filter(vote -> voteTime.isBefore(DEADLINE))
                .map(vote -> {
                    vote.setRestaurant(restaurant);
                    vote.setVoteTime(voteTime);
                    return vote;
                })
                .orElseThrow(() -> new DataConflictException("Vote can't be changed after 11:00AM"));
    }
}
