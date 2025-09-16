package com.travelbooking.Controllers;

import com.travelbooking.Entities.User;
import com.travelbooking.Repositories.UserRepository;
import com.travelbooking.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Long, UserRepository, UserService> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
