package com.example.ProductService.Controller;

import com.example.ProductService.Model.User;
import com.example.ProductService.Service.UserService;
import com.example.ProductService.Util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoggerUtil userControllerLogger = new LoggerUtil(UserController.class);


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/{password}")
    public ResponseEntity<User> getUserByIdAndPassword(@PathVariable String id,@PathVariable String password) {
        User user = userService.getUserByIdAndPassword(id,password);
        if (user != null) {
            userControllerLogger.info("THe user details for id "+id+" retrieved!");
            return ResponseEntity.ok(user);
        } else {
            userControllerLogger.error("User not found!",new Exception("UserNotFound"));
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        ResponseEntity<User> response = ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        userControllerLogger.info("The user has been created!");
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            userControllerLogger.info("The user has been updated!");
            return ResponseEntity.ok(updatedUser);
        } else {
            userControllerLogger.warn("There was an error incurred while updating the user details");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/{password}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id,@PathVariable String password) {
        boolean deleted = userService.deleteUser(id,password);
        if (deleted) {
            userControllerLogger.info("The user has been deleted!");
            return ResponseEntity.noContent().build();
        } else {
            userControllerLogger.error("User not found!",new Exception("UserNotFound"));
            return ResponseEntity.notFound().build();
        }
    }
}