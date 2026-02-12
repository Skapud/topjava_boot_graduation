package com.github.skapud.vote.service;

import com.github.skapud.common.error.DataConflictException;
import com.github.skapud.common.error.NotFoundException;
import com.github.skapud.restaurant.model.Restaurant;
import com.github.skapud.restaurant.repository.RestaurantRepository;
import com.github.skapud.user.model.User;
import com.github.skapud.user.repository.UserRepository;
import com.github.skapud.vote.model.Vote;
import com.github.skapud.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<Vote> voteToday = voteRepository.getByDate(userId, voteDate);
        if (voteToday.isPresent()) {
            throw new DataConflictException("You have already voted");
        }

        LocalTime voteTime = localDateTime.toLocalTime();
        Restaurant restaurant = restaurantRepository.getExistedById(restaurantId);
        User user = userRepository.getReferenceById(userId);

        return voteRepository.save(new Vote(null, voteDate, voteTime, restaurant, user));
    }

    @Transactional
    public void update(int userId, int restaurantId, LocalDateTime localDateTime) {
        LocalDate voteDate = localDateTime.toLocalDate();
        Optional<Vote> voteToday = voteRepository.getByDate(userId, voteDate);
        if (voteToday.isEmpty()) {
            throw new NotFoundException("Vote for today not found");
        }

        LocalTime voteTime = localDateTime.toLocalTime();
        Restaurant restaurant = restaurantRepository.getExistedById(restaurantId);
        voteToday.filter(vote -> voteTime.isBefore(DEADLINE))
                .map(vote -> {
                    vote.setRestaurant(restaurant);
                    vote.setVoteTime(voteTime);
                    return vote;
                })
                .orElseThrow(() -> new DataConflictException("Vote can't be changed after " + DEADLINE));
    }
}
