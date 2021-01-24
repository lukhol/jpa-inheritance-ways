package com.lukhol.hibernate.inheritance.tableperclass;

import com.lukhol.hibernate.inheritance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class UserAggregator extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAggregator")
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
        user.setUserAggregator(this);
    }
}
