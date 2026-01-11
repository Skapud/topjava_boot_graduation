package ru.javaops.topjava.user.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.app.AuthUser;
import ru.javaops.topjava.user.model.Vote;
import ru.javaops.topjava.user.repository.VoteRepository;
import ru.javaops.topjava.user.service.VoteService;
import ru.javaops.topjava.user.to.VoteTo;
import ru.javaops.topjava.user.util.VotesUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileVoteController {
    static final String REST_URL = "/api/profile/votes";

    private final VoteService service;
    private final VoteRepository repository;

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return VotesUtil.getTos(repository.getAllWithRestaurants(authUser.id()));
    }

    @GetMapping("/today")
    public ResponseEntity<VoteTo> get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get for user {}", authUser.id());
        return ResponseEntity.of(
                repository.getByDateWithRestaurant(authUser.id(), LocalDate.now())
                        .map(VotesUtil::createTo)
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote vote(@AuthenticationPrincipal AuthUser authUser, @RequestBody int restaurantId) {
        int userId = authUser.id();
        log.info("user {} vote for {}", userId, restaurantId);
        return service.save(userId, restaurantId, LocalDateTime.now());
    }
}
