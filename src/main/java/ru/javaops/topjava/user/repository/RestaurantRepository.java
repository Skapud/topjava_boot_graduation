package ru.javaops.topjava.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.common.BaseRepository;
import ru.javaops.topjava.user.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishes d " +
            "WHERE d.dishDate=:date AND r.enabled = TRUE " +
            "ORDER BY r.name ASC, d.name ASC")
    List<Restaurant> getAllWithDishesForToday(@Param("date") LocalDate localDate);
}