package ru.javaops.topjava.user.util;

import ru.javaops.topjava.user.model.Dish;
import ru.javaops.topjava.user.to.DishTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {
    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream()
                .map(d -> new DishTo(d.getId(), d.getName(), d.getPrice(), d.getDishDate()))
                .collect(Collectors.toList());
    }
}
