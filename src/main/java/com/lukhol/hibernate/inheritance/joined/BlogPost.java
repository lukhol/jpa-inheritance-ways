package com.lukhol.hibernate.inheritance.joined;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class BlogPost extends Publication {
    private String url;
}
