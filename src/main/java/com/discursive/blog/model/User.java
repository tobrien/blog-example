package com.discursive.blog.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import static java.util.Random.*;

@Entity
@NamedQuery(name = "User.findByUsername", query = "from User u where u.username = ?1")
public class User extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private byte[] salt;
    private byte[] hash;

    @OneToMany( mappedBy = "user")
    private Set<Post> posts;

    public User() {

    }

    public User(Long id) {
        this.setId(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public void encryptPass( String password ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        salt = new byte[16];
        new Random().nextBytes(salt);
        setSalt( salt );


        KeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt(), 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        setHash( f.generateSecret(spec).getEncoded() );

    }

    public boolean authPass( String password ) throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt(), 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] encoded = f.generateSecret( spec ).getEncoded();

        return Arrays.equals( encoded, getHash() );

    }
}

