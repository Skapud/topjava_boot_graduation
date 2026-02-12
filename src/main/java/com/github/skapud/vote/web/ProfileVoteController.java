package com.github.skapud.vote.web;

import com.github.skapud.app.AuthUser;
import com.github.skapud.vote.model.Vote;
import com.github.skapud.vote.repository.VoteRepository;
import com.github.skapud.vote.service.VoteService;
import com.github.skapud.vote.to.VoteInputTo;
import com.github.skapud.vote.to.VoteTo;
import com.github.skapud.vote.util.VotesUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/by-date")
    public ResponseEntity<VoteTo> get(@AuthenticationPrincipal AuthUser authUser,
                                      @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateParam) {
        LocalDate date = dateParam != null ? dateParam : LocalDate.now();
        log.info("get for user {} on date {}", authUser.id(), date);
        return ResponseEntity.of(
                repository.getByDateWithRestaurant(authUser.id(), date)
                        .map(VotesUtil::createTo)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteTo> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteInputTo voteInputTo) {
        int userId = authUser.id();
        int restaurantId = voteInputTo.getRestaurantId();
        log.info("user {} vote for {}", userId, restaurantId);
        Vote created = service.save(userId, restaurantId, LocalDateTime.now());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(VotesUtil.createTo(created));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteInputTo voteInputTo) {
        int userId = authUser.id();
        int restaurantId = voteInputTo.getRestaurantId();
        log.info("user {} vote update for {}", userId, restaurantId);
        service.update(userId, restaurantId, LocalDateTime.now());
    }
}
