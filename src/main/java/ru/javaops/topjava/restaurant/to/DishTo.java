package ru.javaops.topjava.restaurant.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.topjava.common.to.NamedTo;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    Integer price;

    public DishTo(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }
}
