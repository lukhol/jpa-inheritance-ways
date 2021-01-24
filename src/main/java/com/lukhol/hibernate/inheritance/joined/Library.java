package com.lukhol.hibernate.inheritance.joined;

import com.lukhol.hibernate.inheritance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Library extends BaseEntity {

    @OneToMany(mappedBy = "library")
    private List<Publication> publications = new ArrayList<>();

    public void addPublication(Publication publication) {
        this.publications.add(publication);
        publication.setLibrary(this);
    }
}
