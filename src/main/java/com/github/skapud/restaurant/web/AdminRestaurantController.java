package com.github.skapud.restaurant.web;

import com.github.skapud.restaurant.model.Restaurant;
import com.github.skapud.restaurant.repository.RestaurantRepository;
import com.github.skapud.restaurant.service.RestaurantService;
import com.github.skapud.restaurant.to.RestaurantTo;
import com.github.skapud.restaurant.util.RestaurantUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.github.skapud.common.validation.ValidationUtil.assureIdConsistent;
import static com.github.skapud.common.validation.ValidationUtil.checkIsNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantRepository repository;
    private final RestaurantService service;

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return RestaurantUtil.getTos(service.getAll());
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get {}", id);
        return RestaurantUtil.createTo(repository.getExisted(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("update {}", restaurantTo);
        assureIdConsistent(restaurantTo, id);
        service.update(RestaurantUtil.createFromTo(restaurantTo));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> createWithLocation(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        checkIsNew(restaurantTo);
        Restaurant created = service.save(RestaurantUtil.createFromTo(restaurantTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(RestaurantUtil.createTo(created));
    }
}
