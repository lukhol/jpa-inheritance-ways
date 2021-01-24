package com.lukhol.hibernate.inheritance.tableperclass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    // Not extends BaseEntity because: Cannot use identity column key generation with <union-subclass> mapping for...
    // It require GenerationType of type TABLE.
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String email;

    @ManyToOne
    private UserAggregator userAggregator;
}
