package ru.javaops.topjava.user;

import ru.javaops.topjava.MatcherFactory;
import ru.javaops.topjava.restaurant.model.Dish;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static final int DISH1_ID = 1;

    public static final Dish dish1 = new Dish(DISH1_ID, "Varenyky with Cherry", 950, LocalDate.now().minusDays(1));
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Borscht", 1400, LocalDate.now());
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Meat Dumplings", 1550, LocalDate.now());
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Vinegret", 1250, LocalDate.now());
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Blini with Meat 3 pcs", 1550, LocalDate.now().plusDays(1));

    public static final List<Dish> RESTAURANT1_DISHES = getFiltered(List.of(dish5, dish4, dish3, dish2, dish1));
    public static final List<Dish> RESTAURANT1_TODAY_DISHES = getFiltered(List.of(dish2, dish3, dish4));

    public static final Dish dish6 = new Dish(DISH1_ID + 5, "French Fries", 465, LocalDate.now().minusDays(1));
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Hamburger", 630, LocalDate.now().minusDays(1));
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Cheeseburger", 655, LocalDate.now().minusDays(1));
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Chicken McNuggets 4 pcs", 600, LocalDate.now().minusDays(1));
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "French Fries", 465, LocalDate.now());
    public static final Dish dish11 = new Dish(DISH1_ID + 10, "Grand Big Mac", 2095, LocalDate.now());
    public static final Dish dish12 = new Dish(DISH1_ID + 11, "Big Tasty", 1795, LocalDate.now().plusDays(1));
    public static final Dish dish13 = new Dish(DISH1_ID + 12, "French Fries", 465, LocalDate.now().plusDays(1));

    public static final List<Dish> RESTAURANT2_DISHES = getFiltered(List.of(dish13, dish12, dish11, dish10, dish9, dish8, dish7, dish6));
    public static final List<Dish> RESTAURANT2_TODAY_DISHES = getFiltered(List.of(dish10, dish11));

    public static final Dish dish14 = new Dish(DISH1_ID + 13, "Capricciossa Pizza", 2500, LocalDate.now());
    public static final Dish dish15 = new Dish(DISH1_ID + 14, "Caesar Salad", 2300, LocalDate.now());
    public static final Dish dish16 = new Dish(DISH1_ID + 15, "Cream of Mushroom soup", 1400, LocalDate.now().plusDays(1));
    public static final Dish dish17 = new Dish(DISH1_ID + 16, "Pasta Boloneze", 2000, LocalDate.now().plusDays(1));

    public static final List<Dish> RESTAURANT3_DISHES = getFiltered(List.of(dish17, dish16, dish15, dish14));
    public static final List<Dish> RESTAURANT3_TODAY_DISHES = getFiltered(List.of(dish14, dish15));

    public static final Dish dish18 = new Dish(DISH1_ID + 17, "Kharcho", 2550, LocalDate.now().minusDays(1));
    public static final Dish dish19 = new Dish(DISH1_ID + 18, "Khinkali Kalakuri 5 pcs", 1550, LocalDate.now().minusDays(1));
    public static final Dish dish20 = new Dish(DISH1_ID + 19, "Lobiani", 1800, LocalDate.now().minusDays(1));
    public static final Dish dish21 = new Dish(DISH1_ID + 20, "Adjarian Khachapuri", 2300, LocalDate.now().plusDays(1));
    public static final Dish dish22 = new Dish(DISH1_ID + 21, "Chikhirtma", 2200, LocalDate.now().plusDays(1));
    public static final Dish dish23 = new Dish(DISH1_ID + 22, "Cucumber-tomato Salad", 1950, LocalDate.now().plusDays(1));

    public static final List<Dish> RESTAURANT4_DISHES = getFiltered(List.of(dish23, dish22, dish21, dish20, dish19, dish18));

    public static final Dish dish24 = new Dish(DISH1_ID + 23, "Mercimek", 1000, LocalDate.now().minusDays(1));
    public static final Dish dish25 = new Dish(DISH1_ID + 24, "Chicken with Rice", 3000, LocalDate.now().minusDays(1));
    public static final Dish dish26 = new Dish(DISH1_ID + 25, "Sutlac", 1200, LocalDate.now().minusDays(1));
    public static final Dish dish27 = new Dish(DISH1_ID + 26, "Mercimek", 1000, LocalDate.now());
    public static final Dish dish28 = new Dish(DISH1_ID + 27, "Lamb chops", 4500, LocalDate.now());
    public static final Dish dish29 = new Dish(DISH1_ID + 28, "Lahmacun 3 pcs", 3600, LocalDate.now());

    public static final List<Dish> RESTAURANT5_DISHES = getFiltered(List.of(dish29, dish28, dish27, dish26, dish25, dish24));
    public static final List<Dish> RESTAURANT5_TODAY_DISHES = getFiltered(List.of(dish29, dish28, dish27));

    public static Dish getNew() {
        return new Dish(null, "Created dish", 7777, LocalDate.now().plusDays(1));
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated dish", 8888, LocalDate.now().plusDays(1));
    }

    @SafeVarargs
    private static List<Dish> getFiltered(List<Dish>... dishes) {
        return Stream.of(dishes)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Dish::getDishDate).reversed()
                        .thenComparing(Dish::getName))
                .collect(Collectors.toList());
    }
}
