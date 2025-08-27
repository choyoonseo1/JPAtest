package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "idol_member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdolMember {

    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id" ) // FK → GirlGroup.group_id
    private GirlGroup group;

    public void setGroup(GirlGroup group) {
        this.group = group;
    }
}