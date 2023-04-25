package oop.project.chatroom.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "https://322-frontend-jyl8.vercel.app")
public class GreetingController {
    //Get https:localhost:8080
    @GetMapping
    public String greetings() {
        return "Hello there!";
    }
}
