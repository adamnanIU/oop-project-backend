package oop.project.chatroom.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import oop.project.chatroom.Model.Login;
import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Patterns.Singleton;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;
import oop.project.chatroom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }



    @PostMapping
    public Long createUser(@RequestBody User user){
       return userService.createUser(user);
    }







    @PostMapping("/admin")
    public Long createAdmin(@RequestBody User user){
        return userService.createAdmin(user);
    }




    @PostMapping("/login")
    public boolean login(@RequestBody Login login) {
        return userService.login(login);
    }



}
