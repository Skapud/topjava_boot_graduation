package ru.javaops.topjava.user.util;

import ru.javaops.topjava.user.model.Restaurant;
import ru.javaops.topjava.user.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(r -> new RestaurantTo(r.id(), r.getName(), DishUtil.getTos(r.getDishes())))
                .collect(Collectors.toList());
    }
}
