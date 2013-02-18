package com.discursive.blog.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Tag extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String name;

    public Tag() {

    }

    public Tag(Long id) {
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

