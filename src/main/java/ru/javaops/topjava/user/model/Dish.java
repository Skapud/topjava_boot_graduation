package ru.javaops.topjava.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.javaops.topjava.common.model.NamedEntity;

import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_date", "name"},
        name = "dish_unique_restaurant_date_idx")})
@Getter
@Setter
public class Dish extends NamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1, max = 100000)
    private Integer price;

    @Column(name = "dish_date", nullable = false)
    @NotNull
    private LocalDate dishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "restaurant-dishes")
    @JsonIgnore
    private Restaurant restaurant;

    public Dish() {}

    public Dish(Integer id, String name, Integer price, LocalDate dishDate) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
    }

    public Dish(Integer id, String name, Integer price, LocalDate dishDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dishDate=" + dishDate +
                '}';
    }
}