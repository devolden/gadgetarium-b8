package com.example.gadgetariumb8.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;
    private String firstName;
    private String lastName;
    private String image;
    private String phoneNumber;
    private String address;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "users_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorites_id"))
    private List<Product> favorites;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "users_last_views",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "last_views_id"))
    private List<Product> lastViews;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "users_comparisons",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comparisons_id"))
    private List<SubProduct> comparisons;

    @ElementCollection
    @Cascade({CascadeType.ALL})
    private Map<SubProduct, Integer> basket;

    @OneToMany(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    @JoinColumn(name = "user_id")
    private List<Order> order;

    @OneToOne(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
}