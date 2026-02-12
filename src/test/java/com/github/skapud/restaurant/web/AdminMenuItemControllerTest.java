package com.github.skapud.restaurant.web;

import com.github.skapud.AbstractControllerTest;
import com.github.skapud.common.util.JsonUtil;
import com.github.skapud.restaurant.repository.MenuRepository;
import com.github.skapud.restaurant.to.MenuItemTo;
import com.github.skapud.restaurant.util.MenuItemUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.github.skapud.restaurant.MenuItemTestData.*;
import static com.github.skapud.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static com.github.skapud.restaurant.web.AdminMenuItemController.REST_URL;
import static com.github.skapud.user.UserTestData.ADMIN_MAIL;
import static com.github.skapud.user.UserTestData.USER_MAIL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuItemControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private MenuRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_TO_MATCHER.contentJson(MenuItemUtil.getTos(RESTAURANT_1_MENU_ITEMS)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_TO_MATCHER.contentJson(MenuItemUtil.createTo(menu_item_1)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID))
                .andExpect(status().isNoContent());
        assertFalse(repository.get(RESTAURANT1_ID, MENU_ITEM1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteDataConflict() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID + 1))
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        MenuItemTo updated = MenuItemUtil.createTo(getUpdated());
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MENU_ITEM_TO_MATCHER.assertMatch(MenuItemUtil.createTo(repository.getBelonged(RESTAURANT1_ID, MENU_ITEM1_ID)), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        MenuItemTo invalid = new MenuItemTo(MENU_ITEM1_ID, null, null, null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableContent());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(value = ADMIN_MAIL)
    void updateDuplicate() throws Exception {
        MenuItemTo duplicate = new MenuItemTo(MENU_ITEM1_ID, menu_item_2.getName(), 777, menu_item_2.getItemDate());
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        MenuItemTo newMenuItem = MenuItemUtil.createTo(getNew());
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuItem)));

        MenuItemTo created = MENU_ITEM_TO_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenuItem.setId(newId);
        MENU_ITEM_TO_MATCHER.assertMatch(created, newMenuItem);
        MENU_ITEM_TO_MATCHER.assertMatch(MenuItemUtil.createTo(repository.getExisted(newId)), newMenuItem);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        MenuItemTo invalid = new MenuItemTo(null, null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableContent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createDuplicate() throws Exception {
        MenuItemTo duplicate = new MenuItemTo(null, menu_item_1.getName(), 777, menu_item_1.getItemDate());
        perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getFiltered() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "filter", RESTAURANT1_ID)
                .param("date", LocalDate.now().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MENU_ITEM_TO_MATCHER.contentJson(MenuItemUtil.getTos(List.of(menu_item_2, menu_item_3, menu_item_4))));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getFilteredNull() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "filter", RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MENU_ITEM_TO_MATCHER.contentJson(MenuItemUtil.getTos(RESTAURANT_1_MENU_ITEMS)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateHtmlUnsafe() throws Exception {
        MenuItemTo unsafe = new MenuItemTo(MENU_ITEM1_ID, "<script>alert(123)</script>", 777, menu_item_2.getItemDate());
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ITEM1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(unsafe)))
                .andDo(print())
                .andExpect(status().isUnprocessableContent());
    }
}
