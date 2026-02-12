package com.github.skapud.restaurant.web;

import com.github.skapud.app.AuthUser;
import com.github.skapud.restaurant.service.RestaurantService;
import com.github.skapud.restaurant.to.RestaurantTo;
import com.github.skapud.restaurant.to.RestaurantWithMenuTo;
import com.github.skapud.restaurant.util.RestaurantUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileRestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantService service;

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return RestaurantUtil.getTos(service.getAll());
    }

    @GetMapping("/menu")
    public List<RestaurantWithMenuTo> getAllWithMenuByDate(@AuthenticationPrincipal AuthUser authUser,
                                                           @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateParam) {
        LocalDate date = dateParam != null ? dateParam : LocalDate.now();
        log.info("getAllWithMenuByDate for user {} on date {}", authUser.id(), date);
        return RestaurantUtil.getWithMenuTos(service.getAllWithMenuByDate(date));
    }
}
