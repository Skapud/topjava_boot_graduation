package ru.javaops.topjava.user.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.user.repository.VoteRepository;
import ru.javaops.topjava.user.to.VoteAdminTo;
import ru.javaops.topjava.user.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava.user.util.VotesUtil.getAdminTos;

@RestController
@RequestMapping(value = ru.javaops.topjava.user.web.AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVoteController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/votes";

    private final VoteRepository repository;

    @GetMapping()
    public List<VoteAdminTo> getWithUsers(@PathVariable int restaurantId) {
        log.info("get with users for {}", restaurantId);
        return getAdminTos(repository.getWithUsers(restaurantId));
    }

    @GetMapping("/filter")
    public List<VoteAdminTo> getWithUsersFiltered(@PathVariable int restaurantId,
                                                  @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("getBetween dates({} - {}) for restaurant {}", startDate, endDate, restaurantId);
        return getAdminTos(repository.getWithUsersBetween(restaurantId, DateTimeUtil.orMin(startDate), DateTimeUtil.orMax(endDate)));
    }
}
