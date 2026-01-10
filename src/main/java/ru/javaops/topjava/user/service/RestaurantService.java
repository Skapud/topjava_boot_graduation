package ru.javaops.topjava.user.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.topjava.user.model.Restaurant;
import ru.javaops.topjava.user.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant createOrUpdate(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public void enable(int id, boolean enabled) {
        Restaurant restaurant = repository.getExisted(id);
        restaurant.setEnabled(enabled);
    }
}
