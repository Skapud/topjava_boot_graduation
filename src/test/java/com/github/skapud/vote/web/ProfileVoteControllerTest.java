package com.github.skapud.vote.web;

import com.github.skapud.AbstractControllerTest;
import com.github.skapud.common.util.JsonUtil;
import com.github.skapud.vote.repository.VoteRepository;
import com.github.skapud.vote.to.VoteInputTo;
import com.github.skapud.vote.to.VoteTo;
import com.github.skapud.vote.util.VotesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.github.skapud.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static com.github.skapud.user.UserTestData.*;
import static com.github.skapud.vote.VoteTestData.*;
import static com.github.skapud.vote.util.VotesUtil.createTo;
import static com.github.skapud.vote.util.VotesUtil.getTos;
import static com.github.skapud.vote.web.ProfileVoteController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(getTos(USER_VOTE_LIST)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-date")
                .param("dateParam", String.valueOf(LocalDate.now())))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(createTo(vote3)));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void save() throws Exception {
        VoteInputTo newVote = new VoteInputTo(RESTAURANT1_ID);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .content(JsonUtil.writeValue(newVote))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        VoteTo saved = VotesUtil.createTo(voteRepository.getByDate(ADMIN_ID, LocalDate.now())
                .orElseThrow());
        VOTE_TO_MATCHER.assertMatch(saved, created);
        Assertions.assertEquals(RESTAURANT1_ID, saved.getRestaurantId());
    }
}
