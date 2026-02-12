package com.github.skapud.restaurant.to;

import com.github.skapud.common.to.NamedTo;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {
    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }
}
