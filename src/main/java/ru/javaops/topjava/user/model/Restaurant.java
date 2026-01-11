package ru.javaops.topjava.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.topjava.common.model.NamedEntity;

import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_name_idx")})
@Getter
@Setter
public class Restaurant extends NamedEntity {
    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("dishDate DESC")
    @JsonManagedReference(value = "restaurant-dishes")
    private List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("voteTime DESC")
    @JsonManagedReference(value = "restaurant-votes")
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, List<Dish> dishes, List<Vote> votes) {
        super(id, name);
        this.dishes = dishes;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
