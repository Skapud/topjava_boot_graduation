package com.github.skapud.restaurant.util;

import com.github.skapud.restaurant.model.Restaurant;
import com.github.skapud.restaurant.to.RestaurantTo;
import com.github.skapud.restaurant.to.RestaurantWithMenuTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static Restaurant createFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(restaurantTo.getId(), restaurantTo.getName());
    }

    public static List<RestaurantWithMenuTo> getWithMenuTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(r -> new RestaurantWithMenuTo(r.id(), r.getName(), MenuItemUtil.getTos(r.getMenuItems())))
                .collect(Collectors.toList());
    }
}
