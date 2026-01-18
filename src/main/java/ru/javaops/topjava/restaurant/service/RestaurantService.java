package ru.javaops.topjava.restaurant.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.topjava.restaurant.model.Restaurant;
import ru.javaops.topjava.restaurant.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Transactional
    @CacheEvict(value = {"menuToday", "restaurants"}, allEntries = true)
    public Restaurant createOrUpdate(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    @CacheEvict(value = {"menuToday", "restaurants"}, allEntries = true)
    public void enable(int id, boolean enabled) {
        Restaurant restaurant = repository.getExisted(id);
        restaurant.setEnabled(enabled);
    }

    @Cacheable("menuToday")
    public List<Restaurant> getAllWithDishesForToday(LocalDate localDate) {
        return repository.getAllWithDishesForToday(localDate);
    }
}
