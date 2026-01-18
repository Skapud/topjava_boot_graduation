package ru.javaops.topjava.restaurant.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.restaurant.model.Dish;
import ru.javaops.topjava.restaurant.repository.DishRepository;
import ru.javaops.topjava.restaurant.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @CacheEvict(value = {"menuToday"}, allEntries = true)
    public Dish createOrUpdate(int restaurantId, Dish dish) {
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}
