package com.github.skapud.restaurant.web;

import com.github.skapud.restaurant.model.MenuItem;
import com.github.skapud.restaurant.repository.MenuRepository;
import com.github.skapud.restaurant.service.MenuService;
import com.github.skapud.restaurant.to.MenuItemTo;
import com.github.skapud.restaurant.util.MenuItemUtil;
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

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.github.skapud.common.validation.ValidationUtil.assureIdConsistent;
import static com.github.skapud.common.validation.ValidationUtil.checkIsNew;

@RestController
@RequestMapping(value = AdminMenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuItemController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menu-items";

    private final MenuRepository repository;
    private final MenuService service;

    @GetMapping
    public List<MenuItemTo> getAll(@PathVariable int restaurantId) {
        log.info("getAll for {}", restaurantId);
        return MenuItemUtil.getTos(repository.getAll(restaurantId));
    }

    @GetMapping("/{id}")
    public MenuItemTo get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get {} from {}", id, restaurantId);
        return MenuItemUtil.createTo(repository.getBelonged(restaurantId, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete {} from {}", id, restaurantId);
        service.delete(restaurantId, id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId, @Valid @RequestBody MenuItemTo menuItemTo, @PathVariable int id) {
        log.info("update {} for {}", menuItemTo, restaurantId);
        assureIdConsistent(menuItemTo, id);
        service.update(restaurantId, MenuItemUtil.createFromTo(menuItemTo));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemTo> createWithLocation(@PathVariable int restaurantId, @Valid @RequestBody MenuItemTo menuItemTo) {
        log.info("create {} for {}", menuItemTo, restaurantId);
        checkIsNew(menuItemTo);
        MenuItem created = service.save(restaurantId, MenuItemUtil.createNewFromTo(menuItemTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(MenuItemUtil.createTo(created));
    }

    @GetMapping("/filter")
    public List<MenuItemTo> getFiltered(@PathVariable int restaurantId,
                                      @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("getFiltered date {} for restaurant {}", date, restaurantId);
        return (date != null) ? MenuItemUtil.getTos(repository.getFiltered(restaurantId, date)) : getAll(restaurantId);
    }
}
