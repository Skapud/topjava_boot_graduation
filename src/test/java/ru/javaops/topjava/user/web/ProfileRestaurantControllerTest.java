package ru.javaops.topjava.user.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava.AbstractControllerTest;
import ru.javaops.topjava.user.util.RestaurantUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.user.RestaurantTestData.RESTAURANTS_WITH_MENU_TODAY;
import static ru.javaops.topjava.user.RestaurantTestData.RESTAURANT_TO_MATCHER;
import static ru.javaops.topjava.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava.user.web.ProfileRestaurantController.REST_URL;


public class ProfileRestaurantControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithDishesForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(ProfileRestaurantController.REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RestaurantUtil.getTos(RESTAURANTS_WITH_MENU_TODAY)));
    }

    @Test
    void getAllUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}
