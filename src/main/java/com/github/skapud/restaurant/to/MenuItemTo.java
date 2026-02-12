package com.github.skapud.restaurant.to;

import com.github.skapud.common.to.NamedTo;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class MenuItemTo extends NamedTo {
    @NotNull
    @Range(min = 1, max = 100000)
    Integer price;

    @NotNull
    LocalDate itemDate;

    public MenuItemTo(Integer id, String name, Integer price, LocalDate itemDate) {
        super(id, name);
        this.price = price;
        this.itemDate = itemDate;
    }
}
