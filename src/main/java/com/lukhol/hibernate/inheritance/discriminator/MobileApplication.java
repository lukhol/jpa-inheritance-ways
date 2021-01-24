package com.lukhol.hibernate.inheritance.discriminator;

import com.lukhol.hibernate.inheritance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class MobileApplication extends BaseEntity {

    private String token;

    @OneToMany(mappedBy = "application")
    private List<PushMessageHistory> messages = new ArrayList<>();
}
