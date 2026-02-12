package com.github.skapud.restaurant;

import com.github.skapud.MatcherFactory;
import com.github.skapud.restaurant.model.MenuItem;
import com.github.skapud.restaurant.to.MenuItemTo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuItemTestData {
    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant");
    public static final int MENU_ITEM1_ID = 1;
    public static final int NOT_FOUND = 500;

    public static final MenuItem menu_item_1 = new MenuItem(MENU_ITEM1_ID, "Varenyky with Cherry", 950, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_2 = new MenuItem(MENU_ITEM1_ID + 1, "Borscht", 1400, LocalDate.now());
    public static final MenuItem menu_item_3 = new MenuItem(MENU_ITEM1_ID + 2, "Meat Dumplings", 1550, LocalDate.now());
    public static final MenuItem menu_item_4 = new MenuItem(MENU_ITEM1_ID + 3, "Vinegret", 1250, LocalDate.now());
    public static final MenuItem menu_item_5 = new MenuItem(MENU_ITEM1_ID + 4, "Blini with Meat 3 pcs", 1550, LocalDate.now().plusDays(1));

    public static final List<MenuItem> RESTAURANT_1_MENU_ITEMS = getFiltered(List.of(menu_item_5, menu_item_4, menu_item_3, menu_item_2, menu_item_1));
    public static final List<MenuItem> RESTAURANT_1_TODAY_MENU_ITEMS = getFiltered(List.of(menu_item_2, menu_item_3, menu_item_4));

    public static final MenuItem menu_item_6 = new MenuItem(MENU_ITEM1_ID + 5, "French Fries", 465, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_7 = new MenuItem(MENU_ITEM1_ID + 6, "Hamburger", 630, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_8 = new MenuItem(MENU_ITEM1_ID + 7, "Cheeseburger", 655, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_9 = new MenuItem(MENU_ITEM1_ID + 8, "Chicken McNuggets 4 pcs", 600, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_10 = new MenuItem(MENU_ITEM1_ID + 9, "French Fries", 465, LocalDate.now());
    public static final MenuItem menu_item_11 = new MenuItem(MENU_ITEM1_ID + 10, "Grand Big Mac", 2095, LocalDate.now());
    public static final MenuItem menu_item_12 = new MenuItem(MENU_ITEM1_ID + 11, "Big Tasty", 1795, LocalDate.now().plusDays(1));
    public static final MenuItem menu_item_13 = new MenuItem(MENU_ITEM1_ID + 12, "French Fries", 465, LocalDate.now().plusDays(1));

    public static final List<MenuItem> RESTAURANT_2_MENU_ITEMS = getFiltered(List.of(menu_item_13, menu_item_12, menu_item_11, menu_item_10, menu_item_9, menu_item_8, menu_item_7, menu_item_6));
    public static final List<MenuItem> RESTAURANT_2_TODAY_MENU_ITEMS = getFiltered(List.of(menu_item_10, menu_item_11));

    public static final MenuItem menu_item_14 = new MenuItem(MENU_ITEM1_ID + 13, "Capricciossa Pizza", 2500, LocalDate.now());
    public static final MenuItem menu_item_15 = new MenuItem(MENU_ITEM1_ID + 14, "Caesar Salad", 2300, LocalDate.now());
    public static final MenuItem menu_item_16 = new MenuItem(MENU_ITEM1_ID + 15, "Cream of Mushroom soup", 1400, LocalDate.now().plusDays(1));
    public static final MenuItem menu_item_17 = new MenuItem(MENU_ITEM1_ID + 16, "Pasta Boloneze", 2000, LocalDate.now().plusDays(1));

    public static final List<MenuItem> RESTAURANT_3_MENU_ITEMS = getFiltered(List.of(menu_item_17, menu_item_16, menu_item_15, menu_item_14));
    public static final List<MenuItem> RESTAURANT_3_TODAY_MENU_ITEMS = getFiltered(List.of(menu_item_14, menu_item_15));

    public static final MenuItem menu_item_18 = new MenuItem(MENU_ITEM1_ID + 17, "Kharcho", 2550, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_19 = new MenuItem(MENU_ITEM1_ID + 18, "Khinkali Kalakuri 5 pcs", 1550, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_20 = new MenuItem(MENU_ITEM1_ID + 19, "Lobiani", 1800, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_21 = new MenuItem(MENU_ITEM1_ID + 20, "Adjarian Khachapuri", 2300, LocalDate.now().plusDays(1));
    public static final MenuItem menu_item_22 = new MenuItem(MENU_ITEM1_ID + 21, "Chikhirtma", 2200, LocalDate.now().plusDays(1));
    public static final MenuItem menu_item_23 = new MenuItem(MENU_ITEM1_ID + 22, "Cucumber-tomato Salad", 1950, LocalDate.now().plusDays(1));

    public static final List<MenuItem> RESTAURANT_4_MENU_ITEMS = getFiltered(List.of(menu_item_23, menu_item_22, menu_item_21, menu_item_20, menu_item_19, menu_item_18));

    public static final MenuItem menu_item_24 = new MenuItem(MENU_ITEM1_ID + 23, "Mercimek", 1000, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_25 = new MenuItem(MENU_ITEM1_ID + 24, "Chicken with Rice", 3000, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_26 = new MenuItem(MENU_ITEM1_ID + 25, "Sutlac", 1200, LocalDate.now().minusDays(1));
    public static final MenuItem menu_item_27 = new MenuItem(MENU_ITEM1_ID + 26, "Mercimek", 1000, LocalDate.now());
    public static final MenuItem menu_item_28 = new MenuItem(MENU_ITEM1_ID + 27, "Lamb chops", 4500, LocalDate.now());
    public static final MenuItem menu_item_29 = new MenuItem(MENU_ITEM1_ID + 28, "Lahmacun 3 pcs", 3600, LocalDate.now());

    public static final List<MenuItem> RESTAURANT_5_MENU_ITEMS = getFiltered(List.of(menu_item_29, menu_item_28, menu_item_27, menu_item_26, menu_item_25, menu_item_24));
    public static final List<MenuItem> RESTAURANT_5_TODAY_MENU_ITEMS = getFiltered(List.of(menu_item_29, menu_item_28, menu_item_27));

    public static MatcherFactory.Matcher<MenuItemTo> MENU_ITEM_TO_MATCHER = MatcherFactory.usingEqualsComparator(MenuItemTo.class);

    public static MenuItem getNew() {
        return new MenuItem(null, "Created menuItem", 7777, LocalDate.now().plusDays(1));
    }

    public static MenuItem getUpdated() {
        return new MenuItem(MENU_ITEM1_ID, "Updated menuItem", 8888, LocalDate.now().plusDays(1));
    }

    @SafeVarargs
    private static List<MenuItem> getFiltered(List<MenuItem>... menutItems) {
        return Stream.of(menutItems)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(MenuItem::getItemDate).reversed()
                        .thenComparing(MenuItem::getName))
                .collect(Collectors.toList());
    }
}
