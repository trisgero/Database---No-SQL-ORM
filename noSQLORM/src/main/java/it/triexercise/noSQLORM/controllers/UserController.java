package it.triexercise.noSQLORM.controllers;

import it.triexercise.noSQLORM.entity.User;
import it.triexercise.noSQLORM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public void createUser(@RequestBody User user) {
        user.setId(null);
        userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public void editUser(@PathVariable String id, @RequestBody User user) throws Exception {
        if (!userService.getUser(id).isPresent()) throw new Exception("USER is NOT FOUND");
        user.setId(id);
        userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
