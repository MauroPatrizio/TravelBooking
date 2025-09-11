package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelbooking.DTO.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User extends Base{

    @Pattern(regexp = "^\\S.*",message = "The user name can't start with an invalid char")
    @NotEmpty(message = "The user must contain a name")
    @Size(min = 3,max = 50,message = "The user name must be between 3 and 50 chars long")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^\\S.*",message = "The user surname can't start with an invalid char")
    @NotEmpty(message = "The user must contain a surname")
    @Size(min = 3,max = 50,message = "The user surname must be between 3 and 50 chars long")
    @Column(name = "surname")
    private String surname;

    @Pattern(regexp = "^\\S.*",message = "The user email name can't start with an invalid char")
    @Email(message = "The email must be valid")
    @NotEmpty(message = "The user must contain a surname")
    @Size(min = 5,max = 50,message = "The user surname must be between 5 and 50 chars long")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "The user must contain a password")
    @Column(name ="password", length = 300)
    private String password;

    @Pattern(regexp ="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",message = "The phone number must be entered in the format '+xx (xxx) xxx-xxxx'")
    @NotEmpty(message = "The user must contain a phone number")
    @Size(min = 5,max = 50,message = "The user phone number must be between 5 and 50 chars long")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "The user must have a role")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id",nullable = false)
    private Role roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Score> score;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Favorite> favorites;

    //Equals y HashCode para comparar objetos por id
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
