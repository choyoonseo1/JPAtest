package com.my.jpaTest.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "entertainment")
public class Entertainment {
    @Id
    @Column(name = "ent_id")
    private String id;

    @Column(name = "ent_name")
    private String name;

    @OneToMany(mappedBy = "entertainment",
    cascade = CascadeType.PERSIST,
    orphanRemoval = true)
    @Builder.Default
    private List<GirlGroup>groups = new ArrayList<>();

    public void addGroup(GirlGroup group) {
        groups.add(group);
        group.setEntertainment(this);
    }
}
