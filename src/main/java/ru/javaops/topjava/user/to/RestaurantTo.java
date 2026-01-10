package ru.javaops.topjava.user.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.topjava.common.to.NamedTo;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    List<DishTo> dishes;

    public RestaurantTo(Integer id, String name, List<DishTo> dishes) {
        super(id, name);
        this.dishes = dishes;
    }
}
