package com.lukhol.hibernate.inheritance.discriminator;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("admin")
public class AdminMobileApplication extends MobileApplication {
    private String customerId;
}
