package com.sipl.springhelloworld.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 72)
    private String password;

    @Column(nullable = false, length = 64)
    private String email;

    // @ManyToMany(cascade = { CascadeType.ALL })
    // @JoinTable(name = "user_role", joinColumns = {
    //         @JoinColumn(name = "user", referencedColumnName = "id") }, inverseJoinColumns = {
    //                 @JoinColumn(name = "role", referencedColumnName = "id") })
    @Column(nullable = false, length = 16)
    private String role;

    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     List<SimpleGrantedAuthority> authorities = new ArrayList<>(null);
    //     authorities = this.roles.stream()
    //             .map((role) -> new SimpleGrantedAuthority(role.getName()))
    //             .collect(Collectors.toList());
    //     return authorities;
    // }

    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // public boolean isEnabled() {
    //     return true;
    // }
}
