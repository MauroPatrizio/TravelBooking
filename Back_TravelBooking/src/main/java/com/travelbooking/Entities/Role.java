package com.travelbooking.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role extends Base{

    @Pattern(regexp = "^\\S.*",message = "The role name can't start with an invalid char")
    @NotEmpty(message = "The role must contain a name")
    @Size(min = 3,max = 50,message = "The role name must be between 3 and 50 chars long")
    @Column(name = "name")
    private String name;
}
