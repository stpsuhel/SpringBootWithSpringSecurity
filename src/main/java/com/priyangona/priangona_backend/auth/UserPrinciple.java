package com.priyangona.priangona_backend.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.priyangona.priangona_backend.auth.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.priyangona.priangona_backend.auth.role.PrimaryRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

    private Long id;
    private String name;
    private String username;

    @JsonIgnore
    private String password;
    private String mobile;
    private String email;
    private PrimaryRole primaryRole;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple build(User user) {
        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getMobile(),
                user.getEmail(),
                user.getPrimaryRole(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserPrinciple getUserPrinciple(){
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
