package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "girl_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GirlGroup {
    @Id
    @Column(name="group_id")
    private String id;

    @Column(name="group_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ent_id")
    private Entertainment entertainment;

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    @Builder.Default
    private List<IdolMember> members = new ArrayList<>();

    public void setEntertainment(Entertainment entertainment) {
        this.entertainment = entertainment;
    }

    public void addMember(IdolMember m) {
        members.add(m);
        m.setGroup(this);
    }
}
