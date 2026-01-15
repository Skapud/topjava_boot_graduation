package ru.javaops.topjava.user.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava.AbstractControllerTest;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.user.RestaurantTestData.RESTAURANT1_ID;
import static ru.javaops.topjava.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.topjava.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava.user.VoteTestData.*;
import static ru.javaops.topjava.user.util.VotesUtil.getAdminTos;
import static ru.javaops.topjava.user.web.AdminVoteController.REST_URL;

public class AdminVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = AdminVoteController.REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithUsers() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_ADMIN_TO_MATCHER.contentJson(getAdminTos(RESTAURANT1_VOTE_LIST)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithUsersFiltered() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "filter", RESTAURANT1_ID)
                .param("startDate", LocalDate.now().minusDays(2).toString())
                .param("endDate", LocalDate.now().minusDays(1).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_ADMIN_TO_MATCHER.contentJson(getAdminTos(List.of(vote5,vote4,vote1))));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithUsersFilteredNull() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "filter", RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_ADMIN_TO_MATCHER.contentJson(getAdminTos(RESTAURANT1_VOTE_LIST)));
    }
}
