package com.lukhol.hibernate.inheritance.joined;

import com.lukhol.hibernate.inheritance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Publication extends BaseEntity {
    private String title;

    @ManyToOne
    private Library library;
}
