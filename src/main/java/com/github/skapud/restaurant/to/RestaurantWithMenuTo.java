package com.github.skapud.restaurant.to;

import com.github.skapud.common.to.NamedTo;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantWithMenuTo extends NamedTo {

    List<MenuItemTo> menuItemTos;

    public RestaurantWithMenuTo(Integer id, String name, List<MenuItemTo> menuItemTos) {
        super(id, name);
        this.menuItemTos = menuItemTos;
    }
}
