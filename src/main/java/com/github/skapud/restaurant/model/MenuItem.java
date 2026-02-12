package com.github.skapud.restaurant.model;

import com.github.skapud.common.model.NamedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Entity
@Table(name = "menu_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "item_date", "name"},
        name = "menu_item_unique_restaurant_date_idx")})
@Getter
@Setter
public class MenuItem extends NamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1, max = 100000)
    private Integer price;

    @Column(name = "item_date", nullable = false)
    @NotNull
    private LocalDate itemDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public MenuItem() {}

    public MenuItem(Integer id, String name, Integer price, LocalDate itemDate) {
        super(id, name);
        this.price = price;
        this.itemDate = itemDate;
    }

    public MenuItem(Integer id, String name, Integer price, LocalDate itemDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.itemDate = itemDate;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", itemDate=" + itemDate +
                '}';
    }
}