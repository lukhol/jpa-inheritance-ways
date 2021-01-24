package com.lukhol.hibernate.inheritance.discriminator;

import com.lukhol.hibernate.inheritance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PushMessageHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private MobileApplication application;

    private String title;

    public void setApplication(MobileApplication mobileApplication) {
        this.application = mobileApplication;
        mobileApplication.getMessages().add(this);
    }
}
