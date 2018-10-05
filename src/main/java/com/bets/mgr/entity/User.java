package com.bets.mgr.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USR_USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "username", unique=true, nullable = false)
    private String userName;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List <Role> getRoles() {
        return roles;
    }

    public void setRoles(List <Role> roles) {
        this.roles = roles;
    }
}







