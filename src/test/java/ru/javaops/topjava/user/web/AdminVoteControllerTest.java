package ru.javaops.topjava.user.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.user.RestaurantTestData.RESTAURANT1_ID;
import static ru.javaops.topjava.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.topjava.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava.user.VoteTestData.RESTAURANT1_VOTE_LIST;
import static ru.javaops.topjava.user.VoteTestData.VOTES_WITH_USER_MATCHER;
import static ru.javaops.topjava.user.web.AdminVoteController.REST_URL;

public class AdminVoteControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithUsers() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_WITH_USER_MATCHER.contentJson(RESTAURANT1_VOTE_LIST));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isForbidden());
    }
}
