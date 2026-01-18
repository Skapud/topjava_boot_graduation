package ru.javaops.topjava.user;

import ru.javaops.topjava.MatcherFactory;
import ru.javaops.topjava.restaurant.model.Restaurant;
import ru.javaops.topjava.restaurant.to.RestaurantTo;

import java.util.List;

import static ru.javaops.topjava.user.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes", "votes");
    public static final int RESTAURANT1_ID = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Ukrainochka");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Macdonald's");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "Amedeo");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "New Era");
    public static final Restaurant restaurant5 = new Restaurant(RESTAURANT1_ID + 4, "Kapadokya");

    public static final Restaurant restaurantWithMenuToday1 = new Restaurant(RESTAURANT1_ID, "Ukrainochka");
    public static final Restaurant restaurantWithMenuToday2 = new Restaurant(RESTAURANT1_ID + 1, "Macdonald's");
    public static final Restaurant restaurantWithMenuToday3 = new Restaurant(RESTAURANT1_ID + 2, "Amedeo");
    public static final Restaurant restaurantWithMenuToday5 = new Restaurant(RESTAURANT1_ID + 4, "Kapadokya");

    public static final List<Restaurant> RESTAURANTS = List.of(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5);

    public static final List<Restaurant> RESTAURANTS_WITH_MENU_TODAY = List.of(restaurantWithMenuToday3,
            restaurantWithMenuToday5, restaurantWithMenuToday2, restaurantWithMenuToday1);

    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    static {
        restaurant1.setDishes(RESTAURANT1_DISHES);
        restaurant2.setDishes(RESTAURANT2_DISHES);
        restaurant3.setDishes(RESTAURANT3_DISHES);
        restaurant4.setDishes(RESTAURANT4_DISHES);
        restaurant5.setDishes(RESTAURANT5_DISHES);

        restaurantWithMenuToday1.setDishes(RESTAURANT1_TODAY_DISHES);
        restaurantWithMenuToday2.setDishes(RESTAURANT2_TODAY_DISHES);
        restaurantWithMenuToday3.setDishes(RESTAURANT3_TODAY_DISHES);
        restaurantWithMenuToday5.setDishes(RESTAURANT5_TODAY_DISHES);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Created restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }
}
