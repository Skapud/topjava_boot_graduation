package ru.javaops.topjava.user.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.user.model.Vote;
import ru.javaops.topjava.user.repository.VoteRepository;

import java.util.List;

@RestController
@RequestMapping(value = ru.javaops.topjava.user.web.AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVoteController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/votes";

    private final VoteRepository repository;

    @GetMapping()
    public List<Vote> getWithUsers(@PathVariable int restaurantId) {
        log.info("get with users for {}", restaurantId);
        return repository.getWithUsers(restaurantId);
    }
}
