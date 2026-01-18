package ru.javaops.topjava.restaurant.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.app.AuthUser;
import ru.javaops.topjava.restaurant.service.RestaurantService;
import ru.javaops.topjava.restaurant.to.RestaurantTo;
import ru.javaops.topjava.restaurant.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileRestaurantController {
    static final String REST_URL = "/api/profile/restaurants";

    private final RestaurantService service;

    @GetMapping
    public List<RestaurantTo> getAllWithDishesForToday(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return RestaurantUtil.getTos(service.getAllWithDishesForToday(LocalDate.now()));
    }
}
