package com.discursive.blog.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
public class Post extends AbstractPersistable<Long> {

    @ManyToOne
    public User user;

    private String title;
    private String body;
    private Date publishDate;

    @OneToMany
    @JoinTable (
        name="PostTags",
        joinColumns={ @JoinColumn (name="postId", referencedColumnName="id") },
        inverseJoinColumns={ @JoinColumn (name="tagId", referencedColumnName="id") }
    )
    public Set<Tag> tags;

    public Post() {}

    public Post(Long id) {
        setId(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
