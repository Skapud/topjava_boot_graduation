package ru.javaops.topjava.user.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava.user.model.Dish;
import ru.javaops.topjava.user.repository.DishRepository;
import ru.javaops.topjava.user.service.DishService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava.common.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava.common.validation.ValidationUtil.checkIsNew;
import static ru.javaops.topjava.user.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.javaops.topjava.user.util.DateTimeUtil.atStartOfNextDayOrMax;

@RestController
@RequestMapping(value = ru.javaops.topjava.user.web.AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminDishController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    private final DishRepository repository;
    private final DishService service;

    @GetMapping
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("getAll for {}", restaurantId);
        return repository.getAll(restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete {} from {}", id, restaurantId);
        Dish dish = repository.getBelonged(restaurantId, id);
        repository.delete(dish);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId, @Valid @RequestBody Dish dish, @PathVariable int id) {
        log.info("update {} for {}", dish, restaurantId);
        assureIdConsistent(dish, id);
        repository.getBelonged(restaurantId, id);
        service.createOrUpdate(restaurantId, dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@PathVariable int restaurantId, @Valid @RequestBody Dish dish) {
        log.info("create {} for {}", dish, restaurantId);
        checkIsNew(dish);
        Dish created = service.createOrUpdate(restaurantId, dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/filter")
    public List<Dish> getFiltered(@PathVariable int restaurantId,
                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("getFiltered date {} for restaurant {}", date, restaurantId);
        return (date != null) ? repository.getFiltered(restaurantId, date) : getAll(restaurantId);
    }
}
