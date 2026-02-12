package com.github.skapud.restaurant.service;

import com.github.skapud.restaurant.model.Restaurant;
import com.github.skapud.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    @CacheEvict(value = {"menuByDate", "restaurants"}, allEntries = true)
    public void delete(int id) {
        repository.deleteExisted(id);
    }

    @Transactional
    @CacheEvict(value = {"menuByDate", "restaurants"}, allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    @CacheEvict(value = {"menuByDate", "restaurants"}, allEntries = true)
    public Restaurant update(Restaurant restaurant) {
        repository.getExisted(restaurant.id());
        return save(restaurant);
    }

    @Cacheable("menuByDate")
    public List<Restaurant> getAllWithMenuByDate(LocalDate localDate) {
        return repository.getAllWithMenuByDate(localDate);
    }
}
