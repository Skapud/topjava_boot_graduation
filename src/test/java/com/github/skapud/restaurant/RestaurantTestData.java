package com.github.skapud.restaurant;

import com.github.skapud.MatcherFactory;
import com.github.skapud.restaurant.model.Restaurant;
import com.github.skapud.restaurant.to.RestaurantTo;
import com.github.skapud.restaurant.to.RestaurantWithMenuTo;

import java.util.List;

import static com.github.skapud.restaurant.MenuItemTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItems", "votes");
    public static final int RESTAURANT1_ID = 10;
    public static final int NOT_FOUND = 500;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Ukrainochka");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Macdonald's");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "Amedeo");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "New Era");
    public static final Restaurant restaurant5 = new Restaurant(RESTAURANT1_ID + 4, "Kapadokya");

    public static final Restaurant restaurantWithMenuToday1 = new Restaurant(RESTAURANT1_ID, "Ukrainochka");
    public static final Restaurant restaurantWithMenuToday2 = new Restaurant(RESTAURANT1_ID + 1, "Macdonald's");
    public static final Restaurant restaurantWithMenuToday3 = new Restaurant(RESTAURANT1_ID + 2, "Amedeo");
    public static final Restaurant restaurantWithMenuToday5 = new Restaurant(RESTAURANT1_ID + 4, "Kapadokya");

    public static final List<Restaurant> RESTAURANTS = List.of(restaurant3, restaurant5, restaurant2, restaurant4, restaurant1);
    public static final List<Restaurant> RESTAURANTS_WITH_MENU_TODAY = List.of(restaurantWithMenuToday3,
            restaurantWithMenuToday5, restaurantWithMenuToday2, restaurantWithMenuToday1);

    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);
    public static MatcherFactory.Matcher<RestaurantWithMenuTo> RESTAURANT_WITH_MENU_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantWithMenuTo.class);

    static {
        restaurant1.setMenuItems(RESTAURANT_1_MENU_ITEMS);
        restaurant2.setMenuItems(RESTAURANT_2_MENU_ITEMS);
        restaurant3.setMenuItems(RESTAURANT_3_MENU_ITEMS);
        restaurant4.setMenuItems(RESTAURANT_4_MENU_ITEMS);
        restaurant5.setMenuItems(RESTAURANT_5_MENU_ITEMS);

        restaurantWithMenuToday1.setMenuItems(RESTAURANT_1_TODAY_MENU_ITEMS);
        restaurantWithMenuToday2.setMenuItems(RESTAURANT_2_TODAY_MENU_ITEMS);
        restaurantWithMenuToday3.setMenuItems(RESTAURANT_3_TODAY_MENU_ITEMS);
        restaurantWithMenuToday5.setMenuItems(RESTAURANT_5_TODAY_MENU_ITEMS);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Created restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }
}
