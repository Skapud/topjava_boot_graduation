package ru.javaops.topjava.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.common.BaseRepository;
import ru.javaops.topjava.common.error.DataConflictException;
import ru.javaops.topjava.user.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.dishDate DESC, d.name ASC")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.dishDate=:dishDate ORDER BY d.dishDate DESC")
    List<Dish> getFiltered(@Param("restaurantId") int restaurantId, @Param("dishDate") LocalDate dishDate);

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.restaurant.id = :restaurantId")
    Optional<Dish> get(@Param("restaurantId") int restaurantId, @Param("id") int id);

    default Dish getBelonged(int restaurantId, int id) {
        return get(restaurantId, id).orElseThrow(
                () -> new DataConflictException("Dish id=" + id +
                        " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}