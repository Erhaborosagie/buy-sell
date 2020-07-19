package com.osagieerhabor.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osagieerhabor.backend.enums.EnabledStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Email
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private EnabledStatus active;

    private String roles = "";

    private String permission = "";

    public User(String username, String password, String roles, String permission) {
        this.username=username;
        this.password=password;
        this.roles=roles;
        this.permission=permission;
        this.active=EnabledStatus.DISABLED;
    }

    protected  User(){}

    public User(String username, String password, String roles, String permission, EnabledStatus enabled) {
        this.username=username;
        this.password=password;
        this.roles=roles;
        this.permission=permission;
        this.active=enabled;
    }

    public List<String> getRoleList(){
        if (this.roles.length()>0)return Arrays.asList(this.roles.split(","));
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if (this.permission.length()>0)return Arrays.asList(this.permission.split(","));
        return new ArrayList<>();
    }
}
