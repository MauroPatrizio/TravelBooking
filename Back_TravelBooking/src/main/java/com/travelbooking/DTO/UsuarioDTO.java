package com.travelbooking.DTO;


import com.travelbooking.Entities.Score;
import com.travelbooking.Entities.Role;
import com.travelbooking.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private Role roles;
    private List<Score> score;

}
