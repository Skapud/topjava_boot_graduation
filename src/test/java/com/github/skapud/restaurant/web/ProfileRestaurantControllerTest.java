package com.github.skapud.restaurant.web;

import com.github.skapud.AbstractControllerTest;
import com.github.skapud.restaurant.util.RestaurantUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.github.skapud.restaurant.RestaurantTestData.*;
import static com.github.skapud.restaurant.web.ProfileRestaurantController.REST_URL;
import static com.github.skapud.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProfileRestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = ProfileRestaurantController.REST_URL + '/';

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(ProfileRestaurantController.REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RestaurantUtil.getTos(RESTAURANTS)));
    }

    @Test
    void getAllUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithMenuByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "menu")
                .param("dateParam", String.valueOf(LocalDate.now())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_MENU_TO_MATCHER.contentJson(RestaurantUtil.getWithMenuTos(RESTAURANTS_WITH_MENU_TODAY)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithMenuNullable() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "menu"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_MENU_TO_MATCHER.contentJson(RestaurantUtil.getWithMenuTos(RESTAURANTS_WITH_MENU_TODAY)));
    }
}
