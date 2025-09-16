package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User extends Base implements UserDetails {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Email(message = "The email must be valid")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name ="password", length = 300)
    private String password;

    @Column(name = "phone")
    private String phone;

    @NotNull(message = "The user must have a role")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id",nullable = false)
    private Role roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Score> score;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Favorite> favorites;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.getName()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
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
        return Boolean.TRUE.equals(this.getActive());
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}
}
