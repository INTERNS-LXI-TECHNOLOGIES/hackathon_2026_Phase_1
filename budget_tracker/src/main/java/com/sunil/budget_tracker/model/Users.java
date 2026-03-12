package com.sunil.budget_tracker.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Users {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int userId;
@Column(name = "user_name")
private String userName;

private String password;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(

        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
)

private Set<Roles> roles;

public int getUserId(){
    return userId;
}

public void setUserId(int userId){
    this.userId = userId;
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

public Set<Roles> getRoles() {
    return roles;
}

public void setRoles(Set<Roles> roles) {
    this.roles = roles;
}

}