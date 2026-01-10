package ru.javaops.topjava.user.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.topjava.common.to.NamedTo;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    Integer price;

    LocalDate dishDate;

    public DishTo(Integer id, String name, Integer price, LocalDate dishDate) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
    }
}
