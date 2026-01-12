package ru.javaops.topjava.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javaops.topjava.common.BaseRepository;
import ru.javaops.topjava.user.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v " +
            "WHERE v.voteDate=:voteDate AND v.user.id=:userId")
    Optional<Vote> getByDate(@Param("userId") int userId, @Param("voteDate") LocalDate date);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant " +
            "WHERE v.voteDate=:voteDate AND v.user.id=:userId")
    Optional<Vote> getByDateWithRestaurant(@Param("userId") int userId, @Param("voteDate") LocalDate date);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant " +
            "WHERE v.user.id=:userId ORDER BY v.voteDate DESC, v.voteTime DESC")
    List<Vote> getAllWithRestaurants(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user " +
            "WHERE v.restaurant.id=:restaurantId ORDER BY v.voteDate DESC, v.voteTime DESC")
    List<Vote> getWithUsers(@Param ("restaurantId") int restaurantId);
}
