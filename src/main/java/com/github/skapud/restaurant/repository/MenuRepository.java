package com.github.skapud.restaurant.repository;

import com.github.skapud.common.BaseRepository;
import com.github.skapud.common.error.DataConflictException;
import com.github.skapud.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<MenuItem> {
    @Query("SELECT d FROM MenuItem d WHERE d.restaurant.id=:restaurantId ORDER BY d.itemDate DESC, d.name ASC")
    List<MenuItem> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM MenuItem d WHERE d.restaurant.id=:restaurantId AND d.itemDate=:itemDate ORDER BY d.itemDate DESC, d.name ASC")
    List<MenuItem> getFiltered(@Param("restaurantId") int restaurantId, @Param("itemDate") LocalDate itemDate);

    @Query("SELECT d FROM MenuItem d WHERE d.id = :id and d.restaurant.id = :restaurantId")
    Optional<MenuItem> get(@Param("restaurantId") int restaurantId, @Param("id") int id);

    default MenuItem getBelonged(int restaurantId, int id) {
        return get(restaurantId, id).orElseThrow(
                () -> new DataConflictException("MenuItem id=" + id +
                        " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}