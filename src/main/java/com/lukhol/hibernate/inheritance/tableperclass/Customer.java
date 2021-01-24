package com.lukhol.hibernate.inheritance.tableperclass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Customer extends User {
    private String externalId;
}
