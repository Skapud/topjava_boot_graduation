package com.github.skapud.vote.to;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteInputTo {
    @NotNull
    private Integer restaurantId;

    public VoteInputTo(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
