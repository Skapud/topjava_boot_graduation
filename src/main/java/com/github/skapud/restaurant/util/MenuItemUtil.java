package com.github.skapud.restaurant.util;

import com.github.skapud.restaurant.model.MenuItem;
import com.github.skapud.restaurant.to.MenuItemTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MenuItemUtil {
    public static List<MenuItemTo> getTos(Collection<MenuItem> menuItems) {
        return menuItems.stream()
                .map(MenuItemUtil::createTo)
                .collect(Collectors.toList());
    }

    public static MenuItemTo createTo(MenuItem menuItem) {
        return new MenuItemTo(menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getItemDate());
    }

    public static MenuItem createFromTo(MenuItemTo menuItemTo) {
        return new MenuItem(menuItemTo.getId(), menuItemTo.getName(), menuItemTo.getPrice(), menuItemTo.getItemDate());
    }

    public static MenuItem createNewFromTo(MenuItemTo menuItemTo) {
        return new MenuItem(null, menuItemTo.getName(), menuItemTo.getPrice(), menuItemTo.getItemDate());
    }
}
