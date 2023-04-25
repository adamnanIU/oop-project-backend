package oop.project.chatroom.Controller;


import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Service.AdminService;
import oop.project.chatroom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {



    @Autowired
    private AdminService adminService;



    @PostMapping()
    public String findAll() {
        return adminService.changeState();
    }



    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody String user){
        return adminService.deleteUser(user);
    }

    @DeleteMapping("/deleteMessage")
        public String deleteMessage(@RequestBody Message message){
        return adminService.deleteMessage(message);
    }


    @PostMapping("/addMessage")
    public String addMessage(@RequestBody Message message){
        return adminService.addMessage(message);
    }





}
