package com.github.skapud.restaurant.repository;

import com.github.skapud.common.BaseRepository;
import com.github.skapud.common.error.NotFoundException;
import com.github.skapud.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menuItems d " +
            "WHERE d.itemDate=:date " +
            "ORDER BY r.name ASC, d.name ASC")
    List<Restaurant> getAllWithMenuByDate(@Param("date") LocalDate localDate);

    default Restaurant getExistedById(int id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Restaurant with id=" + id + " not found"));
    }
}