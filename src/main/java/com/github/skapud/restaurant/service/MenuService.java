package com.github.skapud.restaurant.service;

import com.github.skapud.restaurant.model.MenuItem;
import com.github.skapud.restaurant.repository.MenuRepository;
import com.github.skapud.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @CacheEvict(value = {"menuByDate"}, allEntries = true)
    public void delete(int restaurantId, int id) {
        MenuItem menuItem = menuRepository.getBelonged(restaurantId, id);
        menuRepository.delete(menuItem.id());
    }

    @Transactional
    @CacheEvict(value = {"menuByDate"}, allEntries = true)
    public MenuItem save(int restaurantId, MenuItem menuItem) {
        menuItem.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return menuRepository.save(menuItem);
    }

    @Transactional
    @CacheEvict(value = {"menuByDate"}, allEntries = true)
    public MenuItem update(int restaurantId, MenuItem menuItem) {
        menuRepository.getBelonged(restaurantId, menuItem.id());
        return save(restaurantId, menuItem);
    }
}
